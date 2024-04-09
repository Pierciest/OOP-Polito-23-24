package week3.lecture3.ClassExercises;

public class ExtendedStack extends Stack {

    public ExtendedStack(int size){
        super(size); //It refers to the parent of the class.
    }
    
    public void printStack(){
        for (int i = 0; i< sp; i++){
            System.out.println(s[i].getE());
        }
    }
}
