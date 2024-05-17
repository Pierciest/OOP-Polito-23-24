import java.util.HashMap;
import java.util.Map;

public class HashMaps {
    public static void main(String[] args) {
        // Create a HashMap to store people's names and corresponding Person objects
        Map<String, Person> people = new HashMap<>();

        // Create some Person objects
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Alice", 30);
        Person person3 = new Person("Bob", 35);

        // Add the Person objects to the HashMap
        people.put(person1.getName(), person1);
        people.put(person2.getName(), person2);
        people.put(person3.getName(), person3);

        // Get a Person object by name
        String name = "Alice";
        Person alice = people.get(name);
        System.out.println("Person with name " + name + ": " + alice);

        // Update the age of a Person
        String nameToUpdate = "John";
        int newAge = 26;
        Person john = people.get(nameToUpdate);
        if (john != null) {
            john.setAge(newAge);
            System.out.println("Updated age of " + nameToUpdate + " to " + newAge);
        }

        // Remove a Person from the HashMap
        String nameToRemove = "Bob";
        people.remove(nameToRemove);
        System.out.println("Removed " + nameToRemove + " from the HashMap");

        // Iterate over the HashMap and print all entries
        for (Map.Entry<String, Person> entry : people.entrySet()) {
            String personName = entry.getKey();
            Person person = entry.getValue();
            System.out.println("Name: " + personName + ", Person: " + person);
        }
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}
// Hash functions are used by HashMaps to convert keys into hash codes,
// which are then used to determine the index of the corresponding entry in the underlying array.
// A good hash function should distribute the hash codes evenly across the array,
// minimizing collisions and improving the performance of the HashMap.
