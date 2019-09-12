package tictactoe;

import java.util.Scanner;

public class Main {
    private static int numberOfElement(String[][] gameField, String element) {
        int counter = 0;
        for(int i=0; i<gameField.length; i++) {
            for(int j=0; j<gameField[i].length; j++) {
                if(gameField[i][j].equals(element)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static boolean isRow(String[][] gameField, String element) {
        boolean row = false;
        for(int i=0; i<gameField.length; i++) {
            if(gameField[i][0].equals(element) && gameField[i][1].equals(element) && gameField[i][2].equals(element)) {
                row = true;
                break;
            } else if(gameField[0][i].equals(element) && gameField[1][i].equals(element) && gameField[2][i].equals(element)) {
                row = true;
                break;
            } else if (gameField[0][0].equals(element) && gameField[1][1].equals(element) && gameField[2][2].equals(element)) {
                row = true;
                break;
            } else if (gameField[0][2].equals(element) && gameField[1][1].equals(element) && gameField[2][0].equals(element)) {
                row = true;
                break;
            }

        }
        return row;
    }

    private static void gameSituation(String[][] gameField) {
        int numberOfO = numberOfElement(gameField, "O");
        int numberOfX = numberOfElement(gameField, "X");
        boolean xRow = isRow(gameField, "X");
        boolean oRow = isRow(gameField, "O");
        if((xRow && oRow) || Math.abs(numberOfO-numberOfX) > 1) {
            System.out.println("Impossible");
        } else if (xRow) {
            System.out.println("X wins");
        } else if (oRow) {
            System.out.println("O wins");
        } else if (numberOfO + numberOfX == 9) {
            System.out.println("Draw");
        } else {
            System.out.println("Game not finished");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String cells = scanner.nextLine();
        String[][] gameField = new String[3][3];
        System.out.println();
        System.out.print("---------\n| ");
        for (int i = 0, j = 0, k = 0; i < cells.length(); i++) {
            if (cells.charAt(i) == '\"') {
                continue;
            } else {
                if (i > 3 && i % 3 == 1) {
                    System.out.print("|\n| ");
                    j++;
                }
                System.out.print(cells.charAt(i) + " ");
                gameField[j][k%3] = Character.toString(cells.charAt(i));
                k++;
            }
        }
        System.out.println("|");
        System.out.println("---------");
        gameSituation(gameField);
    }
}

