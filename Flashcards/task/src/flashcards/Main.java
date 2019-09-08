package flashcards;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
    private static void add(Map<String, String> flashCards, ArrayList<String> logs) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The card:");
        logs.add("The card:");
        String card = scanner.nextLine();
        logs.add(card);
        if(flashCards.containsKey(card)) {
            System.out.println("The card \"" + card +"\" already exists.");
            logs.add("The card \"" + card +"\" already exists.");
            return;
        }
        System.out.println("The definition of the card:");
        logs.add("The definition of the card:");
        String definition = scanner.nextLine();
        logs.add(definition);
        if(flashCards.containsValue(definition)) {
            System.out.println("The definition \"" + definition +"\" already exists.");
            logs.add("The definition \"" + definition +"\" already exists.");
            return;
        }
        System.out.println("The pair (\""+ card + "\":\""+ definition +"\") has been added.");
        logs.add("The pair (\""+ card + "\":\""+ definition +"\") has been added.");
        flashCards.put(card, definition);
    }
    private static void remove(Map<String, String> flashCards, ArrayList<String> logs) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The card:");
        logs.add("The card:");
        String card = scanner.nextLine();
        logs.add(card);
        if(flashCards.containsKey(card)) {
            flashCards.remove(card);
            System.out.println("The card has been removed.");
            logs.add("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + card + "\": there is no such card.");
            logs.add("Can't remove \"" + card + "\": there is no such card.");
        }
    }
    private static void importCards(Map<String, String> flashCards, ArrayList<String> logs)  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File name:");
        logs.add("File name:");
        String fileName = scanner.nextLine();
        logs.add(fileName);
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
            logs.add(counter + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            logs.add("File not found.");
        }

    }
    private static void exportCards(Map<String, String> flashCards, ArrayList<String> logs)  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File name:");
        logs.add("File name:");
        String fileName = scanner.nextLine();
        logs.add(fileName);
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            for(Map.Entry<String, String> temp : flashCards.entrySet()) {
                writer.write(temp.getKey() +"\n" + temp.getValue() + "\n");
            }
            System.out.println(flashCards.size() + " cards have been saved.");
            logs.add(flashCards.size() + " cards have been saved.");
        } catch (IOException e) {
            System.out.println("Someting went wrong!");
            logs.add("Someting went wrong!");
        }
    }
    private static void ask(Map<String, String> flashCards, ArrayList<String> logs, Map<String, Integer> hardestCards) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many times to ask?");
        logs.add("How many times to ask?");
        int times = Integer.parseInt(scanner.nextLine());
        logs.add(Integer.toString(times));
        Random random = new Random();
        for(int i=0; i<times; i++) {
            int number = random.nextInt(flashCards.size()) + 1;
            String card = getKey(flashCards, number);
            System.out.println("Print the definition of \"" + card + "\":");
            logs.add("Print the definition of \"" + card + "\":");
            String answer = scanner.nextLine();
            logs.add(answer);
            if(answer.equals(flashCards.get(card))) {
                System.out.println("Correct answer.");
                logs.add("Correct answer.");
            } else {
                if(flashCards.containsValue(answer)) {
                    System.out.println("Wrong answer. (The correct one is \""+ flashCards.get(card) +"\", you've just written the definition of \"" + getKey(flashCards, answer) + "\".)");
                    if(hardestCards.containsKey(card)) {
                        hardestCards.replace(card, hardestCards.get(card)+1);
                    } else {
                        hardestCards.put(card, 1);
                    }
                    logs.add("Wrong answer. (The correct one is \""+ flashCards.get(card) +"\", you've just written the definition of \"" + getKey(flashCards, answer) + "\".)");
                } else {
                    System.out.println("Wrong answer. (The correct one is \""+ flashCards.get(card) +"\".)");
                    if(hardestCards.containsKey(card)) {
                        hardestCards.replace(card, hardestCards.get(card)+1);
                    } else {
                        hardestCards.put(card, 1);
                    }
                    logs.add("Wrong answer. (The correct one is \""+ flashCards.get(card) +"\".)");
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
    private static void log(ArrayList<String> logs) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File name:");
        logs.add("File name:");
        String fileName = scanner.nextLine();
        logs.add(fileName);
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            for(String log : logs) {
                writer.write(log + "\n");
            }
            System.out.println("The log has been saved.");
            logs.add("The log has been saved.");
        } catch (IOException e) {
            System.out.println("Someting went wrong!");
            logs.add("Someting went wrong!");
        }
    }
    private static void getHardestCards(Map<String, Integer> hardestCards, ArrayList<String> logs) {
        if(hardestCards.size() == 0) {
            System.out.println("There are no cards with errors.");
            logs.add("There are no cards with errors.");
            return;
        }
        ArrayList<String> hardestCardName = new ArrayList<>();
        int mostError = 0;
        for(Map.Entry<String, Integer> temp : hardestCards.entrySet()) {
            if(temp.getValue() > mostError) {
                hardestCardName.clear();
                hardestCardName.add(temp.getKey());
                mostError = temp.getValue();
            } else if (temp.getValue() == mostError) {
                hardestCardName.add(temp.getKey());
            }
        }
        String print = "";
        if(hardestCardName.size() == 1) {
                print = "The hardest card is ";
            } else {
                print = "The hardest cards are ";
        }
        for (int i=0; i<hardestCardName.size(); i++) {
            print += "\"" + hardestCardName.get(i) + "\"";
            if(i == hardestCardName.size()-1) {
                print += ". You have " + hardestCards.get(hardestCardName.get(i)) + " errors answering it.";
            } else {
                print += ",";
            }
        }
        System.out.println(print);
        logs.add(print);

    }
    private static void resetStats(Map<String, Integer> hardestCards, ArrayList<String> logs) {
        hardestCards.clear();
        System.out.println("Card statistics has been reset.");
        logs.add("Card statistics has been reset.");
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Map<String, String> flashCards = new LinkedHashMap<>();
        Map<String, Integer> hardestCards = new LinkedHashMap<>();
        ArrayList<String> logs = new ArrayList<>();
        System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        logs.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        String action = scanner.nextLine();
        logs.add(action);

        while (!"exit".equals(action)) {
            if("add".equals(action)) {
                add(flashCards, logs);
            } else if("remove".equals(action)) {
                remove(flashCards, logs);
            } else if("import".equals(action)) {
                importCards(flashCards, logs);
            } else if("export".equals(action)) {
                exportCards(flashCards, logs);
            } else if("ask".equals(action)) {
                ask(flashCards, logs, hardestCards);
            } else if("log".equals(action)) {
                log(logs);
            } else if("hardest card".equals(action)) {
                getHardestCards(hardestCards, logs);
            } else if("reset stats".equals(action)) {
                resetStats(hardestCards, logs);
            }
            System.out.println();
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            logs.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            action = scanner.nextLine();
            logs.add(action);
        }
        System.out.println("Bye bye!");
        logs.add("Bye bye!");
    }
}
