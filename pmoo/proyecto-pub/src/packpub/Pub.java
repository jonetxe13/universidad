package packpub;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import packfeed.Food;
import packfeed.packDrink.Beer;
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
    public Beer cheapestBeer(){
        Beer beer = new Beer();
        beer.setPrecio(5000);
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i) instanceof Beer) {
                if (drinks.get(i).getPrecio() < beer.getPrecio()) {
                    beer = (Beer) drinks.get(i);
                }
            }
        }
        return beer;
    }
    public void addFood(Food food){
        for(int i = 0; i < foods.size(); i++) {
            if(food.equals(foods.get(i))){
                throw new IllegalArgumentException("Food already exists");
            }
        }
        foods.add(food);
    }
    public void storeFoodInFile() throws IOException{
        String filePath = Path.of("src/packpub/data").toString();
        String nombredearchivo= JOptionPane.showInputDialog("ingrese el nombre del archivo a crear"); 
        BufferedWriter fw = null;
        try {
            fw = new BufferedWriter(new FileWriter( filePath + "/" + nombredearchivo + ".txt"));
            for (int i = 0; i < foods.size(); i++) {
                fw.write(foods.get(i).toString());
                fw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo");
        }
        catch( NullPointerException n){
            System.out.println("No se ingreso ningun nombre");
        }
        finally{
            fw.close();
        }
    }
    public void loadFoodFromFile(){
        String filePath = Path.of("src/packpub/data").toString();
        String nombredearchivo= JOptionPane.showInputDialog("ingrese el nombre del archivo a cargar"); 
        try {
            BufferedReader fr = new BufferedReader(new FileReader(filePath + "/" + nombredearchivo + ".txt"));
            String linea;
            while((linea = fr.readLine()) != null){
                String[] datos = linea.split("'");
                Food food = new Food(datos[1], Integer.parseInt(datos[3]));
                addFood(food);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }

    }
    public void removeFood(Food food){
        foods.remove(food);
    }
    public void removeFoodsByCalories(int calories){
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getCalories() >= calories) {
                foods.remove(i);
            }
        }
    }
    public Food mostCaloricFood(){
        Food food = foods.get(0);
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getCalories() > food.getCalories()) {
                food = foods.get(i);
            }
        }
        return food;
    }
    public void showFoods(){
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(foods.get(i).toString());
        }
        System.out.println("\n\n");
    }
}