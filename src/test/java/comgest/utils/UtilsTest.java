package comgest.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

public class UtilsTest {

    // TESTS DE isAdminRole

    @Test
    public void isAdminRoleConAdministradorRetornaTrue() {
        assertTrue(Utils.isAdminRole("Administrador"));
    }

    @Test
    public void isAdminRoleConMayusculasMixtasRetornaTrue() {
        assertTrue(Utils.isAdminRole("ADMINISTRADOR"));
        assertTrue(Utils.isAdminRole("administrador"));
        assertTrue(Utils.isAdminRole("  Administrador  "));
    }

    @Test
    public void isAdminRoleConOtroRolRetornaFalse() {
        assertFalse(Utils.isAdminRole("Estudiante"));
        assertFalse(Utils.isAdminRole("Profesor"));
        assertFalse(Utils.isAdminRole("Empleado"));
    }

    @Test
    public void isAdminRoleConNuloRetornaFalse() {
        assertFalse(Utils.isAdminRole(null));
    }

    @Test
    public void isAdminRoleConVacioRetornaFalse() {
        assertFalse(Utils.isAdminRole(""));
        assertFalse(Utils.isAdminRole("   "));
    }

    // TESTS DE normalizar

    @Test
    public void normalizarQuitaEspacios() {
        assertEquals("hola", Utils.normalizar("  hola  "));
    }

    @Test
    public void normalizarConNuloRetornaVacio() {
        assertEquals("", Utils.normalizar(null));
    }

    @Test
    public void normalizarConVacioRetornaVacio() {
        assertEquals("", Utils.normalizar(""));
    }

    // TESTS DE parsearHora

    @Test
    public void parsearHoraFormatoAM() {
        LocalTime resultado = Utils.parsearHora("7:00 AM");
        assertNotNull(resultado);
        assertEquals(LocalTime.of(7, 0), resultado);
    }

    @Test
    public void parsearHoraFormatoPM() {
        LocalTime resultado = Utils.parsearHora("2:30 PM");
        assertNotNull(resultado);
        assertEquals(LocalTime.of(14, 30), resultado);
    }

    @Test
    public void parsearHoraFormato24Horas() {
        LocalTime resultado = Utils.parsearHora("14:30");
        assertNotNull(resultado);
        assertEquals(LocalTime.of(14, 30), resultado);
    }

    @Test
    public void parsearHoraConNuloRetornaNull() {
        assertNull(Utils.parsearHora(null));
    }

    @Test
    public void parsearHoraConVacioRetornaNull() {
        assertNull(Utils.parsearHora(""));
        assertNull(Utils.parsearHora("   "));
    }

    @Test
    public void parsearHoraConTextoInvalidoRetornaNull() {
        assertNull(Utils.parsearHora("no es hora"));
    }

    // TESTS DE estaEnHorario

    @Test
    public void estaEnHorarioDentroDelRango() {
        LocalTime ahora = LocalTime.of(9, 0);
        assertTrue(Utils.estaEnHorario("7:00 AM - 10:00 AM", ahora));
    }

    @Test
    public void estaEnHorarioFueraDelRango() {
        LocalTime ahora = LocalTime.of(11, 0);
        assertFalse(Utils.estaEnHorario("7:00 AM - 10:00 AM", ahora));
    }

    @Test
    public void estaEnHorarioEnElLimiteInicio() {
        LocalTime ahora = LocalTime.of(7, 0);
        assertTrue(Utils.estaEnHorario("7:00 AM - 10:00 AM", ahora));
    }

    @Test
    public void estaEnHorarioEnElLimiteFin() {
        LocalTime ahora = LocalTime.of(10, 0);
        assertTrue(Utils.estaEnHorario("7:00 AM - 10:00 AM", ahora));
    }

    @Test
    public void estaEnHorarioConNuloRetornaTrue() {
        assertTrue(Utils.estaEnHorario(null, LocalTime.of(9, 0)));
    }

    @Test
    public void estaEnHorarioConVacioRetornaTrue() {
        assertTrue(Utils.estaEnHorario("", LocalTime.of(9, 0)));
    }

    // TESTS DE parsearPrecio

    @Test
    public void parsearPrecioConPrefijo() {
        assertEquals(15.50, Utils.parsearPrecio("Precio: $15.50"), 0.01);
    }

    @Test
    public void parsearPrecioConComaDecimal() {
        assertEquals(15.50, Utils.parsearPrecio("15,50"), 0.01);
    }

    @Test
    public void parsearPrecioSoloNumero() {
        assertEquals(42.0, Utils.parsearPrecio("42"), 0.01);
    }

    @Test
    public void parsearPrecioConNuloRetornaCero() {
        assertEquals(0.0, Utils.parsearPrecio(null), 0.01);
    }

    @Test
    public void parsearPrecioConVacioRetornaCero() {
        assertEquals(0.0, Utils.parsearPrecio(""), 0.01);
        assertEquals(0.0, Utils.parsearPrecio("   "), 0.01);
    }
}
