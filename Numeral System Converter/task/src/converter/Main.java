package converter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static String convertToRadix(int radix, String number) {
        //Burası source radix i 10 dan büyük olanlar için yazılacak
        return "";
    }
    private static String convertToRadix(int radix, int number) {
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
        return convertedRadix;
    }
    private static int convertTo10(int sourceRadix, String number) {
        int total = 0;
        for(int i=number.length()-1, j=0; i>=0; i--,j++) {
            total += Math.pow(sourceRadix,j) * Integer.parseInt(Character.toString(number.charAt(i)));
        }
        return total;
    }
    private static String convertFractionToRadix(int radix, double number) {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<5; i++) {
            if(i==Double.toString(number).length())
                break;
            StringBuilder temp = new StringBuilder(Double.toString(number * radix));
            result.append(temp.charAt(0));
            temp.replace(0,1,"0");
            number = Double.parseDouble(temp.toString());
        }
        return result.toString();
    }
    private static double convertFractionTo10(int sourceRadix, String number) {
        double result = 0.0;
        for(int i=0; i<number.length(); i++) {
            result += Integer.parseInt(number.charAt(i)+ "") * (1/Math.pow(sourceRadix,i+1));
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sourceRadix = Integer.parseInt(scanner.nextLine());
        String number = scanner.nextLine();
        String[] fractions = number.split("\\.");
        int radix = Integer.parseInt(scanner.nextLine());
        if (radix == 1) {
            for(int i=0; i<Integer.parseInt(fractions[0]); i++) {
                System.out.print("1");
            }
        } else if(sourceRadix != 10) {
            int intTo10 = convertTo10(sourceRadix, fractions[0]);
            double fractionTo10 = convertFractionTo10(sourceRadix, fractions[1]);
            String intPart = convertToRadix(radix, intTo10);
            String fractionPart = convertFractionToRadix(radix, fractionTo10);
            System.out.print(intPart + "." + fractionPart);
        } else {
            String intPart = convertToRadix(radix,Integer.parseInt(fractions[0]));
            String fractionPart = convertFractionToRadix(radix, Double.parseDouble("0." + fractions[1]));
            System.out.print(intPart + "." + fractionPart);
        }
    }
}



