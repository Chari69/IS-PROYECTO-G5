package comgest.model;

import java.util.UUID;
import java.io.File;
//LIBRERIAS USADAS: UUID para generar un id aleatorio e irrepetible para los usuarios

public class Usuario {
    private String id;
    private String name;
    private String password;
    private String email;
    private String cedula;
    private String role;
    private float saldo;
    private String pfpPath;

    public Usuario(String name, String password, String email, String cedula, String role, float saldo,
            String pfpPath) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.password = password;
        this.email = email;
        this.cedula = cedula;
        this.role = role;
        this.saldo = saldo;
        this.pfpPath = pfpPathSet(pfpPath);
    }

    // getters
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public float getSaldo() {
        return saldo;
    }

    public String getRole() {
        return role;
    }

    public String getCedula() {
        return cedula;
    }

    public String getPfpPath() {
        return pfpPath;
    }

    public void addSaldo(float saldo) {
        this.saldo += saldo;
    }

    public void subSaldo(float saldo) {
        this.saldo -= saldo;
    }

    private String pfpPathSet(String pfp) {
        String imgPath = "src\\main\\java\\comgest\\data\\img\\pfp\\";
        try {
            if (pfp != null && !pfp.trim().isEmpty()) {
                File img = new File(imgPath + pfp);
                if (img.exists()) {
                    return imgPath + pfp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgPath + "default.png";
    }
}
