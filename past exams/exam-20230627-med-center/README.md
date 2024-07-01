Medical Centre
==============

Write a program to support booking and performing medical checks in medical center.
Classes are located in the `it.polito.med` package; the main class is `MedManager`. The `TestApp` class in the `example` package shows usage examples for the main methods and examples of the requested checks. 
Only implement the requested checks. 
Exceptions in the methods described below are of `MedException` type.

The [JDK documentation](https://oop.polito.it/api/) is located on the local server.

The Italian version of these requirements is available in [README_it.md](README_it.md).


R1: Customers and Doctors
-------------------------

The medical centre manages the work of several doctors, each having a given medical speciality.

The `addSpecialities()` method enables the medical centre to add a set of medical specialities to the list of specialities offered. It can be invoked multiple times, and any duplicates are ignored.

The `getSpecialities()` method retrieves the list of specialities offered by the medical centre. It returns a collection of strings representing the specialities.

The `addDoctor()` method allows the medical centre to add a new doctor along with their speciality. It requires providing the doctor's unique ID, name, surname, and speciality. If there is a duplicate ID or the speciality does not exist, a `MedException` is thrown.


The `getSpecialists()` method retrieves the list of doctors specialised in a specific speciality. It takes the required speciality as input and returns a collection of strings representing the IDs of doctors with that speciality.

The `getDocName()` method retrieves the name of a doctor given their code ID. It takes the doctor's code ID as input and returns a string representing their name.

The `getDocSurame()` method retrieves the surname of a doctor given their code ID. It takes the doctor's code ID as input and returns a string representing their surname.

R2: Schedule
-----------

Doctors can define their schedules for appointments on a daily basis.

The `addDailySchedule()` method allows the medical centre to define a schedule for a doctor on a specific day. Slots are created between the provided start and end hours, with the duration of each slot expressed in minutes. The method takes the doctor's code ID, date, start time, end time, and duration as inputs. It returns the number of slots defined.


The `findSlots()` method retrieves the available slots on a given date for a specific speciality. The method returns a map that contains an entry for each doctor -- for the given speciality -- who has slots scheduled on the date. Each entry in the map contains a list of slots described as strings in the format `"hh:mm-hh:mm"`. For example, the string `"14:00-14:30"` represents a slot starting at 14:00 and lasting 30 minutes, until 14:30. The method takes the date and the required speciality as inputs and returns a map with doctor IDs as keys and corresponding lists of slot strings as values.


R3: Appointments
----------------

It is possible to set appointments with the patients in the scheduled slots.

The `setAppointment()` method allows the system to define an appointment for a patient in an existing slot of a doctor's schedule. It requires providing the patient's SSN, name, surname, the doctor's code ID, the appointment date, and the slot to be booked. The method returns a unique ID for the appointment. If any of the provided code, date, or slot values are invalid, a `MedException` is thrown.


The methods `getAppointmentDoctor()`, `getAppointmentPatient()`, `getAppointmentTime()`, `getAppointmentDate()`,  retrieves the code ID of the doctor, patient SSN, initial time, and date for a specific appointment. It takes the appointment ID.

The `listAppointments()` method retrieves the list of appointments for a specific doctor on a given day. Appointments are reported as strings in the format `"hh:mm=SSN"`, where `"hh:mm"` represents the appointment time and `"SSN"` represents the patient's SSN. It takes the doctor's code ID and the required date as inputs and returns a collection of appointment strings.


R4: Patient Management
--------------------

The `setCurrentDate()` method allows the medical centre to define the current date for patient management operations. This date will be used to accept patients who arrive at the centre, call the next patient and complete an appointment. The method takes the date as input and returns the number of total appointments scheduled for the day as an integer.

The `accept()` method marks a patient as accepted by the medical centre reception. It requires providing the patient's SSN as input.

The `nextAppointment()` method returns the next appointment for a patient who has been accepted by the reception. It retrieves the ID of the earliest appointment where the patient has been accepted and the appointment has not been completed yet. If no such appointment is available, it returns null. The method takes the doctor's code ID as input and returns the appointment ID as a string.

The method `completeAppointment()` marks an appointment as complete. It accepts the doctor id and the appointment id as parameters. It throws an exception in case code or appointment code not valid, or appointment with another doctor or patient not accepted or appointment not for the current day.


R5: Stats
---------

The `showRate()` method computes the show rate for the appointments of a doctor on a given date. The rate is calculated as the ratio of accepted patients over the total number of appointments. It takes the doctor's code ID and the reference date as inputs and returns the no-show rate as a double.

The `scheduleCompleteness()` method computes the schedule completeness for all doctors of the medical centre. The completeness for a doctor is calculated as the ratio of the number of appointments scheduled over the total number of slots in their schedule. The method returns a map that associates each doctor's code ID with their relative completeness.
