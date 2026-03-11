package comgest.model;

public class Reserva {
    private String cedulaUsuario;
    private String idMenu;
    private boolean consumido;

    public Reserva(String cedulaUsuario, String idMenu) {
        this.cedulaUsuario = cedulaUsuario;
        this.idMenu = idMenu;
        this.consumido = false;
    }

    // Getters
    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public boolean isConsumido() {
        return consumido;
    }

    // Setters
    public void setConsumido(boolean consumido) {
        this.consumido = consumido;
    }
}
