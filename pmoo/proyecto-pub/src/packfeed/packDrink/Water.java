package packfeed.packDrink;

import packfeed.Food;

/**
 * @author jonet
 *
 */
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
    /**setter del sabor del agua
     * @param sabor
     */
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }
    /**geter del sabor del agua
     * @return sabor
     */
    public String getSabor() {
        return sabor;
    }
    /**setter de si tiene burbujas o no
     * @param burbujas
     */
    public void setBurbujas(boolean burbujas) {
        this.burbujas = burbujas;
    }
    /**getter de si tiene burbujas o no
     * @return burbujas
     */
    public boolean getBurbujas() {
        return burbujas;
    }
    /**setter de si es de manantial o no
     * @param manantial
     */
    public void setManantial(boolean manantial) {
        this.manantial = manantial;
    }
    /**getter de si es de manantial o no
     * @return
     */
    public boolean getManantial() {
        return manantial;
    }
    /**
     *getter del precio del agua
     *@return precio
     */
    public double getPrecio() {
        return precio;
    }
    /**
     *redefinicion del metodo toString
     */
    public String toString() {
        return super.toString() + " sabor: " + sabor + " burbujas: " + burbujas + " manantial: " + manantial + " }";
    }
}
