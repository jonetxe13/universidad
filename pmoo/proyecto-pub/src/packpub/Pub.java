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

/**
 * @author jonetxe13
 *
 */
/**
 * @author jonetxe13
 *
 */
/**
 * @author jonetxe13
 *
 */
public class Pub {
    private ArrayList<Drink> drinks;
    private ArrayList<Food> foods;
    
    public Pub(){
        drinks = new ArrayList<Drink>();
        foods = new ArrayList<Food>();
    }
    /**
     * redefine equals
     * @param nombre
     * @return boolean
     */
    public boolean equals(String nombre){
        for(Drink drink: drinks){
            if(drink.getBrandName() == (nombre)){
                return true;
            }
        }
        return false;
    }
    /**annade drink al vector de bebidas
     * @param drink
     */
    public void addDrink(Drink drink){
        drinks.add(drink);
    }
    /** elimina drink del ArrayList drinks
     * @param drink
     */
    public void removeDrink(Drink drink){
        drinks.remove(drink);
    }
    /** elimina las bebidas que tengan mas calorias que el valor calories
     * @param calories
     */
    public void removeDrinksByCalories(int calories){
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).getCalories() >= calories) {
                drinks.remove(i);
            }
        }
    }
    /** devuelve la bebida con mas calorias dentro del ArrayList drinks
     * @return drink
     */
    public Drink mostCaloricDrink(){
        Drink drink = drinks.get(0);
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).getCalories() > drink.getCalories()) {
                drink = drinks.get(i);
            }
        }
        return drink;
    }
    
    /**
     * imprime en la consola todas las bebidas dentro del ArrayList drinks
     */
    public void showDrinks(){
        for (int i = 0; i < drinks.size(); i++) {
            System.out.println(drinks.get(i).toString());
        }
        System.out.println("\n\n");
    }
    /**devuelve la bebida del vector ArrayList drinks que tenga el mismo nombre que el especificado 
     * @param name
     * @return
     */
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
    /**
     * ordena las bebidas del ArrayList drinks por su nombre en orden alfabetico descendiente
     */
    public void sortDrinks(){
        for (int i = 0; i < drinks.size()-1; i++) {
            if((drinks.get(i).getBrandName().compareTo(drinks.get(i+1).getBrandName())) > 0){
                Drink temp = drinks.get(i);
                drinks.set(i, drinks.get(i+1));
                drinks.set(i+1, temp);
            }
        }
    }
    /**devuelve la cerveza mas barata dentro del ArrayList drinks
     * @return beer
     */
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
    /**annade la comida especificada 
     * @param food
     */
    public void addFood(Food food){
        for(int i = 0; i < foods.size(); i++) {
            if(food.equals(foods.get(i))){
                throw new IllegalArgumentException("Food already exists");
            }
        }
        foods.add(food);
    }
    /**Guarda en un archivo .txt con el nombre especificado y mete en el toda la comida que haya en el ArrayList foods
     * @throws IOException
     */
    public void storeFoodInFile() throws IOException{

        String filePath = Path.of("src/packpub/data").toString();
        String nombredearchivo= JOptionPane.showInputDialog("ingrese el nombre del archivo a crear"); 
        FileWriter fw = new FileWriter( filePath + "/" + nombredearchivo + ".txt");
        
        for (int i = 0; i < foods.size(); i++) {
            fw.write(foods.get(i).toString() + "\n");
        }
        fw.flush();
        fw.close();
    }
    /**
     * carga en el ArrayList foods todas las comidas que encuentre en el archivo que tenga el nombre que meta el usuario
     */
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
    /**elimina la comida especificada del ArrayList foods
     * @param food
     */
    public void removeFood(Food food){
        foods.remove(food);
    }
    /**elimina dentro del ArrayList foods las comidas que tengan mas calorias que las especificadas
     * @param calories
     */
    public void removeFoodsByCalories(int calories){
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getCalories() >= calories) {
                foods.remove(i);
            }
        }
    }
    /**devuelve la comida que tenga mas calorias dentro del ArrayList foods
     * @return food
     */
    public Food mostCaloricFood(){
        Food food = foods.get(0);
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getCalories() > food.getCalories()) {
                food = foods.get(i);
            }
        }
        return food;
    }
    /**
     * muestra todas las comidas dentro del ArrayList foods
     */
    public void showFoods(){
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(foods.get(i).toString());
        }
        System.out.println("\n\n");
    }
}