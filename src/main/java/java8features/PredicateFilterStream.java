package java8features;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html
public class PredicateFilterStream {
    //we can define multi Line Predicate
    static Predicate<Integer> isEven = number -> {
        return number % 2 == 0;
    };

    public static void main(String args[]) {
        System.out.println(isEven.test(10));
        //CHecks if number is divisible by both 2 and 3
        System.out.println(isEven.and(number -> number % 3 == 0).test(4));
        System.out.println(isEven.and(number -> number % 3 == 0).test(6));
        //If Only divisible by 2 but not 3
        System.out.println(isEven.and(number -> number % 3 == 0).negate().test(4));

    }

    static void filterCheck() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        // Define a Predicate
        Predicate<Integer> isGreaterThanThree = num -> num > 3;
        // Use filter with the Predicate
        List<Integer> filteredNumbers = numbers.stream()
                .filter(isGreaterThanThree) // Applies the predicate
                .collect(Collectors.toList());

    }

    public static void complexFilter() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 25, "IT", Employee.Status.ACTIVE),
                new Employee("Bob", 35, "IT", Employee.Status.ACTIVE),
                new Employee("Charlie", 40, "HR", Employee.Status.ACTIVE),
                new Employee("David", 28, "SALES", Employee.Status.ACTIVE),
                new Employee("Eve", 22, "SALES", Employee.Status.PROBATION),
                new Employee("Frank", 50, "IT", Employee.Status.INACTIVE)
        );
        //Print employees who are in IT or under 30 yeasrs
        Predicate<Employee> inIT = employee -> employee.getDepartment().equalsIgnoreCase("IT");

        Predicate<Employee> active = employee -> {
            return employee.getStatus().equals(Employee.Status.ACTIVE);
        };
        Predicate<Employee> ageLessThan30 = employee -> {
            return employee.getAge() <30;
        };
        //U combine all the predicates
        Predicate<Employee> aboveOR30Years =   inIT.and(active).and(ageLessThan30);

        List<Employee> conditionalEmployees =
                employees.stream().filter(ageLessThan30).collect(Collectors.toList());
        // Group employees by department
        Map<String, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee:: getDepartment));
    }




}


class Employee {
    private String name;
    private int age;
    private String department;
    private Status status;
    public Employee(String name, int age, String department, Status status) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Objects.equals(name, employee.name) &&
                Objects.equals(department, employee.department) &&
                status == employee.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, department, status);
    }

    enum Status {ACTIVE, INACTIVE, PROBATION}
}

