package packfeed;

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
    public void setName(String name) {
        this.name = name;
    }
    public void setCalories(int calories) {
        if(calories < 0){throw new IllegalArgumentException("Calories must be greater than 0");}

        this.calories = calories;
    }
    public String getName() {
        return name;
    }
    public int getCalories() {
        return calories;
    }
    public Boolean equals(Food food1) {
        return this.getName()==food1.getName();
    }
    public String toString() {
        return "Food{ " +
                "name='" + name + "' |" +
                " calories='" + calories + 
                "' }";
    }
}
