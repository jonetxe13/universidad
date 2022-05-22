package packfeed.packDrink;

import packfeed.Food;

public class Ale extends Beer{
    private String marca;
    private double precio;

    public Ale(String brandName, int calories, boolean glutenFree, Food[] pairings, int IBU, int ABV, String marca) {
        super(brandName, calories, glutenFree, pairings, IBU, ABV);
        setMarca(marca);
        setPrecio();
    }
    /**setter de la marca de la cerveza
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**getter de la marca de la cerveza
     * @return marca
     */
    public String getMarca() {
        return marca;
    }
    /**
     * setter del precio de la cerveza
     */
    public void setPrecio() {
        this.precio = 3 + (0.05*super.getABV());
        super.setPrecio(this.precio);
    }
    /**
     *getter del precio de la cerveza
     */
    public double getPrecio(){
        return precio;
    }
    /**
     *redefinicion del metodo toString
     */
    public String toString() {
        return super.toString() + " Marca: " + marca + " }";
    }
    
}
