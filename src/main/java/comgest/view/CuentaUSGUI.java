package comgest.view;

import javax.swing.*;

import comgest.controller.CuentaUSController;
import comgest.view.components.BotonSimple;
import comgest.view.components.PanelInferiorPUI;

import java.awt.*;
import java.awt.event.*;

public class CuentaUSGUI {
    private JFrame frame;
    private JLabel lblNombre;
    private JLabel lblcorreo;
    private JLabel lblced;
    private JLabel lblrol;
    private JLabel lblsald;
    private BotonSimple logout;
    private BotonSimple saldo;
    private BotonSimple administrarMenu;
    private BotonSimple ccb;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CuentaUSGUI view = new CuentaUSGUI();
            view.crearVentana();
        });
    }

    public CuentaUSGUI() {
        crearVentana();
    }

    public JPanel crearVentana() {
        // Frame
        // JFrame frame = FrameStyle.crearFramePrincipal("COMGEST-UCV");

        // Panel Principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setSize(800, 600);
        panel.setOpaque(false);

        // LOGO
        ImageIcon logoIcon = new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png"));
        // PARA REDIMENSIONAR
        Image imgEscalada = logoIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        ImageIcon Redimensionado = new ImageIcon(imgEscalada);
        // CREAMOS EL JLABEL YA CON LA IMAGEN
        JLabel imagenlabel = new JLabel(Redimensionado);

        // Panel Imagen
        JPanel panelim = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // ESCOJO EL GDL
        panelim.setOpaque(false);
        panelim.add(imagenlabel, BorderLayout.CENTER);

        // Agregar el panel imagen al panel principal
        panel.add(panelim, BorderLayout.NORTH);

        // Panel Para Botones

        // Botones para todos los roles

        JPanel botones = new JPanel();
        botones.setSize(800, 600);
        botones.setLayout(new GridBagLayout()); // ESCOJO EL GDL
        GridBagConstraints gdc = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR
        gdc.insets = new Insets(10, 10, 10, 100);
        botones.setOpaque(false);
        botones.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0)); // PARA SUBIRLOS A LA ALTURA DEL PERFIL

        administrarMenu = new BotonSimple("Administrar Menù");
        gdc.gridx = 0;
        gdc.gridy = 1;
        gdc.weightx = 0.0;
        gdc.weighty = 0.0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(administrarMenu, gdc);

        ccb = new BotonSimple("CCB");
        gdc.gridx = 0;
        gdc.gridy = 3;
        gdc.weightx = 0.0;
        gdc.weighty = 0.0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(ccb, gdc);

          // Boton Ver Saldo
        saldo = new BotonSimple("Recargar Saldo");
        gdc.gridx = 0;
        gdc.gridy = 2;
        gdc.weightx = 0.0;
        gdc.weighty = 0.0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(saldo,gdc);

        setAdminVisible(false);

        // PANEL DE LA FOTO DE PERFIL (ESTA CARGADA UNA FOTO GENERICA)

        JPanel perfil = new JPanel();
        // perfil.setSize(300,200);
        perfil.setLayout(new GridBagLayout());
        GridBagConstraints gpc = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR
        perfil.setOpaque(false);

        // imagen de perfil (AQUI DEBERIA ESTAR LA FUNCION PARA BUSCAR LA IMAGEN)
        ImageIcon PerfilIcon = new ImageIcon(LoginGUI.class.getResource("resources/perfilgenerico.png"));
        // PARA REDIMENSIONAR
        Image perfilEsca = PerfilIcon.getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH);
        ImageIcon Redimen = new ImageIcon(perfilEsca);
        // CREAMOS EL JLABEL YA CON LA IMAGEN
        JLabel perfilLabel = new JLabel(Redimen);
        perfilLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // BORDE PARA MARCAR LA IMAGEN

        // TEXTO CON ACCION PARA CAMBIAR LA FOTO

        JLabel lblchangephoto = new JLabel("Cambiar");
        lblchangephoto.setLayout(new GridBagLayout());
        lblchangephoto.setForeground(Color.BLUE);
        lblchangephoto.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblchangephoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "BUSCA LA FOTO");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Efecto visual: Poner negrita o subrayar al pasar el mouse
                lblchangephoto.setText("<html><u>Cambiar</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quitar el subrayado al salir
                lblchangephoto.setText("Cambiar");
            }
        });

        // AÑADO EL JLABEL PARA CAMBIAR LA FOTO AL PANEL DEL PERFIL
        gpc.gridx = 0;
        gpc.gridy = 0;
        gpc.weightx = 0.0;
        gpc.weighty = 0.0;
        gpc.anchor = GridBagConstraints.CENTER;
        gpc.insets = new Insets(0, 85, 15, 0);
        perfil.add(lblchangephoto, gpc);

        // AÑADIMOS LA IMAGEN AL PANEL DE PERFL
        gpc.gridx = 0;
        gpc.gridy = 0;
        gpc.weightx = 0.0;
        gpc.weighty = 0.0;
        gpc.anchor = GridBagConstraints.CENTER;
        gpc.insets = new Insets(10, 100, 100, 10);
        gpc.gridheight = 5; // COMO LA IMAGEN ES TAN GRANDE UTILIZAMOS 5 FILAS ES PARA QUE LOS DATOS DE LA
                            // DERECHA NO SE BAJEN.
        perfil.add(perfilLabel, gpc);

        // Datos del usuario

        // DATO NOMBRE
        lblNombre = new JLabel("Juan Pérez");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setForeground(Color.BLACK);
        gpc.gridx = 1;
        gpc.gridy = 0;
        gpc.weightx = 0.0;
        gpc.weighty = 0.0;
        gpc.insets = new Insets(40, 0, 0, 0); // MUEVO EL TOP PARA QUE EMPUJE AL DE ABAJO Y QUEDEN EN EL MEDIO
        gpc.anchor = GridBagConstraints.NORTHWEST;
        gpc.gridheight = 1; // LUEGO PARA LOS CAMPOS DE NOMBRE Y REGISTER LO VOLVEMOS A LA NORMALIDAD
        // AÑADIMOS EL DATO DEL NOMBRE AL PANEL PERFIL
        perfil.add(lblNombre, gpc);

        // DATO CORREO
        lblcorreo = new JLabel("juanpepito@gmail.com"); // CORREO CAMBIAR PARA QUE APAREZCA
        lblcorreo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblcorreo.setForeground(Color.BLACK);
        gpc.gridx = 1;
        gpc.gridy = 1;
        gpc.weightx = 0.0;
        gpc.weighty = 0.0;
        gpc.insets = new Insets(0, 0, 0, 0);
        gpc.anchor = GridBagConstraints.NORTHWEST;
        // AÑADIMOS EL DATO DEL CORREO AL PANEL PERFIL
        perfil.add(lblcorreo, gpc);

        // DATO cedula
        int cedula = 12343123; // CEDULA CAMBIAR PARA QUE APAREZCA
        lblced = new JLabel("CI: " + cedula);
        lblced.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblced.setForeground(Color.BLACK);
        gpc.gridx = 1;
        gpc.gridy = 2;
        gpc.weightx = 0.0;
        gpc.weighty = 0.0;
        gpc.insets = new Insets(0, 0, 0, 0);
        gpc.anchor = GridBagConstraints.NORTHWEST;
        // AÑADIMOS EL DATO DE cedula AL PANEL PERFIL
        perfil.add(lblced, gpc);

        // DATO ROL
        String rolsito = "Usuario";
        lblrol = new JLabel(rolsito);
        lblrol.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblrol.setForeground(Color.BLACK);
        gpc.gridx = 1;
        gpc.gridy = 3;
        gpc.weightx = 0.0;
        gpc.weighty = 0.0;
        gpc.insets = new Insets(0, 0, 0, 0);
        gpc.anchor = GridBagConstraints.NORTHWEST;
        // AÑADIMOS EL DATO DEL ROL AL PANEL PERFIL
        perfil.add(lblrol, gpc);

        //DATO SALDO
       int saldo = 123; // SALDO X
        lblsald = new JLabel("Saldo: " + saldo);
        lblsald.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblsald.setForeground(Color.BLACK);
        gpc.gridx = 1;
        gpc.gridy = 4;
        gpc.weightx = 0.0;
        gpc.weighty = 0.0;
        gpc.insets = new Insets(0, 0, 0, 0);
        gpc.anchor = GridBagConstraints.NORTHWEST;
        // AÑADIMOS EL DATO DE saldo AL PANEL PERFIL
        perfil.add(lblsald, gpc);

        // //PANEL INFERIOR PARA PODER AREGAR EL PANEL DEL BOTON DE logout  Y DE PANEL INFERIOR,

        JPanel panelSurContenedor = new JPanel(new BorderLayout());
        panelSurContenedor.setOpaque(false);

        //Esta es la fila que contendrá ambos botones
        JPanel filaBotones = new JPanel(new BorderLayout());
        filaBotones.setOpaque(false);
        //Añadimos un pequeño margen para que los botones no toquen los bordes
        filaBotones.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10)); 

        //Botón Logout Izquierda
        logout = new BotonSimple("Logout");
        logout.setPreferredSize(new Dimension(150, 50));
        filaBotones.add(logout, BorderLayout.WEST);

        //Panel Inferior PUI
        PanelInferiorPUI PanelAbajo = new PanelInferiorPUI();

        //Montamos todo en el contenedor sur
        panelSurContenedor.add(filaBotones, BorderLayout.NORTH); // Los botones arriba
        panelSurContenedor.add(PanelAbajo, BorderLayout.SOUTH);  // La barra abajo

        //Agregamos el contenedor al panel principal
        panel.add(panelSurContenedor, BorderLayout.SOUTH);

        // AÑADIMOS EL PANEL DE PERFIL Y DE BOTONES AL PANEL PRINCIPAL
        panel.add(perfil, BorderLayout.WEST);
        panel.add(botones, BorderLayout.EAST);
        // AÑADIMOS EL PANEL PRINCIPAL AL FRAME
        // frame.add(panel, BorderLayout.CENTER);
        // frame.setVisible(true);
        return panel;
    }

    public void asignarControlador(CuentaUSController controller) {
        logout.setActionCommand(CuentaUSController.ACTION_LOGOUT);
        logout.addActionListener(controller);

        saldo.setActionCommand(CuentaUSController.ACTION_VER_SALDO);
        saldo.addActionListener(controller);

        if (administrarMenu != null) {
            administrarMenu.setActionCommand(CuentaUSController.ACTION_ADMIN_MENU);
            administrarMenu.addActionListener(controller);
        }

        if (ccb != null) {
            ccb.setActionCommand(CuentaUSController.ACTION_CCB);
            ccb.addActionListener(controller);
        }
    }

    public void cargarDatos(String nombre, String correo, String cedula, String rol, boolean isAdmin, String Saldo) {
        setNombre(nombre);
        setCorreo(correo);
        setCedula(cedula);
        setRol(rol);
        setAdminVisible(isAdmin);
        setSaldo(Saldo);
    }

    public void setAdminVisible(boolean visible) {
        if (administrarMenu != null) {
            administrarMenu.setVisible(visible);
        }
        if (ccb != null) {
            ccb.setVisible(visible);
        }
    }


    public void setSaldo(String Saldo) {
        if (Saldo == null || Saldo.isEmpty()) {
            lblsald.setText("");
        } else {
            lblsald.setText("Saldo: " + Saldo);
        }
    }

    public void setNombre(String nombre) {
        lblNombre.setText(nombre == null ? "" : nombre);
    }

    public void setCorreo(String correo) {
        lblcorreo.setText(correo == null ? "" : correo);
    }

    public void setCedula(String cedula) {
        if (cedula == null || cedula.isEmpty()) {
            lblced.setText("");
        } else {
            lblced.setText("CI: " + cedula);
        }
    }

    public void setRol(String rol) {
        lblrol.setText(rol == null ? "" : rol);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

}
