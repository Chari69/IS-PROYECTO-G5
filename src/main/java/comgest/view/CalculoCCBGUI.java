package comgest.view;

import javax.swing.*;
import comgest.controller.CalculoCCBController;
import comgest.view.components.BotonPlayHolder;
import comgest.view.components.BotonSimple;
import comgest.view.components.FrameStyle;
import comgest.view.components.PanelInferiorPUI;

import java.awt.event.*;
import java.awt.*;

public class CalculoCCBGUI {
    // Componentes de entrada
    private BotonPlayHolder campoCosFijos;
    private BotonPlayHolder campoCosteosVariables;
    private BotonPlayHolder campoBandejas;
    private BotonPlayHolder campoMerma;
    private JLabel labelCCBResultado;
    private BotonSimple botonGuardarCalcular;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculoCCBGUI().crearVentana();
        });
    }

    public JPanel crearVentana() {

        // // Frame
        // JFrame frame = FrameStyle.crearFramePrincipal("COMGEST-UCV");

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
        panel.add(fant, BorderLayout.EAST);

        // Agregar Panel Para Campos de texto
        JPanel botones = new JPanel();
        botones.setLayout(new GridBagLayout());
        GridBagConstraints gdc = new GridBagConstraints();
        botones.setOpaque(false);
        gdc.insets = new Insets(10, 10, 10, 10);

        // Caja CF
        campoCosFijos = new BotonPlayHolder("Costos fijos totales (Bs)");
        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(campoCosFijos, gdc);
        // Caja CV
        campoCosteosVariables = new BotonPlayHolder("Costos variables (Bs)");
        gdc.gridx = 0;
        gdc.gridy = 1;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(campoCosteosVariables, gdc);
        // Caja Bandejas
        campoBandejas = new BotonPlayHolder("Cantidad de bandejas");
        gdc.gridx = 0;
        gdc.gridy = 2;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(campoBandejas, gdc);
        // Caja merma
        campoMerma = new BotonPlayHolder("Porcentaje de merma (%)");
        gdc.gridx = 0;
        gdc.gridy = 3;
        gdc.weightx = 1.0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(campoMerma, gdc);

        // caja invisible para que se lleve el focus

        JTextField invis = new JTextField("Hi");
        invis.setBackground(new Color(0, 0, 0, 0));
        invis.setBorder(null);
        invis.setOpaque(false);
        panel.add(invis, BorderLayout.CENTER);

        // Agregar boton de confirmar calculo al panel de botones

        botonGuardarCalcular = new BotonSimple("Guardar y Calcular");
        botonGuardarCalcular.setPreferredSize(new Dimension(250, 50));
        botonGuardarCalcular.setActionCommand(CalculoCCBController.ACTION_CALCULAR);
        gdc.gridx = 0;
        gdc.gridy = 5;
        gdc.weightx = 0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(botonGuardarCalcular, gdc);

        // CBB texto
        labelCCBResultado = new JLabel("CCB Actual: 0.00 Bs"); // AQUI VA LA VARIABLE
        labelCCBResultado.setOpaque(true);
        labelCCBResultado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelCCBResultado.setPreferredSize(new Dimension(200, 30));
        labelCCBResultado.setBackground(new Color(255, 255, 255));
        labelCCBResultado.setForeground(new Color(100, 100, 100));
        labelCCBResultado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelCCBResultado.setMaximumSize(new Dimension(250, 50)); // Para evitar que me lo cambian despues
        labelCCBResultado.setMinimumSize(new Dimension(250, 50));
        labelCCBResultado.setHorizontalAlignment(SwingConstants.CENTER);// TEXTO CENTRADO
        labelCCBResultado.setVerticalAlignment(SwingConstants.CENTER); // TEXTO CENTRADO

        gdc.gridx = 0;
        gdc.gridy = 4;
        gdc.weightx = 0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.CENTER;
        botones.add(labelCCBResultado, gdc);

        // AÑADIMOS EL PANEL INFERIOR
        PanelInferiorPUI PanelAbajo = new PanelInferiorPUI();
        panel.add(PanelAbajo, BorderLayout.SOUTH);

        // Agregar el panel botones al principal
        panel.add(botones, BorderLayout.CENTER);
        // Agregar el panel principal al frame
        // frame.add(panel, BorderLayout.CENTER);
        // frame.setVisible(true);
        return panel;

    }

    // Métodos para obtener los valores de los campos de entrada
    public double getCostosFijos() {
        return campoCosFijos.getValue();
    }

    public double getCostosVariables() {
        return campoCosteosVariables.getValue();
    }

    public double getNumeroBandejas() {
        return campoBandejas.getValue();
    }

    public double getPorcentajeMerma() {
        return campoMerma.getValue();
    }

    // Método para actualizar el resultado del CCB en la vista
    public void actualizarCCBResultado(double ccbValor) {
        labelCCBResultado.setText(String.format("CCB Actual: %.2f Bs", ccbValor));
    }

    // Método para asignar el controlador a los botones
    public void asignarControlador(CalculoCCBController controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("El controlador no puede ser null");
        }
        botonGuardarCalcular.addActionListener(controlador);
    }

    // Método para mostrar mensajes al usuario
    public void showMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
