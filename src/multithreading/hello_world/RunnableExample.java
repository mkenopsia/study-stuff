package multithreading.hello_world;

public class RunnableExample {
    public static void main(String[] args) {
        new Thread(new HelloWorldRunnable(), "sample1").start();
        new Thread(new HelloWorldRunnable(), "sample2").start();
        new Thread(new HelloWorldRunnable(), "sample3").start();
        new Thread(() -> System.out.printf("hi there1\n"), "sample").start();
        new Thread(() -> System.out.printf("hi there2\n"), "sample").start();
        new Thread(() -> System.out.printf("hi there3\n"), "sample").start();
        new Thread(() -> System.out.printf("hi there4\n"), "sample").start();
    }

    public static class HelloWorldRunnable implements Runnable {

        @Override
        public void run() {
            System.out.printf(Thread.currentThread().getName() + "\n");
        }
    }
}
