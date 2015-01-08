/* Title: Course.java
 * Abstract: A course object holds all of the courses data
 * like course's number, title, id and classroom location.
 * Every getter method returns class objects similar to primative
 * type but prevents privacy leaks.
 */
import java.util.ArrayList;

public class Course {
	private int courseNumber = 0;
	private String courseTitle;
	private int id;
	private String courseClassroom;
	
	// Course class methods (including accessors and mutators)

	public Course(int courseNum, String courseTitle, int instructorNum, String classroom) {
		this.courseNumber = courseNum;
		this.courseTitle = courseTitle;
		this.id = instructorNum;
		this.courseClassroom = classroom;
	}

	public int getCourseNumber() {
		return new Integer(courseNumber);
	}
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getCourseTitle() {
		return new String(courseTitle);
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getCourseClassroom() {
		return new String(courseClassroom);
	}
	public void setCourseClassroom(String courseClassroom) {
		this.courseClassroom = courseClassroom;
	}
	public int getInstructorID(){
		return new Integer(id);
	}
	public void setInstructorID(int id) {
		this.id = id;
	}
	public void updateLocation(String newLocation) {
		this.courseClassroom = newLocation;
	}

	@Override
	public String toString() {
		return "Course: " + courseNumber + ", courseTitle: "
				+ courseTitle + ", id: " + id + ", courseClassroom: "
				+ courseClassroom;
	}
	
	
	
}
