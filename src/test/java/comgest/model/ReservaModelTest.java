package comgest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReservaModelTest {

    private ReservaModel reservaModel;

    @Before
    public void setUp() throws Exception {
        reservaModel = new ReservaModel();

        List<Reserva> datos = new ArrayList<>();

        datos.add(new Reserva("12345", "1"));
        datos.add(new Reserva("12345", "2"));
        datos.add(new Reserva("67890", "1"));
        datos.add(new Reserva("67890", "3"));

        Reserva consumida = new Reserva("12345", "3");
        consumida.setConsumido(true);
        datos.add(consumida);

        Reserva consumida2 = new Reserva("67890", "2");
        consumida2.setConsumido(true);
        datos.add(consumida2);

        Field campo = ReservaModel.class.getDeclaredField("reservas");
        campo.setAccessible(true);
        campo.set(reservaModel, datos);
    }

    @Test
    public void eliminarReservasPorMenuEliminaTodasLasReservasDelMenu() {
        // 1 tiene 2 reservas (12345 y 67890)
        reservaModel.eliminarReservasPorMenu("1");
        List<Reserva> todas = reservaModel.getReservas();
        for (Reserva r : todas) {
            assertFalse("No deberia quedar ninguna reserva con idMenu 1", "1".equals(r.getIdMenu()));
        }
    }

    @Test
    public void eliminarReservasPorMenuNoAfectaOtrosMenus() {
        int tamanoAntes = reservaModel.getReservas().size(); // 6 reservas totales
        reservaModel.eliminarReservasPorMenu("1"); // elimina 2
        // Deben quedar las demas reservas (2, 3)
        assertEquals(tamanoAntes - 2, reservaModel.getReservas().size());
    }

    @Test
    public void eliminarReservasPorMenuConIdInexistenteNoHaceNada() {
        int tamanoAntes = reservaModel.getReservas().size();
        reservaModel.eliminarReservasPorMenu("INEXISTENTE");
        assertEquals(tamanoAntes, reservaModel.getReservas().size());
    }

    @Test
    public void buscarReservaPendienteEncuentraReservaNoConsumida() {
        Reserva r = reservaModel.buscarReservaPendiente("12345", "1");
        assertNotNull(r);
        assertEquals("12345", r.getCedulaUsuario());
        assertEquals("1", r.getIdMenu());
        assertFalse(r.isConsumido());
    }

    @Test
    public void buscarReservaPendienteRetornaNullSiYaConsumida() {
        // 12345-3 esta marcada como consumida
        Reserva r = reservaModel.buscarReservaPendiente("12345", "3");
        assertNull(r);
    }

    @Test
    public void buscarReservaPendienteRetornaNullSiNoExiste() {
        Reserva r = reservaModel.buscarReservaPendiente("99999", "1");
        assertNull(r);
    }

    @Test
    public void buscarReservaPendienteRetornaNullConMenuInexistente() {
        Reserva r = reservaModel.buscarReservaPendiente("12345", "INEXISTENTE");
        assertNull(r);
    }

    @Test
    public void buscarReservaConsumidaEncuentraReservaConsumida() {
        Reserva r = reservaModel.buscarReservaConsumida("12345", "3");
        assertNotNull(r);
        assertEquals("12345", r.getCedulaUsuario());
        assertEquals("3", r.getIdMenu());
        assertTrue(r.isConsumido());
    }

    @Test
    public void buscarReservaConsumidaRetornaNullSiEsPendiente() {
        // 12345-1 NO esta consumida
        Reserva r = reservaModel.buscarReservaConsumida("12345", "1");
        assertNull(r);
    }

    @Test
    public void buscarReservaConsumidaRetornaNullSiNoExiste() {
        Reserva r = reservaModel.buscarReservaConsumida("99999", "3");
        assertNull(r);
    }

    @Test
    public void getReservasPendientesPorUsuarioRetornaListaCorrecta() {
        // 12345 tiene 1 y 2 pendientes (3 esta consumida)
        List<Reserva> pendientes = reservaModel.getReservasPendientesPorUsuario("12345");
        assertEquals(2, pendientes.size());
        for (Reserva r : pendientes) {
            assertEquals("12345", r.getCedulaUsuario());
            assertFalse(r.isConsumido());
        }
    }

    @Test
    public void getReservasPendientesPorUsuarioRetornaListaVaciaSiNoTiene() {
        List<Reserva> pendientes = reservaModel.getReservasPendientesPorUsuario("99999");
        assertNotNull(pendientes);
        assertTrue(pendientes.isEmpty());
    }

    @Test
    public void getReservasPendientesPorUsuarioNoIncluyeConsumidas() {
        List<Reserva> pendientes = reservaModel.getReservasPendientesPorUsuario("12345");
        for (Reserva r : pendientes) {
            assertFalse("No debe incluir reservas consumidas", r.isConsumido());
        }
    }

    @Test
    public void getReservasPorUsuarioRetornaTodasLasReservas() {
        // 12345 tiene 3 reservas: 1 pendiente, 2 pendiente, 3 consumida
        List<Reserva> todas = reservaModel.getReservasPorUsuario("12345");
        assertEquals(3, todas.size());
        for (Reserva r : todas) {
            assertEquals("12345", r.getCedulaUsuario());
        }
    }

    @Test
    public void getReservasPorUsuarioRetornaListaVaciaSiNoTiene() {
        List<Reserva> todas = reservaModel.getReservasPorUsuario("99999");
        assertNotNull(todas);
        assertTrue(todas.isEmpty());
    }

    @Test
    public void getReservasPorUsuarioIncluyePendientesYConsumidas() {
        List<Reserva> todas = reservaModel.getReservasPorUsuario("12345");
        boolean tienePendiente = false;
        boolean tieneConsumida = false;
        for (Reserva r : todas) {
            if (r.isConsumido())
                tieneConsumida = true;
            else
                tienePendiente = true;
        }
        assertTrue("Debe incluir al menos una pendiente", tienePendiente);
        assertTrue("Debe incluir al menos una consumida", tieneConsumida);
    }
}
