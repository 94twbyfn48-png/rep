package com.yourcompany.sap.engine;

public class SapRetry {
    /**
     * Gets get operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param action input parameter
     * @param attempts input parameter
     * @param sleepMs input parameter
     *
     * @return operation result
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
     * Executes run operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param action input parameter
     * @param attempts input parameter
     * @param sleepMs input parameter
     *
     * @return operation result
     */
    public static void run(Runnable action, int attempts, long sleepMs) {
        get(() -> {
            action.run();
            return true;
        }, attempts, sleepMs);
    }

    /**
     * Executes sleep operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param ms input parameter
     *
     * @return operation result
     */
    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
