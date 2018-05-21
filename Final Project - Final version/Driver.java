
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Driver {
	
	static Admin admin = new Admin();
	
	// Create a variable for the connection string.
	static final String connectionUrl = "jdbc:sqlserver://ICSSQL-1:1433;"
			+ "databaseName=AdventureWorksS18;user=StudentS18;password=Booisfat10";

	String connectionUrlIntegrated = "jdbc:sqlserver://ICSSQL-1:1433;"
			+ " databaseName=AdventureWorks; integratedSecurity=true;";
	// Declare the JDBC objects.
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	
	
	
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		View view = new View();
		System.out.println("What kind of user are you?: ");
		System.out.println("1: Administrator\n2: End-User\n3: EXIT");
		int choice = input.nextInt();
			switch (choice) {
			case 1:
				login();
				break;
			case 2:
				view.question();
				break;
			case 3:
				System.exit(0);
				break;
			default:
					System.out.println("Yet to be implemented");
				break;
			}

	}

	static void login()
	{
		Scanner input = new Scanner(System.in);
		try
		{
			// Establish the connection.  
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	        con = DriverManager.getConnection(connectionUrl);  
	        
	    	System.out.println("Enter your username: ");
			String user = input.nextLine();
			
			System.out.println("Enter your password: ");
			String password = input.nextLine();
			
			// Create and execute an SQL statement that returns some data.
			String queryCheck = "SELECT Username, Password FROM GBSports.Staff WHERE "
								+ "Username = '" + user + "' AND Password = '" + password + "'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(queryCheck);
			
					if(rs.next())
					{
						System.out.println("Access granted\n");
					    admin.ask();
					}
					else
					{
						System.out.println("No record found. Wrong username or password. Try Again");
					    login();
					
					}
		}
	      // Handle any errors that may have occurred.  
	      catch (Exception e) 
		{  
	         e.printStackTrace();  
	      }  
	      finally 
	      {  
	         if (rs != null) try { rs.close(); } catch(Exception e) {}  
	         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
	         if (con != null) try { con.close(); } catch(Exception e) {}  
	      }	 
		

			
	
	}

}