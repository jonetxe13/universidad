package packcheck;
import packfeed.Food;
import packfeed.packDrink.Beer;
import packfeed.packDrink.DarkLager;
import packfeed.packDrink.Drink;
import packfeed.packDrink.Lager;
import packfeed.packDrink.Refresco;

public class DrinkCheck {

	public static void main(String[] args) {
		DarkLager beer1, beer2;
		//usa los constructores (todos)
		beer1 = new DarkLager("Budweiser", 300, false, new Food[] {new Food("Carne")}, 500, 5,  "muy demandada", "USA", "blanco", "botella");
		beer2 = new DarkLager("Budweiser", 300, false, new Food[] {new Food("Carne")}, 500, 5,  "muy demandada", "USA", "blanco", "barril");

		System.out.println(beer1.getPrecio());
		System.out.println(beer2.getPrecio());
		
		// // probando el método toString
		// System.out.println(beer1.toString());
		// System.out.println(beer2.toString());
		
		// beer1.addFood(new Food("Pollo"));
		// beer2.addFood(new Food("Sopa"));
		
		// // probando equals 
		// System.out.println(beer1.getBrandName());
		// System.out.println(beer2.getBrandName());

		// System.out.println("Are they equals? " + beer1.equals(beer2));

		// //probando el método pairs y pairingList
		// System.out.println("Pairing list: " + beer1.pairingList());
		// System.out.println("Pairing list: " + beer2.pairingList());

		// System.out.println("It's a pair? " + beer1.pairs(new Food("Carne")));
		// System.out.println("It's a pair? " + beer2.pairs(new Food("Carne")));

		// //testing the removeFirstFood method
		// beer1.removeFirstFood();
		// beer2.removeFirstFood();

		// //testing the removeFood method
		// beer1.removeFood( new Food("Leche"));

		// System.out.println(beer1.toString());
		// System.out.println(beer2.toString());
	}


}
