package machine;
import java.util.Scanner;

public class CoffeeMachine {
    private static int water = 1200;
    private static int milk = 540;
    private static int coffeeBeans = 120;
    private static int cups = 9;
    private static int money = 550;

    private static void status(){
        System.out.println("The coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                coffeeBeans + " of coffee beans\n" +
                cups + " of disposable cups\n" +
                money +" of money\n");
    }
    private static void buy() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        int chosen = scanner.nextInt();
        System.out.println(chosen);
        if(chosen == 1) {
            water -= 250;
            coffeeBeans -= 16;
            cups -= 1;
            money += 4;
        } else if(chosen == 2) {
            water -= 350;
            milk -= 75;
            coffeeBeans -=20;
            cups -= 1;
            money +=7;
        } else if (chosen == 3) {
            water -= 200;
            milk -= 100;
            coffeeBeans -= 12;
            cups -= 1;
            money += 6;
        }
        status();
    }
    private static void fill() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write how many ml of water do you want to add: ");
        int addWater = scanner.nextInt();
        System.out.println(addWater);
        water += addWater;
        System.out.print("Write how many ml of milk do you want to add: ");
        int addMilk = scanner.nextInt();
        System.out.println(addMilk);
        milk += addMilk;
        System.out.print("Write how many grams of coffee beans do you want to add: ");
        int addCoffeeBeans = scanner.nextInt();
        System.out.println(addCoffeeBeans);
        coffeeBeans += addCoffeeBeans;
        System.out.print("Write how many disposable cups of coffee do you want to add: ");
        int addCups = scanner.nextInt();
        System.out.println(addCups);
        cups += addCups;

        status();
    }

    private static void take() {
        System.out.print("I gave you $"+ money);
        money = 0;
        status();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("An espresso should be as number 1 in the list, a latte as number 2 and a cappuccino as number 3.\n" +
                "Options also should be named as \"buy\", \"fill\", \"take\".\n" +
                "\n" +
                "The coffee machine has:\n" +
                "1200 of water\n" +
                "540 of milk\n" +
                "120 of coffee beans\n" +
                "9 of disposable cups\n" +
                "550 of money\n" +
                "\n" +
                "Write action (buy, fill, take): " );
        String action = scanner.nextLine();
        System.out.print(action);
        if ("take".equals(action)){
            take();
        } else if("buy".equals(action)){
            buy();
        } else if ("fill".equals(action)){
            fill();
        }
    }
}