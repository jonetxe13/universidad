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
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getMarca() {
        return marca;
    }
    public void setPrecio() {
        this.precio = 3 + (0.05*super.getABV());
        super.setPrecio(this.precio);
    }
    public double getPrecio(){
        return precio;
    }
    public String toString() {
        return super.toString() + " Marca: " + marca + " }";
    }
    
}
