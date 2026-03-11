package comgest.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comgest.model.CCB;
import comgest.model.CCBModel;
import comgest.model.UserModel;
import comgest.model.Usuario;
import comgest.view.RegistrarTipoUsuario;

public class RegistrarTipoUsuarioController implements ActionListener {
    public static final String ACTION_SUBMIT = "SUBMIT_TIPO";

    private final RegistrarTipoUsuario view;
    private final UserModel userModel;
    private final CCBModel ccbModel;

    public RegistrarTipoUsuarioController(RegistrarTipoUsuario view) {
        this(view, UserModel.getInstance(), new CCBModel());
    }

    public RegistrarTipoUsuarioController(RegistrarTipoUsuario view, UserModel userModel, CCBModel ccbModel) {
        if (view == null) {
            throw new IllegalArgumentException("view no puede ser null");
        }
        this.view = view;
        this.userModel = userModel;
        this.ccbModel = ccbModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!ACTION_SUBMIT.equals(e.getActionCommand())) {
            return;
        }

        String cedula = view.getCedulaText();
        String tipo = view.getTipoSeleccionado();

        // Validar campos
        if (cedula == null || cedula.trim().isEmpty() || cedula.equals("ej: 32298110")) {
            view.showMessage("Debe ingresar una cédula.");
            return;
        }

        if (tipo == null || tipo.equals("Seleccione tipo...")) {
            view.showMessage("Debe seleccionar un tipo de beneficio.");
            return;
        }

        // Buscar usuario
        userModel.invalidar();
        userModel.cargarUsuarios();
        Usuario usuario = userModel.buscarPorCedula(cedula.trim());
        if (usuario == null) {
            view.showMessage("No se encontró un usuario con la cédula: " + cedula.trim());
            return;
        }

        // Verificar que sea estudiante (cualquier variante)
        String rolActual = usuario.getRole().trim().toLowerCase();
        if (!rolActual.startsWith("estudiante")) {
            view.showMessage("Solo se puede cambiar el tipo a usuarios con rol de estudiante.\nRol actual: "
                    + usuario.getRole());
            return;
        }

        if (tipo.equals("Exonerado")) {
            // Exonerado: paga $0
            usuario.setPorcentajeBecado(100);
            // Cambiar rol
            boolean ok = cambiarRol(usuario, "Estudiante (E)");
            if (ok) {
                view.showMessage("El estudiante " + usuario.getName() + " ha sido exonerado exitosamente.");
            }
        } else if (tipo.equals("Becario")) {
            // Becado: validar porcentaje
            String porcentajeStr = view.getPorcentajeText();
            if (porcentajeStr == null || porcentajeStr.trim().isEmpty() || porcentajeStr.equals("Ej: 5")) {
                view.showMessage("Debe ingresar el porcentaje de descuento para el becario.");
                return;
            }

            float porcentaje;
            try {
                porcentaje = Float.parseFloat(porcentajeStr.trim());
            } catch (NumberFormatException ex) {
                view.showMessage("El porcentaje debe ser un número válido.");
                return;
            }

            if (porcentaje <= 0 || porcentaje > 100) {
                view.showMessage("El porcentaje debe estar entre 1 y 100.");
                return;
            }

            CCB ccb = ccbModel.obtenerCCB();
            if (ccb != null && ccb.getCCBValor() > 0) {
                double precioEstudiante = ccb.calcularPrecioConDescuento("estudiante");
                double precioBecado = precioEstudiante * (1.0 - porcentaje / 100.0);
                if (precioBecado >= precioEstudiante) {
                    view.showMessage(
                            "El porcentaje de descuento debe hacer que el becado pague menos que un estudiante normal ($"
                                    + String.format("%.2f", precioEstudiante) + ").");
                    return;
                }
            }

            usuario.setPorcentajeBecado(porcentaje);
            boolean ok = cambiarRol(usuario, "Estudiante (B)");
            if (ok) {
                view.showMessage("El estudiante " + usuario.getName() + " ahora es becario con " + porcentaje
                        + "% de descuento.");
            }
        }
    }

    private boolean cambiarRol(Usuario usuario, String nuevoRol) {
        usuario.setRole(nuevoRol);

        boolean ok = userModel.actualizarUsuario(usuario);
        if (!ok) {
            view.showMessage("Error al guardar los cambios.");
        }
        return ok;
    }
}
