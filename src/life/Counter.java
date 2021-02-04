package life;

public class Counter {
    public static int alive = 0;
    public static int generation = 1;

    private Counter() {

    }

    public static void resetCounter() {
        alive = 0;
        generation = 1;
    }
}
