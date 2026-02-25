package multithreading.multithreading_signalling;

import multithreading.Logger;
import multithreading.ThreadUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    static class Office {
        private final Lock lock = new ReentrantLock();
        private final Condition docsReadyCondition = lock.newCondition();
        private final Condition moneyReadyCondition = lock.newCondition();

        private boolean hasDocs = false;
        private boolean hasMoney = false;

        public void bring(final String type) {
            lock.lock();
            try {
                if (type.equalsIgnoreCase("docs")) {
                    hasDocs = true;
                    Logger.log("Принесли документы");
                    docsReadyCondition.signalAll();
                } else {
                    hasMoney = true;
                    Logger.log("Принесли баксы");
                    moneyReadyCondition.signalAll();
                }
            } finally {
                lock.unlock();
            }
        }

        public void take(final String type) {
            lock.lock();
            try {
                if (type.equalsIgnoreCase("docs")) {
                    while (!hasDocs) {
                        Logger.log("Жду документы");
                        docsReadyCondition.await();
                    }
                    hasDocs = false;
                    Logger.log("Забрал документы");
                } else {
                    while (!hasMoney) {
                        Logger.log("Жду баксы");
                        moneyReadyCondition.await();
                    }
                    hasMoney = false;
                    Logger.log("Забрал баксы");
                }
            } catch (InterruptedException e) {
                Logger.log("Всё харе ждать пойду поем");
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final Office office = new Office();

        var th1 = new Thread(() -> office.take("docs"), "Employee-Docs");
        th1.start();
        ThreadUtil.sleep(1000);
        var th2 = new Thread(() -> office.take("money"), "Employee-Money");
        th2.start();
        ThreadUtil.sleep(1000);

        var th3 = new Thread(() -> office.take("docs"), "Employee-Docs-2");
        th3.start();
        ThreadUtil.sleep(1000);
        th3.interrupt();

//        new Thread(() -> office.bring("docs"), "Courier-Docs").start();
//        ThreadUtil.sleep(1000);
//        new Thread(() -> office.bring("money"), "Courier-Money").start();
    }
}
