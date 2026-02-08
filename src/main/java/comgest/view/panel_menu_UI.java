package comgest.view;

import javax.swing.*;

import comgest.view.Utils.Estilo_Boton_Sistema;
import comgest.view.components.Boton_JPanel;

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

        // Panel izq Logo
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBackground(new Color(228, 228, 255)); // Mismo color de fondo que el contenedor
        panelIzquierdo.setPreferredSize(new Dimension(100, 400));

        ImageIcon iconoOriginal = new ImageIcon(panel_menu_UI.class.getResource("resources/IconoPrincipal.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel labelImg = new JLabel(new ImageIcon(imagenEscalada));
        labelImg.setHorizontalAlignment(SwingConstants.CENTER);
        panelIzquierdo.add(labelImg, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.weightx = 0.1; 
        this.add(panelIzquierdo, gbc);

       
       
       
        //Tarjetas agregar

      
        gbc.gridx = 1;
        gbc.weightx = 0.4;
        Panel_Agregar = new JPanel();
        configurarTarjeta(Panel_Agregar);
        this.add(Panel_Agregar, gbc);

        Panel_Agregar.setVisible(false);

        gbc.gridx = 2;
        Panel_Agregar2 = new JPanel();
        configurarTarjeta(Panel_Agregar2);
        this.add(Panel_Agregar2, gbc);

        Panel_Agregar2.setVisible(false);



       // Tarjeta 1: Desayuno
        
        gbc.gridx = 1;
        gbc.weightx = 0.4;
        Panel_Desayuno = new JPanel();
        configurarTarjeta(Panel_Desayuno, "Desayuno", "Descripción del desayuno", "Horario: 7:00 - 10:00",  "resources/arepa.jpg", 17);
        this.add(Panel_Desayuno, gbc);
      // Panel_Desayuno.setVisible(false);



        // Tarjeta 2: Almuerzo

        Panel_Almuerzo = new JPanel();
        configurarTarjeta(Panel_Almuerzo, "Almuerzo", "Descripción del almuerzo", "Horario: 12:00 - 15:00", "resources/images.jpg", 18);
        gbc.gridx = 2;
        gbc.weightx = 0.4; // Más espacio para los paneles principales
       
        this.add(Panel_Almuerzo, gbc);
       //Panel_Almuerzo.setVisible(false);

        // **Panel derecho (similar al izquierdo)**
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(228, 228, 249));
        panelDerecho.setPreferredSize(new Dimension(100, 400));
        gbc.gridx = 3;
        gbc.weightx = 0.1;
        this.add(panelDerecho, gbc);
    }

    void configurarTarjeta(JPanel panel, String titulo, String descripcion, String Horario, String imagenRuta, double precio) {

        // Estética
        panel.setPreferredSize(new Dimension(250, 400));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        // Componentes internos
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Fila1
        JPanel fila1 = new JPanel();
        panel.add(fila1);
        fila1.setBackground(Color.WHITE);
        fila1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Configuración de la fila1
        fila1.setLayout(new GridLayout(2, 1));
        // Labels
        JLabel tituloLabel = new JLabel(titulo, JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 25));
        tituloLabel.setAlignmentY(Component.LEFT_ALIGNMENT);
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel tituLabel2 = new JLabel(Horario, JLabel.CENTER);
        tituLabel2.setFont(new Font("Arial", Font.ITALIC, 16));
        tituLabel2.setAlignmentY(Component.LEFT_ALIGNMENT);

        // Agregar los labels a la fila1
        fila1.add(tituloLabel);
        fila1.add(tituLabel2);

        // Fila2


        JPanel fila2 = new JPanel();

        // CAMBIO 1: Usar BorderLayout para que el label pueda ocupar todo
        fila2.setLayout(new BorderLayout());

        // Configurar tamaños para BoxLayout
        fila2.setPreferredSize(new Dimension(300, 150));
        fila2.setMaximumSize(new Dimension(Short.MAX_VALUE, 150));
        fila2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.add(fila2);

        // Cargar imagen
        ImageIcon iconoOriginal = new ImageIcon(panel_menu_UI.class.getResource(imagenRuta));

        // CAMBIO 2: Escalar la imagen manualmente
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        JLabel labelImg = new JLabel(new ImageIcon(imagenEscalada));
        labelImg.setLayout(new BorderLayout());

        JButton hola = new JButton("Cambiar");
        Estilo_Boton_Sistema.aplicarEstiloPrincipal(hola);
        hola.setFont(new Font("Arial", Font.BOLD,11));
        

    
        JPanel contenedor2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        contenedor2.setOpaque(false);
        contenedor2.add(hola);
        labelImg.add(contenedor2);


        labelImg.add(contenedor2, BorderLayout.SOUTH);

        // Agregar al centro
        fila2.add(labelImg, BorderLayout.CENTER);
        

        // Fila3

JPanel fila3 = new JPanel();
fila3.setBackground(Color.WHITE);
fila3.setLayout(new BorderLayout());

// Borde y padding
fila3.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.BLACK, 1, true),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

// 1. El texto (Centro)
JTextArea descArea = new JTextArea(descripcion);
descArea.setLineWrap(true);
descArea.setWrapStyleWord(true);
descArea.setEditable(false);
descArea.setOpaque(false);
descArea.setFont(new Font("Arial", Font.PLAIN, 13));
fila3.add(descArea, BorderLayout.CENTER);


// --- Contenedor para la parte Inferior (Sur) ---

JPanel contenedorInferior = new JPanel(new BorderLayout());
contenedorInferior.setOpaque(false);

    // 2. Sub-panel IZQUIERDA (Controles de Admin)
    JPanel panelIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
    panelIzquierda.setOpaque(false);
    
    // Configuración de botones admin (Basura y Editar)
 
    Boton_JPanel Basura = new Boton_JPanel("resources/papelera.jpg");
    Boton_JPanel Editable = new Boton_JPanel("resources/lapiz.png");
    

    Basura.getBoton().setBackground(Color.WHITE);
    Editable.getBoton().setBackground(Color.WHITE);

    panelIzquierda.add(Basura.getBoton());
    panelIzquierda.add(Editable.getBoton());
   

    //Logica de visibilidad (aqui deben implementar con el controlador cuando se logueen con admin)
    Boolean admin = true;

    if(!admin){
        panelIzquierda.setVisible(false);
    }


    JPanel panelBotonDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
    panelBotonDerecha.setOpaque(false);

    JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setOpaque(false);

    JLabel Precio = new JLabel("Precio:"+" 16$", SwingConstants.CENTER);
    Precio.setFont(new Font("Arial", Font.BOLD, 15));
    
    
    JButton boton = new JButton("Ordenar");
    Estilo_Boton_Sistema.aplicarEstiloPrincipal(boton);
    
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
        
        Boton_JPanel AddBotton1 = new Boton_JPanel("resources/AñadirMenú.png", 135,125);
        panel.add(AddBotton1.getBoton(), BorderLayout.CENTER);
       
      

    }

}
