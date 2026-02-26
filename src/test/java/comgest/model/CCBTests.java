package comgest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class CCBTests {
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    private File dataDir;
    private CCBModel model;

    @Before
    public void setUp() throws Exception {
        dataDir = temp.newFolder("data");
        writeText(new File(dataDir, "DB_CCB.json"), "{}");
        model = createModelWithTempData();
    }

    // TESTS DE CALCULOS MATEMATICOS DE CCB

    @Test
    public void ccbCalculaPrecioBaseCorrectamente() {
        CCB c = new CCB(100.0, 50.0, 10.0, 20.0);
        // ((100 + 50) / 10) * (1 + (20/100)) = (150 / 10) * (1.20) = 15 * 1.2 = 18.0
        assertEquals(18.0, c.getCCBValor(), 0.01);
    }

    @Test
    public void ccbCalculadoRedondeaA2Decimales() {
        // Generar un numero de decimales largo
        CCB c = new CCB(55.22, 10.33, 7.0, 13.5);
        // ((55.22 + 10.33)/7) * 1.135 = 9.364... * 1.135 = 10.6283...
        // Redondeado a dos decimales = 10.63
        assertEquals(10.63, c.getCCBValor(), 0.01);
    }

    // TESTS DE INTEGRACION DEL PRECIO POR ROL

    @Test
    public void ccbCalculaDescuentoRolProfesor() {
        CCB c = new CCB(100.0, 50.0, 10.0, 0.0); // Precio Base (CCBValor) sera: 15.0
        // Profesor = 90%
        assertEquals(13.50, c.calcularPrecioConDescuento("profesor"), 0.01);
    }

    @Test
    public void ccbCalculaDescuentoRolEmpleado() {
        CCB c = new CCB(100.0, 50.0, 10.0, 0.0); // Precio Base (CCBValor) sera: 15.0
        // Empleado = 110%
        assertEquals(16.50, c.calcularPrecioConDescuento("Empleado"), 0.01);
    }

    @Test
    public void ccbCalculaDescuentoRolEstudiante() {
        CCB c = new CCB(100.0, 50.0, 10.0, 0.0); // Precio Base (CCBValor) sera: 15.0
        // Estudiante = 30%
        assertEquals(4.50, c.calcularPrecioConDescuento("EsTudiante"), 0.01);
    }

    @Test
    public void ccbSinRolUsaCienPorCientoPorDefecto() {
        CCB c = new CCB(100.0, 50.0, 10.0, 0.0); // 15.0
        assertEquals(15.0, c.calcularPrecioConDescuento(null), 0.01);
        assertEquals(15.0, c.calcularPrecioConDescuento("rolNoExistente"), 0.01);
        assertEquals(15.0, c.calcularPrecioConDescuento("   "), 0.01);
    }

    // TESTS DE LIMITES Y VALORES NEGATIVOS

    @Test(expected = IllegalArgumentException.class)
    public void ccbFijarCostosFijosNegativoInvalido() {
        CCB c = new CCB(10, 20, 5, 10);
        c.setCostosFijos(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ccbFijarCostosVariablesNegativoInvalido() {
        CCB c = new CCB(10, 20, 5, 10);
        c.setCostosVariables(-1.5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ccbFijarPorcentajeMermaNegativoInvalido() {
        CCB c = new CCB(10, 20, 5, 10);
        c.setPorcentajeMerma(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ccbFijarPorcentajeMermaSuperiorACienInvalido() {
        CCB c = new CCB(10, 20, 5, 10);
        c.setPorcentajeMerma(105);
    }

    @Test(expected = IllegalArgumentException.class)
    public void actualizarCCBModelNumeroBandejasNoValidoLanza() {
        model.actualizarCCB(1, 1, 0, 5); // =0 es ilegal
    }

    @Test(expected = IllegalArgumentException.class)
    public void actualizarCCBModelNumeroBandejasNegativoLanza() {
        model.actualizarCCB(1, 1, -5, 5); // <0 es ilegal
    }

    // TESTS DEL MODELO

    @Test
    public void actualizarCCBModelCambiaElObjetoInterno() {
        model.actualizarCCB(300, 100, 20, 0); // (400)/20 = 20 CCB
        CCB current = model.obtenerCCB();

        assertNotNull(current);
        assertEquals(300, current.getCostosFijos(), 0);
        assertEquals(100, current.getCostosVariables(), 0);
        assertEquals(20, current.getNumeroBandejas(), 0);
        assertEquals(20, current.getCCBValor(), 0.01);
    }

    // Metodos Auxiliares para Test
    private CCBModel createModelWithTempData() throws Exception {
        setDatabasePath(dataDir.getAbsolutePath());
        CCBModel m = new CCBModel();
        return m;
    }

    private void setDatabasePath(String path) throws Exception {
        Field field = CCBModel.class.getDeclaredField("DATABASE_PATH");
        field.setAccessible(true);
        field.set(null, path);
    }

    private void writeText(File file, String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }
}
