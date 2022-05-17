package packfeed.packDrink;

import packfeed.Food;

public class Ale extends Beer{
    private String marca;

    public Ale(String brandName, int calories, boolean glutenFree, Food[] pairings, int IBU, int ABV, String origen, String marca) {
        super(brandName, calories, glutenFree, pairings, IBU, ABV, origen);
        setMarca(marca);
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getMarca() {
        return marca;
    }
    public String toString() {
        return super.toString() + " Marca: " + marca + "\n";
    }
    
}
