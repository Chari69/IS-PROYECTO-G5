package comgest.view.Utils;
import javax.swing.*;
import java.awt.*;

public class Estilo_Boton_Sistema extends JButton {
    
    
    public static final Color MORADO_PRIMARIO = new Color(87, 35, 100);
    public static final Color GRIS_EDITAR = new Color(100, 100, 100);
    
    // Método para botones con texto (como el de "Ordenar")
    public static void aplicarEstiloPrincipal(JButton boton) {
        boton.setBackground(MORADO_PRIMARIO);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Método para botones de icono transparentes (como el del lápiz sobre la imagen)
    public static void aplicarEstiloIconoTransparente(JButton boton) {
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}

