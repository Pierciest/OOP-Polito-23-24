package university;
import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

// R1
	String UniName;
	String RectorName;
	Student[] students = new Student[1000];
	Course[] courses = new Course[50]; 
	int nextId = 10000;
	int nextCourseCode = 10;
	TakenExams[] emptyExamList = new TakenExams[100];
	double avg = 0.0;
	int examCount = 0;
	int actualCourseCount = 0;





	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.UniName = name;
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		return UniName;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first first name of the rector
	 * @param last	last name of the rector
	 */
	public void setRector(String first, String last){
		this.RectorName = first + " " + last;
	}
	
	/**
	 * Retrieves the rector of the university with the format "First Last"
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		//TODO: to be implemented
		return RectorName;
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

	 public class Student {
		String Name;
		String LastName;
		int id;
		Course[] courseList;
		TakenExams[] examList;
		double avg;
		int examCount;
		int actualCourseCount;

		public Student(String Name, String LastName, int id,Course[] courseList, TakenExams[] examList, double avg, int examCount, int actualCourseCount){
			this.Name = Name;
			this.LastName = LastName;
			this.id = id;
			this.courseList = courseList;
			this.examList = examList;
			this.avg = avg;
			this.examCount = examCount;
			this.actualCourseCount = actualCourseCount;
		}

		
	 }
	public int enroll(String first, String last){
		Course[] emptyCourseList = new Course[100];
		Student s = new Student(first,last,nextId,emptyCourseList,emptyExamList,avg,examCount,actualCourseCount);
		students[nextId-10000] = s;
		nextId++;
		logger.info("New student enrolled: " + s.id + ", " + s.Name + " " + s.LastName);
		return s.id;
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
		int index = id - 10000;
        if (index < 0 || index >= students.length || students[index] == null) {
            return "Student not found"; // Basic error handling
        }
        Student s = students[index];
        return id + " " + s.Name + " " + s.LastName;
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
	public class Course {
		String title;
		String teacher;
		int courseCode;
		Student[] StudentList;
		TakenExams[] grades;
		public Course(String title, String teacher, int courseCode, Student[] StudentList, TakenExams[] grades){
			this.title = title;
			this.teacher = teacher;
			this.courseCode = courseCode;
			this.StudentList = StudentList;
			this.grades = grades;
		}
	}

	public int activate(String title, String teacher){
		Student[] emptyStudentList = new Student[100];

		Course c = new Course(title, teacher,nextCourseCode,emptyStudentList, emptyExamList);
		courses[nextCourseCode-10] = c;
		nextCourseCode++;
		logger.info("New course activated: " + c.courseCode + ", " + c.title + " " + c.teacher);
		return c.courseCode;
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
		int index = code-10;
		Course c = courses[index];
		return c.courseCode + "," + c.title + c.teacher;
	}
	
// R4
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		Course c = courses[courseCode-10];
		Student s = students[studentID-10000];

		for(int i = 0; i < 100; i++){
			if(c.StudentList[i] == null){
				c.StudentList[i] = s;
				break;
			}
		}
		for(int j = 0; j < 100; j++){
			if(s.courseList[j] == null){
				s.courseList[j] = c;
				s.actualCourseCount++;
				break;
			}
		}
		logger.info("Student " + s.id + " signed up for course " + c.courseCode);

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
		String attendees = "";
		Course c = courses[courseCode-10];

			for(int j=0;j<100;j++){
				if(c.StudentList[j] != null){
					attendees = attendees + c.StudentList[j].id + " " + c.StudentList[j].Name + " " + c.StudentList[j].LastName + "\n";
				}
			}
		return attendees;
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
		String courses = "";
		Student s = students[studentID-10000];
			for(int j=0;j<100;j++){
				if(s.courseList[j] != null){
					courses = courses + s.courseList[j].courseCode + "," + s.courseList[j].title + "," + s.courseList[j].teacher + "\n";
			}
		}
		return courses;
	}

// R5
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */

	public class TakenExams {
		int courseID;
		int grade;

		public TakenExams(int courseID, int grade){
			this.courseID = courseID;
			this.grade = grade;
		}
	
		
	}

	public void exam(int studentId, int courseID, int grade) {
		Student s = students[studentId-10000];
		Course c = courses[courseID-10];

		for(int i=0;i<s.examList.length;i++){
			if(s.examList[i] == null){
				s.examList[i] = new TakenExams(courseID,grade);
				s.examCount++;
				s.avg = (s.avg+grade)/s.examCount;
				logger.info("Student " + s.id + " took an exam in course " + c.courseCode + " with grade " + grade);

				break;
			}
		}
		for(int i=0;i<c.grades.length;i++){
			if(c.grades[i] == null){
				s.examList[i] = new TakenExams(courseID,grade);
				break;
			}
		}
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
		Student s = students[studentId-10000];
		int sum = 0;
		int count = 0;
		for(int i = 0; i<s.examList.length; i++){
			if(s.examList[i] != null){
				sum+= s.examList[i].grade;
				count++;
			}
		}
		if(count>0){
			double avg = (double) sum / count;
			avg = Math.ceil(avg);
			return "Student " + s.id + " : " + String.format("%.1f", avg);
		}
		else{
			return "Student " + s.id + " hasn't taken any exams";
		}
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
		Course c = courses[courseId-10];
		int sum = 0;
		int count = 0;
		for(int i = 0; i<c.grades.length; i++){
			if(c.grades[i] != null){
				sum+= c.grades[i].grade;
				count++;
			}
		}
		if(count>0){
			double avg = (double) sum / count;
			avg = Math.ceil(avg);
			return "The average for the course "+ c.title + " is: " + String.format("%.1f", avg);
		}
		else{
			return "No student has taken the exam in " + c.title;
		}
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

	 public String topThreeStudents() {
		Student[] topStudents = new Student[3];
		double[] topScores = new double[3];
		double score;
		double bonus;

	
		for (Student s : students) {
			score = 0;
			bonus = 0;


        	if (s == null) continue;
			
			
	
			if (s.actualCourseCount == 0) continue; // Skip if no courses
	
			if (s.examCount == 0) continue; // Skip if no exams taken
	
			bonus = (s.examCount / s.actualCourseCount) * 10; // Calculate bonus with actual course count
			score = s.avg + bonus; // Final score with bonus
	
			for (int i = 0; i < 3; i++) {
				if (topStudents[i] == null || score > topScores[i]) {
					for (int j = 2; j > i; j--) {
						topStudents[j] = topStudents[j - 1];
						topScores[j] = topScores[j - 1];
					}
					topStudents[i] = s;
					topScores[i] = score;
					break;
				}
			}
		}
	
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			if (topStudents[i] != null) {
				result.append(topStudents[i].Name).append(" ")
					  .append(topStudents[i].LastName).append(" : ")
					  .append(String.format("%.1f", topScores[i])).append("\n");
			}
		}
	
		return result.toString();
	}
	
	

// R7
    /**
     * This field points to the logger for the class that can be used
     * throughout the methods to log the activities.
     */
    public static final Logger logger = Logger.getLogger("University");

}
