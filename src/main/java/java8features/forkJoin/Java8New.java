package java8features.forkJoin;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Check this out
 * https://howtodoinjava.com/java8/java-8-list-all-files-example/
 * see the left side TOC and U will find all java 8 features
 * https://medium.com/@javatechie/understanding-the-basics-of-executorservice-vs-forkjoinpool-0fb22f117480
 * Above is a very good article
 * https://medium.com/@javatechie/understanding-the-basics-of-executorservice-vs-forkjoinpool-0fb22f117480
 *
 */
public class Java8New {

    public static  void main(String args[]){
    //Method Reference

        List<Integer> integers = Arrays.asList(1,23,45,67);
        Optional<Integer> max = integers.stream().reduce( Math::max );
        max.ifPresent(value -> System.out.println(value));

        //Method Reference to Instance Method of a Particular Object
        PrintService printService = new PrintService();
        List<String> messages = Arrays.asList("Hello", "World", "Method", "References");
        messages.forEach(message -> printService.print(message));

// iusing method reference
        messages.forEach(PrintService::print);

        // Optional
        Optional<Integer> possible = Optional.ofNullable(null);
        System.out.println(possible.get());
        //or
        Optional<Integer> possible2 = Optional.ofNullable(5);
    }


}
 class PrintService {

    public static void print(String message) {
        System.out.println(message);
    }
}