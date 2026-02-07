package comgest.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Boton_JPanel {

    JPanel Boton;
    String Imagen_ruta;
    JLabel label;
    // Colores para el feedback visual
    Color colorNormal = new Color(228, 228, 255); // Ajusta a tu gusto
    Color colorHover = new Color(220, 220, 220);

    public Boton_JPanel(String Imagen_ruta) {
        this.Imagen_ruta = Imagen_ruta;
        Boton = new JPanel();

        Set_Boton();
        AnimarBoton(); // Llamamos a la animación desde el constructor
    }

    void Set_Boton() {
        Boton.setLayout(new BorderLayout());
        Boton.setPreferredSize(new Dimension(35, 35)); // Un poco más de margen
        Boton.setBackground(colorNormal);

        // Cargamos imagen

        ImageIcon icon = new ImageIcon(Boton_JPanel.class.getResource(Imagen_ruta));
        Image imgEscalada = icon.getImage().getScaledInstance(25, 20, Image.SCALE_SMOOTH);
        label = new JLabel(new ImageIcon(imgEscalada));
        Boton.add(label, BorderLayout.CENTER);

    }

    void AnimarBoton() {
        Boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Boton.setBackground(colorHover); // Efecto al pasar el mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Boton.setBackground(colorNormal); // Volver al estado original
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Boton.setOpaque(true);
                Boton.setBackground(colorHover.brighter()); // Oscurecer un poco más al hacer clic
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Boton.setBackground(colorHover);
            }
        });
    }

    public JPanel getBoton() {
        return Boton;
    }
}