package packpub;
import java.util.ArrayList;
import java.util.Map;

import packfeed.Drink;
import packfeed.Food;

public class Pub {
    private ArrayList<Drink> drinks = new ArrayList<Drink>();
    private ArrayList<Food> foods = new ArrayList<Food>();

    public Pub(){
        drinks = null;
        foods = null;
    }
    public void addDrink(Drink drink){
        drinks.add(drink);
    }
    public void removeDrink(Drink drink){
        drinks.remove(drink);
    }
    public ArrayList<String> removeDrinksByCalories(int calories){
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).getCalories() > calories) {
                drinks.remove(i);
                list.add(drinks.get(i).getBrandName());
            }
        }
        return list;
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
    }
    public Drink obtainDrink(String name){
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).getBrandName().equals(name)) {
                return drinks.get(i);
            }
        }
        return null;
    }
}
