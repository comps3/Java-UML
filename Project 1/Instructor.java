/* Title: Instructor.java
 * Abstract: A instructor object holds all of the instructor data
 * which includes instructor number, name, email, and phone.
 * Every getter method returns class objects similar to primative
 * type but prevents privacy leaks.
 */

public class Instructor {
	private int instructorCount = 0;
	private int instructorNum = 0;
	private String instructorName;
	private String instructorEmail;
	private String instructorPhone;
	
    // Instructor class accessors and mutators

	public Instructor(Instructor copy)
	{
		copy.instructorCount = this.instructorCount;
		copy.instructorName = this.instructorName;
		copy.instructorEmail = this.instructorEmail; 
		copy.instructorPhone = this.instructorPhone;
	}
	
	public Instructor(int instructorCount)
	{
		this.instructorCount = instructorCount;
	}
	
	public Instructor(int num, String name, String email, String phoneNum)
	{
		this.instructorNum = num;
		this.instructorName = name;
		this.instructorEmail = email;
		this.instructorPhone = phoneNum;
	}
	
	public int getInstructorCount() {
		return new Integer(instructorCount);
	}
	public void setInstructorCount(int instructorCount) {
		this.instructorCount = instructorCount;
	}
	public int getInstructorNum() {
		return new Integer(instructorNum);
	}
	public void setInstructorNum(int instructorNum) {
		this.instructorNum = instructorNum;
	}
	public String getInstructorName() {
		return new String(instructorName);
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getInstructorEmail() {
		return new String(instructorEmail);
	}
	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}
	public String getInstructorPhone() {
		return new String(instructorPhone);
	}
	public void setInstructorPhone(String instructorPhone) {
		this.instructorPhone = instructorPhone;
	}

	@Override
	public String toString() {
		return " InstructorNum: " + instructorNum + ", instructorName: "
				+ instructorName + ", instructorEmail: " + instructorEmail
				+ ", instructorPhone: " + instructorPhone;
	}
	
}
