package comgest.view;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import comgest.view.Utils.JTextArea_Personalizado;
import comgest.view.Utils.JTextPane_Personalizado;
import comgest.view.components.*;

import java.awt.event.MouseEvent;


//import javax.swing.border.Border;
import java.awt.*;


public class panel_menu_UI extends JPanel {

    JPanel Panel_Desayuno;
    JPanel Panel_Almuerzo;

    JPanel Panel_Agregar;
    JPanel Panel_Agregar2;
    

    // Constructor
    public panel_menu_UI() {

        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(228, 228, 249));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.fill = GridBagConstraints.VERTICAL;

        //PANEL ICONO COMEDOR
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBackground(new Color(228, 228, 255)); 
        panelIzquierdo.setPreferredSize(new Dimension(100, 400));

        ImageIcon iconoOriginal = new ImageIcon(panel_menu_UI.class.getResource("resources/IconoPrincipal.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel labelImg = new JLabel(new ImageIcon(imagenEscalada));
        labelImg.setHorizontalAlignment(SwingConstants.CENTER);
        panelIzquierdo.add(labelImg, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.weightx = 0.1; 
        this.add(panelIzquierdo, gbc);

       
       
        //TARJETAS

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.weightx = 0.4;
        Panel_Agregar = new JPanel();
        Boton_JPanel AddBotton1 = new Boton_JPanel("resources/AñadirMenú.png", 135,125);
        Panel_Agregar.add(AddBotton1.getBoton(), BorderLayout.CENTER);
        AddBotton1.getBoton().addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            System.out.println("Clic en menu1");
           
        }
     });

        configurarTarjeta(Panel_Agregar);
        this.add(Panel_Agregar, gbc);
         //VISIBILIDAD DEL BOTON AGREGAR1
        Panel_Agregar.setVisible(false);

       

