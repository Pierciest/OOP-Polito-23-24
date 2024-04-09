package week4.lecture1.ClassExercises;

public class Animal {
    protected String name;

    public Animal(String name){
        System.out.println("calling animal constructor!");
        this.name = name;
    }



    public void makeSound() {
        System.out.println("Generic animal sound!");
    }

    public String getName(){
        return this.name;
    }

    public void printName(){
        System.out.println(name);

    }
}
    
    
