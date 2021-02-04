package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class GameOfLife extends JFrame {
    static JLabel aliveCounter = new JLabel("Alive");
    static JLabel generationsCounter = new JLabel("Generations");
    static JToggleButton PlayToggleButton = new JToggleButton("⏸");
    static JButton ResetButton = new JButton("↩");
    static int tableSize = 20;
    static JPanel[] tableEntities = new JPanel[tableSize * tableSize];
    static JPanel counterPanel = new JPanel(new GridLayout(2, 1));
    static JPanel tablePanel = new JPanel(new GridLayout(tableSize, tableSize));

    final static int generations = 1000;
    static char[][] table;
    static int n;

    public Generator generator;

    Control backgroundThread = new Control();

    public GameOfLife() throws InterruptedException {
        super("Game of Life");
        PlayToggleButton.setName("PlayToggleButton");
        ResetButton.setName("ResetButton");
        aliveCounter.setName("AliveLabel");
        generationsCounter.setName("GenerationLabel");


        this.add(counterPanel, BorderLayout.NORTH);
        counterPanel.add(PlayToggleButton);
        counterPanel.add(ResetButton);
        counterPanel.add(aliveCounter);
        counterPanel.add(generationsCounter);
        this.add(tablePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setVisible(true);

        PlayToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!PlayToggleButton.isSelected()) {
                    backgroundThread.resumeThread();
                } else {
                    backgroundThread.pauseThread();
                }
            }
        });

        ResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                backgroundThread.pauseThread();
                Counter.resetCounter();
                generateTable();
                if (!PlayToggleButton.isSelected()) {
                    backgroundThread.resumeThread();
                } else {
                    printMatrix();
                }
            }
        });

        generateTable();

        generator = Generator.getGenerator();
        printTable();
    }

    public void generateTable() {
        tablePanel.removeAll();
        n = tableSize + 2;
        //generations = 10; //scanner.nextInt();
        Random random = new Random(10);
        table = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = '#';
            }
        }

        int entityPosition = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                tableEntities[entityPosition] = new JPanel();
                if (random.nextBoolean()) {
                    table[i][j] = 'O';
                    tableEntities[entityPosition].setBackground(Color.BLACK);
                    Counter.alive++;
                } else {
                    table[i][j] = ' ';
                    tableEntities[entityPosition].setBackground(Color.WHITE);
                }
                tableEntities[entityPosition].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablePanel.add(tableEntities[entityPosition]);
                entityPosition++;
            }
        }
    }

    public void printTable() throws InterruptedException {
        aliveCounter.setText("Alive: " + Counter.alive);

        printMatrix();

        backgroundThread.start();
    }

    public static void printMatrix() {
        int entityPosition = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (table[i][j] == 'O') {
                    tableEntities[entityPosition++].setBackground(Color.BLACK);
                    Counter.alive++;
                } else {
                    tableEntities[entityPosition++].setBackground(Color.WHITE);
                }
            }
        }
        Counter.alive = 0;
    }
}