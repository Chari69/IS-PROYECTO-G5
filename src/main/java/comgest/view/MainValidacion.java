
package comgest.view;

import javax.swing.*;
import comgest.view.components.BotonSimple;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainValidacion extends JFrame {

    private BotonSimple btnConfirmar;
    private BotonSimple btnCerrar;
    private BotonSimple btnSeleccionar;
    private BotonSimple btnFinalizar;
    private BotonSimple btnBuscarCedula;
    private JTextField txtCedula;
    private JLabel lblPreview;
    private ActionListener controlador;

    // Datos de la reserva encontrada (se llenan desde el controlador)
    private String tituloReserva;
    private String descripcionReserva;
    private double precioReserva;

    public MainValidacion() {
        super("Punto de Venta - COMGEST");
        this.precioReserva = 0;
        prepararConfiguracionBase();
    }

    /**
     * Configuración básica del Frame (tamaño, icono, cierre)
     */
    private void prepararConfiguracionBase() {
        this.setBackground(new Color(228, 228, 255));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(440, 360);
        this.setResizable(true);
        this.setLocationRelativeTo(null);

        // Icono de la aplicación
        try {
            ImageIcon icon = new ImageIcon(MainValidacion.class.getResource("/comgest/view/resources/logocompng.png"));
            this.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("No se pudo cargar el logo de la ventana");
        }
    }

    /**
     * VISTA 1: Ingreso de Cédula
     */
    public void mostrarSeccionCedula() {
        Container content = getContentPane();
        content.removeAll();
        content.setLayout(new BorderLayout(15, 15));
        ((JPanel) content).setBackground(new Color(228, 228, 255));
        ((JPanel) content).setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel lblTitulo = new JLabel("PUNTO DE VENTA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        content.add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCedula = new JLabel("Ingrese la cédula del comensal:");
        lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centro.add(lblCedula, gbc);

        txtCedula = new JTextField(20);
        txtCedula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCedula.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        gbc.gridy = 1;
        centro.add(txtCedula, gbc);

        content.add(centro, BorderLayout.CENTER);

        btnBuscarCedula = new BotonSimple("BUSCAR RESERVA");
        btnBuscarCedula.setPreferredSize(new Dimension(200, 40));
        btnBuscarCedula.setFont(new Font("Segoe UI", Font.BOLD, 15));

        if (controlador != null) {
            btnBuscarCedula.setActionCommand("BUSCAR_CEDULA");
            btnBuscarCedula.addActionListener(controlador);
        }

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setOpaque(false);
        panelBoton.add(btnBuscarCedula);
        content.add(panelBoton, BorderLayout.SOUTH);

        content.revalidate();
        content.repaint();
    }

    /**
     * VISTA 2: Subida de Imagen (verificación biométrica)
     */
    public void mostrarSeccionSubida() {
        Container content = getContentPane();
        content.removeAll();
        content.setLayout(new BorderLayout(15, 15));
        ((JPanel) content).setBackground(new Color(228, 228, 255));
        ((JPanel) content).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instructions = new JLabel("VERIFICACIÓN DE IDENTIDAD", SwingConstants.CENTER);
        instructions.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnSeleccionar = new BotonSimple("Seleccionar Fotografía");
        btnSeleccionar.setPreferredSize(new Dimension(80, 35));
        btnSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        lblPreview = new JLabel("Sin foto seleccionada", SwingConstants.CENTER);
        lblPreview.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblPreview.setOpaque(true);
        lblPreview.setBackground(Color.WHITE);
        lblPreview.setPreferredSize(new Dimension(200, 150));

        btnFinalizar = new BotonSimple("VERIFICAR");
        btnFinalizar.setPreferredSize(new Dimension(80, 35));
        btnFinalizar.setFont(new Font("Segoe UI", Font.BOLD, 15));

        // Vincular con el controlador si ya existe
        if (controlador != null) {
            btnSeleccionar.setActionCommand("SELECCIONAR_FOTO");
            btnSeleccionar.addActionListener(controlador);
            btnFinalizar.setActionCommand("FINALIZAR");
            btnFinalizar.addActionListener(controlador);
        }

        JPanel centroSubida = new JPanel(new BorderLayout(10, 10));
        centroSubida.setOpaque(false);
        centroSubida.add(btnSeleccionar, BorderLayout.NORTH);
        centroSubida.add(lblPreview, BorderLayout.CENTER);

        content.add(instructions, BorderLayout.NORTH);
        content.add(centroSubida, BorderLayout.CENTER);
        content.add(btnFinalizar, BorderLayout.SOUTH);

        content.revalidate();
        content.repaint();
    }

    /**
     * VISTA 3: Detalles y Confirmación de Pago
     */
    public void mostrarSeccionDetalles() {
        Container content = getContentPane();
        content.removeAll();
        content.setLayout(new BorderLayout(10, 10));
        ((JPanel) content).setBackground(new Color(228, 228, 255));
        ((JPanel) content).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("CONFIRMAR CONSUMO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        content.add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(8, 8));
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblNombreMenu = new JLabel(tituloReserva != null ? tituloReserva : "");
        lblNombreMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        centro.add(lblNombreMenu, BorderLayout.NORTH);

        JTextArea txtDesc = new JTextArea(descripcionReserva != null ? descripcionReserva : "");
        txtDesc.setEditable(false);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        centro.add(new JScrollPane(txtDesc), BorderLayout.CENTER);

        content.add(centro, BorderLayout.CENTER);

        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);

        JLabel lblPrecio = new JLabel("Total a Descontar: $" + String.format("%.2f", precioReserva),
                SwingConstants.LEFT);
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pie.add(lblPrecio, BorderLayout.WEST);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        botones.setOpaque(false);

        btnConfirmar = new BotonSimple("Confirmar");
        btnConfirmar.setPreferredSize(new Dimension(100, 30));
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnCerrar = new BotonSimple("Cancelar");
        btnCerrar.setPreferredSize(new Dimension(100, 30));
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 15));

        if (controlador != null) {
            btnConfirmar.setActionCommand("CONFIRMAR_RESERVA");
            btnConfirmar.addActionListener(controlador);
            btnCerrar.setActionCommand("CERRAR");
            btnCerrar.addActionListener(controlador);
        }

        botones.add(btnConfirmar);
        botones.add(btnCerrar);
        pie.add(botones, BorderLayout.EAST);

        content.add(pie, BorderLayout.SOUTH);

        content.revalidate();
        content.repaint();
    }

    public void asignarControlador(ActionListener ctrl) {
        this.controlador = ctrl;
        // Iniciar en la sección de cédula
        mostrarSeccionCedula();
    }

    /**
     * Abre el POS como ventana standalone.
     */
    public static void abrirPOS() {
        MainValidacion pos = new MainValidacion();
        new comgest.controller.ValidacionBIOController(pos);
        pos.setVisible(true);
    }

    public void setPreviewImage(Icon icon) {
        if (lblPreview != null) {
            lblPreview.setIcon(icon);
            lblPreview.setText("");
        }
    }

    // --- Getters ---
    public String getCedula() {
        return txtCedula != null ? txtCedula.getText().trim() : "";
    }

    public double getPrecio() {
        return this.precioReserva;
    }

    // --- Setters para datos de reserva (usados por el controlador) ---
    public void setDatosReserva(String titulo, String descripcion, double precio) {
        this.tituloReserva = titulo;
        this.descripcionReserva = descripcion;
        this.precioReserva = precio;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // --- MAIN DE PRUEBA ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            abrirPOS();
        });
    }
}
