package multithreading.interrupt;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyDemo {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ДЕМО: lock() vs lockInterruptibly() ===\n");

        // Сценарий 1: lock() — игнорирует interrupt
        testLock();

        // Сценарий 2: lockInterruptibly() — реагирует на interrupt
        testLockInterruptibly();
    }

    private static void testLock() throws InterruptedException {
        System.out.println("🔒 ТЕСТ 1: lock()");

        // Главный поток захватывает лок и НЕ отпускает
        lock.lock();
        System.out.println("[Main] Захватил lock, держу его 5 секунд...\n");

        Thread worker = new Thread(() -> {
            System.out.println("[Worker-lock] Пытаюсь захватить lock.lock()...");
            lock.lock(); // <-- БУДЕТ ЖДАТЬ, игнорируя interrupt
            try {
                System.out.println("[Worker-lock] УРА! Захватил лок!");
            } finally {
                lock.unlock();
            }
        }, "Worker-lock");

        worker.start();
        Thread.sleep(1000); // Даем воркеру время начать ждать

        System.out.println("[Main] Прерываю Worker-lock...");
        worker.interrupt(); // Пытаемся прервать
        System.out.println("[Main] interrupt() вызван, но lock() его игнорирует!\n");

        // Освобождаем лок, чтобы воркер мог завершиться
        Thread.sleep(4000);
        lock.unlock();
        worker.join();
        System.out.println("[Main] Worker-lock завершил работу (успешно захватил лок)\n");
        System.out.println("─────────────────────────────────\n");
    }

    private static void testLockInterruptibly() throws InterruptedException {
        System.out.println("🔓 ТЕСТ 2: lockInterruptibly()");

        // Главный поток снова захватывает лок
        lock.lock();
        System.out.println("[Main] Захватил lock, держу его 3 секунды...\n");

        Thread worker = new Thread(() -> {
            System.out.println("[Worker-int] Пытаюсь захватить lock.lockInterruptibly()...");
            try {
                lock.lockInterruptibly(); // <-- БУДЕТ ЖДАТЬ, но РЕАГИРУЕТ на interrupt
                try {
                    System.out.println("[Worker-int] УРА! Захватил лок!");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("[Worker-int] ❗ ПОЛУЧИЛ InterruptedException!");
                System.out.println("[Worker-int] Лок НЕ захвачен, работа прервана.");
                // Важно: не делаем unlock(), так как мы не владеем локом!
            }
        }, "Worker-int");

        worker.start();
        Thread.sleep(1000); // Даем воркеру время начать ждать

        System.out.println("[Main] Прерываю Worker-int...");
        worker.interrupt(); // Пытаемся прервать
        System.out.println("[Main] interrupt() вызван, lockInterruptibly() отреагирует!\n");

        worker.join(); // Ждем завершения воркера
        System.out.println("[Main] Worker-int завершил работу (был прерван)\n");

        // Освобождаем лок (он всё ещё у нас)
        lock.unlock();
    }
}