package comgest.view;

import javax.swing.*;
import java.awt.*;
import comgest.view.components.BotonPlayHolder;
import comgest.view.components.BotonSimple;
import comgest.view.components.PanelInferiorPUI;


public class Recargar_SaldoMenuUI extends JPanel {

    JPanel PanelPrincipal;
    PanelInferiorPUI panel_inferior;
    JPanel izq;
    JPanel der;
    BotonPlayHolder Referencia;
    BotonPlayHolder Monto;
    BotonSimple BotonSubmmit;
    
    public
    Recargar_SaldoMenuUI(){
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(228, 228, 249));
        PanelPrincipal = new JPanel(new BorderLayout());
        panel_inferior = new PanelInferiorPUI();
        Referencia = new BotonPlayHolder("Ej: 12345");
        Monto = new BotonPlayHolder("Ej: 150.00"); 
        BotonSubmmit = new BotonSimple("Confirmar");
        izq = new JPanel();
        der = new JPanel();

        this.add(panel_inferior, BorderLayout.SOUTH);
        this.add(PanelPrincipal, BorderLayout.CENTER);
       
        ConfigurarPanel();
        
    }

    void ConfigurarPanel() {

    PanelPrincipal.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    
    c.fill = GridBagConstraints.BOTH; 
    c.weighty = 1.0;                 

    //Panel izq
    c.weightx = 0.5;                 
    c.gridx = 0;                      
    izq.setBackground(new Color(228, 228, 249));
    PanelPrincipal.add(izq, c);

    //Panel der
    c.weightx = 0.5;                 
    c.gridx = 1;                      
    der.setBackground(new Color(228, 228, 249));    
    PanelPrincipal.add(der, c);

    configurar_izq();
    configurar_der();
    
    }

void configurar_izq(){

    izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
    izq.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 20)); 

    
    
        ImageIcon icon = new ImageIcon("src\\main\\java\\comgest\\view\\resources\\Banesco.png");
       
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(img));
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        izq.add(imagenLabel);
    

    
    izq.add(Box.createRigidArea(new Dimension(0, 20)));

    
    JLabel titulo = new JLabel("DATOS DE PAGO");
    titulo.setForeground(Color.BLACK);
    titulo.setFont(new Font("Arial", Font.BOLD, 18));
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    izq.add(titulo);

    izq.add(Box.createRigidArea(new Dimension(0, 15)));

    
    izq.add(crearEtiquetaDato("Banco: Banesco"));
    izq.add(crearEtiquetaDato("Teléfono: 0412-1234567"));
    izq.add(crearEtiquetaDato("Cédula: V-12.345.678"));
}

void configurar_der() {
    der.setLayout(new GridBagLayout());
    der.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    GridBagConstraints gbc = new GridBagConstraints();
    
 
    gbc.gridx = 0;
    gbc.fill = GridBagConstraints.NONE;      
    gbc.anchor = GridBagConstraints.CENTER; 
    gbc.weightx = 1.0;                       

    
    gbc.gridy = 0;
    gbc.weighty = 1.0;
    der.add(Box.createVerticalGlue(), gbc);
    gbc.weighty = 0.0; 

    
    JLabel label_ref = new JLabel("NÚMERO DE REFERENCIA");
    label_ref.setFont(new Font("Arial", Font.BOLD, 15));
    label_ref.setForeground(Color.BLACK);
    gbc.gridy = 1;
    gbc.insets = new Insets(5, 0, 5, 0); 
    der.add(label_ref, gbc);

   
    Referencia.setPreferredSize(new Dimension(250, 40)); 
    gbc.gridy = 2;
    gbc.insets = new Insets(0, 0, 20, 0); 
    der.add(Referencia, gbc); 

   
    JLabel lblMonto = new JLabel("MONTO A RECARGAR");
    lblMonto.setFont(new Font("Arial", Font.BOLD, 15));
    lblMonto.setForeground(Color.BLACK);
    gbc.gridy = 3;
    gbc.insets = new Insets(5, 0, 5, 0);
    der.add(lblMonto, gbc);

    
   
    Monto.setPreferredSize(new Dimension(250, 40));
    gbc.gridy = 4;
    gbc.insets = new Insets(0, 0, 25, 0); 
    der.add(Monto, gbc);

    
    JPanel filaboton = new JPanel();
    filaboton.setOpaque(false);
    gbc.gridy = 5;
    gbc.insets = new Insets(0, 0, 0, 0);
    der.add(filaboton, gbc);
    
    
    BotonSubmmit.setPreferredSize(new Dimension(120, 35)); 
    filaboton.add(BotonSubmmit);

    
    gbc.gridy = 6;
    gbc.weighty = 1.0;
    der.add(Box.createVerticalGlue(), gbc);
}

// Función auxiliar para estilo de etiquetas
private JLabel crearEtiquetaDato(String texto) {
    JLabel label = new JLabel(texto);
    label.setForeground(Color.BLACK);
    label.setFont(new Font("Arial", Font.PLAIN, 15));
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
    return label;

}

String get_Referencia(){
    return Referencia.getTexto();
}
String get_Monto(){
    return Monto.getTexto();

}


}

