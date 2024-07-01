package it.polito.library;
import java.util.*;

public class Book {
    List<String> ids = new LinkedList<>();
    String name;
    int copies = 0;
   

    Map<String,Integer> rentTrack = new HashMap<>();

    Stack<String> availableStack = new Stack<>();
    Stack<String> onRentStack = new Stack<>();
    public Book(String name){
        this.name = name;
    }

    public List<String> getId(){
        return this.ids;
    }

    public String getName(){
        return this.name;
    }
    public int getCopies(){
        return this.copies;
    }
    
    public int getTotalRental(){
        int sum = 0;
        for(int r : rentTrack.values()){
            sum += r;
        }
        return sum;
    }

    public void increaseCopy(){
        this.copies++;
    }

    public void appendId(String id){
        ids.add(id);
        rentTrack.put(id, 0);
    }
    
    public void appendAvailable(String id){
        availableStack.push(id);
    }
    public void appendRent(){
        String id = availableStack.pop();
        onRentStack.push(id);
    }
    public void terminateRent(String id){
        availableStack.push(id);
        onRentStack.removeElement(id);
    }
    public void increaseTrack(String id){
        int curTrack = rentTrack.get(id);
        curTrack++;
        rentTrack.put(id, curTrack);
    }
    public void removeCopy(String id){
        this.ids.remove(id);
        this.rentTrack.remove(id);
        this.copies--;
    }

}
