package flashcards;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class Main {

    private static String getKey(Map<String, String> flashCards, int number) {
        int i = 0;
        for(Map.Entry<String, String> temp : flashCards.entrySet()) {
            if(i == number-1) {
                return temp.getKey();
            }
            i++;
        }
        return "";
    }
    private static String getKey(Map<String, String> flashCards, String value) {
        for(Map.Entry<String, String> temp : flashCards.entrySet()) {
            if(temp.getValue().equals(value)) {
                return temp.getKey();
            }
        }
        return "";
    }
    private static void add(Map<String, String> flashCards) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The card:");
        String card = scanner.nextLine();
        if(flashCards.containsKey(card)) {
            System.out.println("The card \"" + card +"\" already exists.");
            return;
        }
        System.out.println("The definition of the card:");
        String definition = scanner.nextLine();
        if(flashCards.containsValue(definition)) {
            System.out.println("The definition \"" + definition +"\" already exists.");
            return;
        }
        System.out.println("The pair (\""+ card + "\":\""+ definition +"\") has been added.");
        flashCards.put(card, definition);
    }
    private static void remove(Map<String, String> flashCards) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The card:");
        String card = scanner.nextLine();

        if(flashCards.containsKey(card)) {
            flashCards.remove(card);
            System.out.println("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + card + "\": there is no such card.");
        }
    }
    private static void importCards(Map<String, String> flashCards)  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        try(Scanner fileScanner = new Scanner(file)) {
            int counter = 0;
            while (fileScanner.hasNext()) {
                String card = fileScanner.nextLine();
                String definition = fileScanner.nextLine();
                if(flashCards.containsKey(card)) {
                    flashCards.replace(card, definition);
                } else {
                    flashCards.put(card, definition);
                }
                counter++;
            }
            System.out.println(counter + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

    }
    private static void exportCards(Map<String, String> flashCards)  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            for(Map.Entry<String, String> temp : flashCards.entrySet()) {
                writer.write(temp.getKey() +"\n" + temp.getValue() + "\n");
            }
            System.out.println(flashCards.size() + " cards have been saved.");
        } catch (IOException e) {
            System.out.println("Someting went wrong!");
        }
    }
    private static void ask(Map<String, String> flashCards) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many times to ask?");
        int times = Integer.parseInt(scanner.nextLine());

        Random random = new Random();
        for(int i=0; i<times; i++) {
            int number = random.nextInt(flashCards.size()) + 1;
            String card = getKey(flashCards, number);
            System.out.println("Print the definition of \"" + card + "\":");
            String answer = scanner.nextLine();
            if(answer.equals(flashCards.get(card))) {
                System.out.println("Correct answer.");
            } else {
                if(flashCards.containsValue(answer)) {
                    System.out.println("Wrong answer. (The correct one is \""+ flashCards.get(card) +"\", you've just written the definition of \"" + getKey(flashCards, answer) + "\".)");
                } else {
                    System.out.println("Wrong answer. (The correct one is \""+ flashCards.get(card) +"\".)");
                }
            }
        }

        /*for(Map.Entry<String, String> temp : flashCards.entrySet()) {

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
        }*/
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the action (add, remove, import, export, ask, exit):");
        String action = scanner.nextLine();
        Map<String, String> flashCards = new LinkedHashMap<>();

        while (!"exit".equals(action)) {
            if("add".equals(action)) {
                add(flashCards);
            } else if("remove".equals(action)) {
                remove(flashCards);
            } else if("import".equals(action)) {
                importCards(flashCards);
            } else if("export".equals(action)) {
                exportCards(flashCards);
            } else if("ask".equals(action)) {
                ask(flashCards);
            }
            System.out.println();
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            action = scanner.nextLine();
        }
        System.out.println("Bye bye!");
    }
}
