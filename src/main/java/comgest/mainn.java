package comgest;
import comgest.view.*;
import comgest.view.components.FrameStyle;

import javax.swing.*;
import java.awt.*;

public class mainn {
    public static void main(String[] args) {

         JFrame vp = FrameStyle.crearFramePrincipal("COMGEST-UCV");


         Panel_Inferior_PUI panel = new Panel_Inferior_PUI();
         vp.add(panel, BorderLayout.SOUTH);
         panel_menu_UI panel_menu = new panel_menu_UI();
         vp.add(panel_menu, BorderLayout.CENTER);
         vp.revalidate();
         vp.repaint();
         vp.setVisible(true);
        
    }
}

