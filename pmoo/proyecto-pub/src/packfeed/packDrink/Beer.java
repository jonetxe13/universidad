package packfeed.packDrink;

import packfeed.Food;

public class Beer extends Drink {
    private int IBU;
    private int ABV;
    private String origen;
    private double precio;

    public Beer(String brandName, int calories, boolean glutenFree, Food[] pairings, int IBU, int ABV, String origen) {
        super(brandName, calories, glutenFree, pairings);
        setIBU(IBU);
        setABV(ABV);
        setPrecio(precio);
    }
    public void setIBU(int IBU) {
        this.IBU = IBU;
    }
    public int getIBU() {
        return IBU;
    }
    public void setABV(int ABV) {
        this.ABV = ABV;
    }
    public int getABV() {
        return ABV;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
        super.setPrecio(this.precio);
    }
    public double getPrecio(){
        return precio;
    }
    public String toString() {
        return super.toString() + " IBU: " + IBU + " ABV: " + ABV + " origen: " + origen + "\n";
    }
}