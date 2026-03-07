package comgest.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

import comgest.view.components.BotonPlayHolder;
import comgest.view.components.BotonSimple;
import comgest.view.components.PanelInferiorPUI;

public class RegistrarTipoUsuario extends JPanel {
    
    private BotonPlayHolder cedula;
    private JComboBox<String> comboTipo;
    private BotonPlayHolder txtPorcentaje; 
    private BotonSimple submmit;
    private PanelInferiorPUI panelInferior;
    private JPanel container;
    private JLabel lblPorcentaje;

    public RegistrarTipoUsuario() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(228, 228, 249));

        container = new JPanel(new GridBagLayout());
        container.setOpaque(false); 

        cedula = new BotonPlayHolder("ej: 32298110");
        
        // Configuración del Combo Box
        String[] tipos = {"Seleccione tipo...", "Exonerado", "Becario"};
        comboTipo = new JComboBox<>(tipos);
        
      
        lblPorcentaje = new JLabel("Porcentaje de descuento:");
        txtPorcentaje = new BotonPlayHolder("Ej: 5");
        lblPorcentaje.setVisible(false);
        txtPorcentaje.setVisible(false);

        submmit = new BotonSimple("Confirmar");
        panelInferior = new PanelInferiorPUI();
       
        ConfigurarPanel();

        this.add(container, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);

       
        comboTipo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                boolean esBecario = comboTipo.getSelectedItem().equals("Becario");
                lblPorcentaje.setVisible(esBecario);
                txtPorcentaje.setVisible(esBecario);
                revalidate();
                repaint();
            }
        });
    }


    public void configurarCombo(){
        comboTipo.setBackground(Color.WHITE);
        comboTipo.setForeground(new Color(50, 50, 50));
        comboTipo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comboTipo.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 220), 1));

        comboTipo.setFocusable(false);
        
    }


   public void ConfigurarPanel() {
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(10, 10, 5, 10); // Reduje el espacio inferior para que la etiqueta esté cerca de su campo
    c.fill = GridBagConstraints.HORIZONTAL;
    
    
    Font fuenteCabezal = new Font("SansSerif", Font.BOLD, 18);

    JLabel lblCedula = new JLabel("Cédula del Estudiante:");
    lblCedula.setFont(fuenteCabezal); 
    lblCedula.setForeground(new Color(50, 50, 80)); 
    
    c.gridx = 0; c.gridy = 0;
    container.add(lblCedula, c);
    
    c.gridy = 1;
    container.add(cedula, c);

   
    JLabel lblTipo = new JLabel("Tipo de Beneficio:");
    lblTipo.setFont(fuenteCabezal);
    
    c.gridy = 2;
    c.insets = new Insets(20, 10, 5, 10); 
    container.add(lblTipo, c);
    
    c.gridy = 3;
    c.insets = new Insets(5, 10, 5, 10);
    container.add(comboTipo, c);

   
    lblPorcentaje.setFont(fuenteCabezal); 
    
    c.gridy = 4;
    c.insets = new Insets(20, 10, 5, 10);
    container.add(lblPorcentaje, c);
    
    c.gridy = 5;
    c.insets = new Insets(5, 10, 5, 10);
    container.add(txtPorcentaje, c);

    
    c.gridy = 6;
    c.insets = new Insets(40, 10, 10, 10);
    container.add(submmit, c);
}

   

    public String getCedulaText() {
        
        return cedula.getTexto(); 
    }

    public String getTipoSeleccionado() {
        return (String) comboTipo.getSelectedItem();
    }

    public String getPorcentajeText() {
        return txtPorcentaje.isVisible() ? txtPorcentaje.getTexto() : "0";
    }

    public BotonSimple getBtnSubmmit() {
        return submmit;
    }
}