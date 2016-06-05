package in.healthycoder.tnea;

import java.util.function.Supplier;

/**
 * Created by Suresh_Karuppannan on 5/27/2016.
 */
public class RetryOnFailure<T> {
    private int retryCounter;
    private int maxRetries;

    public RetryOnFailure(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public T run(Supplier<T> function) {
        try {
            return function.get();
        } catch (Exception e) {
            return retry(function);
        }
    }

    private T retry(Supplier<T> function) throws RuntimeException {
        System.out.println("FAILED - Command failed, will be retried " + maxRetries + " times.");
        retryCounter = 0;
        while (retryCounter < maxRetries) {
            try {
                waitForSometime();
                return function.get();
            } catch (Exception ex) {
                retryCounter++;
                System.out.println("FAILED - Command failed on retry " + retryCounter + " of " + maxRetries + " error: " + ex);
                if (retryCounter >= maxRetries) {
                    System.out.println("Max retries exceeded.");
                    break;
                }
            }
        }
        throw new RuntimeException("Command failed on all of " + maxRetries + " retries");
    }

    private void waitForSometime() {
        try {
            Thread.sleep(7 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
