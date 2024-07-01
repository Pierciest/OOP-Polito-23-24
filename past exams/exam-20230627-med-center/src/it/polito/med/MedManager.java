package it.polito.med;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MedManager {

	ArrayList<String> specialities = new ArrayList();
	Map<String,Doctor> doctors = new HashMap<>();
	Map<String,List<String>> appointments = new HashMap<>();
	static String curDate = "";
	/**
	 * add a set of medical specialities to the list of specialities
	 * offered by the med centre.
	 * Method can be invoked multiple times.
	 * Possible duplicates are ignored.
	 * 
	 * @param specialities the specialities
	 */
	public void addSpecialities(String ... specialities) {
		this.specialities.addAll(Arrays.asList(specialities));
	}

	/**
	 * retrieves the list of specialities offered in the med centre
	 * 
	 * @return list of specialities
	 */
	public Collection<String> getSpecialities() {
		return specialities.stream().distinct().toList();
	}
	
	
	/**
	 * adds a new doctor with the list of their specialities
	 * 
	 * @param id		unique id of doctor
	 * @param name		name of doctor
	 * @param surname	surname of doctor
	 * @param speciality speciality of the doctor
	 * @throws MedException in case of duplicate id or non-existing speciality
	 */
	public void addDoctor(String id, String name, String surname, String speciality) throws MedException {
		Doctor checkDoctor = doctors.get(id);
		if(checkDoctor != null || ! specialities.contains(speciality)){
			throw new MedException();
		}


		Doctor curDoctor = new Doctor(id, name, surname, speciality);
		doctors.put(id, curDoctor);
	}

	/**
	 * retrieves the list of doctors with the given speciality
	 * 
	 * @param speciality required speciality
	 * @return the list of doctor ids
	 */
	public Collection<String> getSpecialists(String speciality) {
		Collection<String> result = doctors.values().stream().filter(d->d.speciality.equals(speciality)).map(d->d.id).toList();
		return result;
	}

	/**
	 * retrieves the name of the doctor with the given code
	 * 
	 * @param code code id of the doctor 
	 * @return the name
	 */
	public String getDocName(String code) {
		return doctors.get(code).getName();
	}

	/**
	 * retrieves the surname of the doctor with the given code
	 * 
	 * @param code code id of the doctor 
	 * @return the surname
	 */
	public String getDocSurname(String code) {
		return doctors.get(code).getSurname();
	}

	/**
	 * Define a schedule for a doctor on a given day.
	 * Slots are created between start and end hours with a 
	 * duration expressed in minutes.
	 * 
	 * @param code	doctor id code
	 * @param date	date of schedule
	 * @param start	start time
	 * @param end	end time
	 * @param duration duration in minutes
	 * @return the number of slots defined
	 */
	public int addDailySchedule(String code, String date, String start, String end, int duration) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime startTime = LocalTime.parse(start,timeFormatter);
		LocalTime endTime = LocalTime.parse(end,timeFormatter);
        long totalTimeMinutes = endTime.toSecondOfDay() / 60 - startTime.toSecondOfDay() / 60;
        int availableSlots = (int) totalTimeMinutes / duration;

		LocalTime currentTime = startTime;
        Map<String, Patient> dailySchedule = new HashMap<>();

		for (int i = 0; i < availableSlots; i++) {
            LocalTime nextTime = currentTime.plusMinutes(duration);
            String timeInterval = currentTime.format(timeFormatter) + "-" + nextTime.format(timeFormatter);
            dailySchedule.put(timeInterval, null);
            currentTime = nextTime;
        }

		Doctor curDoctor = doctors.get(code);
		curDoctor.schedule.put(date, dailySchedule);

		return (int) availableSlots;
	}

	/**
	 * retrieves the available slots available on a given date for a speciality.
	 * The returned map contains an entry for each doctor that has slots scheduled on the date.
	 * The map contains a list of slots described as strings with the format "hh:mm-hh:mm",
	 * e.g. "14:00-14:30" describes a slot starting at 14:00 and lasting 30 minutes.
	 * 
	 * @param date			date to look for
	 * @param speciality	required speciality
	 * @return a map doc-id -> list of slots in the schedule
	 */
	public Map<String, List<String>> findSlots(String date, String speciality) {
		Map<String, List<String>> result = new HashMap<>();
		List<Doctor> curDoctors = getSpecialists(speciality).stream().map(id->doctors.get(id)).toList();
		for(Doctor d : curDoctors){
			List<String> freeTimes = new LinkedList<>();
			Map<String,Patient> curSchedule = d.getDailySchedule(date);
			if(curSchedule == null){
				continue;
			}
			for(Map.Entry<String,Patient> entry: curSchedule.entrySet()){
				if(entry.getValue() == null){
					freeTimes.add(entry.getKey());
				}
			}
			result.put(d.getId(), freeTimes);
		}
		return result;
	}

	/**
	 * Define an appointment for a patient in an existing slot of a doctor's schedule
	 * 
	 * @param ssn		ssn of the patient
	 * @param name		name of the patient
	 * @param surname	surname of the patient
	 * @param code		code id of the doctor
	 * @param date		date of the appointment
	 * @param slot		slot to be booked
	 * @return a unique id for the appointment
	 * @throws MedException	in case of invalid code, date or slot
	 */
	public String setAppointment(String ssn, String name, String surname, String code, String date, String slot) throws MedException {
		Patient curPatient = new Patient(ssn, name, surname);
		Doctor curDoctor = doctors.get(code);
		if(curPatient == null || curDoctor == null || ! curDoctor.getDailySchedule(date).keySet().contains(slot))
		{
			throw new MedException();
		}
		
		curDoctor.getDailySchedule(date).put(slot, curPatient);

		String appointmentCode = code+";"+ssn+";"+slot.split("-")[0]+";"+date;
		List<String> curAppointments = curDoctor.appointments.get(date);
		List<String> emptyList = new LinkedList<>();
		emptyList.add(appointmentCode); 
		if(curAppointments == null){
			curDoctor.appointments.put(date, emptyList);
		}
		else{
			curDoctor.appointments.get(date).add(appointmentCode);
		}
		return appointmentCode;
	}

	/**s
	 * retrieves the doctor for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return doctor code id
	 */
	public String getAppointmentDoctor(String idAppointment) {
		return idAppointment.split(";")[0];
	}

	/**
	 * retrieves the patient for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return doctor patient ssn
	 */
	public String getAppointmentPatient(String idAppointment) {
		return idAppointment.split(";")[1];
	}

	/**
	 * retrieves the time for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return time of appointment
	 */
	public String getAppointmentTime(String idAppointment) {
		return idAppointment.split(";")[2].split("-")[0];
	}

	/**
	 * retrieves the date for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return date
	 */
	public String getAppointmentDate(String idAppointment) {
		return idAppointment.split(";")[3];
	}

	/**
	 * retrieves the list of a doctor appointments for a given day.
	 * Appointments are reported as string with the format
	 * "hh:mm=SSN"
	 * 
	 * @param code doctor id
	 * @param date date required
	 * @return list of appointments
	 */
	public Collection<String> listAppointments(String code, String date) {
		Doctor curDoctor = doctors.get(code);
		if(curDoctor.appointments.get(date) == null){
			return null;
		}
		return curDoctor.appointments.get(date).stream().map(c->(getAppointmentTime(c)+"="+getAppointmentPatient(c))).toList();
	}

	/**
	 * Define the current date for the medical centre
	 * The date will be used to accept patients arriving at the centre.
	 * 
	 * @param date	current date
	 * @return the number of total appointments for the day
	 */
	public int setCurrentDate(String date) {
		curDate = date;
		int count = 0;
		List<String> emptyList = new LinkedList<>();
		for (Doctor d : doctors.values()) {
			d.completed.put(date, emptyList);
			Collection<String> appointments = listAppointments(d.id, date);
			if (appointments != null) {
				count += appointments.size();
			}
		}
		return count;
	}

	/**
	 * mark the patient as accepted by the med centre reception
	 * 
	 * @param ssn SSN of the patient
	 */
	public void accept(String ssn) {
		for(Doctor d : doctors.values()){
			if(listAppointments(d.id, curDate) == null){
				continue;
			}
			for(String s : listAppointments(d.id, curDate)){
				String curSsn = s.split("=")[1];
				String curTime = s.split("=")[0];
				if(curSsn == null){
					continue;
				}
				if(curSsn.equals(ssn)){
					List<String> acceptedList = new ArrayList<>(Arrays.asList(curTime, curSsn, d.id));
					d.accepted.push(acceptedList);
				}
			}
		}
	}

	/**
	 * returns the next appointment of a patient that has been accepted.
	 * Returns the id of the earliest appointment whose patient has been
	 * accepted and the appointment not completed yet.
	 * Returns null if no such appointment is available.
	 * 
	 * @param code	code id of the doctor
	 * @return appointment id
	 */
	public String nextAppointment(String code) {
		Doctor curDoctor = doctors.get(code);
		curDoctor.accepted.sort(Comparator.comparing(list -> {
			String timeString = list.get(0);
			// Convert time string to LocalTime
			LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
			return time;
		}));
		if(curDoctor.accepted.isEmpty()){
			return null;
		}
		else{
			List<String> appointmentInf = curDoctor.accepted.pop();
			return appointmentInf.get(2)+";"+appointmentInf.get(1)+";"+appointmentInf.get(0)+";"+curDate;
		}
	}

	/**
	 * mark an appointment as complete.
	 * The appointment must be with the doctor with the given code
	 * the patient must have been accepted
	 * 
	 * @param code		doctor code id
	 * @param appId		appointment id
	 * @throws MedException in case code or appointment code not valid,
	 * 						or appointment with another doctor
	 * 						or patient not accepted
	 * 						or appointment not for the current day
	 */
	public void completeAppointment(String code, String appId)  throws MedException {
		Doctor curDoctor = doctors.get(code);
		List<String> dailyAppointments = curDoctor.appointments.get(curDate);
		if(!dailyAppointments.contains(appId)){
			throw new MedException();
		}
		curDoctor.appointments.get(curDate).remove(appId);
		curDoctor.completed.get(curDate).add(appId);
	}

	/**
	 * computes the show rate for the appointments of a doctor on a given date.
	 * The rate is the ratio of accepted patients over the number of appointments
	 *  
	 * @param code		doctor id
	 * @param date		reference date
	 * @return	no show rate
	 */
	public double showRate(String code, String date) {
		Doctor curDoctor = doctors.get(code);
		int remainingCount = curDoctor.appointments.get(date).size();
		int completedCount = curDoctor.completed.get(date).size();
		return (double) completedCount / (remainingCount + completedCount);
	}

	/**
	 * computes the schedule completeness for all doctors of the med centre.
	 * The completeness for a doctor is the ratio of the number of appointments
	 * over the number of slots in the schedule.
	 * The result is a map that associates to each doctor id the relative completeness
	 * 
	 * @return the map id : completeness
	 */
	public Map<String, Double> scheduleCompleteness() {
		Map<String, Double> completenessMap = new HashMap<>();
		for(Doctor d : doctors.values()){
			int totalSchedule = (int) d.schedule.values().stream()
				.flatMap(c -> c.keySet().stream())
				.count();
			long appointments = d.appointments.values().stream()
				.flatMap((Collection<String> c) -> c.stream())
				.count();
			long completed = d.completed.values().stream().flatMap((Collection<String> c) -> c.stream()).count();
			double completeness = (double) (appointments+completed) / totalSchedule;
			completenessMap.put(d.id, completeness);
		}
		return completenessMap;
	}


	
}
