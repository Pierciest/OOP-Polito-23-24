package it.polito.med;

public class Patient {
    String ssn;
    String name;
    String surname;
    
    public Patient(String ssn, String name, String surname){
        this.ssn = ssn;
        this.name = name;
        this.surname = surname;
    }
    public String getSsn(){
        return this.ssn;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
}
