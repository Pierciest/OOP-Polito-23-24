public class GenericInterfaces {
    public static void main(String[] args) {
        // Create an instance of a class that implements the generic interface
        MyGenericClass<Integer> myClass = new MyGenericClass<>();

        // Call the generic method of the interface with an Integer argument
        myClass.genericMethod(10);

        // Create an instance of a class that implements the generic interface
        MyGenericClass<String> myClass2 = new MyGenericClass<>();

        // Call the generic method of the interface with a String argument
        myClass2.genericMethod("Hello");
    }
}

// Define a generic interface with a single method
interface MyGenericInterface<T> {
    void genericMethod(T value);
}

// Implement the generic interface with a concrete class
class MyGenericClass<T> implements MyGenericInterface<T> {
    // Implement the generic method of the interface
    public void genericMethod(T value) {
        System.out.println("Generic method called with value: " + value);
    }
}
