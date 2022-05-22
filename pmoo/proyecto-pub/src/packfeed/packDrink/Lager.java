package packfeed.packDrink;

import packfeed.Food;

public class Lager extends Beer {
    private String importancia = "muy demandada";
    private String color;
    private double precio = 2;

    public Lager(String brandName, int calories, boolean glutenFree, 
                Food[] pairings, int IBU, int ABV, 
                String importancia, String color) {

        super(brandName, calories, glutenFree, pairings, IBU, ABV);
        setImportancia(importancia);
        setColor(color);
    } 
    /**setter de la importancia
     * @param importancia
     */
    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }
    /**getter de la importancia
     * @return importancia
     */
    public String getImportancia() {
        return importancia;
    }
    /**setter del color de la Lager
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }
    /**getter del color de la Lager
     * @return color
     */
    public String getColor() {
        return color;
    }
    /**
     *setter del precio de la Lager
     */
    public void setPrecio(double precio) {
        this.precio = precio;
        super.setPrecio(this.precio);
    }
    /**
     *getter del precio de la Lager
     */
    public double getPrecio(){
        return precio;
    }
    /**
     *redefinicion del metodo toString
     */
    public String toString() {
        return super.toString() + " Importancia: " + importancia + " Color: " + color;
    }
}

