package packfeed.packDrink;

import packfeed.Food;

public class DarkLager extends Lager {
    private String botellaObarril;
    private double precio;

    public DarkLager(String brandName, int calories, boolean glutenFree, 
                    Food[] pairings, int IBU, int ABV, String importancia, String origen, String color, String botellaObarril) {
        super(brandName, calories, glutenFree, pairings, IBU, ABV, importancia, origen, color);
        setBotellaObarril(botellaObarril);
        setPrecio();
    }
    public void setBotellaObarril(String botellaObarril) {
        this.botellaObarril = botellaObarril;
    }
    public String getBotellaObarril() {
        return botellaObarril;
    }
    public double getPrecio(){
        super.setPrecio(this.precio);
        return precio;
    }
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
    public String toString() {
        return super.toString() + " Botella Obarril: " + botellaObarril + "\n";
    }
    
}
