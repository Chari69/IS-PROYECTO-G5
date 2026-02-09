package comgest.controller;

import comgest.model.UserModel;
import comgest.view.RegisterGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    public static final String ACTION_REGISTRAR = "REGISTER";

    private final UserModel userModel;
    private final RegisterGUI view;

    public RegisterController(RegisterGUI view) {
        this(new UserModel(), view);
    }

    public RegisterController(UserModel userModel, RegisterGUI view) {
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
        String command = e.getActionCommand();
        if (!ACTION_REGISTRAR.equals(command)) {
            return;
        }
        String result = registrar(
                view.getNombreUsuario(),
                view.getCedula(),
                view.getCorreo(),
                view.getPassword(),
                view.getPasswordConfirm());
        view.showMessage(result);
    }

    public String registrar(String nombre, String cedula, String email, String password,
            String passwordConfirm) {
        String nombreVal = normalizar(nombre);
        String cedulaVal = normalizar(cedula);
        String emailVal = normalizar(email);

        if (password == null || password.isEmpty()) {
            return "el campo contraseña es obligatorio";
        }
        if (nombreVal.isEmpty()) {
            return "el campo Nombre de usuario es obligatorio";
        }
        if (cedulaVal.isEmpty()) {
            return "el campo Cédula es obligatorio";
        }
        if (emailVal.isEmpty()) {
            return "el campo Correo es obligatorio";
        }
        if (passwordConfirm == null || !password.equals(passwordConfirm)) {
            return "las contraseñas no coinciden";
        }
        if (userModel.verificarCorreoExistente(emailVal)) {
            return "ya hay una cuenta asociada a este correo";
        }

        boolean registrado = userModel.RegistrarUsuario(
                nombreVal,
                password,
                emailVal,
                cedulaVal,
                0);
        if (!registrado) {
            return "La cédula no está autorizada";
        }
        ControladorView.mostrarLogin();
        return "Registrado exitosamente";
    }

    private String normalizar(String value) {
        return value == null ? "" : value.trim();
    }

}
