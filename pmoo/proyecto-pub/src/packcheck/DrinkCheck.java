package packcheck;
import packfeed.Food;
import packfeed.packDrink.Ale;
import packfeed.packDrink.DarkLager;
import packfeed.packDrink.Licores;
import packfeed.packDrink.Refresco;
import packfeed.packDrink.Vino;

public class DrinkCheck {

	public static void main(String[] args) {
		Refresco beer1;
		Licores beer2;
		Ale beer3;
		Vino beer4;
		DarkLager beer5;

		//usa los constructores (todos)
		beer1 = new Refresco("Budweiser", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, "Dulce", true, 5);
		beer2 = new Licores("Corona", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, 40, 5);
		beer3 = new Ale("Heineken", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, 23, 200, "Pale");
		beer4 = new Vino("Casa", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, true, true);
		beer5 = new DarkLager("San Miguel", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, 20, 200, "demandada", "tinto", "botella");

		// probando el método toString
		System.out.println(beer1.toString());
		System.out.println(beer2.toString());
		System.out.println(beer3.toString());
		System.out.println(beer4.toString());
		System.out.println(beer5.toString());
		
		beer1.addFood(new Food("Pollo"));
		beer2.addFood(new Food("Sopa"));
		beer3.addFood(new Food("Pizza"));
		beer4.addFood(new Food("Pizza"));
		beer5.addFood(new Food("Pizza"));

		
		// probando equals 
		System.out.println(beer1.getBrandName());
		System.out.println(beer2.getBrandName());
		System.out.println(beer3.getBrandName());
		System.out.println(beer4.getBrandName());
		System.out.println(beer5.getBrandName());

		System.out.println("Are they equals? " + beer1.equals(beer2));
		System.out.println("Are they equals? " + beer1.equals(beer3));
		System.out.println("Are they equals? " + beer1.equals(beer4));
		System.out.println("Are they equals? " + beer1.equals(beer5));

		//probando el método pairs y pairingList
		System.out.println("Pairing list: " + beer1.pairingList());
		System.out.println("Pairing list: " + beer2.pairingList());
		System.out.println("Pairing list: " + beer3.pairingList());
		System.out.println("Pairing list: " + beer4.pairingList());
		System.out.println("Pairing list: " + beer5.pairingList());


		System.out.println("It's a pair? " + beer1.pairs(new Food("Carne")));
		System.out.println("It's a pair? " + beer2.pairs(new Food("Pizza")));
		System.out.println("It's a pair? " + beer3.pairs(new Food("Pizza")));
		System.out.println("It's a pair? " + beer4.pairs(new Food("Pizza")));
		System.out.println("It's a pair? " + beer5.pairs(new Food("Carne")));

		//testing the removeFirstFood method
		beer1.removeFirstFood();
		beer2.removeFirstFood();
		beer3.removeFirstFood();
		beer4.removeFirstFood();
		beer5.removeFirstFood();

		//testing the removeFood method
		beer1.removeFood( new Food("Leche"));
		beer2.removeFood( new Food("Leche"));
		beer3.removeFood( new Food("Leche"));
		beer4.removeFood( new Food("Leche"));


		System.out.println(beer1.toString());
		System.out.println(beer2.toString());
		System.out.println(beer3.toString());
		System.out.println(beer4.toString());
		System.out.println(beer5.toString());
	}
}
