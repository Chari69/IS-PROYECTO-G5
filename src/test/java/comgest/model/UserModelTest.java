package comgest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;

public class UserModelTest {
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    private File dataDir;

    @Before
    public void setUp() throws Exception {
        dataDir = temp.newFolder("data");
        writeText(new File(dataDir, "DB_usuarios.json"), "[]");
        writeText(new File(dataDir, "UCVSecDB.csv"), "");
    }

    @Test
    public void registrarUsuarioRetornaFalsoCuandoLaCedulaNoEstaEnElCSV() throws Exception {
        UserModel model = createModelWithTempData();

        boolean result = model.RegistrarUsuario("Ana", "secret", "ana@ucv.ve", "999", 10.0f);

        assertFalse(result);
    }

    @Test
    public void registrarUsuarioYAutenticar() throws Exception {
        writeText(new File(dataDir, "UCVSecDB.csv"), "123,Administrador,123.png");
        UserModel model = createModelWithTempData();

        boolean registered = model.RegistrarUsuario("Luis", "secret", "luis@ucv.ve", "123", 25.0f);
        Usuario autenticado = model.autenticar("123", "secret");
        Usuario fallido = model.autenticar("123", "bad");

        assertTrue(registered);
        assertNotNull(autenticado);
        assertEquals("Administrador", autenticado.getRole());
        assertNull(fallido);
    }

    @Test
    public void cargarUsuariosYCompruebaVerificacionesDeCorreoYCedulaExistentes() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String hashed = BCrypt.hashpw("secret", BCrypt.gensalt());
        usuarios.add(new Usuario("Marta", hashed, "marta@ucv.ve", "777", "Estudiante", 5.0f, "default.png"));
        writeText(new File(dataDir, "DB_usuarios.json"), new Gson().toJson(usuarios));

        UserModel model = createModelWithTempData();
        model.cargarUsuarios();

        assertTrue(model.verificarCorreoExistente("marta@ucv.ve"));
        assertTrue(model.verificarCedulaExistente("777"));
    }

    @Test
    public void autenticarUsuarioNoExistenteDevuelveNull() throws Exception {
        UserModel model = createModelWithTempData();
        // sin usuarios en la base de datos temporales
        Usuario u = model.autenticar("doesnotexist", "pwd");
        assertNull(u);
    }

    @Test
    public void verificarCorreoYCedulaNoExistentesSonFalse() throws Exception {
        UserModel model = createModelWithTempData();
        // lista vacia tras carga
        assertFalse(model.verificarCorreoExistente("no@ucv.ve"));
        assertFalse(model.verificarCedulaExistente("000"));
    }

    @Test
    public void actualizarUsuarioConExitoModificaSaldoYRetornaTrue() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String hashed = BCrypt.hashpw("secret", BCrypt.gensalt());
        Usuario u = new Usuario("user", hashed, "jose@ucv.ve", "123", "Estudiante", 10.0f, "default.png");
        usuarios.add(u);
        writeText(new File(dataDir, "DB_usuarios.json"), new Gson().toJson(usuarios));

        UserModel model = createModelWithTempData();

        u.addSaldo(50.0f);

        boolean result = model.actualizarUsuario(u);
        assertTrue("La actualizacion debio ser completada con exito", result);

        // Forzar una recarga simulando una nueva instancia
        UserModel anotherModelInstance = createModelWithTempData();
        Usuario verificado = anotherModelInstance.autenticar("123", "secret");

        // El id deberia seguir existiendo y haber sido guardado el saldo
        assertNotNull(verificado);
        assertEquals(60.0f, verificado.getSaldo(), 0.0f);
    }

    @Test
    public void actualizarUsuarioInexistenteRetornaFalse() throws Exception {
        UserModel model = createModelWithTempData();

        Usuario fantasma = new Usuario("Fantasma", "pwd", "fan@ucv.ve", "000", "Estudiante", 0, "default.png");
        fantasma.addSaldo(100.0f);

        boolean result = model.actualizarUsuario(fantasma);
        assertFalse(result);
    }

    @Test
    public void actualizarUsuarioNuloRetornaFalse() throws Exception {
        UserModel model = createModelWithTempData();
        assertFalse(model.actualizarUsuario(null));
    }

    // Metodos Auxiliares
    private UserModel createModelWithTempData() throws Exception {
        UserModel model = new UserModel();
        setDatabasePath(model, dataDir.getAbsolutePath());
        model.cargarUsuarios();
        return model;
    }

    private void setDatabasePath(UserModel model, String path) throws Exception {
        Field field = UserModel.class.getDeclaredField("database_path");
        field.setAccessible(true);
        field.set(model, path);
    }

    private void writeText(File file, String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }

}
