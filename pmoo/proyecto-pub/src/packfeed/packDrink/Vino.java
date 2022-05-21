package packfeed.packDrink;

import packfeed.Food;

public class Vino extends Drink{
    private boolean denominacionDeOrigen;
    private boolean espumoso;
    private double precio;

    public Vino(String brandName, int calories, boolean glutenFree, Food[] pairings, boolean denominacionDeOrigen, boolean espumoso) {
        super(brandName, calories, glutenFree, pairings);
        setDenominacionDeOrigen(denominacionDeOrigen);
        setEspumoso(espumoso);
        setPrecio();
    }
    public void setPrecio() {
        this.precio = getEspumoso() ? 3 : 2;
        super.setPrecio(this.precio);
    }
    public void setDenominacionDeOrigen(boolean denominacionDeOrigen) {
        this.denominacionDeOrigen = denominacionDeOrigen;
    }
    public boolean getDenominacionDeOrigen() {
        return denominacionDeOrigen;
    }
    public void setEspumoso(boolean espumoso) {
        this.espumoso = espumoso;
    }
    public boolean getEspumoso() {
        return espumoso;
    }
    public double getPrecio() {
        return precio;
    }
    public String toString() {
        return super.toString() + " denominacionDeOrigen: " + denominacionDeOrigen + " espumoso: " + espumoso + " }";
    }
}
