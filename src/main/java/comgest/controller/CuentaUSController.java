package comgest.controller;

import comgest.utils.*;

import comgest.model.UserSession;
import comgest.view.CuentaUSGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CuentaUSController implements ActionListener {
    public static final String ACTION_VER_SALDO = "VER_SALDO";
    public static final String ACTION_CCB = "CCB";
    public static final String ACTION_LISTA = "LISTA_COMENSALES";
    public static final String ACTION_TIPO_USUARIO = "TIPO_USUARIO";

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

        switch (command) {
            case ACTION_VER_SALDO -> ControladorView.mostrarRecargaSaldo();
            case ACTION_CCB -> ControladorView.mostrarCbb();
            case ACTION_LISTA -> ControladorView.mostrarListaComensales();
            case ACTION_TIPO_USUARIO -> ControladorView.mostrarRegistrarTipoUsuario();
        }
    }

    public void cargarPerfil() {
        UserSession session = Utils.getSessionOrRedirect();

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
}
