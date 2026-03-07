package comgest.view;

import comgest.view.components.FrameStyle;
import comgest.view.components.PanelInferiorPUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListCom {
    private JFrame frame;
    private JList<String> listaComensales;
    private DefaultListModel<String> modeloLista;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListCom view = new ListCom();
    
        });
    }

    public ListCom() {
        crearVentana();
    }

    public JPanel crearVentana() {
        // Usar FrameStyle para crear el frame
        frame = FrameStyle.crearFramePrincipal("ListCom - COMGEST-UCV");

        // Panel Principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

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

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);

        PanelInferiorPUI PanelAbajo = new PanelInferiorPUI();
        panel.add(PanelAbajo, BorderLayout.SOUTH);

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

    // Método para asignar controlador si es necesario
    public void asignarControlador(Object controller) {
        // Implementar si se agrega un controlador
    }
}
