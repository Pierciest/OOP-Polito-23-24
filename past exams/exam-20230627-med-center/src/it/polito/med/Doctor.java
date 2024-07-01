package it.polito.med;
import java.util.*;

public class Doctor {
    String id;
    String name;
    String surname;
    String speciality;
    Map<String,Map<String,Patient>> schedule = new HashMap<>();
    Map<String,List<String>> appointments = new HashMap<>();
    Stack<List<String>> accepted = new Stack<>();
    Map<String,List<String>> completed = new HashMap<>();

    
    public Doctor(String id, String name, String surname, String speciality){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getSpeciality(){
        return this.getSpeciality();
    }
    public Map<String,Patient> getDailySchedule(String date){
        return this.schedule.get(date);
    }
}
