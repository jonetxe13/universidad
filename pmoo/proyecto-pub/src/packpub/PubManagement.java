package packpub;

import java.io.IOException;
import java.util.Scanner;

import packfeed.Food;
import packfeed.packDrink.Ale;
import packfeed.packDrink.DarkLager;
import packfeed.packDrink.Drink;
import packfeed.packDrink.Licores;
import packfeed.packDrink.Pilsener;
import packfeed.packDrink.Refresco;
import packfeed.packDrink.Vino;
import packfeed.packDrink.Water;

public class PubManagement {
    public static void main(String[] args){
        System.out.println(" -----------------------------");
        System.out.println("| Bienvenido al Pub de Jon!   |");
        System.out.println(" -----------------------------");

        Pub pub = new Pub();

        Refresco beer1 = new Refresco("Budweiser", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, "Dulce", true, 5);
		Licores beer2 = new Licores("Estrella", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, 40, 5);
		Ale beer3 = new Ale("Heineken", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, 23, 200, "Pale");
		Vino beer4 = new Vino("Casa", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, true, true);
		DarkLager beer5 = new DarkLager("San Miguel", 500, false, new Food[] { new Food("Pizza", 500), new Food("Pizza", 500) }, 20000, 20000, "demandada", "tinto", "botella");

        //addDrinks
        pub.addDrink(beer1); 
        pub.addDrink(beer4); 
        pub.addDrink(beer5); 
        pub.addDrink(beer2); 
        pub.addDrink(beer3); 

        Food food = new Food("Pizza", 500);
        Food food2 = new Food("Carne", 500);
        Food food3 = new Food("Pasta", 500);
        pub.addFood(food);
        pub.addFood(food2);
        pub.addFood(food3);

        String ESTADO = "MENU";
        int BebidasComidasOSalir = 0;
        int opcionSeleccionada = 0;

        while (true){
            Scanner sc = new Scanner(System.in);
            if(ESTADO == "MENU"){
                System.out.println("Selecciona una de las operaciones disponibles: ");
                System.out.println("1.Bebidas       2.Comidas      3.Salir\n");
                BebidasComidasOSalir = sc.nextInt();
                System.out.println("ESTADO: " + ESTADO);
                // sc.close();
                ESTADO = "SELECCIONARACCION";
            }
            else if(ESTADO == "SELECCIONARACCION"){
                System.out.println("ESTADO: " + ESTADO);

                if(BebidasComidasOSalir == 1){ 
                    System.out.println("\n\n1.Agregar bebidas(con todos los parametros)           2.Eliminar bebidas                 3.Eliminar bebidas por calorias\n");
                    System.out.println("4.Mostrar la bebida mas calorica    5.Mostrar todas las bebidas   6.Mostrar bebida por nombre\n");
                    System.out.println("7.Mostrar bebidas ordenadas por nombre                  8.Mostrar la cerveza mas barata\n");

                }
                else if(BebidasComidasOSalir == 2){
                    System.out.println("1.Agregar comida    2.Guardar la comida en un archivo       3.Cargar la comida de un archivo\n");
                    System.out.println("4.Eliminar comida   5.Eliminar comida por calorias       6.Mostrar la comida mas calorica\n");
                    System.out.println("7.Mostrar todas las comidas\n");
                }
                else if(BebidasComidasOSalir == 3){
                    System.out.println("\n\nGracias por usar el Pub de Jon!");
                    System.exit(0);
                }
                else{
                    System.out.println("Opcion invalida");
                }
                opcionSeleccionada = (sc.nextInt());
                // sc.close();
                ESTADO = "EJECUTARACCION";

            }
            else if(ESTADO == "EJECUTARACCION"){
                System.out.println("ESTADO: " + ESTADO);

            if(BebidasComidasOSalir == 1){
            //opciones de bebidas 

                switch(opcionSeleccionada){
                    //crear bebidas
                    case 1: {
                        // Scanner sc = new Scanner(System.in);
                        System.out.println("Ingrese el estilo de la bebida: \n\n 1.Agua     2.Cerveza   3.Vino\n");
                        System.out.println("4.Refresco    5.Licores \n");
                        int estiloBebida = sc.nextInt();
                        switch(estiloBebida){
                            case 1: 
                                System.out.println("Ingrese el nombre de la bebida: \n");
                                String nombreBebida = sc.next();
                                System.out.println("Ingrese la cantidad de calorias de la bebida: \n");
                                int caloriasBebida = sc.nextInt();
                                System.out.println("Ingrese si la bebida es gluten free: \n");
                                boolean glutenFreeBebida = sc.nextBoolean();
                                System.out.println("Ingrese el sabor de la bebida: \n");
                                String saborBebida = sc.next();
                                System.out.println("Ingrese si la bebida tiene burbujas: \n");
                                boolean burbujasBebida = sc.nextBoolean();
                                System.out.println("Ingrese si la bebida es de manantial: \n");
                                boolean manantialBebida = sc.nextBoolean();
                                Water agua = new Water(nombreBebida, caloriasBebida, glutenFreeBebida, null, saborBebida, burbujasBebida, manantialBebida);
                                pub.addDrink(agua);
                                break;
                            case 2:
                                System.out.println("Ingrese el tipo de cerveza que quiere: \n");
                                System.out.println("1.Ale      2.DarkLager    3.Pilsener\n");
                                int tipoCerveza = sc.nextInt();
                                //las variables de cerveza base las inicio aqui para poder reutilizarlas
                                String nombreCerveza = null;
                                int caloriasCerveza = 0;
                                boolean glutenFreeCerveza = false;
                                int IBU = 0;
                                int ABV = 0;

                                switch(tipoCerveza){
                                    case 1:
                                        System.out.println("Ingrese el nombre de la cerveza: \n");
                                        nombreCerveza = sc.next();
                                        System.out.println("Ingrese la cantidad de calorias de la cerveza: \n");
                                        caloriasCerveza = sc.nextInt();
                                        System.out.println("Ingrese si la cerveza es gluten free: \n");
                                        glutenFreeCerveza = sc.nextBoolean();
                                        System.out.println("Ingrese el IBU de la cerveza: \n");
                                        IBU = sc.nextInt();
                                        System.out.println("Ingrese el ABV de la cerveza: \n");
                                        ABV = sc.nextInt();
                                        System.out.println("Ingrese la marca de la cerveza: \n");
                                        String marca = sc.next();
                                        Ale ale = new Ale(nombreCerveza, caloriasCerveza, glutenFreeCerveza, null, IBU, ABV, marca);
                                        pub.addDrink(ale);
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el nombre de la cerveza: \n");
                                        nombreCerveza = sc.next();
                                        System.out.println("Ingrese la cantidad de calorias de la cerveza: \n");
                                        caloriasCerveza = sc.nextInt();
                                        System.out.println("Ingrese si la cerveza es gluten free: \n");
                                        glutenFreeCerveza = sc.nextBoolean();
                                        System.out.println("Ingrese el IBU de la cerveza: \n");
                                        IBU = sc.nextInt();
                                        System.out.println("Ingrese el ABV de la cerveza: \n");
                                        ABV = sc.nextInt();
                                        System.out.println("Ingrese la demanda de la cerveza: \n");
                                        String demanda = sc.next();
                                        System.out.println("Ingrese el color de la cerveza: \n");
                                        String color = sc.next();
                                        System.out.println("Ingrese si se guarda en botella o en barril: \n");
                                        String botellaOBarril = sc.next();


                                        DarkLager darkLager = new DarkLager(nombreCerveza, caloriasCerveza, glutenFreeCerveza, null, IBU, ABV, demanda, color, botellaOBarril);
                                        pub.addDrink(darkLager);
                                        break;
                                    case 3:
                                        System.out.println("Ingrese el nombre de la cerveza: \n");
                                        nombreCerveza = sc.next();
                                        System.out.println("Ingrese la cantidad de calorias de la cerveza: \n");
                                        caloriasCerveza = sc.nextInt();
                                        System.out.println("Ingrese si la cerveza es gluten free: \n");
                                        glutenFreeCerveza = sc.nextBoolean();
                                        System.out.println("Ingrese el IBU de la cerveza: \n");
                                        IBU = sc.nextInt();
                                        System.out.println("Ingrese el ABV de la cerveza: \n");
                                        ABV = sc.nextInt();
                                        System.out.println("Ingrese la demanda de la cerveza: \n");
                                        String demandaPilsener = sc.next();
                                        System.out.println("Ingrese el color de la cerveza: \n");
                                        String colorPilsener = sc.next();
                                        System.out.println("Ingrese la temperatura de la cerveza: \n");
                                        int temperatura = sc.nextInt();

                                        Pilsener pilsener = new Pilsener(nombreCerveza, caloriasCerveza, glutenFreeCerveza, null, IBU, ABV, demandaPilsener, colorPilsener, temperatura);
                                        pub.addDrink(pilsener);
                                        break;
                            }
                            case 3:
                                System.out.println("Ingrese el nombre de la bebida: \n");
                                String nombreBebidaVino = sc.next();
                                System.out.println("Ingrese la cantidad de calorias de la bebida: \n");
                                int caloriasBebidaVino = sc.nextInt();
                                System.out.println("Ingrese si la bebida es gluten free: \n");
                                boolean glutenFreeBebidaVino = sc.nextBoolean();
                                System.out.println("Ingrese si tiene denominacion de origen: \n");
                                boolean denominacionBebidaVino = sc.nextBoolean();
                                System.out.println("Ingrese si es espumoso o no: \n");
                                boolean espumosoONo= sc.nextBoolean();
                                Vino vino = new Vino(nombreBebidaVino, caloriasBebidaVino, glutenFreeBebidaVino, null, denominacionBebidaVino, espumosoONo);
                                pub.addDrink(vino);
                                break;
                            case 4:
                                System.out.println("Ingrese el nombre de la bebida: \n");
                                String nombreRefresco = sc.next();
                                System.out.println("Ingrese la cantidad de calorias de la bebida: \n");
                                int caloriasRefresco = sc.nextInt();
                                System.out.println("Ingrese si la bebida es gluten free: \n");
                                boolean glutenFreeRefresco = sc.nextBoolean();
                                System.out.println("Ingrese el sabor de la bebida: \n");
                                String saborRefresco = sc.next();
                                System.out.println("Ingrese si la bebida tiene burbujas: \n");
                                boolean burbujasRefresco = sc.nextBoolean();
                                System.out.println("Ingrese el porcentaje de zumo de la bebida: \n");
                                int porcentajeZumoRefresco = sc.nextInt();
                                Refresco refresco = new Refresco(nombreRefresco, caloriasRefresco, glutenFreeRefresco, null, saborRefresco, burbujasRefresco, porcentajeZumoRefresco);
                                pub.addDrink(refresco);
                                break;
                            case 5: 
                                System.out.println("Ingrese el nombre de la bebida: \n");
                                String nombreLicor = sc.next();
                                System.out.println("Ingrese la cantidad de calorias de la bebida: \n");
                                int caloriasLicor = sc.nextInt();
                                System.out.println("Ingrese si la bebida es gluten free: \n");
                                boolean glutenFreeLicor = sc.nextBoolean();
                                System.out.println("Ingrese la graduacion de la bebida: \n");
                                int graduacionLicor = sc.nextInt();
                                System.out.println("Ingrese los annos que tiene el licor: \n");
                                int annosLicor = sc.nextInt();

                                Licores licor = new Licores(nombreLicor, caloriasLicor, glutenFreeLicor, null, graduacionLicor, annosLicor);
                                pub.addDrink(licor);
                                break;
                        }
                        sc.close();
                    }
                    case 2: {
                        // Scanner sc = new Scanner(System.in);
                        System.out.println("Ingrese el nombre de la bebida a eliminar: \n");
                        String nombreBebida = sc.next();
                        Drink drink = new Drink(nombreBebida, 0, false, null);
                        pub.removeDrink(drink);
                        break;
                    }
                    case 3:{
                        // Scanner sc = new Scanner(System.in);
                        System.out.println("Ingrese un numero de calorias, las que tengan mas calorias que ese numero seran eliminadas: \n");
                        int calorias = sc.nextInt();
                        pub.removeDrinksByCalories(calorias);
                        break;
                    }
                    case 4:{
                        System.out.println("Mostrar la bebida mas calorica: \n");
                        pub.mostCaloricDrink().toString();
                        break;
                    }
                    case 5:{
                        System.out.println("Mostrar todas las bebidas: \n");
                        pub.showDrinks();
                        break;
                    }
                    case 6:{
                        // Scanner sc = new Scanner(System.in);
                        System.out.println("Ingresa un nombre para ver las caracteristicas de esa bebida: \n");
                        String nombreBebidaCaracteristicas = sc.next();
                        pub.obtainDrink(nombreBebidaCaracteristicas);
                        break; 
                    }
                    case 7:{
                        System.out.println("Mostrar todas las bebidas ordenadas por nombre: \n");
                        pub.sortDrinks();
                        pub.showDrinks();
                        break;
                    }
                    case 8:{
                        System.out.println("Mostrar la cerveza mas barata: \n");
                        pub.cheapestBeer().toString();
                        break;
                    }

                }
                System.out.println("1.Seguir en bebidas     2.Cambiar de clase      3.Salir\n");
                int seguirMenuOSalir = sc.nextInt();

                if (seguirMenuOSalir == 1) {
                    ESTADO = "SELECCIONARACCION";
                }
                else if(seguirMenuOSalir == 2){
                    ESTADO = "SELECCIONARCLASE";
                }
                else if(seguirMenuOSalir == 3){
                    ESTADO = "SALIR";
                }
                else{
                    System.out.println("Ingrese una opcion valida");
                }

            }
            else if(BebidasComidasOSalir == 2){
                switch(opcionSeleccionada){
                    case 1: {
                        System.out.println("Ingrese el nombre de la comida: \n");
                        String nombreComida = sc.next();
                        System.out.println("Ingrese la cantidad de calorias de la comida: \n");
                        int caloriasComida = sc.nextInt();
                        pub.addFood(new Food(nombreComida, caloriasComida));
                        break;
                    }
                    case 2: {
                        System.out.println("La comida se guardar en un archive dentro de la carpeta data con el nombre que le proporciones: \n");
                        try {
                            pub.storeFoodInFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Seleccione el nombre del archivo desde el que quieres coger la comida: \n");
                        pub.loadFoodFromFile();
                        break;
                    }
                    case 4:{
                        System.out.println("Ingrese el nombre de la comida a eliminar: \n");
                        String nombreComida = sc.next();
                        Food comida = new Food(nombreComida, 0);
                        pub.removeFood(comida);
                        break;
                    }
                    case 5:{
                        System.out.println("Ingrese un numero de calorias, las que tengan mas calorias que ese numero seran eliminadas: \n");
                        int calorias = sc.nextInt();
                        pub.removeFoodsByCalories(calorias);
                        break;
                    }
                    case 6:{
                        System.out.println("Mostrar la comida mas calorica: \n");
                        pub.mostCaloricFood().toString();
                        break;
                    }
                    case 7:{
                        System.out.println("Mostrar todas las comidas: \n");
                        pub.showFoods();
                        break;
                    }
           } 
           System.out.println("1.Seguir en bebidas     2.Cambiar de clase      3.Salir\n");
           int seguirMenuOSalir = sc.nextInt();

           if (seguirMenuOSalir == 1) {
               ESTADO = "MENU";
           }
           else if(seguirMenuOSalir == 2){
               ESTADO = "SELECCIONARACCION";
           }
           else if(seguirMenuOSalir == 3){
               ESTADO = "FIN";
           }
           else{
               System.out.println("Ingrese una opcion valida");
           }
        }
        else{
            ESTADO = "FIN";
        } 
        }
        else if(ESTADO == "FIN"){
            System.out.println("Gracias por utilizar nuestra aplicacion");
            sc.close();
            System.exit(0);
        }

    }
}
}