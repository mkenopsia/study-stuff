package multithreading.work2.deadlock;

import multithreading.Logger;
import multithreading.ThreadUtil;

import java.util.concurrent.TimeUnit;

public final class TryLockLockableResourceProcessor implements LockableResourceProcessor {
    @Override
    public void processResources(LockableResource r1, LockableResource r2) {
        Logger.log("starting work r1=%s r2=%s", r1, r2);

        while (true) {
            try {
                Logger.log("Пытаемся захватить первый лок");
                if (!r1.getLock().tryLock(100, TimeUnit.MILLISECONDS)) {
                    Logger.log("Не удалось захватить первый лок, сворачиваюсь");
                    ThreadUtil.sleepRandomTime(5, 15);
                    continue;
                }

                Logger.log("Пытаемся захватить второй лок");
                if (!r2.getLock().tryLock(100, TimeUnit.MILLISECONDS)) {
                    Logger.log("Не удалось захватить второй лок, сворачиваюсь");
                    ThreadUtil.sleepRandomTime(5, 15);
                    continue;
                }
                Logger.log("Захватил оба лока, работа выполнена");
                return;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                if(r1.getLock().isHeldByCurrentThread()) {
                    r1.getLock().unlock();
                }
                if(r2.getLock().isHeldByCurrentThread()) {
                    r2.getLock().unlock();
                }
            }
        }
    }
}
