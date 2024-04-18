/**
 * A generic class that can hold a value of any type.
 *
 * @param <T> the type of the value
 */
public class Generic<T> {
    private T value;

    /**
     * Constructs a new Generic object with the specified value.
     *
     * @param value the value to be stored
     */
    public Generic(T value) {
        this.value = value;
    }

    /**
     * Returns the value stored in this Generic object.
     *
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value of this Generic object.
     *
     * @param value the new value to be set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * The main method of the program.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Create a Generic object with an Integer value
        Generic<Integer> integerGeneric = new Generic<>(10);
        System.out.println("Value: " + integerGeneric.getValue());

        // Create a Generic object with a String value
        Generic<String> stringGeneric = new Generic<>("Hello, World!");
        System.out.println("Value: " + stringGeneric.getValue());
    }
}
    
