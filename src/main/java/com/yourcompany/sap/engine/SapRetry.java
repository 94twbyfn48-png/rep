package com.yourcompany.sap.engine;

public class SapRetry {
    public static <T> T get(java.util.concurrent.Callable<T> action, int attempts, long sleepMs) {
        RuntimeException last = null;
        for (int i = 0; i < attempts; i++) {
            try {
                return action.call();
            } catch (RuntimeException re) {
                last = re;
            } catch (Exception e) {
                last = new RuntimeException(e);
            }
            sleep(sleepMs);
        }
        throw (last != null) ? last : new RuntimeException("Retry failed");
    }

    public static void run(Runnable action, int attempts, long sleepMs) {
        get(() -> {
            action.run();
            return true;
        }, attempts, sleepMs);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
