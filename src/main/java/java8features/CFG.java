package java8features;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CFG {


    interface HelloWorld {
        //public void greet();
        public void greetSomeone(String someone);
    }
    public static void sayHello() {
        HelloWorld inenglish = (a) -> System.out.println( "Hello " + a);
        HelloWorld inFrench = (a) -> System.out.println( "Salut " + a);
        HelloWorld inSpanish = (a) -> System.out.println( "Hola " + a);

        inenglish.greetSomeone("tan");
        inFrench.greetSomeone("tan");
        inSpanish.greetSomeone("man");
    }

    public static void method2(){
        List<String> names = Arrays.asList(
                "Alice", "Bob", "Charlie", "Adam");

        System.out.println("All names:");
        names.forEach(name -> System.out.println(name));

        System.out.println("\nNames starting with 'A':");
        names.stream()
                .filter(n -> n.startsWith("A"))
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);
        //Flatmap example
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("Java", "Spring"),
                Arrays.asList("Node", "React")
        );

        Stream<String> streamOfString = nestedList.stream()
                .flatMap(list -> list.stream());// Function maps List<String> to Stream<String>
              //  .collect(Collectors.toList());
        streamOfString.collect(ArrayList::new ,
                (list, elem) -> list.add(elem),
                (lst1, lst2)-> lst1.addAll(lst2)
                );

        streamOfString.collect(ArrayList::new ,
                ArrayList::add,
                ArrayList:: addAll
        );
        AtomicInteger ai = new AtomicInteger(0);
        nestedList.stream().flatMap(lstOfString -> lstOfString.stream()).collect(
                () -> new HashMap<Integer, String>() ,
                (map, elem) -> {
                    int val = ai.incrementAndGet();
                    map.put(new Integer(val), elem);
                },
                (map1, map2) -> map1.putAll(map2)
        ); //end collect. But this does not gurantee sequence

        //Create a map where key = hashcode of the string and value is the string
        Stream<String>  flatStrm = nestedList.stream().flatMap(List :: stream);
        flatStrm.collect(() -> new LinkedHashMap<Integer, String>() ,
                (map, e)->{
                    map.put(e.hashCode(),e);
                },
                (map1,map2) -> map1.putAll(map2)
        );

        //How to preserve the index without using Atomic Int
        List<String> listStr = nestedList.stream().flatMap(List :: stream).collect(Collectors.toList());
        IntStream intStrm = IntStream.range(0, listStr.size() -1 );
        intStrm.boxed().collect(
                () -> new LinkedHashMap<Integer, String>(),
                (map, i ) ->{
                  String e = listStr.get(i);
                  map.put(i, e);
                },
                (map1,map2) -> map1.putAll(map2)

        );


    }
    //Now lets take a look at the functions of stream that take this type
    /*
        Stream contains elements of type T
     	flatMap(
     	    Function<? super T,? extends Stream<? extends R>> mapper

     	    )
     	 The stream contains elements of type T on which the flat map function is called
     	 flatMap takes a functional interface whose apply method we can implement using lambda
     	 what is the argument to that apply method -- that which is either type T or any of its super Class.
     	                                           --- For  example the stream on which we call flatMap contains
     	                                           DOG then the argument to the apply method or the lambda expression
     	                                           can be either Dog or Animal but it cannot be pomireneianDOG
     	                                           since POmDog is not a superclass of DOG
     	 What does it return ? It returns a Stream whoose Elements are either R or and subclass of R

     	 List<List<String>> nestedList = Arrays.asList(
            Arrays.asList("Alice", "Bob"),
            Arrays.asList("Charlie", "David")
        );

        // Using flatMap to flatten the nested lists into one stream
        List<String> flatList = nestedList.stream()
            .flatMap(list -> list.stream()) // Method reference for a lambda returning a Stream
            .collect(Collectors.toList());

        Here T = List<String>
        The implementation of apply for Functional interface has this
        list -> list.stream() [ List<String> is converted to stream of Strings
        so the input to apply is List<String> ( T  - the type itself, list is the input  )
        output is "Stream <String>"  ? extends Stream<? extends R>>
        Streams ==> it is Stream or something that extends Stream , each element of the Stream should contain R
        R is String
        R

     */

    static void moreThanN(int number, Predicate<Number> predicate)
    {
        if (predicate.test(number)) {
            System.out.println("Number " + number);
        }
    }


    static void isAnimalKnown(Animal animal, Predicate<Animal> predicate)
    {
        if (predicate.test(animal)) {
            System.out.println("Animal name " + animal);
        }
    }

    static void method3PredTest(){

        Predicate<Number> predicate7 = (x) -> x.intValue() > 7;
        List<Integer> nums = Arrays.asList(
                1,3,5,66,8,9,22);
        List<Integer> morethan7 = nums.
                stream().filter(predicate7).collect(Collectors.toList());
        List<Number> numners = Arrays.asList(
                1,3,5,66,8,9,22);
        List<Number> morethan7n = nums.
                stream().filter(predicate7).collect(Collectors.toList());

        List<Animal> animalList = Arrays.asList(
                new Animal("onea"),new Animal("twoa"), new Animal(null) );
        List<Dog> dogList = Arrays.asList(
                new Dog("dog1"),new Dog("dog2"), new Dog(null) );
        List<PomerenianDog> pomDogList = Arrays.asList(
                new PomerenianDog("pomdog1"),new PomerenianDog("pomdog2"), new PomerenianDog(null) );

        Predicate<Animal> animalKnown = a -> a.name !=null;
        Predicate<PomerenianDog> pomAnimalKnown = a -> a.name !=null;

        // the stream animallist has animal as its element - so the prediate should be animal or its superclass
        animalList.stream().filter( animalKnown).count();
        // the stream animallist has dog as its element - so the prediate should
        // be dog or its superclass. Since animalKnown takes Animal which is superclassof do so okay
        dogList.stream().filter( animalKnown).count();
        pomDogList.stream().filter(pomAnimalKnown).count();

        //The following is not allowed because hte perdicate in the filter function should take
        //either the type or the super type of the argument.. In this case pomrenian is a subclass of
        // the animal which is the narrowing down
        //animalList.stream().filter(pomAnimalKnown).count();
    }
    interface Add{

        int addition(int a, int b);
    }
    public static void main(String[] args){

        sayHello();
        // Lambda expression to add two numbers
        Add add = (a, b) -> a + b;

        int result = add.addition(10, 20);
        System.out.println("Sum: " + result);
    }
}

