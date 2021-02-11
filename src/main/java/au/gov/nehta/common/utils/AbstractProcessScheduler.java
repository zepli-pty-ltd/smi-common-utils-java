package au.gov.nehta.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class facilitates the java process to run periodically for the specified
 * time interval. This scheduled thread can be stopped, restarted and cancelled.
 *
 * @author VinSekar
 */
public abstract class AbstractProcessScheduler extends Thread {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(AbstractProcessScheduler.class);

    /**
     * The time interval in seconds nanoseconds
     */
    private final int interval;

    /**
     * Flag value to stop the thread.
     */
    private boolean stopThread = false;

    /**
     * The timer instance to run the scheduled task for the specified time
     * interval.
     */

    /**
     * Default constructor with accepts the time interval as an argument.
     *
     * @param interval
     */
    public AbstractProcessScheduler(int interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        runPeriodically();
    }

    /**
     * This method runs the current thread periodically for the provided interval.
     */
    public void runPeriodically() {
        try {
            this.stopThread = false;
            // This thread check for every x time interval to stop/continue the
            // thread.
            while (!this.stopThread) {
                Thread.sleep(this.interval);
                performThreadTask();
            }

        } catch (InterruptedException ex) {
            String errMsg = "The thread running periodic task,'"
                    + this.getClass().getSimpleName()
                    + "', was interrupted while sleeping.";
            LOGGER.warn(errMsg, ex);
        }
    }

    /**
     * Implement the piece of code to be executed for the scheduled time interval.
     */
    public abstract void performThreadTask();

    /**
     * This method stops the scheduled thread. This method is thread safe.
     */
    public synchronized void stopTimerThread() {
        this.stopThread = true;
    }

    /**
     * This method starts the scheduler thread. This method is thread safe.
     */
    public synchronized void startThread() {
        this.stopThread = false;
        runPeriodically();
    }

    /**
     * This method returns true if the timer thread is running.
     *
     * @return true if timer thread is running
     */
    public boolean isThreadRunning() {
        if (!this.stopThread) {
            return true;
        }
        return false;
    }
}
