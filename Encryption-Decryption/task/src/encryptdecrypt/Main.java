package encryptdecrypt;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        int key = scanner.nextInt();
        for(int i=0; i<message.length();i++) {
            char temp = message.charAt(i);
            if(temp>=97 && temp<=122){
                temp += key;
                if(temp > 122){
                    temp = (char)(96 + (temp - 122));
                }
                System.out.print(temp);
            } else if(message.charAt(i) == ' '){
                System.out.print(" ");
            }
        }
    }
}
