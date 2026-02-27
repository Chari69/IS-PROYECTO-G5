package comgest.model;

public final class UserSession {
    private static UserSession instance;
    private final Usuario usuario;

    private UserSession(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("usuario cannot be null");
        }
        this.usuario = usuario;
    }

    public static void startSession(Usuario usuario) {
        instance = new UserSession(usuario);
    }

    public static UserSession getInstance() {
        return instance;
    }

    public static void clearSession() {
        instance = null;
    }

    public boolean isActive() {
        return usuario != null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getId() {
        return usuario.getId();
    }

    public String getName() {
        return usuario.getName();
    }

    public String getEmail() {
        return usuario.getEmail();
    }

    public float getSaldo() {
        return usuario.getSaldo();
    }

    public String getRole() {
        return usuario.getRole();
    }

    public String getCedula() {
        return usuario.getCedula();
    }

    public String getPfpPath() {
        return usuario.getPfpPath();
    }
}
