package packfeed.packDrink;

import packfeed.Food;

public class Pilsener extends Lager {
    private int temperatura;
    private double precio;

    public Pilsener(String brandName, int calories, boolean glutenFree, 
                Food[] pairings, int IBU, int ABV, String importancia, String color, int temperatura) {
        super(brandName, calories, glutenFree, pairings, IBU, ABV, importancia, color);
        setTemperatura(temperatura);
        setPrecio();
    } 
    /**setter de la temperatura del Pilsener
     * @param temperatura
     */
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
    /**getter de la temperatura del Pilsener
     * @return temperatura
     */
    public int getTemperatura() {
        return temperatura;
    }
    /**
     * setter del precio del Pilsener
     */
    public void setPrecio() {
        this.precio = super.getPrecio();
        super.setPrecio(this.precio);
    }
    /**getter del precio del Pilsener
     *@return precio
     */
    public double getPrecio(){
        return precio;
    }
    /**
     *redefinicion del metodo toString
     */
    public String toString() {
        return super.toString() + " Temperatura: " + temperatura + " }";
    }
}
