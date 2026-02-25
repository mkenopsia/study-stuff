package multithreading;

public final class Logger {

    private Logger() {
        throw new UnsupportedOperationException();
    }

    public static void log(final String text) {
        System.out.printf("[%s]: %s\n", Thread.currentThread().getName(), text);
    }

    public static void log(
            String fmt,
            Object... args
    ) {
        String message = args == null || args.length == 0
                ? fmt
                : String.format(fmt, args);

        Thread current = Thread.currentThread();
        String logFormat = "[%tT.%1$tL][thread:%s] %s%n";

        System.out.printf(
                logFormat,
                System.currentTimeMillis(),
                current.getName(),
                message
        );
    }
}
