package comgest.controller;

import comgest.view.ReservarGUI;
import comgest.model.UserSession;
import comgest.model.Usuario;
import comgest.model.ReservaModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ReservarController implements ActionListener {
    public static final String ACTION_CONFIRMAR_RESERVA = "CONFIRMAR_RESERVA";
    public static final String ACTION_CERRAR = "CERRAR";

    private final ReservarGUI view;
    private final ReservaModel reservaModel;

    public ReservarController(ReservarGUI view) {
        this(view, new ReservaModel());
    }

    public ReservarController(ReservarGUI view, ReservaModel reservaModel) {
        if (view == null) {
            throw new IllegalArgumentException("view no puede ser null");
        }
        if (reservaModel == null) {
            throw new IllegalArgumentException("reservaModel no puede ser null");
        }
        this.view = view;
        this.reservaModel = reservaModel;
        this.view.asignarControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (ACTION_CONFIRMAR_RESERVA.equals(command)) {
                confirmarReserva();
            } else if (ACTION_CERRAR.equals(command)) {
                view.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Ocurrió un error inesperado: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarReserva() {
        UserSession session = UserSession.getInstance();
        if (session == null || !session.isActive()) {
            view.showMessage("No hay una sesión activa.");
            return;
        }

        Usuario currentUser = session.getUsuario();
        String cedula = currentUser.getCedula();
        String menuItemId = view.getMenuItemId();

        if (menuItemId == null || menuItemId.trim().isEmpty()) {
            view.showMessage("Error: no se pudo identificar el menú.");
            return;
        }

        // Verificar si ya consumió este menú
        if (reservaModel.buscarReservaConsumida(cedula, menuItemId) != null) {
            view.showMessage("Ya consumiste este menú. No puedes reservar de nuevo.");
            return;
        }

        // Verificar si ya tiene una reserva pendiente para este menú
        if (reservaModel.buscarReservaPendiente(cedula, menuItemId) != null) {
            view.showMessage("Ya tienes una reserva pendiente para este menú.");
            return;
        }

        // Guardar la reserva
        boolean exito = reservaModel.agregarReserva(cedula, menuItemId);

        if (exito) {
            view.showMessage("¡Reserva realizada exitosamente!\nRecuerda pasar por el punto de venta para consumir.");
            view.dispose();
        } else {
            view.showMessage("Error al realizar la reserva. El menú puede no existir.");
        }
    }
}
