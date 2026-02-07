package comgest.view.components;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class BotonSimple extends JButton {

    public BotonSimple(String texto) {
        super(texto); // Llama al constructor original de JButton
        configurarEstilo();
    }

    private void configurarEstilo() {
        // Colores y Fuente
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(250, 50));
        setBackground(new Color(200, 177, 200));
        setForeground(Color.black);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMaximumSize(new Dimension(250, 50)); // Para evitar que me lo cambian despues
        setMinimumSize(new Dimension(250, 50));

        // Efecto "Hover" (cambio de color al pasar el mouse)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(205, 205, 205));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(200, 177, 200));
            }
        });
    }
}