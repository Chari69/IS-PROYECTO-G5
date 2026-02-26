package comgest.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MenuModelAdditionalTest {
    private MenuModel model;

    @Before
    public void setUp() {
        model = new MenuModel();
        // limpiamos cualquier elemento preexistente para tener un estado controlado
        model.getMenuItems().clear();
    }

    @Test
    public void actualizarMenuItemConIdVacioDevuelveFalse() {
        boolean result = model.actualizarMenuItem(" ", "n", "d", "img", "h");
        assertFalse(result);
    }

    @Test
    public void actualizarMenuItemNoExisteDevuelveFalse() {
        boolean result = model.actualizarMenuItem("nonexistent", "n", "d", "img", "h");
        assertFalse(result);
    }
}
