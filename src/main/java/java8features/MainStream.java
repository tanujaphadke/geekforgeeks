package java8features;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainStream {
 public static void main(String[] args) {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            // This is a thread-safe counter
            AtomicInteger combinerCount = new AtomicInteger(0);

            List<Integer> result = numbers.parallelStream().collect(
                    ArrayList::new,
                    ArrayList::add,
                    (left, right) -> {
                        // Increment the counter safely
                        combinerCount.incrementAndGet();
                        System.out.println("Combiner thread: " + Thread.currentThread().getName());
                        left.addAll(right);
                    }
            );

            System.out.println("Final List: " + result);
            System.out.println("The combiner was invoked " + combinerCount.get() + " times.");
        }

}
