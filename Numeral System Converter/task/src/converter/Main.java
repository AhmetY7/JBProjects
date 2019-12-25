package converter;

import java.util.Scanner;

public class Main {
    static String letter(int sourceRadix, String number, int destinationRadix) {
        return Integer.toString(Integer.parseInt(number, sourceRadix), destinationRadix);
    }

    static String fractionalCalculate(String fractional, int destinationRadix) {
        StringBuilder fractionalBuilder = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            fractionalBuilder.append(Double.parseDouble("0." + fractional) * destinationRadix);
            result.append(letter(10, fractionalBuilder.substring(0,fractionalBuilder.indexOf(".")), destinationRadix));
            fractional = fractionalBuilder.substring(fractionalBuilder.indexOf(".") + 1,fractionalBuilder.length());
            fractionalBuilder.delete(0,fractionalBuilder.length());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sourceRadix = Integer.parseInt(scanner.nextLine());
        String[] numbers = scanner.nextLine().split("\\.");
        int destinationRadix = Integer.parseInt(scanner.nextLine());
        String intPart = "";
        if(sourceRadix == 1) {
            numbers[0] = Integer.toString(numbers[0].length());
            sourceRadix = 10;
        }
        if(destinationRadix == 1) {
            StringBuilder for1destination = new StringBuilder();
            for(int i=0; i<Integer.parseInt(numbers[0]);i++){
                for1destination.append("1");
            }
            intPart = for1destination.toString();
            System.out.println(intPart);
        } else {
            intPart = letter(sourceRadix, numbers[0], destinationRadix);
            // if source radix is not 10
            if(sourceRadix != 10) {
                numbers[0] = intPart;
                if(numbers.length == 2) {
                    double tenFraction = 0.0;
                    for(int i=0; i<numbers[1].length(); i++) {
                        tenFraction += (Integer.parseInt(letter(sourceRadix,Character.toString(numbers[1].charAt(i)),10))) / (Math.pow(sourceRadix,i+1));
                    }
                    if(tenFraction == 0.0) {
                        numbers[1] = "00000";
                    } else {
                        numbers[1] = Double.toString(tenFraction).substring(2);
                    }
                }
            }
            if(numbers.length == 2){
                String fractionPart = fractionalCalculate(numbers[1], destinationRadix);
                System.out.println(intPart + "." + fractionPart);
            } else {
                System.out.println(intPart);
            }
        }
    }
}