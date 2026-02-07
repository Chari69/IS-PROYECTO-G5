package main.view;
import javax.swing.*;
import main.view.components.BotonPlayHolder;
import main.view.components.BotonSimple;
import java.awt.event.*;
import java.awt.*;

public class CuentaUSGUI {
    public static void main(String[] args){

    SwingUtilities.invokeLater(() -> {
      crearVentana();
       });
    }

    public static void crearVentana() {
JFrame frame = new JFrame("SGCU");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(800, 600);
frame.setLocationRelativeTo(null);
frame.setIconImage(new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png")).getImage());
// frame.setLayout(new GridBagLayout());
frame.setLayout(new BorderLayout(10,5));
frame.setResizable(false);
frame.getContentPane().setBackground(new Color(200, 185, 200));

//Panel Principal
JPanel panel = new JPanel();
panel.setLayout(new BorderLayout(10,10));
panel.setSize(800,600);
panel.setOpaque(false);


//LOGO 
ImageIcon logoIcon = new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png"));
//PARA REDIMENSIONAR
Image imgEscalada = logoIcon.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);
ImageIcon Redimensionado = new ImageIcon(imgEscalada);
//CREAMOS EL JLABEL YA CON LA IMAGEN
JLabel imagenlabel = new JLabel(Redimensionado);


//Panel Imagen
JPanel panelim = new JPanel();
panel.setLayout(new BorderLayout(10,10)); // ESCOJO EL GDL
panelim.setOpaque(false);
panelim.add(imagenlabel,BorderLayout.CENTER); 

//Agregar el panel imagen al panel principal
panel.add(panelim,BorderLayout.NORTH);

// //Panel fantasma para tener simetria por la derecha.
JPanel fant = new JPanel();
fant.setLayout(new BorderLayout(10,10));
fant.setOpaque(false);
fant.setPreferredSize(new Dimension(100, 120)); // MISMO TAMAÑO QUE LA IMAGEN
panel.add(fant,BorderLayout.EAST);

//Panel Para Botones

JPanel botones = new JPanel();
botones.setSize(800,600);
botones.setLayout(new GridBagLayout()); // ESCOJO EL GDL
GridBagConstraints gdc = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR 
gdc.insets = new Insets(10, 10,10, 100);
botones.setOpaque(false);

//Boton logout
BotonSimple logout = new BotonSimple("Logout");
gdc.gridx = 0;
gdc.gridy = 0;
gdc.weightx = 0.0;
gdc.weighty= 0.0;
gdc.anchor = GridBagConstraints.CENTER;
botones.add(logout,gdc);
//Boton Ver Saldo
BotonSimple saldo = new BotonSimple("Ver Saldo");
gdc.gridx = 0;
gdc.gridy = 1;
gdc.weightx = 0.0;
gdc.weighty= 0.0;
gdc.anchor = GridBagConstraints.CENTER;
botones.add(saldo,gdc);

//Panel Para Foto Perfil

JPanel perfil = new JPanel();
// perfil.setSize(300,200);
perfil.setLayout(new GridLayout());
perfil.setOpaque(false);
perfil.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

//imagen de perfil
ImageIcon PerfilIcon = new ImageIcon(LoginGUI.class.getResource("resources/perfilgenerico.png"));
//PARA REDIMENSIONAR
Image perfilEsca = PerfilIcon.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);
ImageIcon Redimen = new ImageIcon(perfilEsca);
//CREAMOS EL JLABEL YA CON LA IMAGEN
JLabel perfilLabel = new JLabel(Redimen);
perfil.add(perfilLabel,BorderLayout.CENTER);









panel.add(perfil,BorderLayout.WEST);
panel.add(botones,BorderLayout.EAST);
frame.add(panel,BorderLayout.CENTER);
frame.setVisible(true);
}

}
