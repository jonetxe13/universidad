package packcheck;

import java.io.IOException;

import packfeed.Food;
import packfeed.packDrink.Ale;
import packfeed.packDrink.DarkLager;
import packfeed.packDrink.Licores;
import packfeed.packDrink.Refresco;
import packfeed.packDrink.Vino;
import packpub.Pub;

public class PubCheck {
    public static void main(String[] args) throws IOException {
        Pub pub1 = new Pub();
		Refresco beer1;
		Licores beer2;
		Ale beer3;
		Vino beer4;
		DarkLager beer5;
        
		//usa los constructores (todos)
		beer1 = new Refresco("Budweiser", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, "Dulce", true, 5);
		beer2 = new Licores("Estrella", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, 40, 5);
		beer3 = new Ale("Heineken", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, 23, 200, "Pale");
		beer4 = new Vino("Casa", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, true, true);
		beer5 = new DarkLager("San Miguel", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, 20000, 20000, "demandada", "tinto", "botella");

        //addDrinks
        pub1.addDrink(beer1); 
        pub1.addDrink(beer4); 
        pub1.addDrink(beer5); 
        pub1.addDrink(beer2); 
        pub1.addDrink(beer3); 

        pub1.showDrinks();

        //softDrinks
        pub1.sortDrinks();
        System.out.println("las bebidas ordenadas:" +  "\n");
        pub1.showDrinks();

        Food carne = new Food("carne", 500);

        pub1.addFood(carne);
        pub1.storeFoodInFile();
        pub1.showFoods();

        pub1.removeFood(carne);
        pub1.loadFoodFromFile();
        pub1.showFoods();

        //cheapestBeer
        System.out.println("la bebida mas barata es: " + pub1.cheapestBeer().toString() + "\n");
    }
}
