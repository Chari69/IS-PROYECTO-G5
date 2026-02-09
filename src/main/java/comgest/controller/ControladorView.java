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

    public static void main(String[] args) {
        JFrame pantalla = FrameStyle.crearFramePrincipal("COMGEST-UCV");

        loginView = new LoginGUI();
        JPanel pantallaLogin = loginView.crearVentana();
        loginController = new LoginController(loginView);
        loginView.asignarControlador(loginController);
        
        JPanel pantallaMenu = panel_menu_UI.CrearVentana();
        JPanel pantallaCalculoCbb = CalculoCCBGUI.crearVentana();
        cuentaView = new CuentaUSGUI();
        JPanel pantallaCuenta = cuentaView.crearVentana();
        cuentaController = new CuentaUSController(cuentaView);
        cuentaView.asignarControlador(cuentaController);

        registerView = new RegisterGUI();
        JPanel pantallaRegister = registerView.crearVentana();
        registerController = new RegisterController(registerView);
        registerView.asignarControlador(registerController);

        contenedor.add(pantallaLogin, "LOGIN");
        contenedor.add(pantallaMenu, "MENU");
        contenedor.add(pantallaRegister, "REGISTER");
        contenedor.add(pantallaCalculoCbb, "CBB");
        contenedor.add(pantallaCuenta, "CUENTA");

        pantalla.add(contenedor);
        
        // Mostrar panel inicial
        cardLayout.show(contenedor, "REGISTER");

        pantalla.setVisible(true);
    }

    public static void mostrarMenu() {
        cardLayout.show(contenedor, "MENU");

    }
    public static void mostrarRegister() {
        cardLayout.show(contenedor, "REGISTER");

    } 
    public static void mostrarLogin(){
        cardLayout.show(contenedor, "LOGIN");

    } 
    public static void mostrarCbb(){
        cardLayout.show(contenedor, "CBB");

    } 
    public static void mostrarCuenta(){
        if (cuentaController != null) {
            cuentaController.cargarPerfil();
        }
        cardLayout.show(contenedor, "CUENTA");
    }
}