package multithreading.happensBefore;

public class HappensBeforeVolatile {

    static int data = 0;
    static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!flag) {}
            System.out.println("Data: " + data);
        }).start();

        Thread.sleep(100);

        new Thread(() -> {
            data = 111;
            flag = true;
        }).start();
    }
}
