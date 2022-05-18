package packfeed.packDrink;

import packfeed.Food;

public class Licores extends Drink{
    private int graduacion;
    private int años;
    private double precio;

    public Licores(String brandName, int calories, boolean glutenFree, Food[] pairings, int graduacion, int años) {
        super(brandName, calories, glutenFree, pairings);
        setGraduacion(graduacion);
        setAños(años);
        setPrecio();
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
    public void setPrecio() {
        this.precio = 3 + (0.25* this.años);
        super.setPrecio(this.precio);
    }
    public double getPrecio(){
        return precio;
    }
    public String toString() {
        return super.toString() + " graduacion: " + graduacion + " años: " + años + "\n";
    }
}
