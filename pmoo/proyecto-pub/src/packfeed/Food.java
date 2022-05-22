package packfeed;

/**
 * @author jonetxe13
 *
 */

public class Food {
    private String name;
    private int calories;

    public Food(String name, int calories) {
        setName(name);
        setCalories(calories);
    }
    public Food(String name){
        this.name = name;
    }
    /**setter del nombre
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**setter de las calorias
     * @param calories
     */
    public void setCalories(int calories) {
        if(calories < 0){throw new IllegalArgumentException("Calories must be greater than 0");}

        this.calories = calories;
    }
    /**getter del nombre
     * @return name
     */
    public String getName() {
        return name;
    }
    /**getter de las calorias
     * @return calories
     */
    public int getCalories() {
        return calories;
    }
    /** redefinicion del metodo equals
     * @param food1
     * @return boolean
     */
    public Boolean equals(Food food1) {
        return this.getName()==food1.getName();
    }
    /**
     *redefinicion del metodo toString
     */
    public String toString() {
        return "Food{ " +
                "name='" + name + "' |" +
                " calories='" + calories + 
                "' }";
    }
}
