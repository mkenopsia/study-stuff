package multithreading.signalization;

import multithreading.Logger;
import multithreading.ThreadUtil;

public class CoffeeSignalization {

    static class CoffeeMachine {

        private static boolean coffeeReady = false;

        synchronized void takeCoffee() {
            while (!coffeeReady) {
                Logger.log("Кофе не готов, жду баристу");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            Logger.log("Забрал кофе");
            coffeeReady = false;
            notify();
        }

        synchronized void makeCoffee() {
            while (coffeeReady) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            Logger.log("Готовлю кофе");
            ThreadUtil.sleep(1000);
            coffeeReady = true;
            Logger.log("Кофе готов");
            this.notify();
        }
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Thread consumer = new Thread(coffeeMachine::takeCoffee, "consumer");
        Thread consumer1 = new Thread(coffeeMachine::takeCoffee, "consumer1");
        Thread barista = new Thread(coffeeMachine::makeCoffee, "barista");
        Thread barista1 = new Thread(coffeeMachine::makeCoffee, "barista1");

        consumer.start();
//        consumer1.start();
        ThreadUtil.sleep(500);
        barista.start();
        barista1.start();
    }
}
