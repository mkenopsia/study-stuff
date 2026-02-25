package multithreading.work1;

public class LoopWorker {

    private static int counter = 0;

    public static void main(String[] args) {

        var thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                counter++;
                try {
                    Thread.sleep(200);
                    System.out.println("Incrementing");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Ending my work");
                }
            }
        });

        thread.start();

        System.out.println("main: sleep");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main: interrupt working thread");
        thread.interrupt();
    }
}
