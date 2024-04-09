package week3.lecture3.ClassExercises;

public class Stack {

    Element s[];
    int sp;

    public Stack(int size){
        s = new Element[size];
        sp = 0;
    }

    public void push(int i){
        if (sp < s.length){
            s[sp] = new Element(i);
            sp++;
        } else{
            System.out.println("Stack full");
        }

    }

    public int pop(){
        if (sp > 0 ){
            int r = s[--sp].getE();
            s[sp] = null;
            return r;
        } else{
            System.out.println("Stack is empty");
            return -1;
        }
    }

    public static class Element {
        private int e;
        public Element(int e){
            this.e = e;
        }

        public int getE() {
            return e;
        }
    }
}
