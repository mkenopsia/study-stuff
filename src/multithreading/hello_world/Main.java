package multithreading.hello_world;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new HelloWorldThread("serious name");
        Thread thread2 = new HelloWorldThread("not serious name");
        Thread thread3 = new HelloWorldThread("not serious name2");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static class HelloWorldThread extends Thread {
        public HelloWorldThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.printf("Hello world! My name is %s\n", getName());
        }
    }
}
