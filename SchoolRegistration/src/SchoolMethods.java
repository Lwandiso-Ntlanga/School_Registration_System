import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SchoolMethods {
	static Scanner userInput = new Scanner(System.in);
	static ResultSet results;
	static int studentID, grade, rowsAffected, option;
	static String subjects, studentName;	
	static String stdSubjects, stdName, stdGrade;
	
	/**
	 * Method validates the user's login details, if true it returns true to grant access to the rest of the program.
	 * @param statement
	 * @throws InputMismatchException
	 * @throws SQLException
	 */
	public static boolean login(Statement statement) {
		try {
			System.out.println("username: ");
			String username = userInput.nextLine();
			
			System.out.println("password: ");
			String password = userInput.nextLine();
			
			results = statement.executeQuery("SELECT * FROM teachers WHERE username = " + username + "AND password = " + password);	
			if (results.next()) {
				return true;
			}
			
		}
		
		catch(InputMismatchException e) {
			System.out.println("Invalid entry.");
		}
		
		catch (SQLException e) {
			System.out.println("Incorrect username/password.");
		}
		return false;
	}
	
	/**
	 * Method let's the user add a student to the database.
	 * @param statement
	 * @throws SQLException
	 * @throws InputMismatchException
	 */
	public static void  addStudent(Statement statement) {
		try {	
			//increments the student id automatically from the last entry
			results = statement.executeQuery("SELECT MAX(id ) FROM students");
			results.next();			
			studentID = results.getInt(1) + 1;
			
			System.out.println("Enter the full name of the student: ");
			studentName = userInput.nextLine();
			stdName = "'"+ studentName +"'";
			
			System.out.println("Enter 2 subjects for the student(e.g English, Maths): ");
			subjects = userInput.nextLine();
			stdSubjects = "'"+ subjects +"'";
			
			System.out.println("Enter the grade of the student: ");
			grade = userInput.nextInt();
			stdGrade = "'"+ grade +"'";
			
			//adds the new student to the table of students.
			rowsAffected = statement.executeUpdate("INSERT INTO students VALUES(" + studentID + "," + stdName + "," + stdSubjects + "," + stdGrade +")");
			System.out.println("Query complete, " + rowsAffected + " student added");
		
		} 
		catch (SQLException e) {
			System.out.println("Failed to add student. \n" + e);
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid entry.");
		}
	}
	
	/**
	 * Method displays all students in the students database.
	 * @param statement
	 * @throws SQLException
	 */
	public static void showStudents(Statement statement) {
		try {
			results = statement.executeQuery("SELECT * FROM students");	
			while (results.next()) {
				System.out.println(results.getInt("id") + ", " + results.getString("Name") + ", " +results.getString("Subjects") + ", " +results.getInt("Grade"));
			}
		} 
		catch (SQLException e) {
			System.out.println("Failed to show students. \n" + e);
		}
	}
	
	/**
	 * Method updates a student's details in the database using the student id to identity them.
	 * @param statement
	 */
	public static void updateStudent(Statement statement) {
		System.out.println("Option to update: \n"
				+ "1. Subjects \n"
				+ "2. Student's name \n"
				+ "3. Grade \n"
				+ "0. Back");
		option = userInput.nextInt();
		
		//this is so when the user wants to return to the menu they don't enter the student id unnecessarily.
		if (option != 0) {
			System.out.println("Enter the student id for the student's details to be updated: ");
			studentID = userInput.nextInt();
			userInput.nextLine();
		}
		//allows the user to update the student's subjects, name and grade.
		switch (option) {
			case 1:
				System.out.println("Enter the student's full name: ");
				studentName = userInput.nextLine();
				stdName = "'"+ studentName +"'";
				
				try {
					rowsAffected = statement.executeUpdate("UPDATE students SET Name = " + stdName + " WHERE id = " + studentID);
					System.out.println("Query complete, " + rowsAffected + " student details updated.");
				} 
				catch (SQLException e) {
					System.out.println("Failed to update student details. \n" + e);
				}
				break;
				
			case 2:
				System.out.println("Enter 2 subjects for the student(e.g English, Maths): ");
				subjects = userInput.nextLine();
				stdSubjects = "'"+ subjects +"'";
				
				try {
					rowsAffected = statement.executeUpdate("UPDATE students SET Subjects = " + stdSubjects + " WHERE id = " + studentID);
					System.out.println("Query complete, " + rowsAffected + " student details updated.");
				} 
				catch (SQLException e) {
					System.out.println("Failed to update student details. \n" + e);
				}				
				break;
				
			case 3:
				System.out.println("Enter the student's grade: ");
				grade = userInput.nextInt();
				stdGrade = "'"+ grade +"'";
				
				try {
					rowsAffected = statement.executeUpdate("UPDATE students SET Grade = " + stdGrade + " WHERE id = " + studentID);
					System.out.println("Query complete, " + rowsAffected + " student details updated.");
				} 
				catch (SQLException e) {
					System.out.println("Failed to update student details. \n" + e);
				}
				break;
				
			case 0:
				System.out.println("back...");
				break;
				
			default:
				System.out.println("Invalid option entered.");
		}
	}
	
	/**
	 * Method deletes the a student from the database that the user selected, using the student id.
	 * @param statement
	 * @throws SQLException
	 */
	public static void deleteStudent(Statement statement) {
		System.out.println("Enter the student id for the student to be deleted: ");
		studentID = userInput.nextInt();
		
		try {
			rowsAffected = statement.executeUpdate("DELETE FROM students WHERE id = " + studentID);
			System.out.println("Query complete, " + rowsAffected + " student deleted.");
		} 
		catch (SQLException e) {
			System.out.println("Failed to delete student. \n" + e);
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid student id entry.");
		};
		
	}
	
}
 