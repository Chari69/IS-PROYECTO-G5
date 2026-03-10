
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
    private JLabel lblPreview;
    private ActionListener controlador;
    private double precio;
    
    // Variables guardadas para usarlas cuando cambiemos a la pestaña de descripción
    private String tituloGuardado;
    private String descripcionGuardada;
    private Icon imagenGuardada;

    public MainValidacion(String titulo, String descripcion, double precio, Icon imagen) {
        super("Validar Pago: " + titulo);
        this.precio = precio;
        this.tituloGuardado = titulo;
        this.descripcionGuardada = descripcion;
        this.imagenGuardada = imagen;
        
        prepararConfiguracionBase();
    }

    /**
     * Configuración básica del Frame (tamaño, icono, cierre)
     */
    private void prepararConfiguracionBase() {
        this.setBackground(new Color(228, 228, 255));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(440, 360);
        this.setResizable(false);
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
     * VISTA 1: Ahora es la de Subida de Imagen
     */
    public void mostrarSeccionSubida() {
        Container content = getContentPane();
        content.removeAll();
        content.setLayout(new BorderLayout(15, 15));
        ((JPanel) content).setBackground(new Color(228, 228, 255));
        ((JPanel) content).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instructions = new JLabel("SUBE TU COMPROBANTE", SwingConstants.CENTER);
        instructions.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnSeleccionar = new BotonSimple("Seleccionar Fotografía");
        btnSeleccionar.setPreferredSize(new Dimension(80, 35));
        btnSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        lblPreview = new JLabel("Sin foto seleccionada", SwingConstants.CENTER);
        lblPreview.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblPreview.setOpaque(true);
        lblPreview.setBackground(Color.WHITE);
        lblPreview.setPreferredSize(new Dimension(200, 150));

        btnFinalizar = new BotonSimple("CONTINUAR");
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
     * VISTA 2: Detalles y Confirmación (Antes era initComponents)
     */
    public void mostrarSeccionDetalles() {
        Container content = getContentPane();
        content.removeAll();
        content.setLayout(new BorderLayout(10, 10));
        ((JPanel) content).setBackground(new Color(228, 228, 255));
        ((JPanel) content).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("CONFIRMAR DETALLES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        content.add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(8, 8));
        centro.setBackground(Color.WHITE);

        if (imagenGuardada != null) {
            JLabel lblImg = new JLabel(imagenGuardada);
            lblImg.setHorizontalAlignment(SwingConstants.CENTER);
            centro.add(lblImg, BorderLayout.WEST);
        }

        JTextArea txtDesc = new JTextArea(descripcionGuardada);
        txtDesc.setEditable(false);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        centro.add(new JScrollPane(txtDesc), BorderLayout.CENTER);

        content.add(centro, BorderLayout.CENTER);

        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);

        JLabel lblPrecio = new JLabel("Total a Pagar: $" + precio, SwingConstants.LEFT);
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
        // Se llama a mostrarSeccionSubida para asegurar que los botones tengan el controlador asignado
        mostrarSeccionSubida();
    }

    public static void mostrarReserva(String titulo, String descripcion, double precio, Icon imagen) {
        MainValidacion res = new MainValidacion(titulo, descripcion, precio, imagen);
        // El controlador debe llamar a asignarControlador, que a su vez activa la vista de subida
        new comgest.controller.ValidacionBIOController(res);
        res.setVisible(true);
    }

    public void setPreviewImage(Icon icon) {
        if (lblPreview != null) {
            lblPreview.setIcon(icon);
            lblPreview.setText("");
        }
    }

    public double getPrecio() {
    return this.precio;
}

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // --- MAIN DE PRUEBA ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mostrarReserva("RESERVA DE CANCHA", "Ubicación: Sector A\nHorario: 14:00 - 16:00", 25.50, null);
        });
    }
}

    

