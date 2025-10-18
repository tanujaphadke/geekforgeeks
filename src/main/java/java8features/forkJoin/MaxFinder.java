package java8features.forkJoin;

/**
 * Explanation of the MaxFinder logic
 * <p>
 * Inheritance: MaxFinder extends RecursiveTask<Integer>, indicating that it is a result-bearing task that will return an Integer.
 * THRESHOLD: A constant determines the size at which a task should stop splitting and perform the computation sequentially. This is crucial for performance, as too much splitting can cause overhead that outweighs the benefits of parallelism.
 * Constructor: The constructor takes the array and the start and end indices to define the segment of the array that this task is responsible for.
 * compute() method:
 * If-Block (Base Case): If the size of the segment (end - start) is less than or equal to the THRESHOLD, it calls computeSequentially(). This is the non-parallel part of the algorithm.
 * Else-Block (Recursive Case): If the segment is too large, it is split in half by calculating a mid point.
 * fork(): The leftTask.fork() call submits the left subtask to the thread pool for asynchronous execution. The current thread is then free to do other work.
 * compute(): The current thread immediately processes the rightTask by calling its compute() method directly. This is a work-stealing optimization that helps balance the workload among the available threads.
 * join(): The leftTask.join() call waits for the leftTask (which was forked) to complete and retrieves its result. The call blocks until the result is available.
 * Combine Results: The results from the left and right subtasks are combined to produce the final result for this level of the recursion.
 * computeSequentially(): This method simply iterates through the assigned array segment and finds the maximum value, just as a standard single-threaded algorithm would.
 */

//This returns a value...
import java.util.concurrent.RecursiveTask;

/**
 * A RecursiveTask to find the maximum value in a portion of an array.
 */
public class MaxFinder extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 1000; // The threshold for sequential processing
    private final int[] array;
    private final int start;
    private final int end;

    public MaxFinder(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    //This will be called when the invoke method is called . invoke --> execute --> compute.
    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            // Base case: the portion is small enough to be processed sequentially
            return computeSequentially();
        } else {
            // Recursive case: split the task into two subtasks
            int mid = start + (end - start) / 2;
            MaxFinder leftTask = new MaxFinder(array, start, mid);
            MaxFinder rightTask = new MaxFinder(array, mid, end);

            // Fork the left task to run asynchronously
            leftTask.fork();

            // Compute the right task and - call it here to use the current thread. this will also fork
            //initiates the process on the current thread, rather than submitting it to the pool for asynchronous execution.
            //AI search here scroll to the bottom https://www.google.com/search?client=firefox-b-1-d&q=%3A%3A+operator+in+java8&udm=50&fbs=AIIjpHxU7SXXniUZfeShr2fp4giZ1Y6MJ25_tmWITc7uy4KIeoJTKjrFjVxydQWqI2NcOhZVmrJB8DQUK5IzxA2fZbQF4YL5sNSRJGgx0e9Z9AxEx1bmPbSY3ROQyoKhw9UuuwMa6u73fOSUeNMl3nUidiZzLnwz6Z8MHmWi4YdNSL-h_t8V7LARbEt0dOcH9HsmIXwiB-7_vX2wqahbyWxd3rXB8FJu8Q&ved=2ahUKEwjs06Tr6p-QAxUuDjQIHUDwClkQ0NsOegQISRAA&aep=10&ntc=1&mtid=ekLsaOWGGsKu0PEP0pyXyQs&mstk=AUtExfC6c2QxW92aasscNTyoKezMAFYo1WNi8HqvJmGFiFTSgNQtSoXNfyWa285xzY8tpaqbZWd9MuDuKevOj2g8RhTED2nFDUVNosWbswv0SI2xUoEfnpU4cSpvjb4TWfp04nut4lOOsqC_2TW7BqjO7420c1gPrFc0tPPgJ-YRuJKjYZdPjjKSfr7o-u54fua2_Oe3iBgYGekTNFTPbZbSclX6FmW0SVCsQUbOMO53Q7mv5Yf0mZ9Azv8v7sUKgAHYB0DywxttxuYWLCeeb74oGr2h_OWzc0-NvZ6TN1-mGahrwKR_nhru3eDTiLh9cRA4Nnku9T8p2oe6tw&csuir=1
            //https://www.pluralsight.com/resources/blog/guides/introduction-to-the-fork-join-framework
            Integer rightResult = rightTask.compute();

            // Wait for the left task to complete and get its result.
            Integer leftResult = leftTask.join();

            // Combine the results and return the final maximum
            return Math.max(leftResult, rightResult);
        }
    }

    private Integer computeSequentially() {
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
