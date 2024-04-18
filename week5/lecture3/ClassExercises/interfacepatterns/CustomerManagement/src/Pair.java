public class Pair<T extends Comparable>{

    T a;
    T b;
    
    public Pair(T a, T b){
        this.a=a;
        this.b=b;
    }
    
    public T getFirst(){
        return this.a;
    }
    
    public T getSecond(){
        return this.b;
    }
    
    public void setFirst(T x){
        this.a=x;
    }
    
    public void setSecond(T x){
        this.b=x;
    }
    
    public static void printPair(Pair<Object> p){
            System.out.println(p.a + " - "+ p.b);
    }
    
    public static <T extends Comparable<T>> void sortPair(Pair<T> p) {
        if(p.getFirst().compareTo(p.getSecond()) > 0){
            T tmp = p.getFirst();
            p.setFirst(p.getSecond());
            p.setSecond(tmp);
        }
    }
    
}