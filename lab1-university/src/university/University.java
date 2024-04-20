package university;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	String name;
	String rectorName;
	String rectorLastName;
	Student studentDB[] = new Student[1000];
	Course courseDB[] = new Course[50];
// R1
	/**
	 * Constructor
	 * @param name name of the university
	 */

	public class Student {
		String studentName;
		String studentLastName;
		int sid;
		Course takenCourses[];
		StudentExam studentExams[];
		double avg;

		Student(String studentName, String studentLastName, int sid){
			this.studentName = studentName;
			this.studentLastName = studentLastName;
			this.sid = sid;
			this.takenCourses = new Course[25];
			this.studentExams = new StudentExam[25];
			this.avg = 0.0;

		}
	}

	public class Course {
		String courseName;
		String profName;
		int cid;
		Student attendees[];
		CourseExam courseExams[];
		double avg;

		Course(String courseName, String profName, int cid){
			this.courseName = courseName;
			this.profName = profName;
			this.cid = cid;
			this.attendees = new Student[100];
			this.courseExams = new CourseExam[100];
			this.avg = 0.0;

		}
	}

	public class StudentExam{
		Course course;
		int grade;

		StudentExam(Course course, int grade){
			this.course = course;
			this.grade = grade;
		}
	}
	
	public class CourseExam{
		Student student;
		int grade;

		CourseExam(Student student, int grade){
			this.student = student;
			this.grade = grade;
		}
	}

	public <T> int findIndex(T[] dataBase) {
		int index = 0;
		for (int i = 0; i < dataBase.length; i++) {
			if (dataBase[i] != null) {
				continue;
			}
			index = i;
			break;
		}
		return index;
	}

	public void courseAverge(int courseId) {
		Course curCourse = courseDB[courseId-10];
		int takenExamCount = findIndex(curCourse.courseExams);
		int sum = 0;
		int avg;


		for(CourseExam exam: curCourse.courseExams){
			if(exam != null){
				sum += exam.grade;
			}
		}

		if(takenExamCount == 0){
			avg = 0;
		}

		avg = sum/takenExamCount;
		courseDB[courseId-10].avg = avg;
	}

	public void studentAverage(int studentId) {
		Student curStudent = studentDB[studentId-10000];
		int takenExamCount = findIndex(curStudent.studentExams);
		int sum = 0;
		double avg;


		for(StudentExam exam: curStudent.studentExams){
			if(exam != null){
				sum += exam.grade;
			}
		}

		if(takenExamCount == 0){
			avg = 0;
		}
		avg = sum/takenExamCount;
		studentDB[studentId-10000].avg = avg;
	}

	public University(String name){
		this.name = name;
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first first name of the rector
	 * @param last	last name of the rector
	 */
	public void setRector(String first, String last){
		this.rectorName = first;
		this.rectorLastName = last;
	}
	
	/**
	 * Retrieves the rector of the university with the format "First Last"
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		return this.rectorName + " " + this.rectorLastName;
	}
	
// R2
	/**
	 * Enrol a student in the university
	 * The university assigns ID numbers 
	 * progressively from number 10000.
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		int emptyIndex = findIndex(studentDB);
		int sid = emptyIndex+10000;
		Student curStudent = new Student(first,last,sid);
		studentDB[emptyIndex] = curStudent;
		logger.info("New student enrolled: " + sid + ", " + curStudent.studentName + " " + curStudent.studentLastName);
		return sid;

	}
	
	/**
	 * Retrieves the information for a given student.
	 * The university assigns IDs progressively starting from 10000
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		return id + " " + studentDB[id-10000].studentName + " " + studentDB[id-10000].studentLastName;
	}
	
// R3
	/**
	 * Activates a new course with the given teacher
	 * Course codes are assigned progressively starting from 10.
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		int emptyIndex = findIndex(courseDB);
		int cid = emptyIndex+10;
		Course curCourse = new Course(title, teacher, cid);
		courseDB[emptyIndex] = curCourse;
		logger.info("New course activated: " + courseDB[emptyIndex].cid + ", " + courseDB[emptyIndex].courseName + " " + courseDB[emptyIndex].profName);
		return cid;
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		Course curCourse = courseDB[code-10];
		return code + "," + curCourse.courseName + "," + curCourse.profName;
	}
	
// R4
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		Student curStudent = studentDB[studentID - 10000];
		Course curCourse = courseDB[courseCode - 10];

		int emptyIndexCourseList = findIndex(curStudent.takenCourses);
		int emptyIndexAttendees = findIndex(curCourse.attendees);


		curStudent.takenCourses[emptyIndexCourseList] = curCourse;
		curCourse.attendees[emptyIndexAttendees] = curStudent;
		logger.info("Student " + studentID + " signed up for the course " + courseCode);
	}
	
	/**
	 * Retrieve a list of attendees.
	 * 
	 * The students appear one per row (rows end with `'\n'`) 
	 * and each row is formatted as describe in in method {@link #student}
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		String attendeeList = "";
		Student attendees[] = courseDB[courseCode-10].attendees;
		for(Student curStudent: attendees){
			if(curStudent != null){
				attendeeList += student(curStudent.sid) + "\n";
			}
		}
		return attendeeList;
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		String studyPlanString = "";
		Course studyPlanList[] = studentDB[studentID-10000].takenCourses;
		for(Course curCourse: studyPlanList){
			if(curCourse != null){
				studyPlanString += course(curCourse.cid) + "\n";
			}
		}
		return studyPlanString;
	}

// R5
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		Course curCourse = courseDB[courseID-10];
		Student curStudent = studentDB[studentId-10000];
		int courseExamEmptyIndex = findIndex(curCourse.courseExams);
		int studentExamEmptyIndex = findIndex(curStudent.studentExams);

		StudentExam curStudentExam = new StudentExam(curCourse, grade);
		CourseExam curCourseExam = new CourseExam(curStudent, grade);

		logger.info("Student" + studentId + " took an exam in course " + courseID + " with grade " + grade);

		courseDB[courseID-10].courseExams[courseExamEmptyIndex] = curCourseExam;
		studentDB[studentId-10000].studentExams[studentExamEmptyIndex] = curStudentExam;

		studentAverage(studentId);
		courseAverge(courseID);
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		double avg = studentDB[studentId-10000].avg;

		if(avg == 0.0){
			return "Student " + studentId + " hasn't taken any exams";
		}

		return 	"Student " + studentId + " : " + avg;
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */



	public String courseAvg(int courseId) {
		double avg = courseDB[courseId-10].avg;
		return 	"The average for the course " + courseDB[courseId-10].courseName + "is: " + avg;

	}
	

// R6
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info on the best three students.
	 */
	public double calculateScore(Student student){
		double score;
		double avg = student.avg;
		
		int enrolled = findIndex(student.takenCourses);
		int exams = findIndex(student.studentExams);

		if(exams == 0){
			return 0.0;
		}

		score = avg + (exams/enrolled)*10;

		return score;
	}
	public String topThreeStudents() {
		int count = 0;
		for(Student s: studentDB){
			if(s != null && findIndex(s.studentExams) > 0 && count <= 3){
				count++;
			}
		}
		PriorityQueue<Student> topStudents = new PriorityQueue<>(3, (a, b) -> Double.compare(calculateScore(a), calculateScore(b)));

		for (Student curStudent : studentDB) {
			if (curStudent != null) {
				topStudents.add(curStudent);
				if (topStudents.size() > count) {
					topStudents.poll();
				}
			}
		}

		Stack<Student> stack = new Stack<>();
		while (!topStudents.isEmpty()) {
			stack.push(topStudents.poll());
		}

		StringBuilder topStudentsString = new StringBuilder();
		while (!stack.isEmpty()) {
			Student student = stack.pop();
			topStudentsString.append(student.studentName).append(" ")
							.append(student.studentLastName).append(" : ")
							.append(String.format("%.2f", calculateScore(student))).append("\n");
		}
		return topStudentsString.toString();
	}


// R7
    /**
     * This field points to the logger for the class that can be used
     * throughout the methods to log the activities.
     */
    public static final Logger logger = Logger.getLogger("University");

}
