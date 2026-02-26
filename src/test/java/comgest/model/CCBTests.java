package comgest.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CCBTests {

    @Test
    public void calcularCCBFormulaCorrecta() {
        CCB c = new CCB(10, 20, 5, 10); // (10+20)/5=6 *1.1 = 6.6
        assertEquals(6.6, c.getCCBValor(), 0.0);

        // cambiar valores y recalcular para 0
        c.setCostosFijos(0);
        c.setCostosVariables(0);
        c.setNumeroBandejas(1);
        c.setPorcentajeMerma(0);
        c.calcularCCB();
        assertEquals(0.0, c.getCCBValor(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ccbModelActualizarNumeroBandejasNoValidoLanza() {
        CCBModel model = new CCBModel();
        // numero de bandejas igual a 0 debe disparar la validacion
        model.actualizarCCB(1, 1, 0, 5);
    }
}
