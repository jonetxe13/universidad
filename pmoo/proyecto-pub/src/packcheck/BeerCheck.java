package packcheck;
import packfeed.Drink;
import packfeed.Food;

public class BeerCheck {

	public static void main(String[] args) {
		Drink beer1, beer2;
		//usa los constructores (todos)
		beer1 = new Drink("Heineken", 230, false, new Food[]{new Food("Carne"), new Food("Leche")});
		beer2 = new Drink("San Miguel", 350, true, new Food[]{new Food("Pasta"), new Food("Patata")});

		beer1.addFood(new Food("Pollo"));
		beer2.addFood(new Food("Sopa"));
		
		// probando el método toString
		System.out.println(beer1.toString());
		System.out.println(beer2.toString());
		
		// probando equals 
		System.out.println(beer1.getBrandName());
		System.out.println(beer2.getBrandName());

		System.out.println("son iguales? " + beer1.equals(beer2));

		//probando el método pairs y pairingList
		System.out.println("Pairing list: " + beer1.pairingList());
		System.out.println("Pairing list: " + beer2.pairingList());

		System.out.println("It's a pair? " + beer1.pairs(new Food("Carne")));
		System.out.println("It's a pair? " + beer2.pairs(new Food("Carne")));
	}

}