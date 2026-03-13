package comgest.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EliminarReservaCBTest {
    private ReservaModel sistema;

    @Before
    public void setUp() {
        sistema = new ReservaModel();
        // Limpiamos o inicializamos según sea necesario
    }

    // Camino 1: Lista de reservas vacía
    @Test
    public void testEliminar_ListaVacia() {
        // Aseguramos que reservas.size() es 0
        boolean resultado = sistema.eliminarReserva("test6", "d7c9d611-3297-453a-9e51-cb61570c7dc5");
        assertFalse(resultado);
    }

    // Camino 2: El usuario no existe en la lista
    @Test
    public void testEliminar_UsuarioNoCoincide() {
        sistema.agregarReserva("test2", "d7c9d611-3297-453a-9e51-cb61570c7dc5"); // Agregamos una distinta
        boolean resultado = sistema.eliminarReserva("test3", "d7c9d611-3297-453a-9e51-cb61570c7dc5");
        assertFalse(resultado);
    }

    // Camino 3: El usuario coincide, pero el ID del Menú no
    @Test
    public void testEliminar_MenuNoCoincide() {
        sistema.agregarReserva("test5", "d7c9d611-3297-453a-9e51-cb61570c7dc5");
        boolean resultado = sistema.eliminarReserva("test5", "MENU-999"); // Mismo usuario, distinto menú que no existe
        assertFalse(resultado);
    }

    // Camino 4: Coincidencia total y eliminación exitosa
    @Test
    public void testEliminar_Exito() {
        sistema.agregarReserva("test4", "d7c9d611-3297-453a-9e51-cb61570c7dc5");
        boolean resultado = sistema.eliminarReserva("test4", "d7c9d611-3297-453a-9e51-cb61570c7dc5");
        assertTrue(resultado);
        // Verificación adicional de caja blanca: la lista debe estar vacía ahora
        // assertEquals(0, sistema.getReservas().size());
    }
}