package week4.lecture1.ClassExercises;

public class App {

    public static void main(String[] args) throws Exception{


        Animal animal = new Animal("Mert");
        Cat cat = new Cat("Felis");
        Dog dog = new Dog("Messi",15);
        Labrador l1 = new Labrador("Crypto",16);
    
        // Upcasting: Converting a subclass reference to a superclass reference
        Animal a1 = (Animal) dog;
        a1.makeSound();
        System.out.println(a1.getClass());

        // Downcasting: Converting a superclass reference to a subclass reference
        // Dog d1 = (Dog) animal;
        // d1.dogBehaviour(); // wrong

        // The instanceof operator is used to check if an object is an instance of a particular class
        // It returns true if the object is an instance of the specified class or any of its subclasses
        // It returns false otherwise
        // Example:
        if (animal instanceof Dog) {
             Dog d1 = (Dog) animal;
             d1.dogBehaviour();
        }
        else{
            System.out.println("Dog is not an instance of animal!");
        }

        // Generality: Using a superclass reference to refer to objects of different subclasses
        Animal[] animals = {animal, cat, dog, l1};
        System.out.println("Iterating over animals:");
    
        for (Animal a:animals){
            a.makeSound();
            a.printName();
            if (a instanceof Dog) {
                ((Dog)a).getMc();
           }
        }

        l1.getMc();


        

    }



    
}
