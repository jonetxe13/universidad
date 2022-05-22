package packfeed.packDrink;

import packfeed.Food;

public class Refresco extends Drink{
    private String sabor;
    private boolean burbujas;
    private int porcentajeDeZumo;
    private double precio;

    public Refresco(String brandName, int calories, boolean glutenFree, Food[] pairings, String sabor, boolean burbujas, int porcentajeDeZumo) {
        super(brandName, calories, glutenFree, pairings);
        setSabor(sabor);
        setBurbujas(burbujas);
        setPrecio();
    }
    /**setter del sabor del refresco
     * @param sabor2
     */
    public void setSabor(String sabor2) {
        this.sabor = sabor2;
    }
    /**getter del sabor del refresco
     * @return sabor
     */
    public String getSabor() {
        return sabor;
    }
    /**setter de si tiene burbujas o no el refresco
     * @param burbujas
     */
    public void setBurbujas(boolean burbujas) {
        this.burbujas = burbujas;
    }
    /**getter de si tiene burbujas o no el refresco
     * @return burbujas
     */
    public boolean getBurbujas() {
        return burbujas;
    }
    /**setter del porcentaje de zumo que contiene el refresco
     * @param porcentajeDeZumo
     */
    public void setPorcentajeDeZumo(int porcentajeDeZumo) {
        this.porcentajeDeZumo = porcentajeDeZumo;
    }
    /**getter del porcentaje de zumo que contiene el refresco
     * @return porcentajeDeZumo
     */
    public int getPorcentajeDeZumo() {
        return porcentajeDeZumo;
    }
    /**
     *getter del precio del refresco
     *@return precio
     */
    public double getPrecio() {
        return precio;
    }
    /**
     * setter del precie del refresco
     */
    public void setPrecio(){
        this.precio = 3.5;
        super.setPrecio(this.precio);
    }
    /**
     *redefinicion del metodo toString
     */
    public String toString() {
        return super.toString() + " sabor: " + sabor + " burbujas: " + burbujas + " porcentajeDeZumo: " + porcentajeDeZumo + " }";
    }
}
