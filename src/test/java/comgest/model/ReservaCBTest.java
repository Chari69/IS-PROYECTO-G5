package comgest.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ReservaCBTest {

    private ReservaModel sistema;

    @Before
    public void setUp() {
        sistema = ReservaModel.getInstance();
    }

    // Camino 1: El menú no existe (item == null)
	// Salida esperada: false
    @Test
    public void testAgregarReserva_Camino1_MenuNoExiste() {
        boolean resultado = sistema.agregarReserva("27000111", "MENU-999");

        assertFalse("La reserva debe fallar si el menú no existe", resultado);
    }

    // Camino 2: El menú sí existe
	// Salida esperada: true
    @Test
    public void testAgregarReserva_Camino2_MenuExiste() {
        boolean resultado = sistema.agregarReserva("27000111", "d7c9d611-3297-453a-9e51-cb61570c7dc5");

        assertTrue("La reserva debe ser exitosa si el menú existe", resultado);
    }
}