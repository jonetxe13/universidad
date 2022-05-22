package packfeed.packDrink;

import packfeed.Food;

/**
 * @author jonet
 *
 */
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
    /**
     * setter del precio del vino
     */
    public void setPrecio() {
        this.precio = getEspumoso() ? 3 : 2;
        super.setPrecio(this.precio);
    }
    /**getter del precio del vino
     *@return precio
     */
    public double getPrecio() {
        return precio;
    }
    /**setter de si es de denominacion de origen o no
     * @param denominacionDeOrigen
     */
    public void setDenominacionDeOrigen(boolean denominacionDeOrigen) {
        this.denominacionDeOrigen = denominacionDeOrigen;
    }
    /**getter de si es de denominacion de origen o no
     * @return denominacionDeOrigen
     */
    public boolean getDenominacionDeOrigen() {
        return denominacionDeOrigen;
    }
    /**setter de si es espumoso o no
     * @param espumoso
     */
    public void setEspumoso(boolean espumoso) {
        this.espumoso = espumoso;
    }
    /**getter de si es espumoso o no
     * @return espumoso
     */
    public boolean getEspumoso() {
        return espumoso;
    }
    
    /**
     *redefinicion del metodo toString
     */
    public String toString() {
        return super.toString() + " denominacionDeOrigen: " + denominacionDeOrigen + " espumoso: " + espumoso + " }";
    }
}