        gbc.gridx = 2;
        Panel_Agregar2 = new JPanel();
         Boton_JPanel AddBotton2 = new Boton_JPanel("resources/AñadirMenú.png", 135,125);
        Panel_Agregar2.add(AddBotton2.getBoton(), BorderLayout.CENTER);
        AddBotton2.getBoton().addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            System.out.println("Clic en menu2");
           
        }
     });
        configurarTarjeta(Panel_Agregar2);
        this.add(Panel_Agregar2, gbc);
         //VISIBILIDAD DEL BOTON AGREGAR2
        Panel_Agregar2.setVisible(false);


         gbc.fill = GridBagConstraints.BOTH;
       // Tarjeta 1: Desayuno
        gbc.gridx = 1;
        gbc.weightx = 0.4;
        Panel_Desayuno = new JPanel();
        configurarTarjeta(Panel_Desayuno, "Desayuno", "Descripción del desayuno", "Horario: 7:00 - 10:00",  "resources/arepa.jpg", 17);
        this.add(Panel_Desayuno, gbc);

        //VISIBILIDAD DEL PANEL DESAYUNO
        Panel_Desayuno.setVisible(true);


        // Tarjeta 2: Almuerzo
        Panel_Almuerzo = new JPanel();
        configurarTarjeta(Panel_Almuerzo, "Almuerzo", "Descripción del almuerzo", "Horario: 12:00 - 15:00", "resources/images.jpg", 18);
        gbc.gridx = 2;
        gbc.weightx = 0.4;
        this.add(Panel_Almuerzo, gbc);

        //VISIBILIDAD DEL PANEL ALMUERZO
        Panel_Almuerzo.setVisible(true);

        //panel derecho
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(228, 228, 249));
        panelDerecho.setPreferredSize(new Dimension(100, 400));
        gbc.gridx = 3;
        gbc.weightx = 0.1;
        this.add(panelDerecho, gbc);

    }

    
    public static JPanel CrearVentana(JFrame Leonardo){
        
        JPanel vp = new JPanel(new BorderLayout());

        Panel_Inferior_PUI panel = new Panel_Inferior_PUI();
        vp.add(panel, BorderLayout.SOUTH);
        panel_menu_UI panel_menu = new panel_menu_UI();
        vp.add(panel_menu, BorderLayout.CENTER);
        vp.revalidate();
        vp.repaint();
        Leonardo.add(vp);

        return vp;
        
    }

    void configurarTarjeta(JPanel panel, String titulo, String descripcion, String Horario, String imagenRuta, double precio) {

        // Estética
        panel.setPreferredSize(new Dimension(250, 400));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        // Componentes internos
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));



        //FILA1//
        JPanel fila1 = new JPanel();
        panel.add(fila1);
        fila1.setBackground(Color.WHITE);
        fila1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Configuración de la fila1
        fila1.setLayout(new GridLayout(2, 1));

        // Labels
        JTextPane tituloCentrado = new JTextPane();
        JTextPane_Personalizado.Centrar(tituloCentrado, titulo);
        tituloCentrado.setEditable(false); //ESTO ES PARA EDITAR EL TITULO
        tituloCentrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tituloCentrado.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.BLACK));
        tituloCentrado.setBackground(new Color(200, 177, 200));
        fila1.add(tituloCentrado);




        JTextArea tituloLabel2 = new JTextArea(Horario);
        JTextArea_Personalizado.AplicarPersonalización(tituloLabel2);
        tituloLabel2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        

        // Agregar los labels a la fila1
        fila1.add(tituloCentrado);
        fila1.add(tituloLabel2);




        //FILA2

        JPanel fila2 = new JPanel();

        
        fila2.setLayout(new BorderLayout());

        
        fila2.setPreferredSize(new Dimension(300, 150));
        fila2.setMaximumSize(new Dimension(Short.MAX_VALUE, 150));
        fila2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.add(fila2);

        // CARGAR IMAGEN
        ImageIcon iconoOriginal = new ImageIcon(panel_menu_UI.class.getResource(imagenRuta));

        // CAMBIO 2: Escalar la imagen manualmente
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        JLabel labelImg = new JLabel(new ImageIcon(imagenEscalada));
        labelImg.setLayout(new BorderLayout());



        //BOTON CAMBIAR IMAGEN
        BotonSimple hola = new BotonSimple("Cambiar");
        hola.setPreferredSize(new Dimension(60,30));
        hola.setFont(new Font("Segoe UI", Font.BOLD, 13));

        hola.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("hola");
            }
        });

        JPanel contenedor2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        contenedor2.setOpaque(false);
        contenedor2.add(hola);

        labelImg.add(contenedor2);
        labelImg.add(contenedor2, BorderLayout.SOUTH);
        fila2.add(labelImg, BorderLayout.CENTER);
        

        //FILA3

        JPanel fila3 = new JPanel();
        fila3.setBackground(Color.WHITE);
        fila3.setLayout(new BorderLayout());

        // Borde y padding
        fila3.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // 1. El texto (Centro)
        JTextArea descArea = new JTextArea(descripcion);
        JTextArea_Personalizado.AplicarPersonalización(descArea);
        fila3.add(descArea, BorderLayout.CENTER);


        // Contenedor para la parte Inferior

        JPanel contenedorInferior = new JPanel(new BorderLayout());
        contenedorInferior.setOpaque(false);

    // 2. Sub-panel IZQUIERDA (Controles de Admin)
    JPanel panelIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
    panelIzquierda.setOpaque(false);
    
    // Configuración de botones admin (Basura y Editar)
 
    Boton_JPanel Basura = new Boton_JPanel("resources/papelera.jpg");
     Basura.getBoton().addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            System.out.println("Clic Basura");
           
        }
    });
    Boton_JPanel Editable = new Boton_JPanel("resources/lapiz.png");
     Editable.getBoton().addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            System.out.println("Clic Editable");
           
        }
    });
    
    

    Basura.getBoton().setBackground(Color.WHITE);
    Editable.getBoton().setBackground(Color.WHITE);

    panelIzquierda.add(Basura.getBoton());
    panelIzquierda.add(Editable.getBoton());

    JPanel panelBotonDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
    panelBotonDerecha.setOpaque(false);

    JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setOpaque(false);

    JLabel Precio = new JLabel("Precio:"+" 16$", SwingConstants.CENTER);
    Precio.setFont(new Font("Segoe UI", Font.BOLD, 15));
    
    
    BotonSimple boton = new BotonSimple("Ordenar");
    boton.setPreferredSize(new Dimension(40,30));
    boton.setFont(new Font("Segoe UI", Font.BOLD, 15));
    boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("ordenar");
            }
        });
    
    
    contenedor.add(boton, BorderLayout.SOUTH);
    contenedor.add(Precio, BorderLayout.CENTER);
    panelBotonDerecha.add(contenedor);

    

    contenedorInferior.add(panelIzquierda, BorderLayout.WEST);
    contenedorInferior.add(panelBotonDerecha, BorderLayout.EAST);


    fila3.add(contenedorInferior, BorderLayout.SOUTH);


    panel.add(fila3);

    }

    

    void configurarTarjeta(JPanel panel){

        panel.setPreferredSize(new Dimension(135, 125));
        panel.setBackground(new Color(228,228,255));
        
    
    }

    public JPanel getPanel_Agregar() {
        return Panel_Agregar;
    }
    public JPanel getPanel_Agregar2() {
        return Panel_Agregar2;
    }
    public JPanel getPanel_Almuerzo() {
        return Panel_Almuerzo;
    }
    public JPanel getPanel_Desayuno() {
        return Panel_Desayuno;
    }

}
