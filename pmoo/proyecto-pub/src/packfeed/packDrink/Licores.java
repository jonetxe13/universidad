package packfeed.packDrink;

import packfeed.Food;

public class Licores extends Drink{
    private int graduacion;
    private int annos;
    private double precio;

    public Licores(String brandName, int calories, boolean glutenFree, Food[] pairings, 
                int graduacion, int annos) {
        super(brandName, calories, glutenFree, pairings);
        setGraduacion(graduacion);
        setAnnos(annos);
        setPrecio();
    }
    
    /**setter de la graduacion de alcohol del Licor
     * @param graduacion
     */
    public void setGraduacion(int graduacion) {
        this.graduacion = graduacion;
    }
    /**setter de la graduacion de alcohol del Licor
     * @return graduacion
     */
    public int getGraduacion() {
        return graduacion;
    }
    /**setter de los annos del Licor
     * @param annos
     */
    public void setAnnos(int annos) {
        this.annos = annos;
    }
    /**getter de los annos del Licor
     * @return annos
     */
    public int getAnnos() {
        return annos;
    }
    /**
     * setter del precio del Licor
     */
    public void setPrecio() {
        this.precio = 3 + (0.25* this.annos);
        super.setPrecio(this.precio);
    }
    
    /**getter del precio del Licor
     *@return precio
     */
    public double getPrecio(){
        return precio;
    }
    /**
     *redefinicion del metodo toString
     */
    public String toString() {
        return super.toString() + " graduacion: " + graduacion + " años: " + annos + " }";
    }
}
