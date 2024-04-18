# OOP Lab 1 - University

(the Italian version is available in file [README_it.md](README_it.md)).

Design and implement a program to manage university courses, teachers, and students.
All the classes must belong to the package `university`.

## R1. University

The user interface (not part of this assignment) interacts through the class `University`, whose constructor accepts as argument the name of the university.

The name of the university can be retrieved through method `getName()`.

It is possible to assign the name of the Rector by means of the method `setRector()` that accepts as arguments the first and last name of the Rector.

The method `getRector()` returns a string the first and last name of the Rector concatenated and separated by a blank space (`" "`).

## R2. Students

It is possible to enter information concerning a new student thought the method `enroll()` of class `University`, that accepts as arguments first and last name of the student; the method returns the ID number that has been assigned to the student.
ID numbers are are assigned progressively in each university starting from number 10000.

To retrieve the information about a student we can use the method `student()` that accepts as an argument the ID number and returns a string made up of ID, first, and last name separated by blank spaces, e.g. `"10000 Donald Duck"`.

Hints:

- &#9758; You can assume that each university has at most 1000 enrolled students. 

## R3. Courses

To define a new course we can use the method `activate()` that accepts as arguments the title of the course and the name of the responsible teacher. The method returns an integer that corresponds to the course code. Course codes are assigned progressively starting from 10.

To retrieve the information about a course we can use the method `course()` that accepts the course's code and returns a string containing code, title, and teacher separated by commas, e.g., `"10,Object Oriented Programming,James Gosling"`.

Hints:

- &#9758; You can safely assume that any university offers no more than 50 courses.

## R4. Course attendance

Students that wish to attend a course must be registered through the method `register()` that accepts as arguments the ID of the student and the course's code.

To get the list of students attending a course we can use the method `listAttendees()` that accepts the code of the course and returns a string containing the list of attendees.
The students appear one per row (rows are terminated by a new-line character `'\n'`) and each row is formatted as describe in requirement [R2](#r2-students).

Given the ID of a student, it is possible to get the list of courses she is attending through the method `studyPlan()`. The courses are described one per row and formatted as described in requirement [R3](#r3-courses).

Hints:

- &#9758; You can safely assume that no course has more than 100 attendees and that each student can attend no more that 25 distinct courses.

--- 

**Additional requirements** (intended for home practice)

## R5. Exams

Students can take the exams for the courses in which they have been enrolled. The grade for an exam can be recorded through the method `exam()` that accepts as arguments the ID of the student, course code and a grade (integer 0-30).

To get the average grade for a student, the method `studentAvg()` is used. It accepts as argument the ID of the student. If the student took at least one exam it returns a String of the following format `"Student STUDENT_ID : AVG_GRADE"`. Otherwise, it returns `"Student STUDENT_ID hasn't taken any exams"`.

It is possible to get the average grades of all students that took the exam for a given course, method `courseAvg()` accepting as argument the course code can be used for this purpose. The format is the following: "`The average for the course COURSE_TITLE is: COURSE_AVG"`, if at least one student took the exam for that course. If this is not the case, the method should return `"No student has taken the exam in COURSE_TITLE"`.

Hints:

- &#9758; You can safely assume that if a student takes an exam, it has been previously enrolled to the corresponding course. 


## R6. Student award

The university has decided to award the best students for their hard-work and effort. The method `topThreeStudents()` is used to retrieve information for helping the committee to award the price(s).

The students' score is evaluated as the average grade of the exams they've taken. To take into account the number of exams taken and not only the grades, special bonus is assigned on top of the average grade: the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10 is added.

The method returns a string with the information about the three students with the highest score. The students appear one per row (rows are terminated by a new-line character `'\n'`) and each one of them is formatted as: `"STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"`.

Hints:

- &#9758; You can assume that there are no ties. 

## R7. Logger

Add logging of the main operations performed through the `University` facade class. The operations that must be logged are:

- enrolling a new student, with log message like `"New student enrolled: 10000, Mario Rossi"`
- activating a new course, with log message like  `"New course activated: 11, Object Oriented Programming James Gosling"`
- a student signing up for a course, with log message like `"Student 10004 signed up for course 11"`
- a student taking an exam, with log message like `"Student 10001 took an exam in course 12 with grade 27"`

Hints:

- &#9758; to perform logging a logger object of class `java.util.logging.Logger` is available within class [`University`](src/university/University.java);
- &#9758; use the `info()` method of object logger to generate the logging messages;
- &#9758;  please note that by default the messages will be printed to the console. 

---

Version 1.1 - 2024-03-16