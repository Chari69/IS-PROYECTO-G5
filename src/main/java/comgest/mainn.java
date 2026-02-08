package comgest;
import comgest.view.components.FrameStyle;
import comgest.view.panel_menu_UI;

import javax.swing.*;


public class mainn {
    public static void main(String[] args) {

        JFrame vp = FrameStyle.crearFramePrincipal("COMGEST-UCV");
        JPanel ControlaMenuUI = panel_menu_UI.CrearVentana(vp);
        ControlaMenuUI.setVisible(false);
        vp.setVisible(true);
        
    }
}

