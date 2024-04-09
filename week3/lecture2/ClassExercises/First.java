package week3.lecture2.ClassExercises;

public class First {
    public static void main(String[] args){
        int arr[];
        arr =  new int[10];
        arr[2]=3;
       //arr[11] = 4; this is illegal because, the array is initilized for 10 elements.
        //Java does automotic initilization, when we define an empty array all the elements are 0.
        System.out.println("First way to write");
        for (int j = 0; j<arr.length;j++){
            System.out.println("The element of "+ j + " the array is " + arr[j]);
        }
        System.out.println("The compact way:");
        int j=0;
        for(int el: arr){
            System.out.println("The element of "+ j + " the array is " + el);
            j++;
        }

        String[] p = {new String("John"), new String("Susan")};

        for (String s: p){
            System.out.println("Name: " + s);
        }

        System.out.println("Second part of the class:");
        //If we have used non-static attribute, than for both c1 and c2 we would print 1, since we used
        //static int as the attribute of BuiltCars, we now print 1 and 2.
        Car c1 = new Car();
        c1.printBuiltCars();

        Car c2 = new Car();
        c2.printBuiltCars();

        System.out.println("The sum of 5 and 3 is "+ Mathutils.sum(5,3));
        System.out.println("The diff of 5 and 3 is " + Mathutils.diff(5,3));
    }
}
