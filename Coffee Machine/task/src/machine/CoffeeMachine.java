package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write how many ml of water the coffee machine has: ");
        int water = scanner.nextInt();
        System.out.println(water);
        System.out.print("Write how many ml of milk the coffee machine has: ");
        int milk = scanner.nextInt();
        System.out.println(milk);
        System.out.print("Write how many grams of coffee beans the coffee machine has: ");
        int coffeeBeans = scanner.nextInt();
        System.out.println(coffeeBeans);
        System.out.print("Write how many cups of coffee you will need: ");
        int cupsOfCoffee = scanner.nextInt();
        System.out.println(cupsOfCoffee);

        int amountOfCooffe = water/200;
        if(amountOfCooffe > milk/50) {
            amountOfCooffe = milk/50;
        }
        if(amountOfCooffe > coffeeBeans/15){
            amountOfCooffe = coffeeBeans/15;
        }
        if(amountOfCooffe == cupsOfCoffee){
            System.out.println("Yes, I can make that amount of coffee");
        } else if(amountOfCooffe < cupsOfCoffee) {
            System.out.println("No, I can make only "+ amountOfCooffe +"  cup(s) of coffee");
        } else {
            System.out.println("Yes, I can make that amount of coffee (and even "+ (amountOfCooffe-cupsOfCoffee) +" more than that)");
        }
    }
}
