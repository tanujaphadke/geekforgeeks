package java8features;

public class Animal {
        public String name ="animal";
        public Animal(String name) {this.name = name;}
}

class Dog extends Animal{
    public String name ="DOG";
    public Dog(String name) {
        super("");
        this.name = name;}
}
class PomerenianDog extends Animal{
    public String name ="PomerenianDog";
    public PomerenianDog(String name) {
        super("");
        this.name = name;}
}