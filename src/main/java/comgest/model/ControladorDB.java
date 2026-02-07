package comgest.model;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//LIBRERIAS USADAS: BCRYPT para hashear contraseñas y Gson para json

public class ControladorDB {
    private List<Usuario> lista_usuarios;
    private String database_path = "src\\main\\java\\comgest\\data";

    public ControladorDB() {
        this.lista_usuarios = new ArrayList<>();
        cargarUsuarios();
    }

    public void RegistrarUsuario(String name, String password,String email,float saldo){
        //firewalls;
        verificarCorreoExistente();

        String password_hasheada = BCrypt.hashpw(password, BCrypt.gensalt());

        Gson gson = new Gson();
        lista_usuarios.add(new Usuario(name, password_hasheada, email,saldo));

        try (FileWriter writer = new FileWriter(database_path+"/DB_usuarios.json")) {
            gson.toJson(lista_usuarios, writer); 
            System.out.println("Lista actualizada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean InicioDeSesion(String email,String password_candidata){
        for(Usuario u: lista_usuarios){
            if(u.getEmail().equals(email)){
                if(BCrypt.checkpw(password_candidata, u.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    public void cargarUsuarios() {
        try (FileReader reader = new FileReader(database_path+"/DB_usuarios.json")) {
            // Definimos que el JSON es una lista de objetos Usuario
            Type tipoLista = new TypeToken<ArrayList<Usuario>>(){}.getType();
            
            this.lista_usuarios = new Gson().fromJson(reader, tipoLista);
            
            if (this.lista_usuarios == null) {
                this.lista_usuarios = new ArrayList<>();
            }
            System.out.println("Usuarios cargados: " + lista_usuarios.size());
        } catch (IOException e) {
            this.lista_usuarios = new ArrayList<>();
            System.out.println("No se encontró el archivo, se creó una lista nueva.");
        }
    }

    private void verificarCorreoExistente(){

    }
}
