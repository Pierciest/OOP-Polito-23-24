public class Pair<T> {
    T a;
    T b;

    public Pair(T a, T b){
        this.a = a;
        this.b = b;
    }

    public T getFirst(){
        return this.a;
    }

    public T getSecond(){
        return this.b;
    }

    public void setFirst(T x){
        this.a = x;
    }

    public void setSecond(T y){
        this.b = y;
    }

}
