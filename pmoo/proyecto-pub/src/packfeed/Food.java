package packfeed;

public class Food {
    private String name;
    private String description;
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
    public String getDescription() {
        return description;
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
