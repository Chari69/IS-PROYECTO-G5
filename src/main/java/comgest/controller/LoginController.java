package comgest.controller;

import comgest.model.UserModel;
import comgest.model.UserSession;
import comgest.model.Usuario;
import comgest.view.LoginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    public static final String ACTION_LOGIN = "LOGIN";

    private final UserModel userModel;
    private final LoginGUI view;

    public LoginController(LoginGUI view) {
        this(new UserModel(), view);
    }

    public LoginController(UserModel userModel, LoginGUI view) {
        if (userModel == null) {
            throw new IllegalArgumentException("userModel cannot be null");
        }
        if (view == null) {
            throw new IllegalArgumentException("view cannot be null");
        }
        this.userModel = userModel;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!ACTION_LOGIN.equals(e.getActionCommand())) {
            return;
        }

        String cedula = normalizar(view.getCedula());
        String password = view.getPassword();

        if (cedula.isEmpty()) {
            view.showMessage("el campo Cédula es obligatorio");
            return;
        }
        if (password == null || password.isEmpty()) {
            view.showMessage("el campo contraseña es obligatorio");
            return;
        }

        userModel.cargarUsuarios();

        Usuario usuario = userModel.autenticar(cedula, password);
        if (usuario == null) {
            view.showMessage("Usuario o contraseña invalidos");
            return;
        }

        UserSession.startSession(usuario);
        view.showMessage("Bienvenido");
        ControladorView.mostrarMenu();
    }

    private String normalizar(String value) {
        return value == null ? "" : value.trim();
    }
}
