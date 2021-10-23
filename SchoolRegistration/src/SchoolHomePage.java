import java.sql.*;
import java.util.Scanner;

public class SchoolHomePage {
	static Scanner userInput = new Scanner(System.in);
	private static String driver = "jdbc:mysql://localhost:3306/students?useSSL=false";
	private static String user = "admin";
	private static String password = "@password";

	public static void main(String[] args) {
		//SchoolMethods schoolMethods = new SchoolMethods();
		int choice;
		boolean end = true;
		boolean admin = false;
		
		try {
			//Connect to the students database via the jdbc: mysql: channel on localhost (this PC)
			Connection connection = DriverManager.getConnection(driver, user, password);
			Statement statement = connection.createStatement();
			
			//loop until the user enters the correct login details or ends program.
			while(!admin) {
				admin = SchoolMethods.login(statement);
				
				if(admin) {
					end = false;
				}
				
				System.out.println("End program?(yes/no)");
				String stop = userInput.next().toLowerCase();
				
				if(stop.equals("yes")) {
					System.out.println("-----Program Ended-----");
					statement.close();
					connection.close();
					break;
				}
			}
			
			//loop until the user ends the program.
			while(!end) {
				System.out.println("Menu: \n"
						+ "1. Add a student \n"
						+ "2. Show students \n"
						+ "3. Update student details \n"
						+ "4. Delete a student \n"
						+ "0. Exit");
				choice = userInput.nextInt();
			
				switch (choice) {
					case 1: 
						SchoolMethods.addStudent(statement);
						break;
						
					case 2:
						SchoolMethods.showStudents(statement);
						break;
						
					case 3:
						SchoolMethods.updateStudent(statement);						
						break;
						
					case 4:
						SchoolMethods.deleteStudent(statement);
						break;

					case 0:
						System.out.println("-----Program Ended-----");
						statement.close();
						connection.close();
						end = true;
						break;
						
					default:
						System.out.println("Invalid entry, make sure to enter the menu options."); 
				}
			}					
		}
		
		catch (SQLException e) {
			System.out.println("Failed to locate database, please ensure that the database is correct and the login details are correct." + e);
		}
	}
}
