package comgest.view.components;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelFecha {

    JPanel Espacio;
    JLabel Text;
    BotonSimple boton = new BotonSimple("DD/MM/AA");

    public PanelFecha() {
        Espacio = new JPanel();
        Espacio.setLayout(new BorderLayout());

        // Texto
        Text = new JLabel("Panel Administrativo");
        Text.setFont(new Font("Serif", Font.BOLD, 16));

        Espacio.add(Text, BorderLayout.NORTH);
        Espacio.add(boton, BorderLayout.SOUTH);
    }

    public JPanel get_Espacio() {
        return Espacio;
    }

}
