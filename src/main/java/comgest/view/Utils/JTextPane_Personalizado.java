package comgest.view.Utils;
import javax.swing.*;

public class JTextPane_Personalizado {


  public static void Centrar(JTextPane hola, String texto) {
    hola.setContentType("text/html"); // <-- EL TRUCO ESTÁ AQUÍ
    hola.setEditable(false);
    hola.setOpaque(true);
    hola.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    
   
    hola.setText("<html><head><style>body { font-family: Arial; font-size: 20pt; text-align: center; font-weight: bold; }</style></head>"
                + "<body>" + texto + "</body></html>");
     hola.getCaret().setVisible(false);
    
}
}