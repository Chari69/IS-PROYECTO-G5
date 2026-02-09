package comgest.model;

import java.util.UUID;

public class MenuItem {
    private String name;
    private String descripcion;
    private String imgPath;
    private String id;

    public MenuItem(String name, String descripcion, String imgPath) {
        this.name = name;
        this.descripcion = descripcion;
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

    public String getId() {
        return id;
    }
}
