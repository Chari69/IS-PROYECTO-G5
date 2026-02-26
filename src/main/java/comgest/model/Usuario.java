package comgest.model;

import java.util.UUID;
//LIBRERIAS USADAS: UUID para generar un id aleatorio e irrepetible para los usuarios

public class Usuario {
    private String id;
    private String name;
    private String password;
    private String email;
    private String cedula;
    private String role;
    private float saldo;

    public Usuario(String name, String password, String email, String cedula, String role, float saldo) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.password = password;
        this.email = email;
        this.cedula = cedula;
        this.role = role;
        this.saldo = saldo;
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

    public void addSaldo(float saldo) {
        this.saldo += saldo;
    }

    public void subSaldo(float saldo) {
        this.saldo -= saldo;
    }
}
