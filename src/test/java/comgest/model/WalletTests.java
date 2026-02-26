package comgest.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class WalletTests {

    @After
    public void tearDown() {
        // limpieza de sesion despues de tests
        UserSession.clearSession();
    }

    @Test
    public void usuarioGetSaldoDevuelveMontoInicial() {
        Usuario u = new Usuario("Test", "pass", "email@ucv.ve", "111", "Role", 42.5f);
        assertEquals(42.5f, u.getSaldo(), 0.0f);
    }

    @Test
    public void userSessionGetSaldoDespuesDeIniciar() {
        Usuario u = new Usuario("T", "p", "e@ucv.ve", "222", "R", 100.0f);
        UserSession.startSession(u);
        UserSession session = UserSession.getInstance();
        assertNotNull(session);
        assertTrue(session.isActive());
        assertEquals(100.0f, session.getSaldo(), 0.0f);
    }

    @Test
    public void usuarioAddSaldoAumentaElMonto() {
        Usuario u = new Usuario("Test", "pass", "email@ucv.ve", "111", "Role", 50.0f);
        u.addSaldo(25.5f);
        assertEquals(75.5f, u.getSaldo(), 0.0f);
    }

    @Test
    public void usuarioSubSaldoDisminuyeElMonto() {
        Usuario u = new Usuario("Test", "pass", "email@ucv.ve", "111", "Role", 50.0f);
        u.subSaldo(20.0f);
        assertEquals(30.0f, u.getSaldo(), 0.0f);
    }

    @Test
    public void userSessionReflejaCambiosEnElSaldoDelUsuario() {
        Usuario u = new Usuario("Test", "pass", "email@ucv.ve", "111", "Role", 100.0f);
        UserSession.startSession(u);
        UserSession session = UserSession.getInstance();

        u.addSaldo(10.0f);
        assertEquals(110.0f, session.getSaldo(), 0.0f);

        u.subSaldo(50.0f);
        assertEquals(60.0f, session.getSaldo(), 0.0f);
    }
}
