package main.view.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;


public class CampoEtiquetaUI extends JPanel {

    // Componentes internos
    private JLabel etiqueta;
    private JTextField Texto;

    // mismo diseño que BotonSimple
    private final Color COLOR_BASE = new Color(200, 177, 200);
    private final Color COLOR_HOVER = new Color(205, 205, 205);
    private final Color COLOR_TEXTO = Color.BLACK;
    private final Color COLOR_BLANCO = Color.WHITE;
    private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 14);
    private final Font FUENTE_CAMPO = new Font("Segoe UI", Font.BOLD, 14);

    public CampoEtiquetaUI(String titulo) {
        // Configuración del Panel Contenedor
        setLayout(new BorderLayout(0, 8)); // Distancia vertical entre el titulo y el campo.
        setOpaque(false); //Transparente para que no me cubra todo.
        initComponentes(titulo);
    }

    private void initComponentes(String titulo) {
      //TEXTO DE ARRIBA
        etiqueta = new JLabel(titulo);
        etiqueta.setFont(FUENTE_TITULO);
        etiqueta.setForeground(COLOR_TEXTO);
        etiqueta.setHorizontalAlignment(SwingConstants.LEFT);

        //CAMPO DE TEXTO
        Texto = new JTextField();
        Texto.setPreferredSize(new Dimension(250, 50));
        Texto.setFont(FUENTE_CAMPO);
        Texto.setForeground(COLOR_TEXTO);
        Texto.setBackground(COLOR_BASE);
        Texto.setCaretColor(Color.BLACK); // Color del cursor parpadeante

        // BORDES Y PADDING
        // Borde negro exterior + Margen interior de 10px para que el texto no se pegue
        Border bordeLinea = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border bordeVacio = BorderFactory.createEmptyBorder(0, 10, 0, 10);
        Texto.setBorder(BorderFactory.createCompoundBorder(bordeLinea, bordeVacio));

        //Eventos
        agregarEventos();

        //Añadir
        add(etiqueta, BorderLayout.NORTH); // Título arriba
        add(Texto, BorderLayout.CENTER); // Caja abajo
    }

    private void agregarEventos() {
        // Cambio de color al pasar el mouse
      Texto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!Texto.isFocusOwner()) { // Solo si no estás escribiendo
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

        // Cambio de color al hacer clic para escribir
       Texto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                Texto.setBackground(COLOR_BLANCO); // Se pone blanco para escribir
            }

            @Override
            public void focusLost(FocusEvent e) {
               Texto.setBackground(COLOR_BASE); // Vuelve al color base
            }
        });
    }
}