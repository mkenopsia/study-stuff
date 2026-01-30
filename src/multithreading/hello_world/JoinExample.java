package multithreading.hello_world;

public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        var thread = new SlowThread();
        thread.start();
        System.out.println("я всё: " + Thread.currentThread().getName());
        thread.join();
        System.out.println("я жду: " + Thread.currentThread().getName());
    }

    public static class SlowThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
