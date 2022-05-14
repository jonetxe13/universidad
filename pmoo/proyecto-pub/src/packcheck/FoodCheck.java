package packcheck;
import packfeed.Food;

public class FoodCheck {
	public static void main(String[] args) {
		Food salchicha1, tortillaPatata;
		//usa los constructores (todos)
		salchicha1 = new Food("Salchicha");
		tortillaPatata = new Food("Tortilla patata", 285);
		Food salchicha2 = new Food("Salchicha",300);
		
		// probando el método toString
		System.out.println("objeto salchicha1 objeto: " + salchicha1.toString());
		System.out.println("objeto salchicha2 objeto: " + salchicha2.toString());
		System.out.println("objeto tortillaPatata: " + tortillaPatata.toString());
		
		// probando equals 
		System.out.println("Salchicha1 y tortilla Patata son iguales? " + salchicha1.equals(tortillaPatata));
		System.out.println("Salchicha1 y Salchicha2  son iguales? " + salchicha1.equals(salchicha2));
		
	}

}
