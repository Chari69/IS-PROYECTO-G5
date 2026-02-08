package comgest.view;
import javax.swing.*;
import comgest.view.components.BotonPlayHolder;
import comgest.view.components.BotonSimple;
import comgest.view.components.FrameStyle;
import comgest.view.components.Panel_Inferior_PUI;

import java.awt.event.*;
import java.awt.*;

public class CalculoCCBGUI {
    public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
            crearVentana();
});
}


public static void crearVentana() {


//Frame
        JFrame frame = FrameStyle.crearFramePrincipal("COMGEST-UCV");
      
        // Panel Principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setSize(800, 600);
        panel.setOpaque(false);

        // LOGO
        ImageIcon logoIcon = new ImageIcon(LoginGUI.class.getResource("resources/logocompng.png"));
        // PARA REDIMENSIONAR
        Image imgEscalada = logoIcon.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
        ImageIcon Redimensionado = new ImageIcon(imgEscalada);
        // CREAMOS EL JLABEL YA CON LA IMAGEN
        JLabel imagenlabel = new JLabel(Redimensionado);

        // Panel Imagen
        JPanel panelim = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // ESCOJO EL GDL
        panelim.setOpaque(false);
        panelim.add(imagenlabel, BorderLayout.CENTER);

        // Agregar el panel imagen al panel principal
        panel.add(panelim, BorderLayout.WEST);

        // //Panel fantasma para tener simetria por la derecha.
        JPanel fant = new JPanel();
        fant.setLayout(new BorderLayout(10, 10));
        fant.setOpaque(false);
        fant.setPreferredSize(new Dimension(160, 200)); // MISMO TAMAÑO QUE LA IMAGEN
        frame.add(fant, BorderLayout.EAST);

        // Agregar Panel Para Campos de texto
        JPanel botones = new JPanel();
        botones.setLayout(new GridBagLayout());
        GridBagConstraints gdc = new GridBagConstraints();
        botones.setOpaque(false);
        gdc.insets = new Insets(10, 10, 10, 10);

        // Caja CF
        BotonPlayHolder cf = new BotonPlayHolder("Costos fijos totales (Bs)");
        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(cf, gdc);
        // Caja CV
        BotonPlayHolder cv = new BotonPlayHolder("Costos variables (Bs)");
        gdc.gridx = 0;
        gdc.gridy = 1;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(cv, gdc);
        // Caja Bandejas
        BotonPlayHolder bande = new BotonPlayHolder("Cantidad de bandejas");
        gdc.gridx = 0;
        gdc.gridy = 2;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(bande, gdc);
        // Caja merma
        BotonPlayHolder merma = new BotonPlayHolder("Porcentaje de merma (%)");
        gdc.gridx = 0;
        gdc.gridy = 3;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(merma, gdc);

        // caja invisible para que se lleve el focus

        JTextField invis = new JTextField("Hi");
        invis.setBackground(new Color(0, 0, 0, 0));
        invis.setBorder(null);
        invis.setOpaque(false);
        panel.add(invis, BorderLayout.CENTER);



        
        // Agregar boton de confirmar calculo al panel de botones


        BotonSimple bttmreg = new BotonSimple("Guardar y Calcular");
        bttmreg.setPreferredSize(new Dimension(250, 50));
        gdc.gridx = 0;
        gdc.gridy = 5;
        gdc.weightx = 0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(bttmreg, gdc);

        // Accion del boton de confirmar
        bttmreg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Calculando...");
            }
        });

        // CBB texto
        int CBB = 4; // AQUI DEBERIA ESTAR EL RESULTADO DEL CBB
        JLabel CBB_Actual = new JLabel("CBB_Actual: " + CBB + " Bs"); // AQUI VA LA VARIABLE
        CBB_Actual.setOpaque(true);
        CBB_Actual.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        CBB_Actual.setPreferredSize(new Dimension(200, 30));
        CBB_Actual.setBackground(new Color(255, 255, 255));
        CBB_Actual.setForeground(new Color(100,100,100));
        CBB_Actual.setFont(new Font("Segoe UI", Font.BOLD, 14));
        CBB_Actual.setMaximumSize(new Dimension(250, 50)); // Para evitar que me lo cambian despues
        CBB_Actual.setMinimumSize(new Dimension(250, 50));
        CBB_Actual.setHorizontalAlignment(SwingConstants.CENTER);// TEXTO CENTRADO
        CBB_Actual.setVerticalAlignment(SwingConstants.CENTER); // TEXTO CENTRADO


        gdc.gridx = 0;
        gdc.gridy = 4;
        gdc.weightx = 0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(CBB_Actual, gdc);

        //AÑADIMOS EL PANEL INFERIOR
        Panel_Inferior_PUI PanelAbajo = new Panel_Inferior_PUI();
        frame.add(PanelAbajo.getPanel_inf(),BorderLayout.SOUTH);


        // Agregar el panel botones al principal
        panel.add(botones, BorderLayout.CENTER);
        // Agregar el panel principal al frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}












