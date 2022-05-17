package packfeed.packDrink;

import packfeed.Food;

public class Lager extends Beer {
    private String importancia = "muy demandada";
    private String color;
    private double precio = 2;

    public Lager(String brandName, int calories, boolean glutenFree, 
                Food[] pairings, int IBU, int ABV, String origen, 
                String importancia, String color) {

        super(brandName, calories, glutenFree, pairings, IBU, ABV, origen);
        setImportancia(importancia);
        setColor(color);
    } 
    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }
    public String getImportancia() {
        return importancia;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
        super.setPrecio(this.precio);
    }
    public double getPrecio(){
        return precio;
    }
    public String toString() {
        return super.toString() + " Importancia: " + importancia + "\n" + " Color: " + color + "\n";
    }
}

