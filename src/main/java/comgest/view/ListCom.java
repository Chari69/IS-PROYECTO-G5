package comgest.view;

import comgest.view.components.BotonSimple;
import comgest.view.components.PanelInferiorPUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ListCom {
    private JList<String> listaComensales;
    private DefaultListModel<String> modeloLista;
    private JComboBox<String> comboServicio;
    private JComboBox<String> comboTipo;
    private JButton btnFiltrar;
    private JLabel lblContador;

    public ListCom() {
    }

    public JPanel crearVentana() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        // Panel de filtros
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelFiltro.setBackground(new Color(228, 228, 255));
        panelFiltro.setBorder(BorderFactory.createTitledBorder("COMENSALES QUE CONSUMIERON"));

        JLabel lblServicio = new JLabel("Servicio:");
        lblServicio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelFiltro.add(lblServicio);

        comboServicio = new JComboBox<>(new String[] { "todos", "desayuno", "almuerzo" });
        configurarEstiloCombo(comboServicio);
        panelFiltro.add(comboServicio);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelFiltro.add(lblTipo);

        comboTipo = new JComboBox<>(
                new String[] { "todos", "estudiante", "estudiante (b)", "estudiante (e)", "profesor", "trabajador" });
        configurarEstiloCombo(comboTipo);
        panelFiltro.add(comboTipo);

        btnFiltrar = new BotonSimple("Filtrar");
        btnFiltrar.setPreferredSize(new Dimension(100, 30));
        btnFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelFiltro.add(btnFiltrar);

        panel.add(panelFiltro, BorderLayout.NORTH);

        // Lista de comensales
        modeloLista = new DefaultListModel<>();
        listaComensales = new JList<>(modeloLista);
        listaComensales.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaComensales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaComensales.setBackground(new Color(245, 245, 255));
        listaComensales.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(listaComensales);
        scrollPane.setBackground(new Color(228, 228, 255));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane, BorderLayout.CENTER);

        // Contador de resultados
        lblContador = new JLabel("Total: 0 comensales");
        lblContador.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblContador.setHorizontalAlignment(SwingConstants.CENTER);
        lblContador.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Panel inferior: contador + PanelInferiorPUI
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.setOpaque(false);
        panelSur.add(lblContador, BorderLayout.NORTH);
        PanelInferiorPUI panelAbajo = new PanelInferiorPUI();
        panelSur.add(panelAbajo, BorderLayout.SOUTH);
        panel.add(panelSur, BorderLayout.SOUTH);

        return panel;
    }

    private void configurarEstiloCombo(JComboBox<String> combo) {
        combo.setOpaque(true);
        combo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        combo.setBackground(new Color(200, 177, 200));
        combo.setForeground(Color.BLACK);
        combo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        combo.setPreferredSize(new Dimension(140, 30));
    }

    public void asignarControlador(ActionListener ctrl) {
        if (btnFiltrar != null) {
            btnFiltrar.setActionCommand("FILTRAR");
            btnFiltrar.addActionListener(ctrl);
        }
    }

    public void limpiarLista() {
        modeloLista.clear();
    }

    public void agregarComensal(String linea) {
        modeloLista.addElement(linea);
    }

    public String getFiltroServicio() {
        return (String) comboServicio.getSelectedItem();
    }

    public String getFiltroTipo() {
        return (String) comboTipo.getSelectedItem();
    }

    public int getCantidadComensales() {
        return modeloLista.getSize();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void setContador(int count) {
        lblContador.setText("Total: " + count + " comensales");
    }
}
