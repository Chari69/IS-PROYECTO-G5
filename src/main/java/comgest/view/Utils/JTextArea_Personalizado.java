package comgest.view.Utils;

import javax.swing.*;


import java.awt.*;

public class JTextArea_Personalizado extends JTextArea {
    
    public static void AplicarPersonalización(JTextArea hola){
        hola.setLineWrap(true);
        hola.setWrapStyleWord(true);
        hola.setEditable(false);
        hola.setOpaque(false);
        hola.setFont(new Font("Segoe UI", Font.BOLD, 13));
        hola.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        hola.getCaret().setVisible(false);
        
    }
}