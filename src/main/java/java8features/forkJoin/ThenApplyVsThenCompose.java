package java8features.forkJoin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThenApplyVsThenCompose {

    // A simulated executor for our async tasks. Since this is CPU intensive task we should use executor service instead of
    // the default ForkJoinPool executer which is more suited for CPU intensive tasks.
    /*
    fetchUserIdAsync completes, and its result (123) is passed to the thenCompose function.
    The thenCompose function also calls fetchUserDetailsAsync, which returns a CompletableFuture<String>.
    Crucially, thenCompose "flattens" this nested structure. The CompletableFuture returned by the thenCompose call tracks
    the completion of the inner CompletableFuture (fetchUserDetailsAsync).
    The result is a clean, flat CompletableFuture<String>, allowing you to get the final result with a single join() call.
    This is the idiomatic way to chain dependent asynchronous operations.
     */
    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    // Simulated async call to get a user's ID
    public static CompletableFuture<Integer> fetchUserIdAsync() {
        System.out.println("-> [fetchUserIdAsync] Starting task on thread: " + Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1); // Simulate network delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            int userId = 123;
            System.out.println("<- [fetchUserIdAsync] User ID fetched: " + userId);
            return userId;
        }, executor);
    }

    // Simulated async call to get user details, which depends on a user ID
    public static CompletableFuture<String> fetchUserDetailsAsync(int userId) {
        System.out.println("-> [fetchUserDetailsAsync] Starting task for ID " + userId + " on thread: " + Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1); // Simulate network delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String details = "User details for " + userId + " fetched.";
            System.out.println("<- [fetchUserDetailsAsync] Details fetched: " + details);
            return details;
        }, executor);
    }

    public static void main(String[] args) {

        System.out.println("--- Demonstrating thenApply (incorrect usage) ---");
        try {
            CompletableFuture<CompletableFuture<String>> nestedFuture = fetchUserIdAsync()
                    .thenApply(userId -> fetchUserDetailsAsync(userId));

            System.out.println("Calling join() on nested future...");
            String result = nestedFuture.join().join(); // Requires two .join() calls
            System.out.println("thenApply result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Demonstrating thenCompose (correct usage) ---");
        try {
            //Please not that the thenCompose waits for the first Async call that is why the returned object
            //is not CompletableFuture of CompletableFuture ... In this case we will need to do two joins.
            CompletableFuture<String> flatFuture = fetchUserIdAsync()
                    .thenCompose(userId -> fetchUserDetailsAsync(userId));

            System.out.println("Calling join() on flat future...");
            String result = flatFuture.join(); // Requires only one .join() call
            System.out.println("thenCompose result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
    /**
     * How thenCompose works
     * thenCompose is designed specifically to avoid nested CompletableFuture structures. It takes a Function that
     * returns a CompletableFuture,
     * and it waits for that new future to complete before completing its own, effectively flattening the structure.
     */
}
