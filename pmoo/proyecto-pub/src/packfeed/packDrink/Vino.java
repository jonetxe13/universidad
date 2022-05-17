package packfeed.packDrink;

import packfeed.Food;

public class Vino extends Drink{
    private boolean denominacionDeOrigen;
    private boolean espumoso;

    public Vino(String brandName, int calories, boolean glutenFree, Food[] pairings, boolean denominacionDeOrigen, boolean espumoso) {
        super(brandName, calories, glutenFree, pairings);
        setDenominacionDeOrigen(denominacionDeOrigen);
        setEspumoso(espumoso);
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
    public String toString() {
        return super.toString() + " denominacionDeOrigen: " + denominacionDeOrigen + " espumoso: " + espumoso + "\n";
    }
}
