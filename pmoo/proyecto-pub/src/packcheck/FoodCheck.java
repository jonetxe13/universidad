package packcheck;
import packfeed.Food;

public class FoodCheck {

	public static void main(String[] args) {
		Food salchicha1, tortillaPatata;
		//usa los constructores (todos)
		salchicha1 = new Food("Salchicha");
		tortillaPatata = new Food("Tortilla patata", 285);
		Food salchicha2 = new Food("Salchicha");
		
		// probando el método toString
		System.out.println("objeto salchicha1 objeto: " + salchicha1.toString());
		System.out.println("objeto tortillaPatata: " + tortillaPatata.toString());
		
		// probando equals 
		System.out.println("Salchicha y tortilla Patata son iguales? " + salchicha1.equals(tortillaPatata));
		System.out.println("Salchicha y Salchicha  son iguales? " + salchicha1.equals(salchicha2));
		
	}

}
