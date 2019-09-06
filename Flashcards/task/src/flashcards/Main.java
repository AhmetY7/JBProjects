package flashcards;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class Main {
    private static String getKey(Map<String, String> flashCards, String value) {
        for(Map.Entry<String, String> temp : flashCards.entrySet()) {
            if(temp.getValue().equals(value)){
                return temp.getKey();
            }
        }
        return "";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int numberOfCard = Integer.parseInt(scanner.nextLine());
        Map<String, String> flashCards = new LinkedHashMap<>();

        for(int i=0; i<numberOfCard; i++) {
            System.out.println("The card #" + (i+1) + ":");
            String card = scanner.nextLine();
            while(flashCards.containsKey(card)) {
                System.out.println("The card \"" + card +"\" already exists. Try again:");
                card = scanner.nextLine();
            }
            System.out.println("The definition of the card #" + (i+1) + ":");
            String definition = scanner.nextLine();
            while(flashCards.containsValue(definition)) {
                System.out.println("The definition \"" + definition +"\" already exists. Try again:");
                definition = scanner.nextLine();
            }
            flashCards.put(card, definition);
        }

        for(Map.Entry<String, String> temp : flashCards.entrySet()) {
            System.out.println("Print the definition of \"" + temp.getKey() + "\":");
            String answer = scanner.nextLine();
            if(answer.equals(temp.getValue())) {
                System.out.println("Correct answer.");
            } else {
                if(flashCards.containsValue(answer)) {
                    System.out.println("Wrong answer. (The correct one is \""+ temp.getValue() +"\", you've just written the definition of \"" + getKey(flashCards, answer) + "\".)");
                } else {
                    System.out.println("Wrong answer. (The correct one is \""+ temp.getValue() +"\".)");
                }
            }
        }
    }
}
