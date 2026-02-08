package comgest.view;

import javax.swing.*;

import comgest.view.components.BotonSimple;
import comgest.view.components.FrameStyle;
import comgest.view.components.Panel_Inferior_PUI;

import java.awt.*;
import java.awt.event.*;

public class CuentaUSGUI {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            crearVentana();
        });
    }

    public static void crearVentana() {

        boolean Admin = true; // ROL DE MIENTRAS PARA CAMBIAR
        // Frame
        JFrame frame = FrameStyle.crearFramePrincipal("COMGEST-UCV");

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

        // Boton logout
        BotonSimple logout = new BotonSimple("Logout");
        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.weightx = 0.0;
        gdc.weighty = 0.0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(logout, gdc);

        // Accion del boton logout
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Saliendo...");
            }
        });

        // Boton Ver Saldo
        BotonSimple saldo = new BotonSimple("Ver Saldo");
        gdc.gridx = 0;
        gdc.gridy = 1;
        gdc.weightx = 0.0;
        gdc.weighty = 0.0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(saldo, gdc);

        // Accion del boton ver saldo
        saldo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Saldo Actual:0$");
            }
        });

        // Botones para admin
        if (Admin) {

            BotonSimple AdministrarMenu = new BotonSimple("Administrar Menù");
            gdc.gridx = 0;
            gdc.gridy = 2;
            gdc.weightx = 0.0;
            gdc.weighty = 0.0;
            gdc.anchor = GridBagConstraints.CENTER;
            botones.add(AdministrarMenu, gdc);

            // Accion del boton ver saldo
            AdministrarMenu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "Cargando Menu...");
                }
            });

            BotonSimple CCB = new BotonSimple("CCB");
            gdc.gridx = 0;
            gdc.gridy = 3;
            gdc.weightx = 0.0;
            gdc.weighty = 0.0;
            gdc.anchor = GridBagConstraints.CENTER;
            botones.add(CCB, gdc);

            // Accion del boton ver saldo
            CCB.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "Cargando...");
                }
            });

        }

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
        gpc.gridheight = 4; // COMO LA IMAGEN ES TAN GRANDE UTILIZAMOS 4 FILAS ES PARA QUE LOS DATOS DE LA
                            // DERECHA NO SE BAJEN.
        perfil.add(perfilLabel, gpc);

        // Datos del usuario

        // DATO NOMBRE
        JLabel lblNombre = new JLabel("Juan Pérez");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setForeground(Color.BLACK);
        gpc.gridx = 1;
        gpc.gridy = 0;
        gpc.weightx = 0.0;
        gpc.weighty = 0.0;
        gpc.insets = new Insets(50, 0, 0, 0); // MUEVO EL TOP PARA QUE EMPUJE AL DE ABAJO Y QUEDEN EN EL MEDIO
        gpc.anchor = GridBagConstraints.NORTHWEST;
        gpc.gridheight = 1; // LUEGO PARA LOS CAMPOS DE NOMBRE Y REGISTER LO VOLVEMOS A LA NORMALIDAD
        // AÑADIMOS EL DATO DEL NOMBRE AL PANEL PERFIL
        perfil.add(lblNombre, gpc);

        // DATO CORREO
        JLabel lblcorreo = new JLabel("juanpepito@gmail.com"); // CORREO CAMBIAR PARA QUE APAREZCA
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
        JLabel lblced = new JLabel("CI:" + cedula);
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
        String rolsito = " ";
        if (Admin) {
            rolsito = "Admininistrador";
        } else {
            rolsito = "Usuario";
        }
        JLabel lblrol = new JLabel(rolsito);
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

        // AÑADIMOS EL PANEL INFERIOR
        Panel_Inferior_PUI PanelAbajo = new Panel_Inferior_PUI();
        panel.add(PanelAbajo, BorderLayout.SOUTH);

        // AÑADIMOS EL PANEL DE PERFIL Y DE BOTONES AL PANEL PRINCIPAL
        panel.add(perfil, BorderLayout.WEST);
        panel.add(botones, BorderLayout.EAST);
        // AÑADIMOS EL PANEL PRINCIPAL AL FRAME
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

}
