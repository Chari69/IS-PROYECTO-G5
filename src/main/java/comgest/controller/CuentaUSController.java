package comgest.controller;

import comgest.utils.*;

import comgest.model.UserSession;
import comgest.view.CuentaUSGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;

public class CuentaUSController implements ActionListener {
    public static final String ACTION_LOGOUT = "LOGOUT";
    public static final String ACTION_VER_SALDO = "VER_SALDO";
    public static final String ACTION_ADMIN_MENU = "ADMIN_MENU";
    public static final String ACTION_CCB = "CCB";

    private final CuentaUSGUI view;

    public CuentaUSController(CuentaUSGUI view) {
        if (view == null) {
            throw new IllegalArgumentException("view cannot be null");
        }
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (ACTION_LOGOUT.equals(command)) {
            UserSession.clearSession();
            view.showMessage("Sesion cerrada");
            ControladorView.mostrarLogin();
            return;
        }
        if (ACTION_VER_SALDO.equals(command)) {
            ControladorView.mostrarRecargaSaldo();
            return;
        }
        if (ACTION_ADMIN_MENU.equals(command)) {
            ControladorView.mostrarMenu();
            return;
        }
        if (ACTION_CCB.equals(command)) {
            ControladorView.mostrarCbb();
        }
    }

    public void cargarPerfil() {
        UserSession session = getSessionOrRedirect();
        if (session == null) {
            view.cargarDatos("Sin sesión", "", "", "", false, "", "");
            return;
        }
        String nombre = session.getName();
        String correo = session.getEmail();
        String cedula = session.getCedula();
        String rol = session.getRole();
        boolean isAdmin = Utils.isAdminRole(rol);
        float saldito = session.getSaldo();
        String saldo = String.format("%.2f", saldito);
        String pfpPath = session.getPfpPath();

        view.cargarDatos(nombre, correo, cedula, rol, isAdmin, saldo, pfpPath);
    }

    private UserSession getSessionOrRedirect() {
        UserSession session = UserSession.getInstance();
        if (session == null || !session.isActive()) {
            view.showMessage("Sesion no activa");
            ControladorView.mostrarLogin();
            return null;
        }
        return session;
    }
}
