package multithreading.interrupt;

public class InterruptDuringJoin {
    public static void main(String[] args) {
        Thread sleeper = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.print("[sleeper] main разбудил(\n");
                Thread.currentThread().interrupt();
            }
        }, "sleeper");

        Thread worker = new Thread(() -> {
            try {
                System.out.print("start joining\n");
                sleeper.join();
                System.out.print("end joining\n");
            } catch (InterruptedException e) {
                System.out.print("[worker] Вмешался main, сворачиваюсь\n");
                Thread.currentThread().interrupt();
            }
        }, "worker");

        sleeper.start();
        worker.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        worker.interrupt();
        sleeper.interrupt();

        join(sleeper);
        join(worker);
        System.out.print("Конец\n");
    }

    static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
