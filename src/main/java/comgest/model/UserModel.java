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
import au.com.bytecode.opencsv.CSVReader;

//LIBRERIAS USADAS: BCRYPT para hashear contraseñas, OpenCSV para leer la DB de secretaria y Gson para json

public class UserModel {
    private List<Usuario> lista_usuarios;
    private String database_path = "src\\main\\java\\comgest\\data";

    public UserModel() {
        this.lista_usuarios = new ArrayList<>();
        cargarUsuarios();
    }

    public boolean RegistrarUsuario(String name, String password, String email, String cedula, float saldo) {
        String[] datosSecretaria = obtenerDatosSecretaria(cedula);
        if (datosSecretaria == null) {
            return false;
        }
        String role = datosSecretaria[0];
        String pfpPath = datosSecretaria[1];

        String password_hasheada = BCrypt.hashpw(password, BCrypt.gensalt());

        Gson gson = new Gson();
        lista_usuarios.add(new Usuario(name, password_hasheada, email, cedula, role, saldo, pfpPath));

        try (FileWriter writer = new FileWriter(database_path + "/DB_usuarios.json")) {
            gson.toJson(lista_usuarios, writer);
            System.out.println("Lista actualizada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public Usuario autenticar(String cedula, String password_candidata) {
        for (Usuario u : lista_usuarios) {
            if (u.getCedula().equals(cedula)) {
                if (BCrypt.checkpw(password_candidata, u.getPassword())) {
                    return u;
                }
            }
        }
        return null;
    }

    public void cargarUsuarios() {
        try (FileReader reader = new FileReader(database_path + "/DB_usuarios.json")) {
            // Definimos que el JSON es una lista de objetos Usuario
            Type tipoLista = new TypeToken<ArrayList<Usuario>>() {
            }.getType();

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

    public boolean verificarCorreoExistente(String email) {
        for (Usuario u : lista_usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true; // El correo ya está registrado
            }
        }
        return false;
    }

    public boolean verificarCedulaExistente(String cedula) {
        for (Usuario u : lista_usuarios) {
            if (u.getCedula().equalsIgnoreCase(cedula)) {
                return true; // La cedula ya está registrado
            }
        }
        return false;
    }

    private String[] obtenerDatosSecretaria(String cedula) {
        if (cedula == null) {
            return null;
        }

        String cedulaBuscada = cedula.trim();
        if (cedulaBuscada.isEmpty()) {
            return null;
        }

        String ruta = database_path + "/UCVSecDB.csv";
        // Lee la DB externa de secretaria (cedula, rol, imagen) y devuelve el rol.
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            String[] parts;
            while ((parts = reader.readNext()) != null) {
                // Cada fila debe tener al menos cedula y rol.
                if (parts.length < 2) {
                    continue;
                }
                String cedulaActual = parts[0].trim();
                if (cedulaBuscada.equals(cedulaActual)) {
                    String rol = parts[1].trim();
                    String imagen = parts.length > 2 ? parts[2].trim() : "";
                    return new String[] { rol, imagen };
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer UCVSecDB.csv: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarUsuario(Usuario usuarioActualizado) {
        if (usuarioActualizado == null)
            return false;

        cargarUsuarios();

        boolean encontrado = false;
        for (int i = 0; i < lista_usuarios.size(); i++) {
            Usuario u = lista_usuarios.get(i);
            if (u.getId().equals(usuarioActualizado.getId())) {
                lista_usuarios.set(i, usuarioActualizado);
                encontrado = true;
                break;
            }
        }

        if (!encontrado)
            return false;

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(database_path + "/DB_usuarios.json")) {
            gson.toJson(lista_usuarios, writer);
            System.out.println("Usuario actualizado exitosamente y guardado en JSON.");
            return true;
        } catch (IOException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }
}
