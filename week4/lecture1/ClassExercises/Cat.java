package week4.lecture1.ClassExercises;

public class Cat extends Animal{

    //Override

    public void makeSound() {
        System.out.println("Meow meow!");
    }

    //Create

    public void catBehaviour() {
        System.out.println("Walking around with a smug face");
    }

    public Cat(String _catName){
        super(_catName);
        System.out.println("calling Cat constructor!");

    }
}
