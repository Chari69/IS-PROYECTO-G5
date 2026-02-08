package comgest.view;
import comgest.model.ControladorDB;
import comgest.model.Usuario;

import javax.swing.*;

import comgest.view.components.BotonPlayHolder;
import comgest.view.components.BotonSimple;
import comgest.view.components.FrameStyle;

import java.awt.event.*;
import java.awt.*;

public class LoginGUI {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            crearVentana();
        });
    }

   
    public static void crearVentana() {
        //Frame
        JFrame frame = FrameStyle.crearFramePrincipal("COMGEST-UCV");

        // Panel Principal
        JPanel panel = new JPanel();
        panel.setSize(800, 600);
        panel.setLayout(new GridBagLayout()); // ESCOJO EL GDL
        GridBagConstraints gdc = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR
        gdc.insets = new Insets(10, 10, 10, 10);
        panel.setOpaque(false);

        // LOGO
        ImageIcon logoIcon = new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png"));
        // PARA REDIMENSIONAR
        Image imgEscalada = logoIcon.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
        ImageIcon Redimensionado = new ImageIcon(imgEscalada);
        // CREAMOS EL JLABEL YA CON LA IMAGEN
        JLabel imagenlabel = new JLabel(Redimensionado);

        // PANELES

        // PANEL PARA CAMPOS DE TEXTO

        JPanel ctextos = new JPanel();
        // panel.setSize(800,600);

        ctextos.setLayout(new GridBagLayout()); // ESCOJO EL GDL
        GridBagConstraints g2dc = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR
        g2dc.insets = new Insets(10, 10, 10, 10);
        ctextos.setOpaque(false);

        // caja invisible para que se lleve el focus(bien xd)

        JTextField invis = new JTextField("");
        invis.setBackground(new Color(0, 0, 0, 0));
        invis.setBorder(null);
        invis.setOpaque(false);
        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.weightx = 0.0;
        gdc.weighty = 1.0;
        gdc.anchor = GridBagConstraints.NORTH;
        // frame.add(invis, gdc);
        frame.add(invis,BorderLayout.NORTH);

        // cajatexto NOMBRE USUARIO
        BotonPlayHolder cedulita = new BotonPlayHolder("Cédula Ej 13322122");

        gdc.gridx = 0;
        gdc.gridy = 0; // FILA 0 MAS ARRIBA
        gdc.weightx = 1.0;
        gdc.weighty = 0.1;
        gdc.anchor = GridBagConstraints.SOUTH;
        ctextos.add(cedulita, gdc);

        // caja texto CONTRASEÑA
        BotonPlayHolder contraseña = new BotonPlayHolder("Contraseña");
        gdc.gridx = 0;
        gdc.gridy = 1; // FILA 1 MAS ABAJO
        gdc.weightx = 1.0;
        gdc.weighty = 0.1;
        gdc.anchor = GridBagConstraints.SOUTH;
        ctextos.add(contraseña, gdc);

        // boton de confirmar login
        BotonSimple bttmlogin = new BotonSimple("Loguearse");
        gdc.gridx = 0;
        gdc.gridy = 2; // FILA 1 MAS ABAJO
        gdc.weightx = 1.0;
        gdc.weighty = 0.1;
        gdc.anchor = GridBagConstraints.SOUTH;
        ctextos.add(bttmlogin, gdc);

        // Accion del boton de confirmar

        bttmlogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                //------------------CONEXION CON LA BASE DE DATOS-----------------//

                
                ControladorDB controladorDB = new ControladorDB();
                if(controladorDB.InicioDeSesion(usurnname.getTexto(), contraseña.getTexto())){
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña invalidos");
                }
                controladorDB =null;
            }
        });

        // Añadir

        // CONFIGURACION LUGAR PARA IMAGEN
        gdc.gridx = 0;
        gdc.gridy = 0; // MAS ARRIBA
        gdc.weightx = 1.0;
        gdc.weighty = 0.1;
        gdc.anchor = GridBagConstraints.SOUTH;
        panel.add(imagenlabel, gdc);

        // CONFIGURACION LUGAR PARA CTEXTOS
        gdc.gridx = 0;
        gdc.gridy = 2; // MAS ABAJO
        gdc.weightx = 1.0;
        gdc.weighty = 0.1;
        gdc.anchor = GridBagConstraints.SOUTH;
        panel.add(ctextos, gdc);

        // AÑADIR TEXTOS CON FUNCIONES

        // TEXTO OLVIDAR CONTRASEÑA
        JLabel lblpass = new JLabel("Olvidé mi contraseña");
        lblpass.setForeground(Color.BLUE);
        lblpass.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblpass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Redirigiendo a recuperación...");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Efecto visual: Poner negrita o subrayar al pasar el mouse
                lblpass.setText("<html><u>Olvidé mi contraseña</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quitar el subrayado al salir
                lblpass.setText("Olvidé mi contraseña");
            }
        });

        gdc.gridx = 0;
        gdc.gridy = 8; // MAS ABAJO
        gdc.weightx = 1.0;
        gdc.weighty = 0.1;
        gdc.anchor = GridBagConstraints.NORTH;
        panel.add(lblpass, gdc);

        // PANEL PARA TEXTO PREGUNTA
        JPanel registrogo = new JPanel();
        registrogo.setLayout(new GridBagLayout()); // ESCOJO EL GDL
        GridBagConstraints gpdc = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR
        gpdc.insets = new Insets(1, 10, 1, 10);
        registrogo.setOpaque(false);

        // TEXTO PREGUNTA
        JLabel lblregtxt = new JLabel("¿No tienes una cuenta?");
        lblregtxt.setForeground(Color.black);
        // TEXTO PARA IR REGISTRO
        JLabel lblreg = new JLabel("Click aqui para registrarse");
        lblreg.setForeground(Color.BLUE);
        lblreg.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblreg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Redirigiendo a registro...");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Efecto visual: Poner negrita o subrayar al pasar el mouse
                lblreg.setText("<html><u>Click aqui para registrarse</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quitar el subrayado al salir
                lblreg.setText("Click aqui para registrarse");
            }
        });

        // Agregar IR AL REGISTRO
        gpdc.gridx = 0;
        gpdc.gridy = 6; // MAS ABAJO
        gpdc.weightx = 1.0;
        gpdc.weighty = 0.1;
        gpdc.anchor = GridBagConstraints.NORTH;
        registrogo.add(lblreg, gpdc);
        // Agregar texto DE PREGUNTA UN POCO MAS ARRIBA DE LA FUNCION COMO TAL
        gpdc.gridx = 0;
        gpdc.gridy = 5; // MAS ABAJO
        gpdc.weightx = 1.0;
        gpdc.weighty = 0.1;
        gpdc.anchor = GridBagConstraints.NORTH;
        registrogo.add(lblregtxt, gpdc);

        // Agregar el Panel de Registro al panel.
        gpdc.gridx = 0;
        gpdc.gridy = 6; // MAS ABAJO
        gpdc.weightx = 1.0;
        gpdc.weighty = 0.1;
        gpdc.anchor = GridBagConstraints.NORTH;
        panel.add(registrogo, gpdc);

        // AÑADIR TODO A LA VENTANA
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}