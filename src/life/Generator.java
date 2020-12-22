package life;

import java.util.HashMap;

public class Generator {

    private Generator() {

    }

    private static final HashMap<String, Character> deathNote = new HashMap<>();

    public static void nextState(char[][] a, int n) {
        int neighbours = 0;
        for (int i = 1; i < n - 1; i++) {
            a[i][n - 1] = a[i][1]; //Right
            a[i][0] = a[i][n - 2]; //Left
            a[0][i] = a[n - 2][i]; //Up
            a[n - 1][i] = a[1][i]; //Down
        }

        //Corners
        a[0][0] = a[n - 2][n - 2];
        a[n - 1][0] = a[1][n - 2];
        a[n - 1][n - 1] = a[1][1];
        a[0][n - 1] = a[n - 2][1];

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {

                if (a[i - 1][j - 1] == 'O') {
                    neighbours++;
                }
                if (a[i - 1][j] == 'O') {
                    neighbours++;
                }
                if (a[i - 1][j + 1] == 'O') {
                    neighbours++;
                }
                if (a[i][j - 1] == 'O') {
                    neighbours++;
                }
                if (a[i][j + 1] == 'O') {
                    neighbours++;
                }
                if (a[i + 1][j - 1] == 'O') {
                    neighbours++;
                }
                if (a[i + 1][j] == 'O') {
                    neighbours++;
                }
                if (a[i + 1][j + 1] == 'O') {
                    neighbours++;
                }

                //I put the life status changes that need to be done in a HashMap<Position, 'O' or ' '>
                if (a[i][j] == 'O') {
                    String key = i + " " + j;
                    if (neighbours == 2 || neighbours == 3) {
                        deathNote.put(key, 'O');
                    } else {
                        deathNote.put(key, ' ');
                    }
                } else if (a[i][j] == ' ') {
                    String key = i + " " + j;
                    if (neighbours == 3) {
                        deathNote.put(key, 'O');
                    } else {
                        deathNote.put(key, ' ');
                    }
                }
                neighbours = 0;
            }
        }

        //I make the changes
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                a[i][j] = deathNote.get(i + " " + j);
                if (a[i][j] == 'O') {
                    Counter.alive++;
                }
            }
        }
        deathNote.clear();
    }
}