package week3.lecture2.TheoryExercises;

public class Arrays {
    public static void main(String[] args) {
        // Java arrays are fixed in size and can only store elements of the same type.
        int[] numbers = new int[5]; // Declaring an array of integers with a size of 5
        numbers[0] = 1; // Assigning a value to the first element
        numbers[1] = 2;
        numbers[2] = 3;
        numbers[3] = 4;
        numbers[4] = 5;

        // Python lists are dynamic in size and can store elements of different types.
        // In Python, the equivalent code would be:
        // numbers = [1, 2, 3, 4, 5]

        // C arrays are fixed in size and can store elements of the same type.
        // In C, the equivalent code would be:
        // int numbers[5] = {1, 2, 3, 4, 5};

        // Printing the arrays
        System.out.println("Java Array:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

        // Python list
        // print("Python List:")
        // for number in numbers:
        //     print(number)

        // C array
        // printf("C Array:\n");
        // for (int i = 0; i < 5; i++) {
        //     printf("%d\n", numbers[i]);
        // }
    }
}
