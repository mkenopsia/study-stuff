package multithreading;

import java.security.SecureRandom;

public final class ThreadUtil {

    private ThreadUtil() {
        throw new UnsupportedOperationException();
    }

    private static final SecureRandom RANDOM = new SecureRandom();

    public static void sleep(final long millis) {
        try {
            Thread.currentThread().sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void safeJoin(final Thread thread) {
        try {
            if (thread != null) {
                thread.join();
            }
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt(); // восстанавливаем флаг
        } catch (Exception ignored) {
        }
    }

    public static void safeJoin(final Thread... threads) {
        if (threads == null) {
            return;
        }
        for (final Thread t : threads) {
            safeJoin(t);
        }
    }

    public static void sleepRandomTime(final int lowerBound, final int upperBound) {
        long time = RANDOM.nextLong(lowerBound, upperBound);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleep(final int timeToSleep) {
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
