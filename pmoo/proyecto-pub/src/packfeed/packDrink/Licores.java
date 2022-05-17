package packfeed.packDrink;

import packfeed.Food;

public class Licores extends Drink{
    private int graduacion;
    private int años;

    public Licores(String brandName, int calories, boolean glutenFree, Food[] pairings, int graduacion, int años) {
        super(brandName, calories, glutenFree, pairings);
        setGraduacion(graduacion);
        setAños(años);
    }
    public void setGraduacion(int graduacion) {
        this.graduacion = graduacion;
    }
    public int getGraduacion() {
        return graduacion;
    }
    public void setAños(int años) {
        this.años = años;
    }
    public int getAños() {
        return años;
    }
    public String toString() {
        return super.toString() + " graduacion: " + graduacion + " años: " + años + "\n";
    }
}
