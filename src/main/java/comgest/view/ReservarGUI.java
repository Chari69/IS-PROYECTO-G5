package comgest.view;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import comgest.view.components.BotonSimple;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ReservarGUI extends JDialog {

    public ReservarGUI(Window owner, String titulo, String descripcion, double precio, Icon imagen) {
        super(owner instanceof Frame ? (Frame) owner : null, "Reservar: " + titulo, ModalityType.APPLICATION_MODAL);
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

        //título
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

        //precio y botones
        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);

        JLabel lblPrecio = new JLabel("Precio: $" + precio, SwingConstants.LEFT);
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pie.add(lblPrecio, BorderLayout.WEST);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        BotonSimple btnConfirmar = new BotonSimple("Confirmar");
        btnConfirmar.setPreferredSize(new Dimension(80, 30));
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        BotonSimple btnCerrar = new BotonSimple("Cerrar");
        btnCerrar.setPreferredSize(new Dimension(80, 30));
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 15));

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JOptionPane.showMessageDialog(ReservarGUI.this, "Reserva confirmada para: " + titulo,
                //         "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                // dispose();
                mostrarSeccionSubida();
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        botones.add(btnConfirmar);
        botones.add(btnCerrar);

        pie.add(botones, BorderLayout.EAST);

        content.add(pie, BorderLayout.SOUTH);

        this.setContentPane(content);
    }

    public static void mostrarReserva(Window owner, String titulo, String descripcion, double precio, Icon imagen) {
        ReservarGUI res = new ReservarGUI(owner, titulo, descripcion, precio, imagen);
        res.setVisible(true);
    }

    private void mostrarSeccionSubida() {
    //Limpiamos el panel principal
    Container content = getContentPane();
    content.removeAll();
    content.setLayout(new BorderLayout(15, 15));
    ((JPanel)content).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    //Elementos de la nueva vista
    JLabel instructions = new JLabel("SUBE LA IMAGEN PARA RESERVAR", SwingConstants.CENTER);
    instructions.setFont(new Font("Segoe UI", Font.BOLD, 14));
    
    BotonSimple btnSeleccionar = new BotonSimple("Subir Fotografía");
     btnSeleccionar.setPreferredSize(new Dimension(80, 30));
        btnSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 15));
    JLabel lblPreview = new JLabel("Sin foto seleccionada", SwingConstants.CENTER);
    lblPreview.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    lblPreview.setPreferredSize(new Dimension(200, 150));

    //Logica para buscar el archivo
    btnSeleccionar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            // Filtro para que solo acepte imágenes
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "png", "jpeg");
            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(ReservarGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                
                // Cargar y redimensionar la imagen
                ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
                Image img = originalIcon.getImage();
                // Ajustamos la imagen al tamaño del label
                Image newImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                
                lblPreview.setIcon(new ImageIcon(newImg));
                lblPreview.setText(""); // Quitamos el texto para que se vea la foto
            }
        }
    });

    //Botón final para terminar
    BotonSimple btnFinalizar = new BotonSimple("VALIDAR");

    //VALIDACION BOTON




    //
     btnFinalizar.setPreferredSize(new Dimension(80, 30));
        btnFinalizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
    btnFinalizar.addActionListener(al -> dispose());

    //Agregar todo al panel
    JPanel centroSubida = new JPanel(new BorderLayout(10, 10));
    centroSubida.add(btnSeleccionar, BorderLayout.NORTH);
    centroSubida.add(lblPreview, BorderLayout.CENTER);

    content.add(instructions, BorderLayout.NORTH);
    content.add(centroSubida, BorderLayout.CENTER);
    content.add(btnFinalizar, BorderLayout.SOUTH);

    //Refrescar la ventana
    content.revalidate();
    content.repaint();
}
}
