package comgest.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class WalletTests {

    @After
    public void tearDown() {
        // ensure session cleanup after each test
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
}
