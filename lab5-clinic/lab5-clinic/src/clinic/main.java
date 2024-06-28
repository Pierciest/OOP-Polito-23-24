package clinic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.io.FileReader;

public class main {
	public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchPatient, NoSuchDoctor {
		Clinic c = new Clinic();
		c.addPatient("John", "Smith", "SHNSMT23X12A543L");
		c.addPatient("Mary", "White", "MRYWHT98G76F987W");
		c.addPatient("Mario", "Rossi", "MRIRSS32J88K987P");
		c.addPatient("Giuseppe", "Verdi", "GPPVRD43A34H987O");
		c.addPatient("Fang", "Li", "LIIFNG98T54K123A");
		c.addPatient("Sirius", "Black", "BLKSRS11I88F981K");
		c.addDoctor("Umberto", "Veronesi", "MBTVRN43J56K124U", 111, "Oncologist");
		c.addDoctor("Luigi", "Neri", "LGINRI56K34L098K", 123, "Surgeon");
		c.addDoctor("Severus", "Snape", "VRSSNP54K43L098J", 124, "Alchemist");
		c.addDoctor("Felice", "Tranquillo", "FLCTRQ26G98T592R", 220, "Dentist");

		c.assignPatientToDoctor("SHNSMT23X12A543L", 111);
		c.assignPatientToDoctor("MRYWHT98G76F987W", 124);
		c.assignPatientToDoctor("GPPVRD43A34H987O", 124);
		c.assignPatientToDoctor("MRIRSS32J88K987P", 124);
		c.assignPatientToDoctor("BLKSRS11I88F981K", 123);
		c.assignPatientToDoctor("LIIFNG98T54K123A", 123);
		
		Collection<String> byNum = c.countPatientsPerSpecialization();

		byNum.stream().forEach(System.out::println);

		
	}

}
