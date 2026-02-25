package multithreading.happensBefore;

public class HappensBeforeSynchronized {

    private static final Object lock = new Object();
    private static final Object lock2 = new Object();
    private static int counter = 0;
    private static int shared;

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            shared = 0;
            Thread writer = new Thread(() -> {
                synchronized (lock) {
                    shared = 111;
                }
            });
            Thread reader = new Thread(() -> {
                synchronized (lock2) {
                    if(shared == 0) {
                        counter++;
                    }
                }
            });

            writer.start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            reader.start();

            try {
                writer.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                reader.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(counter);
    }
}
