package main.view;
import javax.swing.*;

import main.view.components.BotonPlayHolder;
import main.view.components.BotonSimple;
import main.view.components.CampoEtiquetaUI;
import java.awt.event.*;
import java.awt.*;


public class RegisterGUI {
     public static void main(String[] args){

    SwingUtilities.invokeLater(() -> {
      crearVentana();
       });
    }

//Frame
public static void crearVentana() {
JFrame frame = new JFrame("SGCU");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(1280, 720);
frame.setLocationRelativeTo(null);
frame.setIconImage(new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png")).getImage());
frame.setLayout(new BorderLayout(10,5));
frame.setResizable(false);
frame.getContentPane().setBackground(new Color(200, 185, 200));

//Panel Principal
JPanel panel = new JPanel();
panel.setLayout(new BorderLayout(10,10));
panel.setSize(1280,720);
panel.setOpaque(false);

//LOGO 
ImageIcon logoIcon = new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png"));
//PARA REDIMENSIONAR
Image imgEscalada = logoIcon.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
ImageIcon Redimensionado = new ImageIcon(imgEscalada);
//CREAMOS EL JLABEL YA CON LA IMAGEN
JLabel imagenlabel = new JLabel(Redimensionado);


//Panel Imagen
JPanel panelim = new JPanel();
panel.setLayout(new BorderLayout(10,10)); // ESCOJO EL GDL
panelim.setOpaque(false);
panelim.add(imagenlabel,BorderLayout.CENTER); 

//Agregar el panel imagen al panel principal
panel.add(panelim,BorderLayout.WEST);

// //Panel fantasma para tener simetria por la derecha.
JPanel fant = new JPanel();
fant.setLayout(new BorderLayout(10,10));
fant.setOpaque(false);
fant.setPreferredSize(new Dimension(160, 200)); // MISMO TAMAÑO QUE LA IMAGEN
frame.add(fant,BorderLayout.EAST);




//Agregar Panel Para Campos de texto
JPanel botones = new JPanel();
botones.setLayout(new GridBagLayout());
GridBagConstraints gdc = new GridBagConstraints();
botones.setOpaque(false);
gdc.insets= new Insets(10,10,10,10);




//Caja para Nombre de Usuario
BotonPlayHolder NombreUsuario = new BotonPlayHolder("Nombre De Usuario");
gdc.gridx = 0;
gdc.gridy = 0;
gdc.weightx = 1.0;
gdc.weighty= 0;
gdc.anchor = GridBagConstraints.CENTER;
botones.add(NombreUsuario,gdc);
//Caja para correo
BotonPlayHolder Correo = new BotonPlayHolder("Correo electrónico");
gdc.gridx = 0;
gdc.gridy = 1;
gdc.weightx = 1.0;
gdc.weighty= 0;
gdc.anchor = GridBagConstraints.CENTER;
botones.add(Correo,gdc);
//Caja para contraseña 1
BotonPlayHolder contraseña1 = new BotonPlayHolder("Contraseña");
gdc.gridx = 0;
gdc.gridy = 2;
gdc.weightx = 1.0;
gdc.weighty= 0;
gdc.anchor = GridBagConstraints.CENTER;
botones.add(contraseña1,gdc);
//Caja para contraseña Confirmar
BotonPlayHolder contraseñaconf = new BotonPlayHolder("Confirmar Contraseña");
gdc.gridx = 0;
gdc.gridy = 3;
gdc.weightx = 1.0;
gdc.weighty= 0;
gdc.anchor = GridBagConstraints.CENTER;
botones.add(contraseñaconf,gdc);

//caja invisible para que se lleve el focus(bien xd)

JTextField invis = new JTextField("Hi");
invis.setBackground(new Color(0,0,0,0));
invis.setBorder(null);
invis.setOpaque(false);
panel.add(invis,BorderLayout.CENTER);



//PANEL PARA IR AL LOGIN/PREGUNTA

JPanel loginpa = new JPanel();
loginpa.setLayout(new GridBagLayout()); // ESCOJO EL GDL
GridBagConstraints gpdc = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR 
gpdc.insets = new Insets(1, 10,1, 10);
loginpa.setOpaque(false);

//TEXTO PREGUNTA
JLabel lblLOGINtxt = new JLabel("¿Ya tienes una cuenta?");
lblLOGINtxt.setForeground(Color.black);
//TEXTO PARA IR REGISTRO
JLabel lbllog= new JLabel("Click aqui para ingresar");
lbllog.setForeground(Color.BLUE);
lbllog.setCursor(new Cursor(Cursor.HAND_CURSOR));

lbllog.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "Redirigiendo a login...");
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

//Agregar IR AL LOGIN LABEL AL PANEL DE LOGIN
gpdc.gridx = 0; 
gpdc.gridy = 6; // MAS ABAJO
gpdc.weightx = 1.0;
gpdc.weighty= 0.1;
gpdc.anchor = GridBagConstraints.NORTH;
loginpa.add(lbllog,gpdc);
//Agregar texto DE PREGUNTA AL PANEL DE LOGIN
gpdc.gridx = 0; 
gpdc.gridy = 5; // MAS ABAJO
gpdc.weightx = 1.0;
gpdc.weighty= 0.1;
gpdc.anchor = GridBagConstraints.NORTH;
loginpa.add(lblLOGINtxt,gpdc);

//Agregar el Panel de ir al login al panel de botones.
gdc.gridx = 0; 
gdc.gridy = 6; // MAS ABAJO
gdc.weightx = 0.0;
gdc.weighty= 0.0;
gdc.anchor = GridBagConstraints.CENTER;
botones.add(loginpa,gdc);

//Agregar boton de confirmar register al panel de botones

//boton de confirmar registro
BotonSimple bttmreg = new BotonSimple("Registrarse");
gdc.gridx = 0;
gdc.gridy = 5;
gdc.weightx = 0;
gdc.weighty= 0;
gdc.anchor = GridBagConstraints.CENTER;
botones.add(bttmreg,gdc);

//Accion del boton de confirmar
bttmreg.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "Confirmando...");}
    }
);

//CheckBox de terminos y condiciones, lo agrego al panel de botones

JCheckBox checkTerminos = new JCheckBox("Aceptar términos y condiciones");
checkTerminos.setOpaque(false);
checkTerminos.setFont(new Font("Segoe UI", Font.BOLD, 12));
checkTerminos.setFocusable(false);
gdc.gridx = 0;
gdc.gridy = 4;
gdc.weightx = 0;
gdc.weighty = 0;
gdc.anchor = GridBagConstraints.CENTER;
botones.add(checkTerminos, gdc);




//Agregar el panel botones al principal
panel.add(botones,BorderLayout.CENTER);
//Agregar el panel principal al frame
frame.add(panel,BorderLayout.CENTER);
frame.setVisible(true);



    
}
}
