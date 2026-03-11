package comgest.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReservaModel {
    private List<Reserva> reservas;
    private String database_path = "src\\main\\java\\comgest\\data\\DB_reservas.json";
    private Gson gson;
    private MenuModel menuModel;

    public ReservaModel() {
        this.reservas = new ArrayList<>();
        this.gson = new Gson();
        this.menuModel = new MenuModel();
        cargarReservas();
    }

    /**
     * Agrega una reserva solo si el menu item existe.
     */
    public boolean agregarReserva(String cedulaUsuario, String idMenu) {
        MenuItem item = menuModel.buscarPorId(idMenu);
        if (item == null) {
            System.out.println("No se puede reservar: el menú con ID " + idMenu + " no existe.");
            return false;
        }

        reservas.add(new Reserva(cedulaUsuario, idMenu));
        guardarReservas();
        return true;
    }

    public boolean eliminarReserva(String cedulaUsuario, String idMenu) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            if (cedulaUsuario.equals(r.getCedulaUsuario()) && idMenu.equals(r.getIdMenu())) {
                reservas.remove(i);
                guardarReservas();
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina TODAS las reservas asociadas a un menu item (cascade delete).
     */
    public void eliminarReservasPorMenu(String idMenu) {
        Iterator<Reserva> it = reservas.iterator();
        boolean eliminado = false;
        while (it.hasNext()) {
            if (idMenu.equals(it.next().getIdMenu())) {
                it.remove();
                eliminado = true;
            }
        }
        if (eliminado) {
            guardarReservas();
            System.out.println("Reservas asociadas al menú " + idMenu + " eliminadas.");
        }
    }

    /**
     * Busca una reserva pendiente (no consumida) para un usuario y menu item.
     */
    public Reserva buscarReservaPendiente(String cedula, String idMenu) {
        for (Reserva r : reservas) {
            if (cedula.equals(r.getCedulaUsuario())
                    && idMenu.equals(r.getIdMenu())
                    && !r.isConsumido()) {
                return r;
            }
        }
        return null;
    }

    /**
     * Busca si el usuario ya consumió una reserva para este menu item.
     */
    public Reserva buscarReservaConsumida(String cedula, String idMenu) {
        for (Reserva r : reservas) {
            if (cedula.equals(r.getCedulaUsuario())
                    && idMenu.equals(r.getIdMenu())
                    && r.isConsumido()) {
                return r;
            }
        }
        return null;
    }

    /**
     * Obtiene todas las reservas pendientes (no consumidas) de un usuario.
     */
    public List<Reserva> getReservasPendientesPorUsuario(String cedulaUsuario) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (cedulaUsuario.equals(r.getCedulaUsuario()) && !r.isConsumido()) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    /**
     * Marca una reserva como consumida y persiste.
     */
    public void marcarConsumida(Reserva reserva) {
        reserva.setConsumido(true);
        guardarReservas();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Reserva> getReservasPorUsuario(String cedulaUsuario) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (cedulaUsuario.equals(r.getCedulaUsuario())) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void cargarReservas() {
        try (FileReader reader = new FileReader(database_path)) {
            Type tipoLista = new TypeToken<ArrayList<Reserva>>() {
            }.getType();

            this.reservas = gson.fromJson(reader, tipoLista);

            if (this.reservas == null) {
                this.reservas = new ArrayList<>();
            }
            System.out.println("Reservas cargadas: " + reservas.size());
        } catch (IOException e) {
            this.reservas = new ArrayList<>();
            System.out.println("No se encontró el archivo de reservas, se creó una lista nueva.");
        }
    }

    private void guardarReservas() {
        try (FileWriter writer = new FileWriter(database_path)) {
            gson.toJson(reservas, writer);
            System.out.println("Reservas actualizadas.");
        } catch (IOException e) {
            System.out.println("Error al guardar reservas: " + e.getMessage());
        }
    }
}
