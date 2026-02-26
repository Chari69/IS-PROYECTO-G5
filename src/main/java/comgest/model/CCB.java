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
        if (costosFijos < 0)
            throw new IllegalArgumentException("Costos fijos no pueden ser negativos");
        this.costosFijos = costosFijos;
    }

    public void setCostosVariables(double costosVariables) {
        if (costosVariables < 0)
            throw new IllegalArgumentException("Costos variables no pueden ser negativos");
        this.costosVariables = costosVariables;
    }

    public void setNumeroBandejas(double numeroBandejas) {
        this.numeroBandejas = numeroBandejas;
    }

    public void setPorcentajeMerma(double porcentajeMerma) {
        if (porcentajeMerma < 0 || porcentajeMerma > 100)
            throw new IllegalArgumentException("Porcentaje de merma debe estar entre 0 y 100");
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

    public double calcularPrecioConDescuento(String role) {
        double descuentoPorcentaje = obtenerDescuentoPorRol(role);
        return CCBValor * (descuentoPorcentaje / 100.0);
    }

    private double obtenerDescuentoPorRol(String role) {
        if (role == null)
            return 100; // Sin descuento devuelve 100% del precio

        String normalized = role.trim().toLowerCase();

        switch (normalized) {
            case "profesor":
                return 90; // Pagan el 90%
            case "empleado":
                return 110; // Pagan el 110%
            case "estudiante":
                return 30; // Pagan el 30%
            default:
                return 100; // Sin descuento por defecto
        }
    }
}
