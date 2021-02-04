package life;

public class Control extends Thread {
    private volatile boolean running = true;

    public void pauseThread() {
        running = false;
    }

    public void resumeThread() {
        running = true;
    }

    @Override
    public void run() {
        while (Counter.generation < GameOfLife.generations) {
            while (!running) {
                Thread.yield();
            }
            Generator.getGenerator().nextState(GameOfLife.table, GameOfLife.n);
            Counter.generation++;
            GameOfLife.generationsCounter.setText("Generation #" + Counter.generation);
            GameOfLife.aliveCounter.setText("Alive: " + Counter.alive);

            GameOfLife.printMatrix();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
