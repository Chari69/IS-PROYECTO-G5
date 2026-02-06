package main.view;
import javax.swing.*;
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
frame.setVisible(true);
frame.setLocationRelativeTo(null);
frame.setIconImage(new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png")).getImage());
frame.setLayout(new GridBagLayout());
frame.setResizable(false);
frame.getContentPane().setBackground(new Color(200, 185, 200));

//Panel Principal
JPanel panel = new JPanel();
panel.setLayout(new GridBagLayout()); // ESCOJO EL GDL
GridBagConstraints gdc = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR 
gdc.insets = new Insets(10, 10,10, 10);
panel.setOpaque(false);

//LOGO 
ImageIcon logoIcon = new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png"));
JLabel imagenlabel = new JLabel(logoIcon);
//Panel Imagen
JPanel panelim = new JPanel();
panel.setLayout(new GridBagLayout()); // ESCOJO EL GDL
GridBagConstraints gep = new GridBagConstraints(); // CREO MI OBJETO PARA CONTROLAR 
gep.insets = new Insets(10, 10,10, 10);
panelim.setOpaque(false);

//CONFIGURACION LUGAR PARA IMAGEN
gep.gridx = 0; 
gep.gridy = 0; // MAS ARRIBA
gep.weightx = 1.0;
gep.weighty= 1.0;
gep.anchor = GridBagConstraints.CENTER;
gep.insets = new Insets(0, 0, 0, 0);
panelim.add(imagenlabel,gep); 

//CONFIGURACION PARA PANELIM EN PANEL PRINCIPAL
gdc.gridx = 0; 
gdc.gridy = 0; // MAS ARRIBA
gdc.weightx = 0.0;
gdc.weighty= 0.0;
gdc.anchor = GridBagConstraints.NORTHWEST;

panel.add(panelim,gdc);



frame.add(panel);


    
}
}
