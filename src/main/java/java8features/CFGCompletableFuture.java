package java8features;
/*package whatever //do not write package name here */

import java.util.concurrent.*;

class GFGCompletableFuture {
    public static void main(String[] args) throws Exception{

    }
    public static void sampleRun() throws Exception
    {
        CompletableFuture<String> helloFuture
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> greetingFuture
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<String> combinedFuture
                = helloFuture.thenCombine(
                greetingFuture, (m1, m2) -> m1 + " " + m2);

        System.out.println(combinedFuture.get());
    }


    public static void allRun() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 20);

        // Running multiple tasks in parallel
        CompletableFuture<Void> allOf = CompletableFuture.allOf(f1, f2);
        // Waits for both futures to complete
        allOf.join();

        System.out.println("Future1 Result: " + f1.get());
        System.out.println("Future2 Result: " + f2.get());
    }


    public static void exceptionCases() throws Exception
    {
        CompletableFuture<Integer> resultFuture
                // java.lang.ArithmeticException: / by zero
                = CompletableFuture.supplyAsync(() -> 10 / 0)
                .exceptionally(ex -> 0);

        // 0 - returned by exceptionally block
        System.out.println(resultFuture.get());
    }
}