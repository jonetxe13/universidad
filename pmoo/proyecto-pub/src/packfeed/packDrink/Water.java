package packfeed.packDrink;

import packfeed.Food;

public class Water extends Drink{
    private String sabor;
    private boolean burbujas;
    private boolean manantial;
    private double precio = 3.5;

    public Water(String brandName, int calories, boolean glutenFree, Food[] pairings, String sabor, boolean burbujas, boolean manantial) {
        super(brandName, calories, glutenFree, pairings);
        setSabor(sabor);
        setBurbujas(burbujas);
        setManantial(manantial);
    }
    public void setSabor(String sabor) {
        this.sabor = sabor;
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
    public void setManantial(boolean manantial) {
        this.manantial = manantial;
    }
    public boolean getManantial() {
        return manantial;
    }
    public double getPrecio() {
        return precio;
    }
    public String toString() {
        return super.toString() + " sabor: " + sabor + " burbujas: " + burbujas + " manantial: " + manantial + "\n";
    }
}
