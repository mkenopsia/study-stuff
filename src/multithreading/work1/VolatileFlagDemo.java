package multithreading.work1;

public class VolatileFlagDemo {

    private static volatile boolean running = true;

    public static void main(String[] args) {
        start();
        start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        running = false;
    }

    static void start() {
        new Thread(() -> {
            while (running) {
            }
            System.out.println("%s ending my work brotha".formatted(Thread.currentThread().getName()));
        }).start();
    }
}
