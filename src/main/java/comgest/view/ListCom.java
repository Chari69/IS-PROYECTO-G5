package comgest.view;

import comgest.view.components.BotonSimple;
import comgest.view.components.FrameStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCom {
    private JFrame frame;
    private JList<String> listaComensales;
    private DefaultListModel<String> modeloLista;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboSubTipo;
    private JComboBox<String> comboServicio;
    private JButton btnConfirmar;
   

    // Datos simulados CAMBIARRRRR
    private Map<String, Map<String, Integer>> datosSimulados;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListCom view = new ListCom();
        });
    }

    public ListCom() {
        inicializarDatosSimulados();
        JPanel panel = crearVentana();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
    // DATOS FALSOS CAMBIARRRR
    private void inicializarDatosSimulados() {
        datosSimulados = new HashMap<>();

        // Desayuno
        Map<String, Integer> desayuno = new HashMap<>();
        desayuno.put("profesor", 5);
        desayuno.put("empleado", 3);
        desayuno.put("estudiante_regular", 10);
        desayuno.put("estudiante_becario", 5);
        desayuno.put("estudiante_exonerado", 5);
        datosSimulados.put("desayuno", desayuno);

        // Almuerzo
        Map<String, Integer> almuerzo = new HashMap<>();
        almuerzo.put("profesor", 8);
        almuerzo.put("empleado", 4);
        almuerzo.put("estudiante_regular", 15);
        almuerzo.put("estudiante_becario", 7);
        almuerzo.put("estudiante_exonerado", 3);
        datosSimulados.put("almuerzo", almuerzo);
    }

    public JPanel crearVentana() {
        // Usar FrameStyle para crear el frame
        frame = FrameStyle.crearFramePrincipal("ListCom - COMGEST-UCV");

        // Panel Principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        // Panel de selección
        JPanel panelSeleccion = new JPanel(new GridBagLayout());
        panelSeleccion.setBackground(new Color(228, 228, 255));
        panelSeleccion.setBorder(BorderFactory.createTitledBorder("SELECCIONAR"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Tipo de comensal
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblTipo = new JLabel("Tipo de Comensal:");
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelSeleccion.add(lblTipo, gbc);
        gbc.gridx = 1;
        comboTipo = new JComboBox<>(new String[]{"profesor", "empleado", "estudiante"});
        configurarEstiloCombo(comboTipo);
        comboTipo.addActionListener(e -> actualizarSubTipo());
        panelSeleccion.add(comboTipo, gbc);

        // Sub-tipo (solo para estudiante)
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblSubTipo = new JLabel("Sub-tipo (Estudiante):");
        lblSubTipo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelSeleccion.add(lblSubTipo, gbc);
        gbc.gridx = 1;
        comboSubTipo = new JComboBox<>(new String[]{"regular", "becario", "exonerado"});
        configurarEstiloCombo(comboSubTipo);
        comboSubTipo.setEnabled(false);
        panelSeleccion.add(comboSubTipo, gbc);

        // Servicio
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblServicio = new JLabel("Servicio:");
        lblServicio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelSeleccion.add(lblServicio, gbc);
        gbc.gridx = 1;
        comboServicio = new JComboBox<>(new String[]{"desayuno", "almuerzo"});
        configurarEstiloCombo(comboServicio);
        panelSeleccion.add(comboServicio, gbc);

        // Botón confirmar
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        btnConfirmar = new BotonSimple("Confirmar");
        btnConfirmar.setPreferredSize(new Dimension(120, 30));
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCantidad();
            }
        });
        panelSeleccion.add(btnConfirmar, gbc);


        panel.add(panelSeleccion, BorderLayout.NORTH);

        // Crear modelo y lista de comensales
        modeloLista = new DefaultListModel<>();
        listaComensales = new JList<>(modeloLista);
        listaComensales.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaComensales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaComensales.setBackground(new Color(228, 228, 255));
        listaComensales.setOpaque(true);

        // Agregar JScrollPane para la barra de desplazamiento
        JScrollPane scrollPane = new JScrollPane(listaComensales);
        scrollPane.setBackground(new Color(228, 228, 255));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Método para agregar un comensal a la lista
    public void agregarComensal(String nombre) {
        modeloLista.addElement(nombre);
    }

    // Método para remover un comensal
    public void removerComensal(int index) {
        if (index >= 0 && index < modeloLista.getSize()) {
            modeloLista.remove(index);
        }
    }

    // Método para obtener la lista de comensales
    public List<String> getComensales() {
        List<String> comensales = new ArrayList<>();
        for (int i = 0; i < modeloLista.getSize(); i++) {
            comensales.add(modeloLista.getElementAt(i));
        }
        return comensales;
    }

    private void configurarEstiloCombo(JComboBox<String> combo) {
        combo.setOpaque(true);
        combo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        combo.setBackground(new Color(200, 177, 200));
        combo.setForeground(Color.black);
        combo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        combo.setPreferredSize(new Dimension(120, 30));
    }

    private void actualizarSubTipo() {
        String tipo = (String) comboTipo.getSelectedItem();
        comboSubTipo.setEnabled("estudiante".equals(tipo));
    }

    private void mostrarCantidad() {
        String tipo = (String) comboTipo.getSelectedItem();
        String servicio = (String) comboServicio.getSelectedItem();
        String subTipo = comboSubTipo.isEnabled() ? (String) comboSubTipo.getSelectedItem() : null;

        String clave = tipo;
        if ("estudiante".equals(tipo) && subTipo != null) {
            clave = "estudiante_" + subTipo;
        }

        Map<String, Integer> servicioData = datosSimulados.get(servicio);
        Integer cantidad = servicioData != null ? servicioData.get(clave) : 0;

        String mensaje = String.format("Tipo: %s%s\nServicio: %s\nCantidad: %d",
            tipo, subTipo != null ? " (" + subTipo + ")" : "", servicio, cantidad);

        // lblResultado.setText(mensaje);

        // Limpiar la lista y agregar comensales simulados AQUI SE TIENE QUE CAMBIAR Y PONER LOS REALES
        modeloLista.clear();
        for (int i = 1; i <= cantidad; i++) {
            String nombreComensal = String.format("%s %d - %s%s", tipo, i, servicio, subTipo != null ? " (" + subTipo + ")" : "");
            modeloLista.addElement(nombreComensal);
        }

        // También mostrar en un dialog
        JOptionPane.showMessageDialog(frame, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }
}
