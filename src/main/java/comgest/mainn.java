package comgest;

import javax.swing.*;

import comgest.view.components.Panel_Inferior_PUI;
import comgest.view.components.panel_menu_UI;

//import javax.swing.border.Border;
import java.awt.*;

public class mainn {
    public static void main(String[] args) {

        JFrame vp = new JFrame();

        vp.setSize(800, 600);
        vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel_Inferior_PUI panel = new Panel_Inferior_PUI();
        vp.add(panel.getPanel_inf(), BorderLayout.SOUTH);
        panel_menu_UI panel_menu = new panel_menu_UI();
        vp.add(panel_menu, BorderLayout.CENTER);
        vp.revalidate();
        vp.repaint();
        vp.setVisible(true);
        vp.setResizable(false);
    }
}
