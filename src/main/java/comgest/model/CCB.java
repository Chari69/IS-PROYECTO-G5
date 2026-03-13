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
        double porcentaje = obtenerPorcentajePago(role);
        return CCBValor * (porcentaje / 100.0);
    }

    public double calcularPrecioFinal(String role, double porcentajeBeca) {
        double precioBase = calcularPrecioConDescuento(role);

        // Exonerados siempre pagan 0
        String normalized = role != null ? role.trim().toLowerCase() : "";
        if (normalized.equals("estudiante (e)")) {
            return 0;
        }

        // Becados: aplicar descuento adicional sobre el precio de estudiante
        if (normalized.equals("estudiante (b)") && porcentajeBeca > 0) {
            precioBase = precioBase * (1.0 - porcentajeBeca / 100.0);
        }

        return Math.round(precioBase * 100.0) / 100.0;
    }

    // Retorna el porcentaje del CCB que paga cada rol
    private double obtenerPorcentajePago(String role) {
        if (role == null)
            return 100;

        String normalized = role.trim().toLowerCase();

        switch (normalized) {
            case "profesor":
                return 90;
            case "empleado":
                return 110;
            case "estudiante":
            case "estudiante (b)":
                return 30; // Becados pagan base igual a estudiante, luego se aplica descuento extra
            case "estudiante (e)":
                return 0; // Exonerados pagan 0%
            default:
                return 100;
        }
    }
}
