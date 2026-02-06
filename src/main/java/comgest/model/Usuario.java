package comgest.model;
import java.util.UUID;
//LIBRERIAS USADAS: UUID para generar un id aleatorio e irrepetible para los usuarios

public class Usuario {
    private String name;
    private String password;
    private String email;
    private String id;

    public Usuario(String name, String password,String email){
        this.name=name;
        this.password=password;
        this.email=email;
        this.id = UUID.randomUUID().toString();
    }

    //getters 
    public String getId(){return id;}
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
