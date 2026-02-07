package comgest.model;

import java.util.UUID;

public class MenuItem {
    private String name;
    private String descripcion;
    private double precio;
    private String imgPath;
    private String id;


    public MenuItem(String name, String descripcion, double precio , String imgPath) {
        this.name = name;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imgPath = imgPath;
        this.id = UUID.randomUUID().toString();
    }

    // getters
    public String getName() {
        return name;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getImgPath() {
        return imgPath;
    }
    public double getPrecio() { return precio; }
}
