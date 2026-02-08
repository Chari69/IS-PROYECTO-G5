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

    public MenuModel() {
        this.menu_items = new ArrayList<>();
        cargarMenuItems();
    }

    public void agregarMenuItem(String name, String descripcion, String imgPath, double precio) {
        Gson gson = new Gson();
        menu_items.add(new MenuItem(name, descripcion, precio, imgPath));

        try (FileWriter writer = new FileWriter(database_path)) {
            gson.toJson(menu_items, writer);
            System.out.println("Menu actualizado.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
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
}
