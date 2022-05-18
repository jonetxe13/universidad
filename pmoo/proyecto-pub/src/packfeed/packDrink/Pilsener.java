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
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
    public int getTemperatura() {
        return temperatura;
    }
    public void setPrecio() {
        this.precio = super.getPrecio();
        super.setPrecio(this.precio);
    }
    public double getPrecio(){
        return precio;
    }
    public String toString() {
        return super.toString() + " Temperatura: " + temperatura + "\n";
    }
}
