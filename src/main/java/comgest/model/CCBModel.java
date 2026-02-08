package comgest.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CCBModel {
    private static String DATABASE_PATH = "src\\main\\java\\comgest\\data\\DB_CCB.json";

    private CCB ccbActual;
    private Gson gson;

    public CCBModel() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.ccbActual = cargarDesdeArchivo();
    }

    public CCB obtenerCCB() {
        return ccbActual;
    }

    public CCB recargarCCB() {
        this.ccbActual = cargarDesdeArchivo();
        return ccbActual;
    }

    public void actualizarCCB(double costosFijos, double costosVariables, double numeroBandejas,
            double porcentajeMerma) {
        validarNumeroBandejas(numeroBandejas);
        ccbActual.setCostosFijos(costosFijos);
        ccbActual.setCostosVariables(costosVariables);
        ccbActual.setNumeroBandejas(numeroBandejas);
        ccbActual.setPorcentajeMerma(porcentajeMerma);
        ccbActual.calcularCCB();
        guardarEnArchivo(ccbActual);
    }

    public void actualizarCCB(CCB nuevoCCB) {
        if (nuevoCCB == null) {
            throw new IllegalArgumentException("nuevoCCB no puede ser null");
        }
        validarNumeroBandejas(nuevoCCB.getNumeroBandejas());
        nuevoCCB.calcularCCB();
        this.ccbActual = nuevoCCB;
        guardarEnArchivo(ccbActual);
    }

    private CCB cargarDesdeArchivo() {
        try (FileReader reader = new FileReader(DATABASE_PATH)) {
            CCB ccb = gson.fromJson(reader, CCB.class);
            if (ccb == null) {
                ccb = new CCB(0, 0, 1, 0);
                guardarEnArchivo(ccb);
            }
            return ccb;
        } catch (IOException e) {
            CCB ccb = new CCB(0, 0, 1, 0);
            guardarEnArchivo(ccb);
            return ccb;
        }
    }

    private void guardarEnArchivo(CCB ccb) {
        try (FileWriter writer = new FileWriter(DATABASE_PATH)) {
            gson.toJson(ccb, writer);
        } catch (IOException e) {
            System.out.println("Error guardando DB_CCB.json: " + e.getMessage());
        }
    }

    private void validarNumeroBandejas(double numeroBandejas) {
        if (numeroBandejas <= 0) {
            throw new IllegalArgumentException("numeroBandejas debe ser mayor a cero");
        }
    }
}
