package multithreading.work1;

public class BackgroundTicker {

    public static void main(String[] args) {
        Thread thread1 = startDaemon("thread № 1", 100);
        Thread thread2 = startDaemon("thread № 2", 200);
        Thread thread3 = startDaemon("thread № 3", 300);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static Thread startDaemon(String name, int periodMillis) {
        var thread = new Thread(() -> {
            while (true) {
                System.out.printf("[%s] tick\n", Thread.currentThread().getName());
                try {
                    Thread.sleep(periodMillis);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, name);
        thread.setDaemon(true);
        thread.start();
        return thread;
    }
}
