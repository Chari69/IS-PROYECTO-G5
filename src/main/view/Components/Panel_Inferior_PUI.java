package main.view.Components;

import javax.swing.*;

//import javax.swing.border.Border;
import java.awt.*;



public class Panel_Inferior_PUI {

   JPanel Panel_inf;
    Boton_Menu boton_menu;
    Boton_Menu boton_Micuenta;

    //Constructor
   public Panel_Inferior_PUI() {

    boton_menu = new Boton_Menu("resources/image1.png", "Menú");
    boton_Micuenta = new Boton_Menu("resources/myaccount.png", "Mi Cuenta");
    Panel_inf = new JPanel();
    Modificar_Panel_inf();
    Panel_inf.add(boton_menu);
    Panel_inf.add(boton_Micuenta);
    
   }

   public void Modificar_Panel_inf() {
       
        Panel_inf.setBackground(new Color(228,228,255));
        Panel_inf.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
   }

    //Getters
   public JPanel getPanel_inf() {
       return Panel_inf;
   }


    //Clase del boton del menu
   public class Boton_Menu extends JPanel{

    Boton_JPanel boton;
    JLabel Inferior;

    //Constructor
    public Boton_Menu(String ruta, String nombre){

        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        boton = new Boton_JPanel(ruta);
        Inferior = new JLabel(nombre);

        Modificar_Superior();
        Modificar_Inferior();
        
       
         this.add(boton.getBoton(), BorderLayout.CENTER);  // Parte clickeable (más grande)
         this.add(Inferior, BorderLayout.SOUTH);   // Texto abajo

        }

        void Modificar_Superior(){
          boton.getBoton().setBackground(new Color(228,228,255));
        
          
        }

        void Modificar_Inferior(){
            
            
            Inferior.setFont(new Font("Arial", Font.PLAIN, 20));
            Inferior.setOpaque(true);
            Inferior.setBackground(new Color(228,228,255));
            
             
        }


    }

   
}
