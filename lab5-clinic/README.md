# OOP Lab 5 - Medical Clinic

Italian version of requirements available here: [README_it.md](README_it.md).

Implement a system to manage patients of a medical clinic. The classes are hosted in package `clinic`.

The system must meet the following requirements.


## R1: Patients

The main class of the program is class `Clinic`.

Patients are characterized by the first name, the last name, and the unique social security number (SSN). New patients can be added to the system by means of method `addPatient()`.

The information about a patient can be retrieved by means of method `getPatient()` that, given an SSN number, returns a string with the format `"<Last> <First> (<SSN>)"`. If the patient does not exist, exception `NoSuchPatient` is thrown.


## R2: Doctors

Doctors are characterized by the first name, last name, SSN, unique badge ID, and the specialization (e.g. `"cardiologist"`, `"dentist"`, etc.). New doctors can be added to the system by means of method `addDoctor()`.

Method `getDoctor()`, given a badge ID, returns a string with the following format: `"<Last> <First> (<SSN>) [<ID>]: <Specialization>"`.

If the doctor does not exist, exception `NoSuchDoctor` is thrown.

Keep in mind that doctors can be patients of the same clinic they work in.


## R3: Patient registration

When accepted, a patient is assigned to one of the clinic doctors. To this aim, method `assignPatientToDoctor()` is provided. Patient is identified by means of their SSN, and doctor is identified by means of her badge ID. If the doctor does not exist, exception `NoSuchDoctor` is thrown. Further, if the patient does not exist, exception `NoSuchPatient` is thrown.
If `assignPatientToDoctor()` is called multiple times for the same patient, then the latest assigned doctor only is considered.

By means of method `getAssignedDoctor()` that accepts a patient SSN, it is possible to obtain the ID of the Doctor assigned to that person. If the patient does not exist a `NoSuchPatient` exception is thrown. If there is no doctor assigned to the patient a `NoSuchDoctor` is thrown.

The method `getAssignedPatients()` accepts a doctor ID and returns the list of all patients SSN of that doctor. If there is no doctor with the given ID a `NoSuchDoctor` exception is thrown.


## R4: Loading

The method `loadData()` of class `Clinic` accepts a `Reader` parameter and loads through it the information about patients and doctors.
The text file is organized by rows; each row contains info about either a patient or a doctor.

Rows containing a patient's info begin with letter `"P"` followed by first name, last name, and SSN. Rows containing doctor's info start with letter `"M"`, followed by badge ID, first name, last name, SSN, and specialization.
The elements on a line are separated by the `';'` character, possibly surrounded by spaces that should be ignored.

In case of error in the data present on a given row, the method should be able to ignore the row and skip to the next one.
In case of IO errors the method propagates an `IOException` to the caller.

The method returns the number of correctly processed lines.

An additional version of the method `loadData()` accepts a second argument of type `ErrorListener`. Such an interface defines the methods `offendingLine()` that accepts an integer and a string. During the processing of a file, every time a wrong line is encountered -- which should be discarded anyway -- the method `loadData()` must invoke `offendingLine()` on the listener passing the line number (starting at 1) and the line contents.


## R5: Statistics

The method `idleDoctors()` returns the collection of doctors that have no patient at all, sorted in alphabetic order (last name and then first name).

The method `busyDoctors()` returns the collection of doctors that are assigned a number of patients larger than the average.

The method `doctorsByNumPatients()` returns a list of strings containing the name of the doctor, and the relative number of patients, sorted by decreasing number.
The string must be formatted as `"### : ID SURNAME NAME"` where ### represent the number of patients (printed on three characters).

The method `countPatientsPerSpecialization()` computes the number of patients per (their doctor's) specialization. The elements are sorted first by decreasing count and then by alphabetic specialization.
The strings are structured as `"### - SPECIALITY"` where ### represent the number of patients (printed on three characters). Specialization with no patients must be omitted.


Hint:

* Formatting can be performed using the `String.format()` method.
  E.g. `String.format("%2d", i)` prints integer variable `i` on two characters.