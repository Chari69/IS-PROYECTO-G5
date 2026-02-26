package comgest.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comgest.model.UserModel;
import comgest.model.UserSession;
import comgest.model.Usuario;
import comgest.view.Recargar_SaldoMenuUI;

public class RecargarSaldoController implements ActionListener {
    public static final String ACTION_SUBMIT = "SUBMIT_RECARGA";

    private final Recargar_SaldoMenuUI view;
    private final UserModel userModel;

    public RecargarSaldoController(Recargar_SaldoMenuUI view) {
        this(view, new UserModel());
    }

    public RecargarSaldoController(Recargar_SaldoMenuUI view, UserModel userModel) {
        if (view == null) {
            throw new IllegalArgumentException("view cannot be null");
        }
        if (userModel == null) {
            throw new IllegalArgumentException("userModel cannot be null");
        }
        this.view = view;
        this.userModel = userModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!ACTION_SUBMIT.equals(e.getActionCommand())) {
            return;
        }

        String refStr = view.get_Referencia();
        String montoStr = view.get_Monto();

        // Validaciones de vacio
        if (refStr == null || refStr.trim().isEmpty() || refStr.equals("Ej: 12345")) {
            view.showMessage("La referencia es obligatoria.");
            return;
        }

        if (montoStr == null || montoStr.trim().isEmpty() || montoStr.equals("Ej: 150.00")) {
            view.showMessage("El monto es obligatorio.");
            return;
        }

        // Validacion de numeros
        try {
            Long.parseLong(refStr.trim());
        } catch (NumberFormatException ex) {
            view.showMessage("La referencia debe ser un número válido.");
            return;
        }

        float monto = 0;
        try {
            monto = Float.parseFloat(montoStr.trim());
            if (monto <= 0) {
                view.showMessage("El monto a recargar debe ser mayor a 0.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.showMessage("El monto debe ser numérico.");
            return;
        }

        UserSession session = UserSession.getInstance();
        if (session == null || !session.isActive()) {
            view.showMessage("No hay una sesión activa. Por favor inicie sesión.");
            ControladorView.mostrarLogin();
            return;
        }

        Usuario currentUser = session.getUsuario();

        // Agregar el saldo al usuario en memoria
        currentUser.addSaldo(monto);

        // Guardar cambios en la BDD
        boolean success = userModel.actualizarUsuario(currentUser);

        if (success) {
            view.showMessage("Saldo recargado exitosamente.");
            ControladorView.mostrarCuenta();
        } else {
            // revertir cambio en caso de error
            currentUser.subSaldo(monto);
            view.showMessage("Ocurrió un error al procesar la recarga.");
        }
    }
}
