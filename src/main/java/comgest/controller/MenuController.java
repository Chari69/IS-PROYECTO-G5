package comgest.controller;

import comgest.utils.*;

import comgest.model.MenuModel;
import comgest.model.UserSession;
import comgest.model.CCBModel;
import comgest.model.CCB;
import comgest.view.MenuGUI;
import comgest.view.MenuFormDialog;
import comgest.view.ReservarGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class MenuController implements ActionListener {
    public static final String ACTION_ORDENAR_DESAYUNO = "ORDENAR_DESAYUNO";
    public static final String ACTION_ORDENAR_ALMUERZO = "ORDENAR_ALMUERZO";
    public static final String ACTION_AGREGAR_MENU = "AGREGAR_MENU";
    public static final String ACTION_EDITAR_DESAYUNO = "EDITAR_DESAYUNO";
    public static final String ACTION_EDITAR_ALMUERZO = "EDITAR_ALMUERZO";
    public static final String ACTION_ELIMINAR_DESAYUNO = "ELIMINAR_DESAYUNO";
    public static final String ACTION_ELIMINAR_ALMUERZO = "ELIMINAR_ALMUERZO";

    private final MenuGUI view;
    private final MenuModel menuModel;
    private final CCBModel ccbModel;

    public MenuController(MenuGUI view) {
        this(view, new MenuModel(), new CCBModel());
    }

    public MenuController(MenuGUI view, MenuModel menuModel) {
        this(view, menuModel, new CCBModel());
    }

    public MenuController(MenuGUI view, MenuModel menuModel, CCBModel ccbModel) {
        if (view == null) {
            throw new IllegalArgumentException("view cannot be null");
        }
        if (menuModel == null) {
            throw new IllegalArgumentException("menuModel cannot be null");
        }
        if (ccbModel == null) {
            throw new IllegalArgumentException("ccbModel cannot be null");
        }
        this.view = view;
        this.menuModel = menuModel;
        this.ccbModel = ccbModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case ACTION_ORDENAR_DESAYUNO:
                ordenarComida("Desayuno");
                break;
            case ACTION_ORDENAR_ALMUERZO:
                ordenarComida("Almuerzo");
                break;
            case ACTION_AGREGAR_MENU:
                agregarMenu();
                break;
            case ACTION_EDITAR_DESAYUNO:
                editarMenu("Desayuno");
                break;
            case ACTION_EDITAR_ALMUERZO:
                editarMenu("Almuerzo");
                break;
            case ACTION_ELIMINAR_DESAYUNO:
                eliminarMenu("Desayuno");
                break;
            case ACTION_ELIMINAR_ALMUERZO:
                eliminarMenu("Almuerzo");
                break;
            default:
                System.err.println("Comando desconocido: " + command);
        }
    }

    public void cargarMenu() {
        UserSession session = UserSession.getInstance();
        boolean isAdmin = session != null && session.isActive() && Utils.isAdminRole(session.getRole());

        // Cargar items del menú desde MenuModel
        var items = menuModel.getMenuItems();
        int cantidadItems = (items != null) ? items.size() : 0;

        // Mostrar paneles según la cantidad de items
        view.mostrarPanelesSegunItems(cantidadItems);
        view.setAdminVisible(isAdmin);

        // Obtener el CCB actual
        CCB ccbActual = ccbModel.recargarCCB();

        // El modelo CCB calculará el precio con descuento si hay sesión activa, sino
        // pasará null (100%)
        double precioMostrar = (session != null && session.isActive())
                ? ccbActual.calcularPrecioConDescuento(session.getRole())
                : ccbActual.getCCBValor();

        if (items != null && items.size() >= 1) {
            // Actualizar desayuno (primer item)
            var desayuno = items.get(0);
            String horarioDesayuno = normalizarHorario(desayuno.getHorario(), "7:00 AM - 10:00 AM");
            view.actualizarDesayuno(desayuno.getName(), desayuno.getDescripcion(),
                    precioMostrar, desayuno.getImgPath(), desayuno.getId(), horarioDesayuno);

            if (items.size() >= 2) {
                // Actualizar almuerzo (segundo item)
                var almuerzo = items.get(1);
                String horarioAlmuerzo = normalizarHorario(almuerzo.getHorario(), "12:00 PM - 3:00 PM");
                view.actualizarAlmuerzo(almuerzo.getName(), almuerzo.getDescripcion(),
                        precioMostrar, almuerzo.getImgPath(), almuerzo.getId(), horarioAlmuerzo);
            }
        }
    }

    private void ordenarComida(String tipoComida) {
        boolean esDesayuno = tipoComida.equalsIgnoreCase("Desayuno");

        String titulo = esDesayuno ? view.getTituloDesayuno() : view.getTituloAlmuerzo();
        titulo = titulo.replaceAll("<[^>]*>", "").trim();

        String descripcion = esDesayuno ? view.getDescripcionDesayuno() : view.getDescripcionAlmuerzo();

        double precio = 0;
        String textoCompleto = esDesayuno ? view.getPrecioDesayuno() : view.getPrecioAlmuerzo();
        String soloNumero = textoCompleto.replace("Precio: $", "").trim();
        if (!soloNumero.isEmpty()) {
            precio = Double.parseDouble(soloNumero);
        }

        javax.swing.Icon icon = esDesayuno ? view.getIconDesayuno() : view.getIconAlmuerzo();

        ReservarGUI.mostrarReserva(SwingUtilities.getWindowAncestor(view), titulo, descripcion, precio, icon);
    }

    private void agregarMenu() {
        if (!verificarAdmin())
            return;

        // Solo permitir 2 slots (desayuno y almuerzo)
        var items = menuModel.getMenuItems();
        if (items != null && items.size() >= 2) {
            view.showMessage("Ya hay 2 menús. Debe eliminar uno existente primero.");
            return;
        }

        // Obtener el frame padre para el diálogo
        javax.swing.JFrame frameParent = (javax.swing.JFrame) SwingUtilities.getWindowAncestor(view);
        MenuFormDialog dialog = new MenuFormDialog(frameParent, "Agregar Nuevo Menú");
        new MenuFormController(dialog);

        // Establecer valores por defecto según la posición
        String nombreDefault = items == null || items.isEmpty() ? "Desayuno" : "Almuerzo";
        dialog.setNombre(nombreDefault);
        dialog.setDescripcion("Descripción del " + nombreDefault.toLowerCase());
        dialog.setImagenRuta("resources/arepa.jpg");
        dialog.setHorario(nombreDefault.equalsIgnoreCase("Desayuno")
                ? "7:00 AM - 10:00 AM"
                : "12:00 PM - 3:00 PM");

        // Mostrar el diálogo
        if (dialog.mostrarDialog()) {
            // Validar entrada
            String nombre = dialog.getNombre();
            String descripcion = dialog.getDescripcion();
            String imagen = dialog.getImagenRuta();
            String horario = dialog.getHorario();

            if (nombre.isEmpty()) {
                view.showMessage("El nombre del menú no puede estar vacío");
                return;
            }

            if (descripcion.isEmpty()) {
                view.showMessage("La descripción no puede estar vacía");
                return;
            }

            // Agregar el menú
            menuModel.agregarMenuItem(nombre, descripcion, imagen, horario);
            view.showMessage("Menú '" + nombre + "' agregado exitosamente");
            cargarMenu();
        }
    }

    private void editarMenu(String tipoComida) {
        if (!verificarAdmin())
            return;

        abrirDialogEdicion(tipoComida);
    }

    private void abrirDialogEdicion(String tipoComida) {
        String itemId = obtenerIdMenu(tipoComida);
        if (itemId == null) {
            return;
        }

        var item = menuModel.buscarPorId(itemId);
        if (item == null) {
            view.showMessage("Menú no encontrado");
            return;
        }

        javax.swing.JFrame frameParent = (javax.swing.JFrame) SwingUtilities.getWindowAncestor(view);
        MenuFormDialog dialog = new MenuFormDialog(frameParent, "Editar Menú - " + item.getName());
        new MenuFormController(dialog);
        dialog.setNombre(item.getName());
        dialog.setDescripcion(item.getDescripcion());
        dialog.setImagenRuta(item.getImgPath());
        dialog.setHorario(item.getHorario());

        if (dialog.mostrarDialog()) {
            String nuevoNombre = dialog.getNombre();
            String nuevaDescripcion = dialog.getDescripcion();
            String nuevaImagen = dialog.getImagenRuta();
            String nuevoHorario = dialog.getHorario();

            if (nuevoNombre.isEmpty()) {
                view.showMessage("El nombre del menú no puede estar vacío");
                return;
            }

            if (nuevaDescripcion.isEmpty()) {
                view.showMessage("La descripción no puede estar vacía");
                return;
            }

            if (menuModel.actualizarMenuItem(itemId, nuevoNombre, nuevaDescripcion, nuevaImagen, nuevoHorario)) {
                view.showMessage("Menú actualizado exitosamente");
                cargarMenu();
            } else {
                view.showMessage("Error al actualizar el menú");
            }
        }
    }

    private void eliminarMenu(String tipoComida) {
        if (!verificarAdmin())
            return;

        String itemId = obtenerIdMenu(tipoComida);
        if (itemId == null)
            return;

        int confirmacion = view.showConfirmDialog("¿Eliminar " + tipoComida + "?");
        if (confirmacion == 0) {
            if (menuModel.eliminarMenuItem(itemId)) {
                view.showMessage("Menú eliminado exitosamente");
                cargarMenu();
            } else {
                view.showMessage("Error al eliminar el menú");
            }
        }
    }

    private String normalizarHorario(String horario, String valorPorDefecto) {
        if (horario == null || horario.trim().isEmpty()) {
            return valorPorDefecto;
        }
        return horario.trim();
    }

    private boolean verificarAdmin() {
        UserSession session = getSessionOrRedirect();
        if (session == null)
            return false;

        if (!Utils.isAdminRole(session.getRole())) {
            view.showMessage("Acceso denegado. Solo administradores.");
            return false;
        }

        return true;
    }

    private UserSession getSessionOrRedirect() {
        UserSession session = UserSession.getInstance();
        if (session == null || !session.isActive()) {
            view.showMessage("Sesión no activa");
            ControladorView.mostrarLogin();
            return null;
        }
        return session;
    }

    // === MÉTODOS HELPER PARA REDUCIR DUPLICACIÓN ===

    /**
     * Obtiene el ID del menú según el tipo
     */
    private String obtenerIdMenu(String tipoComida) {
        String itemId = tipoComida.equalsIgnoreCase("Desayuno")
                ? view.getIdDesayuno()
                : view.getIdAlmuerzo();

        if (itemId == null || itemId.trim().isEmpty()) {
            view.showMessage("Error: No se encontró la ID del menú");
            return null;
        }
        return itemId;
    }
}
