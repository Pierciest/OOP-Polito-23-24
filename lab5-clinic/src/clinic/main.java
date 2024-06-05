package clinic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public class main {
    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchPatient, NoSuchDoctor {
        Clinic clinic = new Clinic();

		clinic.addPatient("Alice", "Green", "ALCGRN");
		clinic.addPatient("Robert", "Smith", "RBTSMT");
		clinic.addPatient("Steve", "Moore", "STVMRE");
		clinic.addPatient("Susan", "Madison", "SSNMDS");
		
		clinic.addDoctor("Kate", "Love", "LVOKTA",86,"Dentist");
		clinic.addDoctor("Marie", "Curie", "MRICRU",88,"Chemist");
        clinic.addDoctor("George", "Sun","SNUGRG", 14,"Physician");

		
		clinic.assignPatientToDoctor("SSNMDS", 86);
		clinic.assignPatientToDoctor("ALCGRN", 88);
		clinic.assignPatientToDoctor("RBTSMT", 88);
		clinic.assignPatientToDoctor("STVMRE", 86);

		Collection<String> pps = clinic.countPatientsPerSpecialization();

        System.out.println(pps);
    }
    
}
