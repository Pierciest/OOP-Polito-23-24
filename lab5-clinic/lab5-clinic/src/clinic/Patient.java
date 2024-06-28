package clinic;

import java.util.TreeMap;

public class Patient {
    String first;
    String last;
    String ssn;
    TreeMap<Integer, Doctor> assignedDoctors = new TreeMap<>();

    public Patient(String first, String last, String ssn){
        this.first = first;
        this.last = last;
        this.ssn = ssn;
    }
}
