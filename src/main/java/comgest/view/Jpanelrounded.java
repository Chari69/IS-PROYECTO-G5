package comgest.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Jpanelrounded extends JPanel {
    private int radioEsquina = 20;

    public Jpanelrounded() {
        setOpaque(false); // Necesario para que el fondo sea transparente
    }

    public void SetRounded(int radio) {
        this.radioEsquina = radio;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        // Dibuja el rectángulo con esquinas redondeadas
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radioEsquina, radioEsquina));
        g2.dispose();
    }
}
