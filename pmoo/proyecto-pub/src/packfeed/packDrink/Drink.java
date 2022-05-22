package packfeed.packDrink;

import packfeed.Food;

public class Drink {
    private String brandName;
    private int calories;
    private boolean glutenFree;
    private Food[] pairings;
    private double precio;

    public Drink(String brandName, 
            int calories,
            boolean glutenFree,
            Food[] pairings) {
        setBrandName(brandName);
        setCalories(calories);
        setGlutenFree(glutenFree);
        setPairings(pairings);
    }
    public Drink(){

    }
    /**setter del nombre de la marca
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    /**getter del nombre de la marca
     * @return brandName
     */
    public String getBrandName() {
        return brandName;
    }
    /**setter de las calorias de la bebida
     * @param calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }
    /**getter de las calorias de la bebida
     * @return calories
     */
    public int getCalories() {
        return calories;
    }
    /**setter de si contiene gluten o no
     * @param glutenFree
     */
    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }
    /**getter de si tiene gluten o no
     * @return glutenFree
     */
    public boolean getGlutenFree() {
        return glutenFree;
    }
    /**setter de alimentos con los que la bebida marida bien
     * @param pairings
     */
    public void setPairings(Food[] pairings) {
        this.pairings = pairings;
    }
    /**getter de alimentos con los que la bebida marida bien
     * @return pairings
     */
    public Food[] getPairings() {
        return pairings;
    }
    /**setter del precio de la bebida
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    /**getter del precio de la bebida
     * @return precio
     */
    public double getPrecio(){
        return precio;
    }
    /**redefinicion del metodo equals
     * @param drink1
     * @return boolean
     */
    public boolean equals(Drink drink1) {
        return this.brandName == drink1.brandName;
    }
    /**
     *redefinicion del metodo toString
     */
    public String toString() {

        String result = this.getClass().getSimpleName() + "{" +
                "brandName='" + brandName + '\'' +
                ", calories='" + calories + '\'' + ", glutenFree='" + glutenFree + '\'' + ", pairings= "; 
        for (int i = 0; i < pairings.length; i++) {
            result += pairings[i].getName() + ", ";
        }
        result += ", precio: " + precio;
        return result;
    }
    /**annade alimento a la lista de alimentos que maridan bien con esta bebida
     * @param food
     */
    public void addFood(Food food){
        Food[] newPairings = new Food[pairings.length+1];
        for (int i = 0; i < pairings.length; i++) {
            newPairings[i] = pairings[i];
        }
        newPairings[newPairings.length-1] = food;
        setPairings(newPairings);
    }
    /**elimina la comida especificada de la lista de alimentos que maridan bien con esta bebida
     * @param food
     */
    public void removeFood(Food food){
        for(int i = 0; i < pairings.length-1; i++){
            if(pairings[i].equals(food)){
                pairings[i] = pairings[pairings.length - 1];
                pairings[pairings.length - 1] = null;
            }
        }
    }
    /**
     * elimina la el primer alimento de la lista de alimentos que maridan con esta bebida
     */
    public void removeFirstFood(){
        // pairings[0] = pairings[pairings.length - 1];
        // pairings[pairings.length - 1] = null;
        Food[] newPairings = new Food[pairings.length-1];
        for(int i = 0; i < pairings.length-1; i++){
            newPairings[i] = pairings[i+1];
        }
        setPairings(newPairings);
    }
    /**cuenta cuantos alimentos hay en la lista de los que maridan bien con la bebida
     * @return count
     */
    public int howManyPairing(){
        int count = 0;
        for(int i = 0; i < pairings.length; i++){
            if(pairings[i] != null){
                count++;
            }
        }
        return count;
    }
    /**devuelve true si el alimento especificado marida bien con la bebida
     * @param food
     * @return boolean
     */
    public boolean pairs(Food food){
        boolean found = false;
        for(int i = 0; i < pairings.length; i++){
            if(pairings[i].equals(food)){
                found = true;
            }
        }
        return found;
    }
    /**devuelve la lista de los alimentos que maridan bien con la bebida
     * @return list
     */
    public String pairingList(){
        String list = "";
        for(int i = 0; i < pairings.length; i++){
            if(pairings[i] != null){
                list += pairings[i].getName() + " ";
            }
        }
        return list;
    }
}
