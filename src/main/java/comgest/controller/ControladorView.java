package comgest.controller;

import comgest.view.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import comgest.view.components.FrameStyle;

public class ControladorView {

    private static CardLayout cardLayout = new CardLayout();
    private static JPanel contenedor = new JPanel(cardLayout);
    private static LoginGUI loginView;
    private static LoginController loginController;
    private static RegisterGUI registerView;
    private static RegisterController registerController;
    private static CuentaUSGUI cuentaView;
    private static CuentaUSController cuentaController;
    private static MenuGUI menuView;
    private static MenuController menuController;
    private static CalculoCCBGUI calculoCCBView;
    private static CalculoCCBController calculoCCBController;
    private static Recargar_SaldoMenuUI recargaSaldoView;
    private static RecargarSaldoController recargaSaldoController;

    public static void main(String[] args) {
        JFrame pantalla = FrameStyle.crearFramePrincipal("COMGEST-UCV");

        loginView = new LoginGUI();
        JPanel pantallaLogin = loginView.crearVentana();
        loginController = new LoginController(loginView);
        loginView.asignarControlador(loginController);

        menuView = new MenuGUI();
        JPanel pantallaMenu = menuView.crearVentana();
        menuController = new MenuController(menuView);
        menuView.asignarControlador(menuController);

        calculoCCBView = new CalculoCCBGUI();
        JPanel pantallaCalculoCbb = calculoCCBView.crearVentana();
        calculoCCBController = new CalculoCCBController(calculoCCBView);
        calculoCCBView.asignarControlador(calculoCCBController);

        cuentaView = new CuentaUSGUI();
        JPanel pantallaCuenta = cuentaView.crearVentana();
        cuentaController = new CuentaUSController(cuentaView);
        cuentaView.asignarControlador(cuentaController);

        registerView = new RegisterGUI();
        JPanel pantallaRegister = registerView.crearVentana();
        registerController = new RegisterController(registerView);
        registerView.asignarControlador(registerController);

        recargaSaldoView = new Recargar_SaldoMenuUI();
        JPanel pantallaRecargaSaldo = recargaSaldoView.crearVentana();
        recargaSaldoController = new RecargarSaldoController(recargaSaldoView);
        recargaSaldoView.asignarControlador(recargaSaldoController);

        contenedor.setOpaque(false);
        contenedor.add(pantallaLogin, "LOGIN");
        contenedor.add(pantallaMenu, "MENU");
        contenedor.add(pantallaRegister, "REGISTER");
        contenedor.add(pantallaCalculoCbb, "CBB");
        contenedor.add(pantallaCuenta, "CUENTA");
        contenedor.add(pantallaRecargaSaldo, "RECARGA_SALDO");

        pantalla.add(contenedor);

        // Mostrar panel inicial
        mostrarLogin();

        pantalla.setVisible(true);
        pantalla.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void mostrarMenu() {
        if (menuController != null) {
            menuController.cargarMenu();
        }
        cardLayout.show(contenedor, "MENU");
    }

    public static void mostrarRegister() {
        cardLayout.show(contenedor, "REGISTER");
    }

    public static void mostrarLogin() {
        cardLayout.show(contenedor, "LOGIN");
    }

    public static void mostrarCbb() {
        if (calculoCCBController != null) {
            calculoCCBController.cargarCCBActual();
        }
        cardLayout.show(contenedor, "CBB");
    }

    public static void mostrarCuenta() {
        if (cuentaController != null) {
            cuentaController.cargarPerfil();
        }
        cardLayout.show(contenedor, "CUENTA");
    }

    public static void mostrarRecargaSaldo() {
        cardLayout.show(contenedor, "RECARGA_SALDO");
    }
}