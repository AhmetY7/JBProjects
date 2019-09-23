package converter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static void convertToRadix(int radix, int number) {
        ArrayList<Integer> result = new ArrayList<>();
        while (number >= radix) {
            result.add(number % radix);
            number /= radix;
        }
        result.add(number);
        String convertedRadix = "";
        for(int i=result.size()-1; i>=0; i--) {
            if(result.get(i) > 9) {
                convertedRadix += Character.toString(97 + result.get(i) - 10);
            } else {
                convertedRadix += Integer.toString(result.get(i));
            }
        }
        System.out.println(convertedRadix);
    }
    private static int convertTo10(int sourceRadix, String number) {
        int total = 0;
        for(int i=number.length()-1, j=0; i>=0; i--,j++) {
            total += Math.pow(sourceRadix,j) * Integer.parseInt(Character.toString(number.charAt(i)));
        }
        return total;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sourceRadix = Integer.parseInt(scanner.nextLine());
        String number = scanner.nextLine();
        int radix = Integer.parseInt(scanner.nextLine());
        if(sourceRadix != 10) {
            convertToRadix(radix,convertTo10(sourceRadix, number));
        } else if (radix == 1) {
            for(int i=0; i<Integer.parseInt(number); i++) {
                System.out.print("1");
            }
        } else {
            convertToRadix(radix,Integer.parseInt(number));
        }
    }
}
