//package life;
//
//        import javax.swing.*;
//        import java.awt.*;
//        import java.util.Random;
//
//public class GameOfLife extends JFrame {
//    static JLabel aliveCounter = new JLabel("Alive");
//    static JLabel generationsCounter = new JLabel("Generations");
//    static int tableSize = 20;
//    static JPanel[] panels = new JPanel[tableSize * tableSize];
//
//    public GameOfLife() {
//        aliveCounter.setName("AliveLabel");
//        generationsCounter.setName("GenerationLabel");
//        JPanel counterPanel = new JPanel(new GridLayout(2, 1));
//        JPanel tablePanel = new JPanel(new GridLayout(tableSize, tableSize));
//
//        this.add(counterPanel, BorderLayout.NORTH);
//        counterPanel.add(aliveCounter);
//        counterPanel.add(generationsCounter);
//        this.add(tablePanel);
//
//
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(300, 300);
//        this.setVisible(true);
//
//        int n = tableSize + 2;
//        int generations = 10; //scanner.nextInt();
//        Random random = new Random();
//        char[][] table = new char[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                table[i][j] = '#';
//            }
//        }
//
//        int k = 0;
//        for (int i = 1; i < n - 1; i++) {
//            for (int j = 1; j < n - 1; j++) {
//                panels[k] = new JPanel();
//                if (random.nextBoolean()) {
//                    table[i][j] = 'O';
//                    panels[k].setBackground(Color.BLACK);
//                    Counter.alive++;
//                } else {
//                    table[i][j] = ' ';
//                    panels[k].setBackground(Color.WHITE);
//                }
//                panels[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
//                tablePanel.add(panels[k]);
//                k++;
//            }
//        }
//
//        printTable(table, n, generations);
//    }
//
//    public static void printTable(char[][] table, int n, int generations) {
//        aliveCounter.setText("Alive: " + Counter.alive);
//
//        printTable(table, n);
//        while (generations > 0) {
//
//            Generator.nextState(table, n);
//            Counter.generation++;
//            generationsCounter.setText("Generation #" + Counter.generation);
//            aliveCounter.setText("Alive: " + Counter.alive);
//
//            printTable(table, n);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            generations--;
//        }
//
//    }
//
//    public static void printTable(char[][] table, int n) {
//        int k = 0;
//        for (int i = 1; i < n - 1; i++) {
//            for (int j = 1; j < n - 1; j++) {
//                if (table[i][j] == 'O') {
//                    panels[k++].setBackground(Color.BLACK);
//                    Counter.alive++;
//                } else {
//                    panels[k++].setBackground(Color.WHITE);
//                }
//            }
//        }
//        Counter.alive = 0;
//    }
//}