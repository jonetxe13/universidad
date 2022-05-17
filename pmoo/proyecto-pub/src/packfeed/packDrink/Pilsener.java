package packfeed.packDrink;

import packfeed.Food;

public class Pilsener extends Lager {
    private int temperatura;

    public Pilsener(String brandName, int calories, boolean glutenFree, 
                Food[] pairings, int IBU, int ABV, String origen, String importancia, String color, int temperatura) {
        super(brandName, calories, glutenFree, pairings, IBU, ABV, origen, importancia, color);
        setTemperatura(temperatura);
    } 
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
    public int getTemperatura() {
        return temperatura;
    }
    public String toString() {
        return super.toString() + " Temperatura: " + temperatura + "\n";
    }
}
