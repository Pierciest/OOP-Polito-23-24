package clinic;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map;



/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {

	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
		TreeMap<String, Patient> patients = new TreeMap<>();
		TreeMap<Integer, Doctor> doctors = new TreeMap<>();

		public void addPatient(String first, String last, String ssn) {
	   		Patient curPatient = new Patient(first, last, ssn);
			patients.put(ssn, curPatient);
	 	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		for (Doctor d : doctors.values()){
			if (d.ssn.equals(ssn)) {
				try {
					return getDoctor(d.docID);
				} catch (NoSuchDoctor e) {
					return "No doctor found";
				}
			}
		}
		Patient curPatient = patients.get(ssn);
		if(curPatient == null){
			throw new NoSuchPatient();
		}
		return curPatient.last + " " + curPatient.first + " (" + curPatient.ssn + ")";
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
   		Doctor curDoctor = new Doctor(first,last,ssn,docID,specialization);
		doctors.put(docID, curDoctor);
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		Doctor curDoctor = doctors.get(docID);
		if(curDoctor == null){
			throw new NoSuchDoctor();
		}
		return curDoctor.last + " " + curDoctor.first + " (" + curDoctor.ssn + ")" + " ["+ curDoctor.docID +"]: " + curDoctor.specialization;
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
   		Doctor curDoctor = doctors.get(docID);
		Patient curPatient = patients.get(ssn);
		if(curDoctor == null){
			throw new NoSuchDoctor();
		}

		if(curPatient == null){
			throw new NoSuchPatient();
		}

		curDoctor.assignedPatients.put(ssn, curPatient);
		curPatient.assignedDoctors.put(docID, curDoctor);
	}

	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
		public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
	   		Patient curPatient = patients.get(ssn);

				
			if(curPatient == null){
				throw new NoSuchPatient();
			}

			if(curPatient.assignedDoctors.size() == 0){
				throw new NoSuchDoctor();
			}
			
			Doctor curDoctor = curPatient.assignedDoctors.lastEntry().getValue();

			if(curDoctor == null){
				throw new NoSuchDoctor();
			}

			return curDoctor.docID;
		}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
   		Doctor curDoctor = doctors.get(id);

		if(curDoctor == null){
			throw new NoSuchDoctor();
		}

		
		return curDoctor.assignedPatients.keySet();
	}
	
	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param reader reader linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader) throws IOException {
		char[] array = new char[1000];
		reader.read(array);
		String fileString = new String(array);
		String[] dataSet = fileString.split("\n");
		int correctlyReadLines = 0;
		for (String s : dataSet) {
			try {
				String[] curData = s.split(";");
				if (curData[0].replaceAll(" ", "").equals("P")) {
					if (curData.length != 4){
						continue;
					}
					Patient curPatient = new Patient(curData[1].replaceAll(" ", ""), curData[2].replaceAll(" ", ""), curData[3].replaceAll(" ", ""));
					patients.put(curData[3].replaceAll(" ", ""), curPatient);
					correctlyReadLines++;
				} else if (curData[0].replaceAll(" ", "").equals("M")) {
					if (curData.length != 6){
						continue;
					}
					Doctor curDoctor = new Doctor(curData[2].replaceAll(" ", ""), curData[3].replaceAll(" ", ""), curData[4].replaceAll(" ", ""), Integer.valueOf(curData[1].replaceAll(" ", "")), curData[5].replaceAll(" ", ""));
					doctors.put(Integer.valueOf(curData[1].replaceAll(" ", "")), curDoctor);
					correctlyReadLines++;
				}
			} catch (Exception e) {
				throw new IOException("Error reading line: " + s);
			}
		}
		return correctlyReadLines;
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method calls the
	 * {@link ErrorListener#offending} method passing the line itself,
	 * ignores the row, and skip to the next one.<br>
	 * 
	 * @param reader reader linked to the file to be read
	 * @param listener listener used for wrong line notifications
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader, ErrorListener listener) throws IOException {
		char[] array = new char[1000];
		reader.read(array);
		String fileString = new String(array);
		String[] dataSet = fileString.split("\n");
		int correctlyReadLines = 0;
		for (int i = 0; i < dataSet.length; i++) {
			String s = dataSet[i];
			if (s.isEmpty()) {
                listener.offending(i+1, s);
                continue;
            }
			try {
				String[] curData = s.split(";");
				if (curData[0].replaceAll(" ", "").equals("P")) {
					if (curData.length != 4){
						listener.offending(i + 1, s);
						continue;
					}
					Patient curPatient = new Patient(curData[1].replaceAll(" ", ""), curData[2].replaceAll(" ", ""), curData[3].replaceAll(" ", ""));
					patients.put(curData[3].replaceAll(" ", ""), curPatient);
					correctlyReadLines++;
				} else if (curData[0].replaceAll(" ", "").equals("M")) {
					if (curData.length != 6){
						listener.offending(i + 1, s);
						continue;
					}
					Doctor curDoctor = new Doctor(curData[2].replaceAll(" ", ""), curData[3].replaceAll(" ", ""), curData[4].replaceAll(" ", ""), Integer.valueOf(curData[1].replaceAll(" ", "")), curData[5].replaceAll(" ", ""));
					doctors.put(Integer.valueOf(curData[1].replaceAll(" ", "")), curDoctor);
					correctlyReadLines++;
				}
			} catch (Exception e) {
				listener.offending(i + 1, s);
			}
		}
		return correctlyReadLines;
	}
	
	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
		public Collection<Integer> idleDoctors(){
	   		List<Integer> result = doctors.values().stream()
	   				.filter(d -> d.assignedPatients.isEmpty())
	   				.map(Doctor::getID).sorted(Comparator.comparing(d -> doctors.get(d).last).thenComparing(d-> doctors.get(d).first))
	   				.collect(Collectors.toList());
			return result;	
		}

		/**
		 * Retrieves the collection of doctors having a number of patients larger than the average.
		 * 
		 * @return  the collection of doctors' ids
		 */
		public Collection<Integer> busyDoctors(){
			int patientCount = doctors.values().stream().map(d -> d.assignedPatients.size()).reduce(0, (subtotal, element) -> subtotal + element);
			int average = patientCount/doctors.size();
			List<Integer> result = doctors.values().stream()
				.filter(d -> d.assignedPatients.size() > average)
				.map(Doctor::getID)
				.collect(Collectors.toList());
 			return result;
		}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients() {
        return doctors.values().stream()
            .sorted(Comparator.comparingInt((Doctor d) -> d.getPatientSize()).reversed())
            .map(d -> String.format("%3d : %d %s %s", d.getPatientSize(), d.docID, d.last, d.first))
            .collect(Collectors.toList());
    }
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
		public Collection<String> countPatientsPerSpecialization() {
			return doctors.values().stream()
				.collect(Collectors.groupingBy(
					Doctor::getSpeciality,
					Collectors.summingInt(Doctor::getPatientSize)
				))
				.entrySet().stream()
				.filter(entry -> entry.getValue() != 0)
				.sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(entry -> entry.getValue()).reversed())
				.map(entry -> String.format("%3d - %s", entry.getValue(), entry.getKey()))
				.collect(Collectors.toList());
		}
}
