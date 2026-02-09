package comgest.controller;

import comgest.view.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import comgest.view.components.FrameStyle;

public class ControladorView {
    
    private static CardLayout cardLayout = new CardLayout();
    private static JPanel contenedor = new JPanel(cardLayout);

    public static void main(String[] args) {
        JFrame pantalla = FrameStyle.crearFramePrincipal("COMGEST-UCV");

        JPanel pantallaLogin = LoginGUI.crearVentana();
        JPanel pantallaMenu = panel_menu_UI.CrearVentana();
        JPanel pantallaCalculoCbb = CalculoCCBGUI.crearVentana();
        JPanel pantallaCuenta = CuentaUSGUI.crearVentana();

        RegisterGUI registerView = new RegisterGUI(); 
        JPanel pantallaRegister = registerView.crearVentana(); 

        RegisterController controller = new RegisterController(registerView);
        registerView.asignarControlador(controller);

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
        cardLayout.show(contenedor, "CUENTA");
    }
}