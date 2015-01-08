/* Title: Student.java
 * Abstract: A student object holds all of the data for a student
 * which includes student's id, name, enrolled course, final score and letter grade.
 * Every getter method returns class objects similar to primative
 * type but prevents privacy leaks.
 * Author: Brian Huynh
 * ID: 7878
 * Date: 10/24/2014
 */

public class Student {
	private int studentID = 0;
	private String studentName;
	private int studentEnrolledCourse = 0;
	private double studentFinalScore = 0.0;
	private char studentLetterGrade;
	
	// Student classes accessor and mutator methods

	public Student(int studentId2, String studentName2, int classAttend,
			double classAvg, char grade) {
		
		this.studentID = studentId2;
		this.studentName = studentName2;
		this.studentEnrolledCourse = classAttend;
		this.studentFinalScore = classAvg;
		this.studentLetterGrade = grade;
		
	}
	public int getStudentID() {
		return new Integer(studentID);
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return new String(studentName);
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentEnrolledCourse() {
		return new Integer(studentEnrolledCourse);
	}
	public void setStudentEnrolledCourse(int studentEnrolledCourse) {
		this.studentEnrolledCourse = studentEnrolledCourse;
	}
	public double getStudentFinalScore() {
		return new Double(studentFinalScore);
	}
	public void setStudentFinalScore(double studentFinalScore) {
		this.studentFinalScore = studentFinalScore;
	}
	public char getStudentLetterGrade() {
		return new Character(studentLetterGrade);
	}
	public void setStudentLetterGrade(char studentLetterGrade) {
		this.studentLetterGrade = studentLetterGrade;
	}
	@Override
	public String toString() {
		return "Student: " + studentID + ", studentName: "
				+ studentName + ", studentEnrolledCourse: "
				+ studentEnrolledCourse + ", studentFinalScore: "
				+ studentFinalScore + ", studentLetterGrade: "
				+ studentLetterGrade;
	}
	
	
	
	
}
