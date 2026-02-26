package comgest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class MenuModelTest {
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    private File dataDir;
    private MenuModel model;

    @Before
    public void setUp() throws Exception {
        dataDir = temp.newFolder("data");
        writeText(new File(dataDir, "DB_menu.json"), "[]");

        model = createModelWithTempData();
    }

    @Test
    public void agregarMenuItemIncrementaTamanioYGuardaDatos() {
        model.agregarMenuItem("Pizza", "Deliciosa pizza", "img.png", "10:00-22:00");
        List<MenuItem> items = model.getMenuItems();

        assertEquals("Deberia haber un elemento agregado", 1, items.size());
        assertEquals("El nombre debe coincidir", "Pizza", items.get(0).getName());
        assertEquals("La descripcion debe coincidir", "Deliciosa pizza", items.get(0).getDescripcion());
        assertNotNull("El ID no debe ser nulo", items.get(0).getId());
    }

    @Test
    public void actualizarMenuItemConExitoDevuelveTrue() {
        model.agregarMenuItem("Pasta", "Pasta bolonesa", "pasta.png", "12:00-20:00");
        MenuItem item = model.getMenuItems().get(0);
        String id = item.getId();

        boolean result = model.actualizarMenuItem(id, "Cena de Pasta", "Nueva descripcion", "foto2.png", "13:00-21:00");
        assertTrue("Deberia retornar true si la actualizacion es exitosa", result);

        MenuItem updatedItem = model.buscarPorId(id);
        assertEquals("El nombre debio actualizarse", "Cena de Pasta", updatedItem.getName());
        assertEquals("La descripcion debio actualizarse", "Nueva descripcion", updatedItem.getDescripcion());
        assertEquals("El horario debio actualizarse", "13:00-21:00", updatedItem.getHorario());
    }

    @Test
    public void actualizarMenuItemConIdVacioDevuelveFalse() {
        boolean result = model.actualizarMenuItem(" ", "n", "d", "img", "h");
        assertFalse("Actualizar con ID en blanco deberia fallar", result);
    }

    @Test
    public void actualizarMenuItemNoExisteDevuelveFalse() {
        boolean result = model.actualizarMenuItem("nonexistent", "n", "d", "img", "h");
        assertFalse(result);
    }

    @Test
    public void eliminarMenuItemConExitoDisminuyeTamanio() {
        model.agregarMenuItem("Burger", "Clasica", "burger.png", "10:00");
        model.agregarMenuItem("Fries", "Papas fritas", "fries.png", "10:00");

        String idToRemove = model.getMenuItems().get(0).getId();

        boolean result = model.eliminarMenuItem(idToRemove);
        assertTrue("La eliminacion deberia retornar true por exito", result);
        assertEquals("El tamaño de la lista debio reducirse", 1, model.getMenuItems().size());
        assertNull("El elemento ya no deberia existir", model.buscarPorId(idToRemove));
    }

    @Test
    public void eliminarMenuItemConIdVacioDevuelveFalse() {
        boolean result = model.eliminarMenuItem("");
        assertFalse(result);
    }

    @Test
    public void eliminarMenuItemNoExistenteDevuelveFalse() {
        boolean result = model.eliminarMenuItem("id-falso");
        assertFalse(result);
    }

    @Test
    public void buscarPorIdEncuentraElementoCorrecto() {
        model.agregarMenuItem("Helado", "Vainilla", "ice.png", "15:00-18:00");
        MenuItem item = model.getMenuItems().get(0);

        MenuItem found = model.buscarPorId(item.getId());
        assertNotNull("Deberia encontrar el elemento", found);
        assertEquals("Los nombres deben coincidir", item.getName(), found.getName());
    }

    @Test
    public void buscarPorIdDevuelveNullSiNoExiste() {
        MenuItem found = model.buscarPorId("no-existe");
        assertNull(found);
    }

    // Metodos Auxiliares
    private MenuModel createModelWithTempData() throws Exception {
        MenuModel m = new MenuModel();
        setDatabasePath(m, new File(dataDir, "DB_menu.json").getAbsolutePath());
        m.getMenuItems().clear();
        m.cargarMenuItems();
        return m;
    }

    private void setDatabasePath(MenuModel m, String path) throws Exception {
        Field field = MenuModel.class.getDeclaredField("database_path");
        field.setAccessible(true);
        field.set(m, path);
    }

    private void writeText(File file, String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }
}
