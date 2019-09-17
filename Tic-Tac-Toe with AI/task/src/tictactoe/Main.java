package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int numberOfElement(String[][] gameField, String element) {
        int counter = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j].equals(element)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static boolean isRow(String[][] gameField, String element) {
        boolean row = false;
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][0].equals(element) && gameField[i][1].equals(element) && gameField[i][2].equals(element)) {
                row = true;
                break;
            } else if (gameField[0][i].equals(element) && gameField[1][i].equals(element) && gameField[2][i].equals(element)) {
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

    private static String gameSituation(String[][] gameField) {
        int numberOfO = numberOfElement(gameField, "O");
        int numberOfX = numberOfElement(gameField, "X");
        boolean xRow = isRow(gameField, "X");
        boolean oRow = isRow(gameField, "O");
        if ((xRow && oRow) || Math.abs(numberOfO - numberOfX) > 1) {
            return "Impossible";
        } else if (xRow) {
            return "X wins";
        } else if (oRow) {
            return "O wins";
        } else if (numberOfO + numberOfX == 9) {
            return "Draw";
        } else {
            return " ";
        }
    }

    private static void nextMove(String[][] gameField, String item) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        int rightToLeft;
        int leftToRight;
        try {
            rightToLeft = Integer.parseInt(scanner.next());
            leftToRight = Integer.parseInt(scanner.next());
            if (rightToLeft > 3 || rightToLeft < 1 || leftToRight > 3 || leftToRight < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                nextMove(gameField, item);
                return;
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            nextMove(gameField, item);
            return;
        }
        System.out.print(rightToLeft + " " + leftToRight);
        if (leftToRight == 3) {
            leftToRight = 1;
        } else if (leftToRight == 1) {
            leftToRight = 3;
        }
        if (" ".equals(gameField[leftToRight - 1][rightToLeft - 1])) {
            gameField[leftToRight - 1][rightToLeft - 1] = item;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            nextMove(gameField, item);
        }

    }

    private static void easyMove(String[][] gameField, String item) {
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        int rightToLeft;
        int leftToRight;
        rightToLeft = random.nextInt(3) + 1;
        leftToRight = random.nextInt(3) + 1;
        while (!" ".equals(gameField[leftToRight - 1][rightToLeft - 1])) {
            rightToLeft = random.nextInt(3) + 1;
            leftToRight = random.nextInt(3) + 1;
        }
        gameField[leftToRight - 1][rightToLeft - 1] = item;
    }

    private static void drawGameField(String[][] gameField) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input Command: ");
        String[] commands = scanner.nextLine().split("\\s+");
        while (!"exit".equals(commands[0])) {
            if (commands.length != 3) {
                System.out.println("Bad parameters!");
                System.out.print("Input Command: ");
                commands = scanner.nextLine().split("\\s+");
            } else {
                String player1 = commands[1];
                String player2 = commands[2];
                String[][] gameField = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
                String result = " ";
                while (" ".equals(result)) {
                    drawGameField(gameField);
                    if ("user".equals(player1)) {
                        nextMove(gameField, "X");
                    } else {
                        easyMove(gameField, "X");
                    }
                    drawGameField(gameField);
                    result = gameSituation(gameField);
                    if (" ".equals(result)) {
                        if ("user".equals(player2)) {
                            nextMove(gameField, "O");
                        } else {
                            easyMove(gameField, "O");
                        }
                        result = gameSituation(gameField);
                    }
                }
                System.out.println(result);
                System.out.print("Input Command: ");
                commands = scanner.nextLine().split("\\s+");
            }
        }

    }
}

