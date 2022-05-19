package packpub;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import packfeed.Food;
import packfeed.packDrink.DarkLager;
import packfeed.packDrink.Drink;

public class Pub {
    private ArrayList<Drink> drinks;
    private ArrayList<Food> foods;

    public Pub(){
        drinks = new ArrayList<Drink>();
        foods = new ArrayList<Food>();
    }
    public boolean equals(String nombre){
        for(Drink drink: drinks){
            if(drink.getBrandName() == (nombre)){
                return true;
            }
        }
        return false;
    }
    public void addDrink(Drink drink){
        drinks.add(drink);
    }
    public void removeDrink(Drink drink){
        drinks.remove(drink);
    }
    public void removeDrinksByCalories(int calories){
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).getCalories() >= calories) {
                drinks.remove(i);
            }
        }
    }
    public Drink mostCaloricDrink(){
        Drink drink = drinks.get(0);
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).getCalories() > drink.getCalories()) {
                drink = drinks.get(i);
            }
        }
        return drink;
    }
    public void showDrinks(){
        for (int i = 0; i < drinks.size(); i++) {
            System.out.println(drinks.get(i).toString());
        }
        System.out.println("\n\n");
    }
    public Drink obtainDrink(String name){
        Drink result = new Drink();
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).getBrandName().equals(name)) {
                result = drinks.get(i);
            }
        }
        return result;
    }
    //sort the drink list by name
    public void sortDrinks(){
        for (int i = 0; i < drinks.size()-1; i++) {
            if((drinks.get(i).getBrandName().compareTo(drinks.get(i+1).getBrandName())) > 0){
                Drink temp = drinks.get(i);
                drinks.set(i, drinks.get(i+1));
                drinks.set(i+1, temp);
            }
        }
    }
    public void cheapestBeer(){ 
        Drink drink = drinks.get(0);
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).getPrecio() < drink.getPrecio()) {
                drink = drinks.get(i);
            }
        }
        System.out.println(drink.toString());
    }
}
