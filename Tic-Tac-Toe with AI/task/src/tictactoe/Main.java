package tictactoe;

import java.util.Random;
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

    private static void nextMove(String[][] gameField) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        int rightToLeft;
        int leftToRight;
        try{
            rightToLeft = Integer.parseInt(scanner.next());
            leftToRight = Integer.parseInt(scanner.next());
            if(rightToLeft > 3 || rightToLeft < 1 || leftToRight > 3 || leftToRight < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                nextMove(gameField);
                return;
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            nextMove(gameField);
            return;
        }
        System.out.print(rightToLeft +" "+ leftToRight);
        if(leftToRight == 3) {
            leftToRight = 1;
        } else if(leftToRight == 1) {
            leftToRight = 3;
        }
        if(" ".equals(gameField[leftToRight-1][rightToLeft-1])) {
            gameField[leftToRight-1][rightToLeft-1] = "X";
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            nextMove(gameField);
        }

    }

    private static void easyMove(String[][] gameField) {
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        int rightToLeft;
        int leftToRight;
        rightToLeft = random.nextInt(3) + 1;
        leftToRight = random.nextInt(3) + 1;
        if(" ".equals(gameField[leftToRight-1][rightToLeft-1])) {
            gameField[leftToRight-1][rightToLeft-1] = "X";
        } else {
            easyMove(gameField);
        }


    }

    private static void drawGameField(String[][] gameField) {
        System.out.println ("---------");
        for (int i = 0; i< 3; i++) {
            System.out.print("| ");
            for (int j = 0; j< 3; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String cells = scanner.nextLine();
        String[][] gameField = new String[3][3];
        for(int i=0,k=1; i<3; i++) {
            for(int j=0; j<3; j++) {
                gameField[i][j] = Character.toString(cells.charAt(k));
                k++;
            }
        }
        drawGameField(gameField);
        //nextMove(gameField);
        easyMove(gameField);
        drawGameField(gameField);
        //gameSituation(gameField);
    }
}

