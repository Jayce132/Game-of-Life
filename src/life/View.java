package life;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class View {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        n = n + 2;
        //long seed = 1; //scanner.nextLong();
        int generations = 10; //scanner.nextInt();
        Random random = new Random();
        char[][] table = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = '#';
            }
        }
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (random.nextBoolean()) {
                    table[i][j] = 'O';
                    Counter.alive++;
                } else {
                    table[i][j] = ' ';
                }
            }
        }
        printTable(table, n, generations);
    }

    public static void printTable(char[][] table, int n, int generations) {
        System.out.println("Generation #" + Counter.generation);
        System.out.println("Alive: " + Counter.alive + "\n");
        printTable(table, n);
        while (generations > 0) {
            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            }
            catch (IOException | InterruptedException e) {}
            Generator.nextState(table, n);
            Counter.generation++;
            System.out.println("Generation #" + Counter.generation);
            System.out.println("Alive: " + Counter.alive + "\n");
            printTable(table, n);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generations--;
        }

    }

    public static void printTable(char[][] table, int n) {
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        Counter.alive = 0;
    }
}

//        table = new char[][]{
//                {'$', '$', '$', '$', '$'},
//                {'$', 'O', 'O', 'O', '$'},
//                {'$', 'O', 'O', 'O', '$'},
//                {'$', 'O', 'O', 'O', '$'},
//                {'$', '$', '$', '$', '$'}
//        };

