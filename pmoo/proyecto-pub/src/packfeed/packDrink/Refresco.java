package packfeed.packDrink;

import packfeed.Food;

public class Refresco extends Drink{
    private String sabor;
    private boolean burbujas;
    private int porcentajeDeZumo;

    public Refresco(String brandName, int calories, boolean glutenFree, Food[] pairings, String sabor, boolean burbujas, int porcentajeDeZumo) {
        super(brandName, calories, glutenFree, pairings);
        setSabor(sabor);
        setBurbujas(burbujas);
    }
    public void setSabor(String sabor2) {
        this.sabor = sabor2;
    }
    public String getSabor() {
        return sabor;
    }
    public void setBurbujas(boolean burbujas) {
        this.burbujas = burbujas;
    }
    public boolean getBurbujas() {
        return burbujas;
    }
    public String toString() {
        return super.toString() + " sabor: " + sabor + " burbujas: " + burbujas + " porcentajeDeZumo: " + porcentajeDeZumo + "\n";
    }
}
