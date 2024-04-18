public class MixedPair<T, U> {
    T a;
    U b;

    public MixedPair(T a, U b){
        this.a = a;
        this.b = b;
    }

    public T getFirst(){
        return this.a;
    }

    public U getSecond(){
        return this.b;
    }

    public void setFirst(T x){
        this.a = x;
    }

    public void setSecond(U y){
        this.b = y;
    }

}
