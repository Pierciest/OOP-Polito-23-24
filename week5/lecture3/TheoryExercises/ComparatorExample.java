import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class demonstrates the usage of comparators to sort a list of Person objects.
 */
class Person {
    private String name;
    private int age;

    /**
     * Constructs a Person object with the specified name and age.
     *
     * @param name the name of the person
     * @param age  the age of the person
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }
}

public class ComparatorExample {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", 25));
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 20));

        // Sorting by name using lambda function
        Comparator<Person> nameComparator = (p1, p2) -> p1.getName().compareTo(p2.getName());
        Collections.sort(people, nameComparator);
        System.out.println("Sorted by name:");
        for (Person person : people) {
            System.out.println(person.getName() + " - " + person.getAge());
        }

        // Sorting by age using method reference
        Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);
        Collections.sort(people, ageComparator);
        System.out.println("\nSorted by age:");
        for (Person person : people) {
            System.out.println(person.getName() + " - " + person.getAge());
        }
    }
}

/*
 * Logic of the program:
 * 1. Create a class named Person with name and age attributes.
 * 2. Implement a ComparatorExample class to demonstrate the usage of comparators.
 * 3. Create a list of Person objects and add some people to it.
 * 4. Sort the list by name using a lambda function as the comparator.
 * 5. Print the sorted list by name.
 * 6. Sort the list by age using a method reference as the comparator.
 * 7. Print the sorted list by age.
 */
