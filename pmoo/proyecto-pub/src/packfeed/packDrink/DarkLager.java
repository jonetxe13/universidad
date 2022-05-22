package packfeed.packDrink;

import packfeed.Food;

public class DarkLager extends Lager {
    private String botellaObarril;
    private double precio;

    public DarkLager(String brandName, int calories, boolean glutenFree, 
                    Food[] pairings, int IBU, int ABV, String importancia, String color, String botellaObarril) {
        super(brandName, calories, glutenFree, pairings, IBU, ABV, importancia, color);
        setBotellaObarril(botellaObarril);
        setPrecio();
    }

    /**setter de botellaobarril de DarkLager
     * @param botellaObarril
     */
    public void setBotellaObarril(String botellaObarril) {
        this.botellaObarril = botellaObarril;
    }
    /**getter de botellaobarril de DarkLager
     * @return bottelaOBarril
     */
    public String getBotellaObarril() {
        return botellaObarril;
    }
    /**
     *getter del precio de DarkLager
     */
    public double getPrecio(){
        super.setPrecio(this.precio);
        return precio;
    }
    /**
     * setter del precio de DarkLager
     */
    public void setPrecio() {
        double precio1 = 0;
        if (this.botellaObarril == "barril") {
            precio1 = (super.getPrecio()+(super.getIBU()*0.01));
        }else if(this.botellaObarril== "botella") {
            precio1 = (super.getPrecio()+(super.getIBU()*0.01)+1);
        }
        this.precio = precio1;
        super.setPrecio(precio1);
    }
    /**
     *redefinicion del metodo toString para DarkLager
     */
    public String toString() {
        return super.toString() + " Botella Obarril: " + botellaObarril + " }";
    }
    
}
