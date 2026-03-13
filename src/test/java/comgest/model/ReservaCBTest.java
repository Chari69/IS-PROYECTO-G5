package comgest.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ReservaCBTest {

    private ReservaModel sistema;

    @Before
    public void setUp() {
        // Inicializamos el sistema
        sistema = ReservaModel.getInstance();
    }

    // Camino 1: El menú no existe (item == null) -> Salida esperada: false
    @Test
    public void testAgregarReserva_Camino1_MenuNoExiste() {
        // Usamos un ID que no existe
        boolean resultado = sistema.agregarReserva("27000111", "MENU-999");

        // En JUnit 4, el mensaje opcional va como PRIMER argumento
        assertFalse("La reserva debe fallar si el menú no existe", resultado);
    }

    // Camino 2: El menú sí existe -> Salida esperada: true
    @Test
    public void testAgregarReserva_Camino2_MenuExiste() {
        // Usamos un ID que existe en DB_menu.json
        boolean resultado = sistema.agregarReserva("27000111", "d7c9d611-3297-453a-9e51-cb61570c7dc5");

        // En JUnit 4, el mensaje opcional va como PRIMER argumento
        assertTrue("La reserva debe ser exitosa si el menú existe", resultado);
    }
}