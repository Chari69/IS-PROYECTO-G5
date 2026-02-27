package comgest.view;

import javax.swing.*;
import comgest.view.components.BotonSimple;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReservarGUI extends JDialog {

    private BotonSimple btnConfirmar;
    private BotonSimple btnCerrar;
    private BotonSimple btnSeleccionar;
    private BotonSimple btnFinalizar;
    private JLabel lblPreview;
    private ActionListener controlador;
    private double precio;

    public ReservarGUI(Window owner, String titulo, String descripcion, double precio, Icon imagen) {
        super(owner instanceof Frame ? (Frame) owner : null, "Reservar: " + titulo, ModalityType.APPLICATION_MODAL);
        this.precio = precio;
        initComponents(titulo, descripcion, precio, imagen);
    }

    private void initComponents(String titulo, String descripcion, double precio, Icon imagen) {
        this.setBackground(new Color(228, 228, 255));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(440, 340);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBackground(new Color(228, 228, 255));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // título
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        content.add(lblTitulo, BorderLayout.NORTH);

        // Centro: imagen + descripción
        JPanel centro = new JPanel(new BorderLayout(8, 8));
        centro.setBackground(Color.WHITE);

        if (imagen != null) {
            JLabel lblImg = new JLabel(imagen);
            lblImg.setHorizontalAlignment(SwingConstants.CENTER);
            centro.add(lblImg, BorderLayout.WEST);
        }

        JTextArea txtDesc = new JTextArea(descripcion);
        txtDesc.setEditable(false);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDesc.setBackground(Color.WHITE);
        centro.add(new JScrollPane(txtDesc), BorderLayout.CENTER);

        content.add(centro, BorderLayout.CENTER);

        // precio y botones
        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);

        JLabel lblPrecio = new JLabel("Precio: $" + precio, SwingConstants.LEFT);
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pie.add(lblPrecio, BorderLayout.WEST);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnConfirmar = new BotonSimple("Confirmar");
        btnConfirmar.setPreferredSize(new Dimension(80, 30));
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnCerrar = new BotonSimple("Cerrar");
        btnCerrar.setPreferredSize(new Dimension(80, 30));
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 15));

        botones.add(btnConfirmar);
        botones.add(btnCerrar);

        pie.add(botones, BorderLayout.EAST);

        content.add(pie, BorderLayout.SOUTH);

        this.setContentPane(content);
    }

    public void asignarControlador(ActionListener ctrl) {
        this.controlador = ctrl;
        if (btnConfirmar != null) {
            btnConfirmar.setActionCommand("CONFIRMAR_RESERVA");
            btnConfirmar.addActionListener(ctrl);
        }
        if (btnCerrar != null) {
            btnCerrar.setActionCommand("CERRAR");
            btnCerrar.addActionListener(ctrl);
        }
    }

    public static void mostrarReserva(Window owner, String titulo, String descripcion, double precio, Icon imagen) {
        ReservarGUI res = new ReservarGUI(owner, titulo, descripcion, precio, imagen);
        new comgest.controller.ReservarController(res);
        res.setVisible(true);
    }

    public void mostrarSeccionSubida() {
        Container content = getContentPane();
        content.removeAll();
        content.setLayout(new BorderLayout(15, 15));
        ((JPanel) content).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instructions = new JLabel("SUBE LA IMAGEN PARA RESERVAR", SwingConstants.CENTER);
        instructions.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnSeleccionar = new BotonSimple("Subir Fotografía");
        btnSeleccionar.setPreferredSize(new Dimension(80, 30));
        btnSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 15));

        lblPreview = new JLabel("Sin foto seleccionada", SwingConstants.CENTER);
        lblPreview.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblPreview.setPreferredSize(new Dimension(200, 150));

        btnFinalizar = new BotonSimple("VALIDAR");
        btnFinalizar.setPreferredSize(new Dimension(80, 30));
        btnFinalizar.setFont(new Font("Segoe UI", Font.BOLD, 15));

        if (controlador != null) {
            btnSeleccionar.setActionCommand("SELECCIONAR_FOTO");
            btnSeleccionar.addActionListener(controlador);
            btnFinalizar.setActionCommand("FINALIZAR");
            btnFinalizar.addActionListener(controlador);
        }

        JPanel centroSubida = new JPanel(new BorderLayout(10, 10));
        centroSubida.add(btnSeleccionar, BorderLayout.NORTH);
        centroSubida.add(lblPreview, BorderLayout.CENTER);

        content.add(instructions, BorderLayout.NORTH);
        content.add(centroSubida, BorderLayout.CENTER);
        content.add(btnFinalizar, BorderLayout.SOUTH);

        content.revalidate();
        content.repaint();
    }

    public void setPreviewImage(Icon icon) {
        if (lblPreview != null) {
            lblPreview.setIcon(icon);
            lblPreview.setText("");
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public double getPrecio() {
        return this.precio;
    }
}
