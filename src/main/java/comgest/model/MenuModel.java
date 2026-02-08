package comgest.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    private List<MenuItem> menu_items;
    private String database_path = "src\\main\\java\\comgest\\data\\DB_menu.json";
    private Gson gson;

    public MenuModel() {
        this.menu_items = new ArrayList<>();
        this.gson = new Gson();
        cargarMenuItems();
    }

    public void agregarMenuItem(String name, String descripcion, String imgPath, double precio) {
        menu_items.add(new MenuItem(name, descripcion, precio, imgPath));
        guardarMenuItems();
    }

    public boolean actualizarMenuItem(String id, String name, String descripcion, String imgPath, double precio) {
        String idNormalizado = normalizarId(id);
        if (idNormalizado.isEmpty()) {
            return false;
        }

        MenuItem item = buscarPorId(idNormalizado);
        if (item == null) {
            return false;
        }

        item.setName(name);
        item.setDescripcion(descripcion);
        item.setImgPath(imgPath);
        item.setPrecio(precio);
        guardarMenuItems();
        return true;
    }

    public boolean eliminarMenuItem(String id) {
        String idNormalizado = normalizarId(id);
        if (idNormalizado.isEmpty()) {
            return false;
        }

        for (int i = 0; i < menu_items.size(); i++) {
            if (idNormalizado.equals(menu_items.get(i).getId())) {
                menu_items.remove(i);
                guardarMenuItems();
                return true;
            }
        }

        return false;
    }

    public List<MenuItem> getMenuItems() {
        return menu_items;
    }

    public void cargarMenuItems() {
        try (FileReader reader = new FileReader(database_path)) {
            // Definimos que el JSON es una lista de objetos MenuItem
            Type tipoLista = new TypeToken<ArrayList<MenuItem>>() {
            }.getType();

            this.menu_items = new Gson().fromJson(reader, tipoLista);

            if (this.menu_items == null) {
                this.menu_items = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void guardarMenuItems() {
        try (FileWriter writer = new FileWriter(database_path)) {
            gson.toJson(menu_items, writer);
            System.out.println("Menu actualizado.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private MenuItem buscarPorId(String id) {
        for (MenuItem item : menu_items) {
            if (id.equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

    private String normalizarId(String id) {
        return id == null ? "" : id.trim();
    }
}
