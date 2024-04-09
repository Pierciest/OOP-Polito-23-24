package week4.lecture1.ClassExercises;

public class Dog extends Animal {

    public int mc;
    
    //Override

    public void makeSound() {
        System.out.println("Woof woof!");
    }

    
    //Create
    public void dogBehaviour() {
        System.out.println("Running and playing fetch!");
    }

    public Dog(String _dogName, int mc){
        super(_dogName);
        this.mc = mc;
        System.out.println("calling Dog constructor!");
    }

    public void getMc(){
        System.out.println(mc);

    }
}
    
