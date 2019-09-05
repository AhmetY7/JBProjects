package flashcards;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int numberOfCard = Integer.parseInt(scanner.nextLine());
        String[] cards = new String[numberOfCard];
        String[] definitions = new String[numberOfCard];

        for(int i=0; i<numberOfCard; i++) {
            System.out.println("The card #" + (i+1) + ":");
            cards[i] = scanner.nextLine();
            System.out.println("The definition of the card #" + (i+1) + ":");
            definitions[i] = scanner.nextLine();
        }

        for(int i=0; i<numberOfCard; i++) {
            System.out.println("Print the definition of \"" + cards[i] + "\":");
            String answer = scanner.nextLine();
            if(answer.equals(definitions[i])) {
                System.out.println("Correct answer.");
            } else {
                System.out.println("Wrong answer. (The correct one is \""+ definitions[i] +"\".)");
            }
        }
    }
}
