package week3.lecture2.TheoryExercises;

public class TwoDimensionalArrays {
    public static void main(String[] args) {
        int[][] twoDimensionalArray = new int[3][4];

        // Assign values to the array
        twoDimensionalArray[0][0] = 1;
        twoDimensionalArray[0][1] = 2;
        twoDimensionalArray[0][2] = 3;
        twoDimensionalArray[0][3] = 4;
    
        twoDimensionalArray[1][0] = 5;
        twoDimensionalArray[1][1] = 6;
        twoDimensionalArray[1][2] = 7;
        twoDimensionalArray[1][3] = 8;
    
        twoDimensionalArray[2][0] = 9;
        twoDimensionalArray[2][1] = 10;
        twoDimensionalArray[2][2] = 11;
        twoDimensionalArray[2][3] = 12;
    
        // Access and print values from the array
        System.out.println(twoDimensionalArray[0][0]); // Output: 1
        System.out.println(twoDimensionalArray[1][2]); // Output: 7
        System.out.println(twoDimensionalArray[2][3]); // Output: 12
    }
}
