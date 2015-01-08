/* Title: School.java
 * Abstract: School object is able to open and parse text files.
 * It can also display all of the school's data like the instructor's 
 * information, course information and the student's information.
 * Courses and Instructors can also be added.  The instructors, courses, and students
 * are stored in three array list.  Students can be removed from the students 
 * array with the graduate function.
 * Author: Brian Huynh
 * ID: 7878
 * Date: 10/24/2014
 */

import java.util.ArrayList;


import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class School {
	
	private String schoolName;
	private ArrayList<Instructor> schoolInstructor = new ArrayList<Instructor>();
	private ArrayList<Course> schoolCourses = new ArrayList<Course>();
	private ArrayList<Student> schoolStudents = new ArrayList<Student>();
	
	public School(String schoolName)
	{
		this.setSchoolName(schoolName);
	}
	
	public void readData(String file)
	{
		// Begin initialization of file reading components

		 FileReader fin = null;
		 
		 // Global use variables
		 int sections = 0;
		 int records = 0;
		 int i = 0;
		 
		 // 1st Section
		 int classNum = 0;
		 String instructorName = null;
		 String instructorEmail = null;
		 String instructorPhone = null;
		 
		 // 2nd Section
		 int classNum2 = 0;
		 String className = null;
		 int classId = 0;
		 String classLocation = null;
		 
		 // 3rd section
		 int studentId = 0;
		 String studentName = null;
		 int classAttend = 0;
		 double classAvg = 0.0;
		 char grade;
		
		// End of initialization

		try {
			fin = new FileReader(file);
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		Scanner src = new Scanner(fin);
		src.useDelimiter(",");
		

  while(src.hasNext()) {
	
	// Text file required a specific method to be parsed properly

	if(sections == 0) {
		if(i == 0) {
			records = Integer.parseInt(src.nextLine());
			i++;
		}
		else if (i < records + 1) {
			classNum = src.nextInt();
			instructorName = src.next();
			instructorEmail = src.next();
			instructorPhone = src.nextLine();
			instructorPhone = instructorPhone.replace(",", "");
			Instructor newGuy = new Instructor(classNum, instructorName, instructorEmail, instructorPhone);
			
			schoolInstructor.add(newGuy);
			i++;
		}
		else {
			sections++;
			i = 0;
		}

	   }
	
		else if(sections == 1) {
			
			if(i == 0) {
				records = Integer.parseInt(src.nextLine());
				i++;
			}
			else if (i < records + 1) {
				
				classNum2 = src.nextInt();
				className = src.next();
				classId = src.nextInt();
				classLocation = src.nextLine();
				classLocation = classLocation.replace(",", "");
				Course newCourse = new Course(classNum2, className, classId, classLocation);
				schoolCourses.add(newCourse);
				i++;
			}
			else {
				sections++;
				i = 0;
			}
			
		}

	
		else if(sections == 2) {
			
			if(i == 0) {
				records = Integer.parseInt(src.nextLine());
				i++;
			}
			else if (i < records + 1) {
				studentId = src.nextInt();
				studentName = src.next();
				classAttend = src.nextInt();
				classAvg = src.nextDouble();
				grade = src.nextLine().charAt(1);
				Student newStudent = new Student(studentId, studentName, classAttend, classAvg, grade);
				schoolStudents.add(newStudent);
				i++;
			}
			else {
				sections++;
				i = 0;
			}
			
		}
	
	  }

	}

	// Prints information stored on an instance of 'school'
	public void schoolInfo() {
		
		System.out.println("School name: " + schoolName);
		System.out.println("Instructor Information");
		
		int i = 0;
		int j = 0;
		
		while(i != schoolInstructor.size())
		{
			System.out.println("   	" + schoolInstructor.get(i).getInstructorName());
			i++;
		}

		System.out.println();
		System.out.println("Course Information");
		i = 0;
		
		while(i != schoolCourses.size()) {
			System.out.println("   	" + schoolCourses.get(i).getCourseTitle());
			i++;
		}
		
		System.out.println();
		System.out.println("Student Information");
		i = 0;
		
		while(i != schoolStudents.size()) {
			Student easyAccess = schoolStudents.get(i);
			System.out.print("   	" + easyAccess.getStudentName() + ": " );
			
			j = 0;
			
			while(j != schoolCourses.size()) {
				if(easyAccess.getStudentEnrolledCourse() == schoolCourses.get(j).getCourseNumber()) {
					System.out.print(schoolCourses.get(j).getCourseTitle());
					break;
				}
				j++;
				
			}
			i++;
			System.out.println();
		}
		
	}
	
	public void addInstructor(int idNum, String name, String email, String phone) 
	{
		int i = 0;
		
		// Checks if the instructor already exist in array
		while(i != schoolInstructor.size()) {
			
			if(schoolInstructor.get(i).getInstructorNum() == idNum)
			{
				System.out.println("Instructor addition failed - Duplicate instructor id.");
				return;
			}
			if(schoolInstructor.get(i).getInstructorName().equals(name))
			{
				System.out.println("Instructor addition failed - Duplicate instructor name.");
				return;
			}
			if(schoolInstructor.get(i).getInstructorEmail().equals(email))
			{
				System.out.println("Instructor addition failed - Duplicate instructor email.");
				return;
			}
			if(schoolInstructor.get(i).getInstructorPhone().equals(phone))
			{
				System.out.println("Instructor addition failed - Duplicate instructor phone.");
				return;
			}
			i++;
		}
		
		Instructor newPerson = new Instructor(idNum, name, email, phone);
		schoolInstructor.add(newPerson);
	}
	
	public void addCourse(int classNum, String n, int instructorNum, String location)
	{
		int i = 0;
		// Checks if a course already exist 
		while(i != schoolCourses.size())
		{
			if(schoolCourses.get(i).getCourseNumber() == classNum) 
			{
				System.out.println("Course addition failed - Duplicated course number.");
				return;
			}
			if(schoolCourses.get(i).getCourseTitle().equals(n)) 
			{
				System.out.println("Course addition failed - Duplicated course title.");
				return;
			}
			i++;
		}
		
		Course newCourse = new Course(classNum, n, instructorNum, location);
		schoolCourses.add(newCourse);
	}

	public String getSchoolName() {
		return new String(schoolName);
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public Course getCourse(int courseId)
	{
		int i = 0;
		
		while(i != schoolCourses.size()) {
			if(schoolCourses.get(i).getCourseNumber() == courseId)
			{
				return schoolCourses.get(i);
			}
			i++;
		}
		
		System.out.println("Course ID: " + courseId + " was not found.");
		return null;
	}

	public void courseInfo() {
		
		System.out.println("Number of Courses: " + schoolCourses.size());
		int i = 0;
		int j = 0;
		int count = 0;
		int currentCourse = 0;
		int studentCourse = 0;
		while(i != schoolCourses.size()) {
			
			currentCourse = schoolCourses.get(i).getCourseNumber();
			System.out.print("    " + currentCourse + ": ");
			
			while(j != schoolStudents.size()) {
				
				studentCourse = schoolStudents.get(j).getStudentEnrolledCourse();
				
				if(studentCourse == currentCourse) {
					count++;
				}
				j++;
			}
			
			System.out.println(count + " enrolled");
			count = 0;
			j = 0;
			i++;
			
		}
		
	}
	
	public void courseInfo(int courseNum) 
	{
		int i = 0;
		int j = 0;
		int k = 0;
		
		double courseAvg = 0;
		int count = 0;
		
		boolean found = false;
		
		while(i != schoolCourses.size()) {
			
			if(schoolCourses.get(i).getCourseNumber() == courseNum)
			{
				found = true;
				Course easyInfo = schoolCourses.get(i);
				System.out.println("Course number: " + easyInfo.getCourseNumber());
				System.out.print("Instructor: ");
				j = 0;
				
				while(j != schoolInstructor.size()) {
					if(schoolInstructor.get(j).getInstructorNum() == easyInfo.getInstructorID())
					{
						System.out.println(schoolInstructor.get(j).getInstructorName() + " ");
						break;
					}
					j++;
				}
				
				System.out.println("Course Title: " + easyInfo.getCourseTitle());
				System.out.println("Room: " + easyInfo.getCourseClassroom());
				System.out.println("Enrolled Students: ");
				while(k != schoolStudents.size()) {
					if(schoolStudents.get(k).getStudentEnrolledCourse() == easyInfo.getCourseNumber())
					{
						Student easyStudent = schoolStudents.get(k);
						System.out.print("   " + easyStudent.getStudentName() + ": ");
						System.out.printf("%.2f",easyStudent.getStudentFinalScore());
						System.out.println(" (" + easyStudent.getStudentLetterGrade() + ") ");
						count++;
						courseAvg += easyStudent.getStudentFinalScore();
					}
					k++;
					
				}
				
				System.out.printf("Course Average: %.2f", courseAvg / count);
				System.out.println();
				break;
				
			}
			i++;
		}
		
		if(found == false)
		{
			System.out.println("Course " + courseNum + " does not exist.");
		}
	}
	
	public void deleteCourse(int course) 
	{
			int i = 0;
			int j = 0;
			boolean found = false;
			boolean courseFound = false;
			
			// Deletes a course if it exist in the array

			while(i != schoolCourses.size())
			{
				if(schoolCourses.get(i).getCourseNumber() == course)
				{
					courseFound = true;
					
					// Check if students are enrolled in that course.
					
					while(j != schoolStudents.size()) {
						
						if(schoolStudents.get(j).getStudentEnrolledCourse() == course) {
							found = true;
							break;
						}
						j++;
					}
					if(found == false) {
						schoolCourses.remove(i);
						break;
					}
				}
				i++;
			}
			
			if(courseFound == false)
			{
				System.out.println("Course " + course + " could not be deleted");
			}
		
	}

	public void addStudent(int i, String string, int j, double d, char string2) {
		
		int k = 0;
		
		while(k != schoolStudents.size())
		{
			// Edge case: The user enters the correct name but incorrect Student ID Number
			if(schoolStudents.get(k).getStudentName().equals(string) && schoolStudents.get(k).getStudentID() != i)
			{
				System.out.println("Student " + string + "already has Student ID " + schoolStudents.get(k).getStudentID());
				return;
			}
			
			// Edge case: The user enters a name that already uses that Student ID Number
			if(!schoolStudents.get(k).getStudentName().equals(string) && schoolStudents.get(k).getStudentID() == i)
			{
				System.out.println("This Student ID does not to " + string);
				return;
			}
			// Edge case: The user is already enrolled in a certain course
			if(schoolStudents.get(k).getStudentName().equals(string) && schoolStudents.get(k).getStudentID() == i &&
			   schoolStudents.get(k).getStudentEnrolledCourse() == j)
			{
				System.out.println("Student: " + string + " is already enrolled in " + j);
				return;
			}
			k++;
			
		}
		
		Student newGuy = new Student(i , string, j, d, string2);
		schoolStudents.add(newGuy);
		
	}
	
	public void studentInfo(int id)
	{
		int i = 0;
		int j = 0;
		double avg = 0.0;
		int count = 0;
		boolean found = false;
		
		System.out.println("Student Number: " + id);
		
		while(i != schoolStudents.size())
		{
			if(schoolStudents.get(i).getStudentID() == id)
			{
				found = true;
				Student play = schoolStudents.get(i);
				
				System.out.println("Name: " + play.getStudentName());
				System.out.println("Courses Enrolled: ");
				
				while(j != schoolStudents.size()) 
				{
					if(schoolStudents.get(j).getStudentID() == id)
					{
						System.out.println("    " + schoolStudents.get(j).getStudentEnrolledCourse() + ": " + 
						schoolStudents.get(j).getStudentFinalScore() + "  (" + schoolStudents.get(j).getStudentLetterGrade() + ")" );
						avg += schoolStudents.get(j).getStudentFinalScore();
						count++;
					}
					j++;
					
				}
				System.out.printf("Course Average: %.2f", avg / count);
				System.out.println();
				break;
			}
			i++;
		}
		
		if(found == false) {
			System.out.println("Not exist!");
		}
	}
	
	public void graduateStudent(int success)
	{
		int i = 0;
		boolean found = false;
		
		// Removes a student from the student array
		while(i != schoolStudents.size())
		{
			if(schoolStudents.get(i).getStudentID() == success) {
				schoolStudents.remove(i);
				found = true;
				i = 0;
			}
			i++;
			
		}
		
		if(found == false)
		{
			System.out.println("Could not graduate student, ID number was not found");
		}
		
	}
	
	
	
	
}
