package comgest.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuFormDialog extends JDialog {
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextField txtImagenRuta;
    private JButton btnSeleccionarImagen;
    private JComboBox<String> horaInicioCombo;
    private JComboBox<String> meridianoInicioCombo;
    private JComboBox<String> horaFinCombo;
    private JComboBox<String> meridianoFinCombo;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private boolean guardado = false;

    public MenuFormDialog(Frame parent, String titulo) {
        super(parent, titulo, true);
        inicializarUI();
        configurarDialog();
    }

    private void inicializarUI() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(245, 245, 250));

        // Panel de campos
        JPanel panelCampos = crearPanelCampos();
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = crearPanelBotones();
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        this.setContentPane(panelPrincipal);
    }

    private JPanel crearPanelCampos() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblNombre = new JLabel("Nombre del menú:");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtNombre = new JTextField(25);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtNombre.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        panel.add(txtNombre, gbc);

        // Descripción
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(lblDescripcion, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        txtDescripcion = new JTextArea(5, 25);
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        panel.add(scrollDescripcion, gbc);

        // Imagen
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel lblImagen = new JLabel("Ruta de imagen:");
        lblImagen.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(lblImagen, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JPanel panelImagen = new JPanel(new BorderLayout(5, 0));
        panelImagen.setOpaque(false);
        txtImagenRuta = new JTextField("resources/arepa.jpg");
        txtImagenRuta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtImagenRuta.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        panelImagen.add(txtImagenRuta, BorderLayout.CENTER);

        btnSeleccionarImagen = new JButton("Buscar...");
        btnSeleccionarImagen.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelImagen.add(btnSeleccionarImagen, BorderLayout.EAST);

        panel.add(panelImagen, gbc);

        // Horario
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel lblHorario = new JLabel("Horario:");
        lblHorario.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(lblHorario, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JPanel panelHorario = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        panelHorario.setOpaque(false);

        String[] horas = new String[12];
        for (int i = 0; i < 12; i++) {
            horas[i] = String.valueOf(i + 1);
        }

        horaInicioCombo = new JComboBox<>(horas);
        meridianoInicioCombo = new JComboBox<>(new String[] { "AM", "PM" });
        horaFinCombo = new JComboBox<>(horas);
        meridianoFinCombo = new JComboBox<>(new String[] { "AM", "PM" });

        horaInicioCombo.setSelectedItem("7");
        meridianoInicioCombo.setSelectedItem("AM");
        horaFinCombo.setSelectedItem("10");
        meridianoFinCombo.setSelectedItem("AM");

        panelHorario.add(horaInicioCombo);
        panelHorario.add(meridianoInicioCombo);
        panelHorario.add(new JLabel("-"));
        panelHorario.add(horaFinCombo);
        panelHorario.add(meridianoFinCombo);

        panel.add(panelHorario, gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBackground(new Color(245, 245, 250));

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setPreferredSize(new Dimension(100, 35));
        btnGuardar.setBackground(new Color(76, 175, 80));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCancelar.setPreferredSize(new Dimension(100, 35));
        btnCancelar.setBackground(new Color(244, 67, 54));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        return panel;
    }

    private void configurarDialog() {
        this.setSize(450, 320);
        this.setLocationRelativeTo(this.getParent());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    public boolean mostrarDialog() {
        guardado = false;
        this.setVisible(true);
        return guardado;
    }

    public void cerrarConResultado(boolean guardado) {
        this.guardado = guardado;
        this.dispose();
    }

    public void setOnGuardar(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void setOnCancelar(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }

    public void setOnSeleccionarImagen(ActionListener listener) {
        btnSeleccionarImagen.addActionListener(listener);
    }

    // Getters
    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public String getImagenRuta() {
        return txtImagenRuta.getText().trim();
    }

    public String getHorario() {
        String inicio = (String) horaInicioCombo.getSelectedItem();
        String meridianoInicio = (String) meridianoInicioCombo.getSelectedItem();
        String fin = (String) horaFinCombo.getSelectedItem();
        String meridianoFin = (String) meridianoFinCombo.getSelectedItem();
        if (inicio == null || meridianoInicio == null || fin == null || meridianoFin == null) {
            return "";
        }
        return inicio + ":00 " + meridianoInicio + " - " + fin + ":00 " + meridianoFin;
    }

    // Setters para edición
    public void setNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    public void setDescripcion(String descripcion) {
        txtDescripcion.setText(descripcion);
    }

    public void setImagenRuta(String ruta) {
        txtImagenRuta.setText(ruta);
    }

    public void setHorario(String horario) {
        String limpio = (horario == null) ? "" : horario.trim();
        if (limpio.toLowerCase().startsWith("horario:")) {
            limpio = limpio.substring("horario:".length()).trim();
        }

        String[] partes = limpio.split("-");
        if (partes.length == 2) {
            aplicarHoraDesdeTexto(partes[0].trim(), horaInicioCombo, meridianoInicioCombo);
            aplicarHoraDesdeTexto(partes[1].trim(), horaFinCombo, meridianoFinCombo);
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtImagenRuta.setText("resources/arepa.jpg");
    }

    public void deshabilitarCampo(String campo) {
        switch (campo.toLowerCase()) {
            case "nombre":
                txtNombre.setEnabled(false);
                break;
            case "descripcion":
                txtDescripcion.setEnabled(false);
                break;
            case "imagen":
                txtImagenRuta.setEnabled(false);
                if (btnSeleccionarImagen != null) {
                    btnSeleccionarImagen.setEnabled(false);
                }
                break;
            case "horario":
                if (horaInicioCombo != null) {
                    horaInicioCombo.setEnabled(false);
                }
                if (meridianoInicioCombo != null) {
                    meridianoInicioCombo.setEnabled(false);
                }
                if (horaFinCombo != null) {
                    horaFinCombo.setEnabled(false);
                }
                if (meridianoFinCombo != null) {
                    meridianoFinCombo.setEnabled(false);
                }
                break;
        }
    }

    public void habilitarCampo(String campo) {
        switch (campo.toLowerCase()) {
            case "nombre":
                txtNombre.setEnabled(true);
                break;
            case "descripcion":
                txtDescripcion.setEnabled(true);
                break;
            case "imagen":
                txtImagenRuta.setEnabled(true);
                if (btnSeleccionarImagen != null) {
                    btnSeleccionarImagen.setEnabled(true);
                }
                break;
            case "horario":
                if (horaInicioCombo != null) {
                    horaInicioCombo.setEnabled(true);
                }
                if (meridianoInicioCombo != null) {
                    meridianoInicioCombo.setEnabled(true);
                }
                if (horaFinCombo != null) {
                    horaFinCombo.setEnabled(true);
                }
                if (meridianoFinCombo != null) {
                    meridianoFinCombo.setEnabled(true);
                }
                break;
        }
    }

    private void aplicarHoraDesdeTexto(String texto, JComboBox<String> horaCombo, JComboBox<String> meridianoCombo) {
        if (texto == null || horaCombo == null || meridianoCombo == null) {
            return;
        }

        String limpio = texto.trim();
        String[] partes = limpio.split(" ");
        if (partes.length >= 2) {
            String hora = partes[0];
            String meridiano = partes[1];
            if (hora.endsWith(":00")) {
                hora = hora.substring(0, hora.length() - 3);
            }
            horaCombo.setSelectedItem(hora);
            meridianoCombo.setSelectedItem(meridiano.toUpperCase());
        }
    }

}
