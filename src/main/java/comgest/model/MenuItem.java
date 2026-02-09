package comgest.model;

import java.util.UUID;

public class MenuItem {
    private String name;
    private String descripcion;
    private String imgPath;
    private String horario;
    private String id;

    public MenuItem(String name, String descripcion, String imgPath, String horario) {
        this.name = name;
        this.descripcion = descripcion;
        this.imgPath = imgPath;
        this.horario = horario;
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

    public void setHorario(String horario) {
        this.horario = horario;
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

    public String getHorario() {
        return horario;
    }
}
