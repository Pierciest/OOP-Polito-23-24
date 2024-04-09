package week3.lecture3.ClassExercises;

public class App {
    public static void main(String[] args){

        Stack s = new Stack(5);
        int e;
        e = s.pop();

        for(int i = 1; i<=6; i++){
            s.push(i);
        }


        for(int i = 1; i<=6; i++){
            e = s.pop();
            System.out.println(e);
        }

        //The element class is private so we can not see it.
        //Element e = new Element(3);
        Stack.Element e1 = new Stack.Element(2);

        Element e2 = new Element(3);

        System.out.println(e1.getE());
        e2.printmessage();

        ExtendedStack es = new ExtendedStack(3);
        es.push(1);
        es.push(2);
        es.push(3);
        es.printStack();

    }
}
