
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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        this(view, new UserModel(), new ReservaModel(), new CCBModel());
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
        usuarioEncontrado = buscarUsuarioPorCedula(cedula);
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
            if (item != null && estaEnHorario(item.getHorario(), ahora)) {
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

        // Calcular precio con CCB (incluye descuento por rol)
        double precioFinal = calcularPrecioBandeja(usuarioEncontrado.getRole());
        double porcentajeBeca = usuarioEncontrado.getPorcentajeBecado();
        if (porcentajeBeca > 0) {
            precioFinal = precioFinal * (1.0 - porcentajeBeca / 100.0);
        }

        // Pasar los datos a la vista
        String descripcion = menuItemActivo.getDescripcion()
                + "\nHorario: " + menuItemActivo.getHorario()
                + "\nUsuario: " + usuarioEncontrado.getName()
                + "\nCédula: " + usuarioEncontrado.getCedula();

        if (porcentajeBeca > 0) {
            descripcion += "\nDescuento beca: " + porcentajeBeca + "%";
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

        // Comparación biométrica/imagen
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

        // Descontar saldo
        usuarioEncontrado.subSaldo((float) precio);
        boolean success = userModel.actualizarUsuario(usuarioEncontrado);

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
            // Revertir saldo
            usuarioEncontrado.addSaldo((float) precio);
            view.showMessage("Error crítico al actualizar el saldo.");
        }
    }

    private void seleccionarFoto() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Imagenes (JPG, PNG)", "jpg", "png", "jpeg"));

        if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image newImg = originalIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            view.setPreviewImage(new ImageIcon(newImg));
        }
    }

    /**
     * Busca un usuario por cédula usando el UserModel.
     */
    private Usuario buscarUsuarioPorCedula(String cedula) {
        return userModel.buscarPorCedula(cedula);
    }

    /**
     * Verifica si la hora actual está dentro del rango del horario del menú.
     * Formato esperado: "7:00 AM - 10:00 AM"
     */
    private boolean estaEnHorario(String horario, LocalTime ahora) {
        if (horario == null || horario.trim().isEmpty()) {
            return true; // Si no tiene horario, siempre es válido
        }

        try {
            String[] partes = horario.split("-");
            if (partes.length != 2) {
                return true;
            }

            LocalTime inicio = parsearHora(partes[0].trim());
            LocalTime fin = parsearHora(partes[1].trim());

            if (inicio == null || fin == null) {
                return true;
            }

            // Verificar si la hora actual está en el rango [inicio, fin]
            return !ahora.isBefore(inicio) && !ahora.isAfter(fin);
        } catch (Exception e) {
            System.out.println("Error parseando horario: " + e.getMessage());
            return true; // En caso de error, permitir
        }
    }

    /**
     * Parsea una hora en formato "7:00 AM" o "10:00 PM" a LocalTime.
     */
    private LocalTime parsearHora(String texto) {
        try {
            texto = texto.trim().toUpperCase();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", java.util.Locale.US);
            return LocalTime.parse(texto, formatter);
        } catch (Exception e) {
            try {
                DateTimeFormatter formatter24 = DateTimeFormatter.ofPattern("H:mm");
                return LocalTime.parse(texto.trim(), formatter24);
            } catch (Exception e2) {
                System.out.println("No se pudo parsear la hora: " + texto);
                return null;
            }
        }
    }

    /**
     * Calcula el precio por bandeja usando el CCB con descuento por rol.
     */
    private double calcularPrecioBandeja(String role) {
        try {
            CCB ccb = ccbModel.obtenerCCB();
            if (ccb != null && ccb.getCCBValor() > 0) {
                return ccb.calcularPrecioConDescuento(role);
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo CCB: " + e.getMessage());
        }
        // Valor por defecto si no se puede calcular
        return 0;
    }
}