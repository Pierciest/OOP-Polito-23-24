import java.util.Arrays;

public class GenericBox<T>{
	private T content;
	private int size;
	
	public GenericBox(T content) {
		this.content=content;
	}
	
	public T get() {
		T temp=content;
		
		return temp;
	}

	public void put(T obj) {
		this.content=obj;
	}
	
	public boolean isEmpty(){
		if(content==null)
			return true;
		else
			return false;
	}

	

	public T remove(T element) {
        if (element != null && element.equals(this.content)) {
            T removed = this.content;
            this.content = null;
            return removed;
        }
        return null; // Return null if the condition is not met
    }

	

	

}
