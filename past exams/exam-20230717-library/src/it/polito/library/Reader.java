package it.polito.library;
import java.util.*;

public class Reader {
    String name;
    String surname;
    String id;
    Map<String,String> onRent = new HashMap<>();
    Map<String,String> completed = new HashMap<>();

    
    public Reader(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public void appendId(String id){
        this.id = id;
    }
    public void appendRent(String Bid, String startDate ){
        this.onRent.put(Bid, startDate);
    }
    public void terminateRent(String bId, String endDate){
       String startDate = onRent.get(bId);
       this.onRent.remove(bId);
       this.completed.put(bId, startDate+ " " + endDate);
    }
}
