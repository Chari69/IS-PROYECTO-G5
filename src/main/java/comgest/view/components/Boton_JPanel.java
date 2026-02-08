package comgest.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Boton_JPanel {

    JPanel Boton;
    JLabel label;
    Color colorNormal = new Color(228, 228, 255); 
    Color colorHover = new Color(220, 220, 220);
    int x;
    int y;

    public Boton_JPanel(String Imagen_ruta) {
        Boton = new JPanel();
        this.x = 25;
        this.y = 25;

        Boton.setLayout(new BorderLayout());
        Boton.setPreferredSize(new Dimension(35, 35)); 
        Boton.setBackground(colorNormal);
        CambiarIcono(Imagen_ruta);
        AnimarBoton(); 
    }
      public Boton_JPanel(String Imagen_ruta, int x, int y) {
        Boton = new JPanel();
        this.x = (int)(x * 0.7);
        this.y = (int)(y * 0.7);
        Boton.setLayout(new BorderLayout());
        Boton.setPreferredSize(new Dimension(x, y)); 
        Boton.setBackground(colorNormal);
        
        CambiarIcono(Imagen_ruta);
        AnimarBoton(); 
    }

    void AnimarBoton() {
        Boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Boton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                Boton.setBackground(colorHover); // Efecto al pasar el mouse
                Boton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Boton.setBackground(colorNormal); // Volver al estado original
                Boton.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Boton.setOpaque(true);
                Boton.setBackground(colorHover.brighter()); // Oscurecer un poco más al hacer clic
                Boton.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Boton.setBackground(colorHover);
                Boton.repaint();
            }
        });
    }

    public JPanel getBoton() {
        return Boton;
    }

    public void CambiarIcono(String ruta){

        ImageIcon icon = new ImageIcon(Boton_JPanel.class.getResource("../"+ruta));
        Image imgEscalada = icon.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
        label = new JLabel(new ImageIcon(imgEscalada));
        label.setOpaque(false); 
        Boton.add(label, BorderLayout.CENTER);

    }
}