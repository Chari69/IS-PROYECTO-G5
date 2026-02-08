package comgest.model;

import java.util.UUID;

public class MenuItem {
    private String name;
    private String descripcion;
    private double precio;
    private String imgPath;
    private String id;

    public MenuItem(String name, String descripcion, double precio, String imgPath) {
        this.name = name;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imgPath = imgPath;
        this.id = UUID.randomUUID().toString();
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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

    public double getPrecio() {
        return precio;
    }

    public String getId() {
        return id;
    }
}
