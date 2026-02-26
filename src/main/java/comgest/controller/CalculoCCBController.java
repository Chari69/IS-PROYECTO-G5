package comgest.controller;

import comgest.model.CCBModel;
import comgest.view.CalculoCCBGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculoCCBController implements ActionListener {
    public static final String ACTION_CALCULAR = "CALCULAR_CCB";

    private final CCBModel ccbModel;
    private final CalculoCCBGUI view;

    public CalculoCCBController(CalculoCCBGUI view) {
        this(new CCBModel(), view);
    }

    public CalculoCCBController(CCBModel ccbModel, CalculoCCBGUI view) {
        if (ccbModel == null) {
            throw new IllegalArgumentException("ccbModel no puede ser null");
        }
        if (view == null) {
            throw new IllegalArgumentException("view no puede ser null");
        }
        this.ccbModel = ccbModel;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!ACTION_CALCULAR.equals(e.getActionCommand())) {
            return;
        }

        try {
            // Obtener valores de la vista
            double costosFijos = view.getCostosFijos();
            double costosVariables = view.getCostosVariables();
            double numeroBandejas = view.getNumeroBandejas();
            double porcentajeMerma = view.getPorcentajeMerma();

            // Actualizar modelo
            ccbModel.actualizarCCB(costosFijos, costosVariables, numeroBandejas, porcentajeMerma);

            // Obtener resultado del modelo
            double ccbValor = ccbModel.obtenerCCB().getCCBValor();

            // Actualizar vista con el resultado
            view.actualizarCCBResultado(ccbValor);
            view.showMessage("CCB calculado exitosamente: " + String.format("%.2f", ccbValor) + " Bs");

        } catch (NumberFormatException ex) {
            view.showMessage("Error: Asegúrate de ingresar números válidos");
        } catch (IllegalArgumentException ex) {
            view.showMessage("Error de validación: " + ex.getMessage());
        } catch (Exception ex) {
            view.showMessage("Error al calcular CCB: " + ex.getMessage());
        }
    }



    public void cargarCCBActual() {
        try {
            double ccbValor = ccbModel.obtenerCCB().getCCBValor();
            view.actualizarCCBResultado(ccbValor);
        } catch (Exception ex) {
            System.err.println("Error al cargar CCB actual: " + ex.getMessage());
        }
    }
}
