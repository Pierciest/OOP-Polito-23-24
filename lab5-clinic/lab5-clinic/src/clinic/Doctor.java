package clinic;
import java.util.*;

public class Doctor {
    String first;
    String last;
    String ssn;
    String specialization;
    int docID;
    Map<String, Patient> assignedPatients = new LinkedHashMap<>();
    public Doctor(String first, String last, String ssn, int docID , String specialization){
        this.first = first;
        this.last = last;
        this.ssn = ssn;
        this.docID = docID;
        this.specialization = specialization;
    }

    public int getID(){
        return this.docID;
    }

    public String getSpeciality(){
        return this.specialization;
    }

    public int getPatientSize(){
        return this.assignedPatients.size();
    }
}
