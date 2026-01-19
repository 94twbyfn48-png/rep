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

    /**
     * Run an action with retries, returning the result.
     *
     * @param action   the callable to execute
     * @param attempts maximum number of attempts
     * @param sleepMs  milliseconds to sleep between attempts
     * @param <T>      return type
     * @return the callable result
     * @throws RuntimeException if all attempts fail
     */
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

    /**
     * Run a runnable with retries.
     *
     * @param action   the action to run
     * @param attempts number of attempts
     * @param sleepMs  sleep between attempts
     */
    public static void run(Runnable action, int attempts, long sleepMs) {
        get(() -> {
            action.run();
            return true;
        }, attempts, sleepMs);
    }

    /**
     * Sleep helper that preserves interrupt status when interrupted.
     *
     * @param ms milliseconds to sleep
     */
    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
