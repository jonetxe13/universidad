package packfeed.packDrink;

import packfeed.Food;

public class Beer extends Drink {
    private int IBU;
    private int ABV;
    private double precio;

    public Beer(String brandName, int calories, boolean glutenFree, Food[] pairings, int IBU, int ABV) {
        super(brandName, calories, glutenFree, pairings);
        setIBU(IBU);
        setABV(ABV);
        setPrecio(precio);
    }
    public Beer(){

    }
    /**setter del IBU de la cerveza
     * @param IBU
     */
    public void setIBU(int IBU) {
        this.IBU = IBU;
    }
    /**getter del IBU de la cerveza
     * @return IBU
     */
    public int getIBU() {
        return IBU;
    }
    /**setter del ABV de la cerveza
     * @param ABV
     */
    public void setABV(int ABV) {
        this.ABV = ABV;
    }
    /**getter del ABV de la cerveza
     * @return ABV
     */
    public int getABV() {
        return ABV;
    }
    /**
     *setter del precio de la cerveza
     */
    public void setPrecio(double precio) {
        this.precio = precio;
        super.setPrecio(this.precio);
    }
    /**
     *getter del precio de la cerveza
     */
    public double getPrecio(){
        return precio;
    }
    /**
     *redefincion del metodo toString
     */
    public String toString() {
        return super.toString() + " IBU: " + IBU + " ABV: " + ABV + " ";
    }
}
