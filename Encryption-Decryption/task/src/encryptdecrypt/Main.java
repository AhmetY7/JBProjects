package encryptdecrypt;

import java.util.Scanner;

public class Main {
    private static void decryption(String message, int key) {
        for(int i=0; i<message.length(); i++) {
            char temp = message.charAt(i);
            temp -=key;
            System.out.print(temp);
        }
    }

    private static void encryption(String message, int key) {
        for(int i=0; i<message.length(); i++) {
            char temp = message.charAt(i);
            temp +=key;
            System.out.print(temp);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        String message = scanner.nextLine();
        int key = scanner.nextInt();
        if("dec".equals(operation)) {
            decryption(message,key);
        } else {
            encryption(message,key);
        }
    }
}
