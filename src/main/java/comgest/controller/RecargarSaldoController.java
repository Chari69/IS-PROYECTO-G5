package comgest.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comgest.model.UserModel;
import comgest.model.UserSession;
import comgest.model.Usuario;
import comgest.utils.Utils;
import comgest.view.Recargar_SaldoMenuUI;

public class RecargarSaldoController implements ActionListener {
    public static final String ACTION_SUBMIT = "SUBMIT_RECARGA";
    public static final String ACTION_PANITA = "TOGGLE_PANITA";

    private final Recargar_SaldoMenuUI view;
    private final UserModel userModel;

    public RecargarSaldoController(Recargar_SaldoMenuUI view) {
        this(view, UserModel.getInstance());
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
        String command = e.getActionCommand();

        if (ACTION_PANITA.equals(command)) {
            view.togglePanita();
            return;
        }

        if (!ACTION_SUBMIT.equals(command)) {
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

        UserSession session = Utils.getSessionOrRedirect();
        if (session == null) {
            view.showMessage("No hay una sesión activa. Por favor inicie sesión.");
            return;
        }

        // Determinar a quién recargar
        Usuario targetUser;

        if (view.isSaldoPana()) {
            // Saldo Pana: recargar a otro usuario por CI
            String ciPana = view.get_Cedula();
            if (ciPana == null || ciPana.trim().isEmpty() || ciPana.equals("Ej: 32298110")) {
                view.showMessage("Debe ingresar la cédula del beneficiario.");
                return;
            }

            try {
                Long.parseLong(ciPana.trim());
            } catch (NumberFormatException ex) {
                view.showMessage("La cédula debe ser un número válido.");
                return;
            }

            userModel.invalidar();
            userModel.cargarUsuarios();
            targetUser = userModel.buscarPorCedula(ciPana.trim());
            if (targetUser == null) {
                view.showMessage("No se encontró un usuario con la cédula: " + ciPana.trim());
                return;
            }
        } else {
            // Recarga personal
            targetUser = session.getUsuario();
        }

        boolean success = userModel.modificarSaldo(targetUser, monto);

        if (success) {
            if (view.isSaldoPana()) {
                view.showMessage("Saldo recargado exitosamente al usuario: " + targetUser.getName());
            } else {
                view.showMessage("Saldo recargado exitosamente.");
            }
            view.resetPanita();
            ControladorView.mostrarCuenta();
        } else {
            view.showMessage("Ocurrió un error al procesar la recarga.");
        }
    }
}
