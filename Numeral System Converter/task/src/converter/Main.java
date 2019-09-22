package converter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static void convertToRadix(String radix, int number) {
        ArrayList<Integer> result = new ArrayList<>();
        if("2".equals(radix)) {
            while (number >= 2) {
                result.add(number%2);
                number /= 2;
            }
            result.add(number);
            System.out.print("0b");
        } else if("8".equals(radix)) {
            while (number >= 8) {
                result.add(number%8);
                number /= 8;
            }
            result.add(number);
            System.out.print("0");
        } else {
            while (number >= 16) {
                result.add(number%16);
                number /= 16;
            }
            result.add(number);
            System.out.print("0x");
        }
        for(int i=result.size()-1; i>=0; i--) {
            if("2".equals(radix)) {
                System.out.print(result.get(i));
            } else if("8".equals(radix)) {
                System.out.print(result.get(i));
            } else {
                switch (result.get(i)) {
                    case 10:
                        System.out.print("a");
                        break;
                    case 11:
                        System.out.print("b");
                        break;
                    case 12:
                        System.out.print("c");
                        break;
                    case 13:
                        System.out.print("d");
                        break;
                    case 14:
                        System.out.print("e");
                        break;
                    case 15:
                        System.out.print("f");
                        break;
                    default:
                        System.out.print(result.get(i));

                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        String radix = scanner.nextLine();
        convertToRadix(radix, number);
    }
}
