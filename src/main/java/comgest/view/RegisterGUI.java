package comgest.view;

import comgest.controller.ControladorView;
import comgest.controller.RegisterController;

import javax.swing.*;
import comgest.view.components.BotonPlayHolder;
import comgest.view.components.BotonSimple;

import java.awt.event.*;

import java.awt.*;

public class RegisterGUI {
    private JFrame frame;
    private BotonPlayHolder NombreUsuario;
    private BotonPlayHolder Cedula;
    private BotonPlayHolder Correo;
    private BotonPlayHolder contraseña1;
    private BotonPlayHolder contraseñaconf;
    private BotonSimple bttmreg;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            RegisterGUI view = new RegisterGUI();
            RegisterController controller = new RegisterController(view);
            view.asignarControlador(controller);

        });
    }

    public RegisterGUI() {
        crearVentana();
    }

    public JPanel crearVentana() {
        // // Frame
        // frame = FrameStyle.crearFramePrincipal("COMGEST-UCV");

        // Panel Principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setSize(800, 600);
        panel.setOpaque(false);

        // LOGO
        ImageIcon logoIcon = new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png"));
        // PARA REDIMENSIONAR
        Image imgEscalada = logoIcon.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
        ImageIcon Redimensionado = new ImageIcon(imgEscalada);
        // CREAMOS EL JLABEL YA CON LA IMAGEN
        JLabel imagenlabel = new JLabel(Redimensionado);

        // Panel Imagen
        JPanel panelim = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // ESCOJO EL GDL
        panelim.setOpaque(false);
        panelim.add(imagenlabel, BorderLayout.CENTER);

        // Agregar el panel imagen al panel principal
        panel.add(panelim, BorderLayout.WEST);

        // //Panel fantasma para tener simetria por la derecha.
        JPanel fant = new JPanel();
        fant.setLayout(new BorderLayout(10, 10));
        fant.setOpaque(false);
        fant.setPreferredSize(new Dimension(160, 200)); // MISMO TAMAÑO QUE LA IMAGEN
        // frame.add(fant, BorderLayout.EAST);
        panel.add(fant, BorderLayout.EAST);

        // Agregar Panel Para Campos de texto
        JPanel botones = new JPanel();
        botones.setLayout(new GridBagLayout());
        GridBagConstraints gdc = new GridBagConstraints();
        botones.setOpaque(false);
        gdc.insets = new Insets(10, 10, 10, 10);

        // Caja para Nombre de Usuario
        NombreUsuario = new BotonPlayHolder("Nombre De Usuario");
        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(NombreUsuario, gdc);
        // Caja para cedula
        Cedula = new BotonPlayHolder("Cédula Ej 13322122");
        gdc.gridx = 0;
        gdc.gridy = 1;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(Cedula, gdc);
        // Caja para correo
        Correo = new BotonPlayHolder("Correo electrónico");
        gdc.gridx = 0;
        gdc.gridy = 2;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(Correo, gdc);
        // Caja para contraseña 1
        contraseña1 = new BotonPlayHolder("Contraseña");
        gdc.gridx = 0;
        gdc.gridy = 3;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(contraseña1, gdc);
        // Caja para contraseña Confirmar
        contraseñaconf = new BotonPlayHolder("Confirmar Contraseña");
        gdc.gridx = 0;
        gdc.gridy = 4;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(contraseñaconf, gdc);

        // caja invisible para que se lleve el focus

        JTextField invis = new JTextField("Hi");
        invis.setBackground(new Color(0, 0, 0, 0));
        invis.setBorder(null);
        invis.setOpaque(false);
        panel.add(invis, BorderLayout.CENTER);

        // PANEL PARA IR AL LOGIN/PREGUNTA

        JPanel loginpa = new JPanel();
        loginpa.setLayout(new GridBagLayout()); // ESCOJO EL GDL
        GridBagConstraints gpdc = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR
        gpdc.insets = new Insets(1, 10, 1, 10);
        loginpa.setOpaque(false);

        // TEXTO PREGUNTA
        JLabel lblLOGINtxt = new JLabel("¿Ya tienes una cuenta?");
        lblLOGINtxt.setForeground(Color.black);
        // TEXTO PARA IR REGISTRO
        JLabel lbllog = new JLabel("Click aqui para ingresar");
        lbllog.setForeground(Color.BLUE);
        lbllog.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lbllog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControladorView.mostrarLogin();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Efecto visual: Poner negrita o subrayar al pasar el mouse
                lbllog.setText("<html><u>Click aqui para ingresar</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quitar el subrayado al salir
                lbllog.setText("Click aqui para ingresar");
            }
        });

        // Agregar IR AL LOGIN LABEL AL PANEL DE LOGIN
        gpdc.gridx = 0;
        gpdc.gridy = 6; // MAS ABAJO
        gpdc.weightx = 1.0;
        gpdc.weighty = 0.1;
        gpdc.anchor = GridBagConstraints.NORTH;
        loginpa.add(lbllog, gpdc);
        // Agregar texto DE PREGUNTA AL PANEL DE LOGIN
        gpdc.gridx = 0;
        gpdc.gridy = 5; // MAS ABAJO
        gpdc.weightx = 1.0;
        gpdc.weighty = 0.1;
        gpdc.anchor = GridBagConstraints.NORTH;
        loginpa.add(lblLOGINtxt, gpdc);

        // Agregar el Panel de ir al login al panel de botones.
        gdc.gridx = 0;
        gdc.gridy = 7; // MAS ABAJO
        gdc.weightx = 0.0;
        gdc.weighty = 0.0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(loginpa, gdc);

        // Agregar boton de confirmar register al panel de botones

        // boton de confirmar registro
        bttmreg = new BotonSimple("Registrarse");
        gdc.gridx = 0;
        gdc.gridy = 6;
        gdc.weightx = 0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(bttmreg, gdc);

        // CheckBox de terminos y condiciones, lo agrego al panel de botones

        JCheckBox checkTerminos = new JCheckBox("Aceptar términos y condiciones");
        checkTerminos.setOpaque(false);
        checkTerminos.setFont(new Font("Segoe UI", Font.BOLD, 12));
        checkTerminos.setFocusable(false);
        gdc.gridx = 0;
        gdc.gridy = 5;
        gdc.weightx = 0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(checkTerminos, gdc);

        // Agregar el panel botones al principal
        panel.add(botones, BorderLayout.CENTER);
        // Agregar el panel principal al frame
        // frame.add(panel, BorderLayout.CENTER);
        // frame.setVisible(false);
        return panel;
    }

    public void asignarControlador(RegisterController registerController) {
        bttmreg.setActionCommand(RegisterController.ACTION_REGISTRAR);
        bttmreg.addActionListener(registerController);
    }

    public String getNombreUsuario() {
        return NombreUsuario.getTexto();
    }

    public String getCedula() {
        return Cedula.getTexto();
    }

    public String getCorreo() {
        return Correo.getTexto();
    }

    public String getPassword() {
        return contraseña1.getTexto();
    }

    public String getPasswordConfirm() {
        return contraseñaconf.getTexto();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
