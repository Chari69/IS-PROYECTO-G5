package comgest.view;

import javax.swing.*;
import comgest.controller.MenuController;
import comgest.view.Utils.JTextArea_Personalizado;
import comgest.view.Utils.JTextPane_Personalizado;
import comgest.view.components.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MenuGUI extends JPanel {

    // Paneles principales
    private JPanel Panel_Desayuno;
    private JPanel Panel_Almuerzo;
    private JPanel Panel_Agregar;
    private JPanel Panel_Agregar2;

    // Botones de acción
    private JPanel btnAgregar1;
    private JPanel btnAgregar2;
    private BotonSimple btnOrdenarDesayuno;
    private BotonSimple btnOrdenarAlmuerzo;
    private JPanel btnEliminarDesayuno;
    private JPanel btnEditarDesayuno;
    private JPanel btnEliminarAlmuerzo;
    private JPanel btnEditarAlmuerzo;

    // Componentes editables
    private JTextPane tituloDesayuno;
    private JTextArea descripcionDesayuno;
    private JTextArea horarioDesayuno;
    private JLabel precioDesayuno;
    private JLabel imgLabelDesayuno;

    private JTextPane tituloAlmuerzo;
    private JTextArea descripcionAlmuerzo;
    private JTextArea horarioAlmuerzo;
    private JLabel precioAlmuerzo;
    private JLabel imgLabelAlmuerzo;

    // IDs de los items
    private String idDesayuno;
    private String idAlmuerzo;

    // Constructor
    public MenuGUI() {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(228, 228, 249));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.fill = GridBagConstraints.VERTICAL;

        // Panel izquierdo con icono
        agregarPanelIcono(gbc);

        // Botones de agregar (inicialmente ocultos)
        agregarBotonesAgregar(gbc);

        // Tarjetas de menú
        agregarTarjetasMenu(gbc);

        // Panel derecho
        agregarPanelDerecho(gbc);
    }

    private void agregarPanelIcono(GridBagConstraints gbc) {
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBackground(new Color(228, 228, 255));
        panelIzquierdo.setPreferredSize(new Dimension(100, 400));

        ImageIcon iconoOriginal = new ImageIcon(MenuGUI.class.getResource("resources/IconoPrincipal.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel labelImg = new JLabel(new ImageIcon(imagenEscalada));
        labelImg.setHorizontalAlignment(SwingConstants.CENTER);
        panelIzquierdo.add(labelImg, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.weightx = 0.1;
        this.add(panelIzquierdo, gbc);
    }

    private void agregarBotonesAgregar(GridBagConstraints gbc) {
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.4;

        // Botón agregar 1
        gbc.gridx = 1;
        Panel_Agregar = new JPanel(new BorderLayout());
        Panel_Agregar.setBackground(new Color(230, 245, 230));
        Panel_Agregar.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 2, true));

        BotonJPanel AddBotton1 = new BotonJPanel("resources/AñadirMenú.png", 135, 125);
        btnAgregar1 = AddBotton1.getBoton();
        Panel_Agregar.add(AddBotton1.getBoton(), BorderLayout.CENTER);

        // Etiqueta de instrucción
        JLabel lblAgregar1 = new JLabel("Agregar Menú");
        lblAgregar1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblAgregar1.setHorizontalAlignment(SwingConstants.CENTER);
        lblAgregar1.setForeground(new Color(76, 175, 80));
        Panel_Agregar.add(lblAgregar1, BorderLayout.SOUTH);

        configurarTarjeta(Panel_Agregar);
        Panel_Agregar.setVisible(false);
        this.add(Panel_Agregar, gbc);

        // Botón agregar 2
        gbc.gridx = 2;
        Panel_Agregar2 = new JPanel(new BorderLayout());
        Panel_Agregar2.setBackground(new Color(230, 245, 230));
        Panel_Agregar2.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 2, true));

        BotonJPanel AddBotton2 = new BotonJPanel("resources/AñadirMenú.png", 135, 125);
        btnAgregar2 = AddBotton2.getBoton();
        Panel_Agregar2.add(AddBotton2.getBoton(), BorderLayout.CENTER);

        JLabel lblAgregar2 = new JLabel("Agregar Menú");
        lblAgregar2.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblAgregar2.setHorizontalAlignment(SwingConstants.CENTER);
        lblAgregar2.setForeground(new Color(76, 175, 80));
        Panel_Agregar2.add(lblAgregar2, BorderLayout.SOUTH);

        configurarTarjeta(Panel_Agregar2);
        Panel_Agregar2.setVisible(false);
        this.add(Panel_Agregar2, gbc);
    }

    private void agregarTarjetasMenu(GridBagConstraints gbc) {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.4;

        // Tarjeta Desayuno
        gbc.gridx = 1;
        Panel_Desayuno = new JPanel();
        configurarTarjeta(Panel_Desayuno, "Desayuno", "Descripción del desayuno",
                "7:00 AM - 10:00 AM", "resources/arepa.jpg", 17, true);
        Panel_Desayuno.setVisible(true);
        this.add(Panel_Desayuno, gbc);

        // Tarjeta Almuerzo
        gbc.gridx = 2;
        Panel_Almuerzo = new JPanel();
        configurarTarjeta(Panel_Almuerzo, "Almuerzo", "Descripción del almuerzo",
                "12:00 PM - 3:00 PM", "resources/images.jpg", 18, false);
        Panel_Almuerzo.setVisible(true);
        this.add(Panel_Almuerzo, gbc);
    }

    private void agregarPanelDerecho(GridBagConstraints gbc) {
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(228, 228, 249));
        panelDerecho.setPreferredSize(new Dimension(100, 400));
        gbc.gridx = 3;
        gbc.weightx = 0.1;
        this.add(panelDerecho, gbc);
    }

    public JPanel crearVentana() {
        JPanel vp = new JPanel(new BorderLayout());

        PanelInferiorPUI panel = new PanelInferiorPUI();
        vp.add(panel, BorderLayout.SOUTH);
        vp.add(this, BorderLayout.CENTER);
        vp.revalidate();
        vp.repaint();

        return vp;
    }

    void configurarTarjeta(JPanel panel, String titulo, String descripcion, String horario,
            String imagenRuta, double precio, boolean esDesayuno) {
        panel.setPreferredSize(new Dimension(250, 400));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Fila 1: Título y horario
        panel.add(crearFilaTitulo(titulo, horario, esDesayuno));

        // Fila 2: Imagen
        panel.add(crearFilaImagen(imagenRuta, esDesayuno));

        // Fila 3: Descripción y controles
        panel.add(crearFilaDescripcion(descripcion, precio, esDesayuno));
    }

    private JPanel crearFilaTitulo(String titulo, String horario, boolean esDesayuno) {
        JPanel fila1 = new JPanel(new GridLayout(2, 1));
        fila1.setBackground(Color.WHITE);
        fila1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Título
        JTextPane tituloCentrado = new JTextPane();
        JTextPane_Personalizado.Centrar(tituloCentrado, titulo);
        tituloCentrado.setEditable(false);
        tituloCentrado.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        tituloCentrado.setBackground(new Color(200, 177, 200));

        // Guardar referencia
        if (esDesayuno) {
            tituloDesayuno = tituloCentrado;
        } else {
            tituloAlmuerzo = tituloCentrado;
        }

        // Horario
        String horarioTexto = (horario == null || horario.trim().isEmpty())
                ? "Horario: No definido"
                : "Horario: " + horario.trim();
        JTextArea labelHorario = new JTextArea(horarioTexto);
        JTextArea_Personalizado.AplicarPersonalización(labelHorario);
        labelHorario.setFont(new Font("Segoe UI", Font.BOLD, 16));

        if (esDesayuno) {
            horarioDesayuno = labelHorario;
        } else {
            horarioAlmuerzo = labelHorario;
        }

        fila1.add(tituloCentrado);
        fila1.add(labelHorario);

        return fila1;
    }

    private JPanel crearFilaImagen(String imagenRuta, boolean esDesayuno) {
        JPanel fila2 = new JPanel(new BorderLayout());
        fila2.setPreferredSize(new Dimension(300, 150));
        fila2.setMaximumSize(new Dimension(Short.MAX_VALUE, 150));
        fila2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Cargar imagen
        ImageIcon icono = cargarImagen(imagenRuta, 300, 150);
        JLabel labelImg = new JLabel(icono);
        if (icono == null) {
            labelImg.setText("Sin imagen");
            labelImg.setHorizontalAlignment(SwingConstants.CENTER);
        }
        labelImg.setLayout(new BorderLayout());

        // Guardar referencia
        if (esDesayuno) {
            imgLabelDesayuno = labelImg;
        } else {
            imgLabelAlmuerzo = labelImg;
        }

        fila2.add(labelImg, BorderLayout.CENTER);

        return fila2;
    }

    private JPanel crearFilaDescripcion(String descripcion, double precio, boolean esDesayuno) {
        JPanel fila3 = new JPanel(new BorderLayout());
        fila3.setBackground(Color.WHITE);
        fila3.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Descripción
        JTextArea descArea = new JTextArea(descripcion);
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descArea.setBackground(Color.WHITE);
        descArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        if (esDesayuno) {
            descripcionDesayuno = descArea;
        } else {
            descripcionAlmuerzo = descArea;
        }

        fila3.add(descArea, BorderLayout.CENTER);

        // Controles inferiores
        JPanel contenedorInferior = new JPanel(new BorderLayout());
        contenedorInferior.setOpaque(false);

        // Botones admin (izquierda)
        contenedorInferior.add(crearBotonesAdmin(esDesayuno), BorderLayout.WEST);

        // Precio y ordenar (derecha)
        contenedorInferior.add(crearPanelOrdenar(precio, esDesayuno), BorderLayout.EAST);

        fila3.add(contenedorInferior, BorderLayout.SOUTH);

        return fila3;
    }

    private JPanel crearBotonesAdmin(boolean esDesayuno) {
        JPanel panelIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelIzquierda.setOpaque(false);

        BotonJPanel btnBasura = new BotonJPanel("resources/papelera.jpg");
        BotonJPanel btnEditar = new BotonJPanel("resources/lapiz.png");

        btnBasura.getBoton().setBackground(Color.WHITE);
        btnEditar.getBoton().setBackground(Color.WHITE);

        if (esDesayuno) {
            btnEliminarDesayuno = btnBasura.getBoton();
            btnEditarDesayuno = btnEditar.getBoton();
        } else {
            btnEliminarAlmuerzo = btnBasura.getBoton();
            btnEditarAlmuerzo = btnEditar.getBoton();
        }

        panelIzquierda.add(btnBasura.getBoton());
        panelIzquierda.add(btnEditar.getBoton());

        return panelIzquierda;
    }

    private JPanel crearPanelOrdenar(double precio, boolean esDesayuno) {
        JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelDerecha.setOpaque(false);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setOpaque(false);

        JLabel labelPrecio = new JLabel("Precio: $" + precio, SwingConstants.CENTER);
        labelPrecio.setFont(new Font("Segoe UI", Font.BOLD, 15));

        if (esDesayuno) {
            precioDesayuno = labelPrecio;
        } else {
            precioAlmuerzo = labelPrecio;
        }

        BotonSimple btnOrdenar = new BotonSimple("Ordenar");
        btnOrdenar.setPreferredSize(new Dimension(40, 30));
        btnOrdenar.setFont(new Font("Segoe UI", Font.BOLD, 15));

        if (esDesayuno) {
            btnOrdenarDesayuno = btnOrdenar;
        } else {
            btnOrdenarAlmuerzo = btnOrdenar;
        }

        contenedor.add(labelPrecio, BorderLayout.CENTER);
        contenedor.add(btnOrdenar, BorderLayout.SOUTH);
        panelDerecha.add(contenedor);

        return panelDerecha;
    }

    void configurarTarjeta(JPanel panel) {
        panel.setPreferredSize(new Dimension(135, 125));
        panel.setBackground(new Color(228, 228, 255));
    }

    // === MÉTODOS PÚBLICOS PARA EL CONTROLADOR ===

    public void asignarControlador(MenuController controller) {
        conectarBoton(btnOrdenarDesayuno, MenuController.ACTION_ORDENAR_DESAYUNO, controller);
        conectarBoton(btnOrdenarAlmuerzo, MenuController.ACTION_ORDENAR_ALMUERZO, controller);
        conectarBoton(btnEditarDesayuno, MenuController.ACTION_EDITAR_DESAYUNO, controller);
        conectarBoton(btnEliminarDesayuno, MenuController.ACTION_ELIMINAR_DESAYUNO, controller);
        conectarBoton(btnEditarAlmuerzo, MenuController.ACTION_EDITAR_ALMUERZO, controller);
        conectarBoton(btnEliminarAlmuerzo, MenuController.ACTION_ELIMINAR_ALMUERZO, controller);
        conectarBoton(btnAgregar1, MenuController.ACTION_AGREGAR_MENU, controller);
        conectarBoton(btnAgregar2, MenuController.ACTION_AGREGAR_MENU, controller);
    }

    private void conectarBoton(JComponent boton, String actionCommand, MenuController controller) {
        if (boton == null)
            return;
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.actionPerformed(new java.awt.event.ActionEvent(
                        boton, java.awt.event.ActionEvent.ACTION_PERFORMED, actionCommand));
            }
        });
    }

    public void setAdminVisible(boolean visible) {
        setVisible(btnEliminarDesayuno, visible);
        setVisible(btnEditarDesayuno, visible);
        setVisible(btnEliminarAlmuerzo, visible);
        setVisible(btnEditarAlmuerzo, visible);
        if (!visible) {
            setVisible(Panel_Agregar, false);
            setVisible(Panel_Agregar2, false);
        }
    }

    private void setVisible(JComponent component, boolean visible) {
        if (component != null) {
            component.setVisible(visible);
        }
    }

    public void mostrarPanelesSegunItems(int cantidadItems) {
        switch (cantidadItems) {
            case 0:
                Panel_Desayuno.setVisible(false);
                Panel_Almuerzo.setVisible(false);
                Panel_Agregar.setVisible(true);
                Panel_Agregar2.setVisible(true);
                break;
            case 1:
                Panel_Desayuno.setVisible(true);
                Panel_Almuerzo.setVisible(false);
                Panel_Agregar.setVisible(false);
                Panel_Agregar2.setVisible(true);
                break;
            default: // 2 o más
                Panel_Desayuno.setVisible(true);
                Panel_Almuerzo.setVisible(true);
                Panel_Agregar.setVisible(false);
                Panel_Agregar2.setVisible(false);
                break;
        }
        revalidate();
        repaint();
    }

    public void actualizarDesayuno(String titulo, String descripcion, double precio, String imgPath, String id,
            String horario) {
        this.idDesayuno = id;
        actualizarTitulo(tituloDesayuno, titulo);
        actualizarTexto(descripcionDesayuno, descripcion);
        actualizarHorario(horarioDesayuno, horario);
        actualizarTexto(precioDesayuno, "Precio: $" + precio);
        actualizarImagen(imgLabelDesayuno, imgPath);
    }

    public void actualizarAlmuerzo(String titulo, String descripcion, double precio, String imgPath, String id,
            String horario) {
        this.idAlmuerzo = id;
        actualizarTitulo(tituloAlmuerzo, titulo);
        actualizarTexto(descripcionAlmuerzo, descripcion);
        actualizarHorario(horarioAlmuerzo, horario);
        actualizarTexto(precioAlmuerzo, "Precio: $" + precio);
        actualizarImagen(imgLabelAlmuerzo, imgPath);
    }

    private void actualizarTitulo(JTextPane textPane, String texto) {
        if (textPane != null) {
            JTextPane_Personalizado.Centrar(textPane, texto);
        }
    }

    private void actualizarTexto(JTextArea textArea, String texto) {
        if (textArea != null) {
            textArea.setText(texto);
        }
    }

    private void actualizarTexto(JLabel label, String texto) {
        if (label != null) {
            label.setText(texto);
        }
    }

    private void actualizarHorario(JTextArea textArea, String horario) {
        if (textArea != null) {
            String texto = (horario == null || horario.trim().isEmpty())
                    ? "Horario: No definido"
                    : "Horario: " + horario.trim();
            textArea.setText(texto);
        }
    }

    private void actualizarImagen(JLabel label, String imgPath) {
        if (label == null || imgPath == null)
            return;
        try {
            ImageIcon icono = cargarImagen(imgPath, 300, 150);
            if (icono != null) {
                label.setIcon(icono);
                label.setText(null);
            }
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
        }
    }

    private ImageIcon cargarImagen(String imgPath, int width, int height) {
        if (imgPath == null || imgPath.trim().isEmpty()) {
            return null;
        }

        ImageIcon icono = null;
        try {
            var resource = MenuGUI.class.getResource(imgPath);
            if (resource != null) {
                icono = new ImageIcon(resource);
            } else {
                Path path = Paths.get(imgPath);
                if (!path.isAbsolute()) {
                    path = Paths.get("src", "main", "java", "comgest").resolve(imgPath);
                }
                if (Files.exists(path)) {
                    icono = new ImageIcon(path.toString());
                }
            }
        } catch (Exception e) {
            System.err.println("Error leyendo imagen: " + e.getMessage());
        }

        if (icono == null) {
            return null;
        }

        Image imagenEscalada = icono.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    public void setEditableDesayuno(boolean editable) {
        setEditable(tituloDesayuno, editable);
        setEditable(descripcionDesayuno, editable);
    }

    public void setEditableAlmuerzo(boolean editable) {
        setEditable(tituloAlmuerzo, editable);
        setEditable(descripcionAlmuerzo, editable);
    }

    private void setEditable(JTextPane textPane, boolean editable) {
        if (textPane != null) {
            textPane.setEditable(editable);
        }
    }

    private void setEditable(JTextArea textArea, boolean editable) {
        if (textArea != null) {
            textArea.setEditable(editable);
        }
    }

    // GETTERS

    public String getTituloDesayuno() {
        return tituloDesayuno != null ? tituloDesayuno.getText() : "";
    }

    public String getDescripcionDesayuno() {
        return descripcionDesayuno != null ? descripcionDesayuno.getText() : "";
    }

    public String getTituloAlmuerzo() {
        return tituloAlmuerzo != null ? tituloAlmuerzo.getText() : "";
    }

    public String getDescripcionAlmuerzo() {
        return descripcionAlmuerzo != null ? descripcionAlmuerzo.getText() : "";
    }

    public String getIdDesayuno() {
        return idDesayuno;
    }

    public String getIdAlmuerzo() {
        return idAlmuerzo;
    }

    // DIALOGOS

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public int showConfirmDialog(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Confirmación", JOptionPane.YES_NO_OPTION);
    }

    public String showInputDialog(String message, String valorInicial) {
        return (String) JOptionPane.showInputDialog(this, message, "Entrada",
                JOptionPane.QUESTION_MESSAGE, null, null, valorInicial);
    }

}
