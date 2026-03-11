
package comgest.controller;

import comgest.utils.*;
import comgest.view.MainValidacion;
import comgest.model.UserModel;
import comgest.model.Usuario;
import comgest.model.ReservaModel;
import comgest.model.Reserva;
import comgest.model.MenuItem;
import comgest.model.CCBModel;
import comgest.model.CCB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Image;

public class ValidacionBIOController implements ActionListener {
    public static final String ACTION_BUSCAR_CEDULA = "BUSCAR_CEDULA";
    public static final String ACTION_CONFIRMAR_RESERVA = "CONFIRMAR_RESERVA";
    public static final String ACTION_CERRAR = "CERRAR";
    public static final String ACTION_SELECCIONAR_FOTO = "SELECCIONAR_FOTO";
    public static final String ACTION_FINALIZAR = "FINALIZAR";

    private final MainValidacion view;
    private final UserModel userModel;
    private final ReservaModel reservaModel;
    private final CCBModel ccbModel;

    private File selectedFile;
    private Usuario usuarioEncontrado;
    private Reserva reservaActiva;
    private MenuItem menuItemActivo;

    public ValidacionBIOController(MainValidacion view) {
        this(view, UserModel.getInstance(), ReservaModel.getInstance(), new CCBModel());
    }

    public ValidacionBIOController(MainValidacion view, UserModel userModel, ReservaModel reservaModel,
            CCBModel ccbModel) {
        if (view == null || userModel == null || reservaModel == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser null");
        }
        this.view = view;
        this.userModel = userModel;
        this.reservaModel = reservaModel;
        this.ccbModel = ccbModel;
        this.view.asignarControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch (command) {
                case ACTION_BUSCAR_CEDULA -> buscarCedula();
                case ACTION_SELECCIONAR_FOTO -> seleccionarFoto();
                case ACTION_FINALIZAR -> validarImagenYContinuar();
                case ACTION_CONFIRMAR_RESERVA -> procesarPagoYConfirmar();
                case ACTION_CERRAR -> view.dispose();
            }
        } catch (Exception ex) {
            view.showMessage("Error: " + ex.getMessage());
        }
    }

    /**
     * PASO 1: Buscar al usuario por cédula y verificar que tenga reserva válida
     * para la hora actual.
     */
    private void buscarCedula() {
        String cedula = view.getCedula();
        if (cedula.isEmpty()) {
            view.showMessage("Por favor ingrese una cédula.");
            return;
        }

        // Buscar usuario por cédula
        userModel.cargarUsuarios();
        usuarioEncontrado = userModel.buscarPorCedula(cedula);
        if (usuarioEncontrado == null) {
            view.showMessage("No se encontró un usuario con la cédula: " + cedula);
            return;
        }

        // Buscar reservas pendientes del usuario
        reservaModel.cargarReservas();
        List<Reserva> pendientes = reservaModel.getReservasPendientesPorUsuario(cedula);
        if (pendientes.isEmpty()) {
            view.showMessage("El usuario no tiene reservas pendientes.");
            return;
        }

        // Verificar cuál reserva coincide con la hora actual
        LocalTime ahora = LocalTime.now();
        reservaActiva = null;
        menuItemActivo = null;

        for (Reserva r : pendientes) {
            MenuItem item = reservaModel.getMenuModel().buscarPorId(r.getIdMenu());
            if (item != null && Utils.estaEnHorario(item.getHorario(), ahora)) {
                reservaActiva = r;
                menuItemActivo = item;
                break;
            }
        }

        if (reservaActiva == null || menuItemActivo == null) {
            view.showMessage("No hay reservas válidas para la hora actual ("
                    + ahora.format(DateTimeFormatter.ofPattern("hh:mm a")) + ").");
            return;
        }

        // Calcular precio final con CCB (incluye descuento por rol y beca)
        double precioFinal = calcularPrecioBandeja(usuarioEncontrado.getRole(), usuarioEncontrado.getPorcentajeBecado());

        // Pasar los datos a la vista
        String descripcion = menuItemActivo.getDescripcion()
                + "\nHorario: " + menuItemActivo.getHorario()
                + "\nUsuario: " + usuarioEncontrado.getName()
                + "\nCédula: " + usuarioEncontrado.getCedula();

        String rolNorm = usuarioEncontrado.getRole().trim().toLowerCase();
        if (rolNorm.equals("estudiante (b)")) {
            descripcion += "\nDescuento beca: " + usuarioEncontrado.getPorcentajeBecado() + "%";
        } else if (rolNorm.equals("estudiante (e)")) {
            descripcion += "\nExonerado (paga $0)";
        }

        view.setDatosReserva(menuItemActivo.getName(), descripcion, precioFinal);

        // Avanzar a la sección de foto
        view.mostrarSeccionSubida();
    }

    /**
     * PASO 2: Valida que la imagen coincida con la foto del usuario.
     */
    private void validarImagenYContinuar() {
        if (selectedFile == null) {
            view.showMessage("Por favor seleccione una imagen primero.");
            return;
        }

        if (usuarioEncontrado == null) {
            view.showMessage("Error interno: no se encontró al usuario.");
            return;
        }

        String rutaEsperada = usuarioEncontrado.getPfpPath();
        boolean esValido = Utils.CompararImagenes(selectedFile.getAbsolutePath(), rutaEsperada);

        if (esValido) {
            view.mostrarSeccionDetalles();
        } else {
            view.showMessage("La verificación de identidad ha fallado. La foto no coincide.");
        }
    }

    /**
     * PASO 3: Realiza el cobro, marca la reserva como consumida y finaliza.
     */
    private void procesarPagoYConfirmar() {
        double precio = view.getPrecio();

        // Verificación de saldo
        if (usuarioEncontrado.getSaldo() < precio) {
            view.showMessage("Saldo insuficiente. Saldo actual: $"
                    + String.format("%.2f", usuarioEncontrado.getSaldo()));
            return;
        }

        // Descontar saldo de forma atómica
        boolean success = userModel.modificarSaldo(usuarioEncontrado, (float) -precio);

        if (success) {
            // Marcar reserva como consumida
            reservaModel.marcarConsumida(reservaActiva);
            view.showMessage("¡Consumo registrado exitosamente!\n"
                    + "Menú: " + menuItemActivo.getName() + "\n"
                    + "Descontado: $" + String.format("%.2f", precio));
            // Resetear estado y volver al inicio
            usuarioEncontrado = null;
            reservaActiva = null;
            menuItemActivo = null;
            selectedFile = null;
            view.mostrarSeccionCedula();
        } else {
            view.showMessage("Error crítico al actualizar el saldo.");
        }
    }

    private void seleccionarFoto() {
        File archivo = Utils.seleccionarArchivoImagen(view);
        if (archivo != null) {
            selectedFile = archivo;
            ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image newImg = originalIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            view.setPreviewImage(new ImageIcon(newImg));
        }
    }

    private double calcularPrecioBandeja(String role, double porcentajeBeca) {
        try {
            CCB ccb = ccbModel.obtenerCCB();
            if (ccb != null && ccb.getCCBValor() > 0) {
                return ccb.calcularPrecioFinal(role, porcentajeBeca);
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo CCB: " + e.getMessage());
        }
        return 0;
    }
}