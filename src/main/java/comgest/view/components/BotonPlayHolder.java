package comgest.view.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class BotonPlayHolder extends JPanel {

    private JTextField Texto;

    private final Color COLOR_BASE = new Color(200, 177, 200);
    private final Color COLOR_HOVER = new Color(205, 205, 205);
    private final Color COLOR_TEXTO = Color.BLACK;
    private final Color COLOR_PLACEHOLDER = new Color(100, 100, 100);
    private final Color COLOR_BLANCO = Color.WHITE;
    private final Font FUENTE_CAMPO = new Font("Segoe UI", Font.BOLD, 14);

    private String textoSugerencia; // Guardamos el texto original

    public BotonPlayHolder(String titulo) {
        this.textoSugerencia = titulo;
        setLayout(new BorderLayout(0, 8));
        setOpaque(false);
        initComponentes(titulo);
    }

    private void initComponentes(String titulo) {
        Texto = new JTextField(titulo); // Iniciamos con el texto
        Texto.setPreferredSize(new Dimension(250, 50));
        Texto.setFont(FUENTE_CAMPO);
        Texto.setForeground(COLOR_PLACEHOLDER); // Color de sugerencia inicial
        Texto.setBackground(COLOR_BASE);
        Texto.setCaretColor(Color.BLACK);

        Border bordeLinea = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border bordeVacio = BorderFactory.createEmptyBorder(0, 10, 0, 10);
        Texto.setBorder(BorderFactory.createCompoundBorder(bordeLinea, bordeVacio));

        agregarEventos();
        add(Texto, BorderLayout.CENTER);
    }

    private void agregarEventos() {
        Texto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!Texto.isFocusOwner()) {
                    Texto.setBackground(COLOR_HOVER);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!Texto.isFocusOwner()) {
                    Texto.setBackground(COLOR_BASE);
                }
            }
        });

        Texto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                Texto.setBackground(COLOR_BLANCO);
                // Si el texto actual es el placeholder, lo borramos para dejar escribir
                if (Texto.getText().equals(textoSugerencia)) {
                    Texto.setText("");
                    Texto.setForeground(COLOR_TEXTO);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Si al salir no escribió nada, reponemos el placeholder
                if (Texto.getText().isEmpty()) {
                    Texto.setForeground(COLOR_PLACEHOLDER);
                    Texto.setText(textoSugerencia);
                    Texto.setBackground(COLOR_BASE);
                } else {
                    Texto.setBackground(COLOR_BASE);
                }
            }
        });
    }

}
