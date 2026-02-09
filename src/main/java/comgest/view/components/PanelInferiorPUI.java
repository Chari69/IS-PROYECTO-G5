package comgest.view.components;

import javax.swing.*;

import comgest.controller.ControladorView;

//import javax.swing.border.Border;
import java.awt.*;

public class PanelInferiorPUI extends JPanel {

    Boton_Menu boton_menu;
    Boton_Menu boton_Micuenta;

    // Constructor
    public PanelInferiorPUI() {
        boton_menu = new Boton_Menu("resources/image1.png", "Menú");
        boton_Micuenta = new Boton_Menu("resources/myaccount.png", "Mi Cuenta");

        Modificar_Panel_inf();

        boton_Micuenta.getboton().getBoton().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                ControladorView.mostrarCuenta();

            }
        });
        boton_menu.getboton().getBoton().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                ControladorView.mostrarMenu();

            }
        });

        this.add(boton_menu);
        this.add(boton_Micuenta);
    }

    public void Modificar_Panel_inf() {

        this.setBackground(new Color(228, 228, 255));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
    }

    // Clase del boton del menu
    public class Boton_Menu extends JPanel {

        BotonJPanel boton;
        JLabel Inferior;

        // Constructor
        public Boton_Menu(String ruta, String nombre) {

            this.setOpaque(false);
            this.setLayout(new BorderLayout());
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Botones JPanel
            boton = new BotonJPanel(ruta);
            Inferior = new JLabel(nombre);

            Modificar_Superior();
            Modificar_Inferior();

            this.add(boton.getBoton(), BorderLayout.CENTER); // Parte clickeable (más grande)
            this.add(Inferior, BorderLayout.SOUTH); // Texto abajo

        }

        void Modificar_Superior() {
            boton.getBoton().setBackground(new Color(228, 228, 255));

        }

        void Modificar_Inferior() {

            Inferior.setFont(new Font("Segoe UI", Font.BOLD, 20));
            Inferior.setOpaque(true);
            Inferior.setBackground(new Color(228, 228, 255));

        }

        BotonJPanel getboton() {
            return boton;
        }

    }

}