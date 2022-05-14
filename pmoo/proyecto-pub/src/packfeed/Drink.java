package packfeed;

public class Drink {
    private String brandName;
    private int calories;
    private boolean glutenFree;
    private Food[] pairings;

    public Drink(String brandName, int calories, boolean glutenFree, Food[] pairings) {
        setBrandName(brandName);
        setCalories(calories);
        setGlutenFree(glutenFree);
        setPairings(pairings);
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public int getCalories() {
        return calories;
    }
    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }
    public boolean getGlutenFree() {
        return glutenFree;
    }
    public void setPairings(Food[] pairings) {
        this.pairings = pairings;
    }
    public Food[] getPairings() {
        return pairings;
    }
    public boolean equals(Drink drink1) {
        return this.brandName == drink1.brandName;
    }
    public String toString() {
        String result ="Drink{" +
                "brandName='" + brandName + '\'' +
                ", calories='" + calories + '\'';
        for (int i = 0; i < pairings.length; i++) {
            if(pairings[i] != null) result += " " + pairings[i].getName() + " ";
        }
        result += '}';
        return result;
    }
    public void addFood(Food food){
        Food[] newPairings = new Food[pairings.length+1];
        for (int i = 0; i < pairings.length; i++) {
            newPairings[i] = pairings[i];
        }
        newPairings[newPairings.length-1] = food;
        setPairings(newPairings);
    }
    public void removeFood(Food food){
        for(int i = 0; i < pairings.length-1; i++){
            if(pairings[i].equals(food)){
                pairings[i] = pairings[pairings.length - 1];
                pairings[pairings.length - 1] = null;
            }
        }
    }
    public void removeFirstFood(){
        // pairings[0] = pairings[pairings.length - 1];
        // pairings[pairings.length - 1] = null;
        Food[] newPairings = new Food[pairings.length-1];
        for(int i = 0; i < pairings.length-1; i++){
            newPairings[i] = pairings[i+1];
        }
        setPairings(newPairings);
    }
    public int howManyPairing(){
        int count = 0;
        for(int i = 0; i < pairings.length; i++){
            if(pairings[i] != null){
                count++;
            }
        }
        return count;
    }
    public boolean pairs(Food food){
        boolean found = false;
        for(int i = 0; i < pairings.length; i++){
            if(pairings[i].equals(food)){
                found = true;
            }
        }
        return found;
    }
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
