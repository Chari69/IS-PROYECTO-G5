package comgest.controller;

import comgest.model.Reserva;
import comgest.model.ReservaModel;
import comgest.model.MenuModel;
import comgest.model.MenuItem;
import comgest.model.UserModel;
import comgest.model.Usuario;
import comgest.view.ListCom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListComController implements ActionListener {

    private final ListCom view;
    private final ReservaModel reservaModel;
    private final MenuModel menuModel;
    private final UserModel userModel;

    public ListComController(ListCom view) {
        this(view, ReservaModel.getInstance(), MenuModel.getInstance(), UserModel.getInstance());
    }

    public ListComController(ListCom view, ReservaModel reservaModel, MenuModel menuModel, UserModel userModel) {
        if (view == null) {
            throw new IllegalArgumentException("view no puede ser null");
        }
        this.view = view;
        this.reservaModel = reservaModel;
        this.menuModel = menuModel;
        this.userModel = userModel;
        this.view.asignarControlador(this);
    }

    public void cargarListaComensales() {
        filtrar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("FILTRAR".equals(e.getActionCommand())) {
            filtrar();
        }
    }

    private void filtrar() {
        // Forzar recarga desde disco para datos frescos
        reservaModel.invalidar();
        reservaModel.cargarReservas();
        menuModel.cargarMenuItems();
        userModel.invalidar();
        userModel.cargarUsuarios();

        String filtroServicio = view.getFiltroServicio();
        String filtroTipo = view.getFiltroTipo();
        List<Reserva> todas = reservaModel.getReservas();

        view.limpiarLista();
        int count = 0;

        for (Reserva r : todas) {
            if (!r.isConsumido()) {
                continue;
            }

            MenuItem item = menuModel.buscarPorId(r.getIdMenu());
            if (item == null) {
                continue;
            }

            // Filtrar por servicio si no es "todos"
            if (!"todos".equals(filtroServicio)) {
                String nombreMenu = item.getName().toLowerCase();
                if (!nombreMenu.contains(filtroServicio.toLowerCase())) {
                    continue;
                }
            }

            // Buscar datos del usuario
            Usuario usuario = userModel.buscarPorCedula(r.getCedulaUsuario());
            String nombreUsuario = usuario != null ? usuario.getName() : "Desconocido";
            String rolUsuario = usuario != null ? usuario.getRole() : "N/A";

            // Filtrar por tipo de usuario si no es "todos"
            if (!"todos".equals(filtroTipo) && usuario != null) {
                String rolNorm = rolUsuario.toLowerCase().trim();
                if (!rolNorm.contains(filtroTipo.toLowerCase())) {
                    continue;
                }
            }

            String linea = String.format("CI: %s | %s | Rol: %s | Menú: %s",
                    r.getCedulaUsuario(), nombreUsuario, rolUsuario, item.getName());

            view.agregarComensal(linea);
            count++;
        }

        view.setContador(count);
    }
}
