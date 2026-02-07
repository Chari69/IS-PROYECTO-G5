package comgest.view;

import javax.swing.*;
//import java.awt.event.*;
import java.awt.*;

public class IGUPaginaPrincipal {
    // ----MAIN----//
    public static void main(String[] args) {
        Initialize_frame();
    }

    // ----ESTO CREA LA VENTANA---- //
    private static void Initialize_frame() {
        // Constantes
        String frame_name = "SGCU";
        int width = 800;
        int height = 600;

        JFrame frame = new JFrame(frame_name);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Elementos graficos
        Buttons(frame);

        // instanciar ventana
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private static void Buttons(JFrame frame) {
        // crea panel para los botones
        Jpanelrounded panel_buttons = new Jpanelrounded();
        panel_buttons.setLayout(new GridLayout(0, 1, 0, 50));
        panel_buttons.setBackground(Color.white);
        panel_buttons.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panel_buttons.setPreferredSize(new Dimension(250, 300));// dimension de botones en el panel
        panel_buttons.SetRounded(40);// clase escrita por mi

        // boton iniciar sesion
        JButton button_inicio_sesion = new JButton("Iniciar Sesión");
        button_inicio_sesion.addActionListener(e -> {
            button_inicio_sesion.setForeground(Color.gray);
        });

        button_inicio_sesion.setContentAreaFilled(false);
        button_inicio_sesion.setBorderPainted(false);
        button_inicio_sesion.setFocusPainted(false);

        panel_buttons.add(button_inicio_sesion);

        // boton registrarse
        JButton button_registro = new JButton("Registrarse");
        button_registro.addActionListener(e -> {
            button_registro.setForeground(Color.gray);
        });
        button_registro.setContentAreaFilled(false);
        button_registro.setBorderPainted(false);
        button_registro.setFocusPainted(false);
        panel_buttons.add(button_registro);

        // añadimos nuestro panel al frame principal
        frame.add(panel_buttons);
    }

}