package comgest.model;

public class CCB {
    private double costosFijos;
    private double costosVariables;
    private double numeroBandejas;
    private double porcentajeMerma;
    private double CCBValor;

    public CCB(double costosFijos, double costosVariables, double numeroBandejas, double porcentajeMerma) {
        this.costosFijos = costosFijos;
        this.costosVariables = costosVariables;
        this.numeroBandejas = numeroBandejas;
        this.porcentajeMerma = porcentajeMerma;
        calcularCCB();
    }

    public void calcularCCB() {
        CCBValor = ((costosFijos + costosVariables) / numeroBandejas) * (1 + (porcentajeMerma / 100));
        CCBValor = Math.round(CCBValor * 100.0) / 100.0;
    }

    public void setCostosFijos(double costosFijos) {
        this.costosFijos = costosFijos;
    }

    public void setCostosVariables(double costosVariables) {
        this.costosVariables = costosVariables;
    }

    public void setNumeroBandejas(double numeroBandejas) {
        this.numeroBandejas = numeroBandejas;
    }

    public void setPorcentajeMerma(double porcentajeMerma) {
        this.porcentajeMerma = porcentajeMerma;
    }

    public double getCostosFijos() {
        return costosFijos;
    }

    public double getCostosVariables() {
        return costosVariables;
    }

    public double getNumeroBandejas() {
        return numeroBandejas;
    }

    public double getPorcentajeMerma() {
        return porcentajeMerma;
    }

    public double getCCBValor() {
        return CCBValor;
    }
}
