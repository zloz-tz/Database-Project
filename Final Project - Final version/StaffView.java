import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StaffView {
	
	// Create a variable for the connection string.
			static final String connectionUrl = "jdbc:sqlserver://ICSSQL-1:1433;"
					+ "databaseName=AdventureWorksS18;user=StudentS18;password=Booisfat10";

			String connectionUrlIntegrated = "jdbc:sqlserver://ICSSQL-1:1433;"
					+ " databaseName=AdventureWorks; integratedSecurity=true;";
			// Declare the JDBC objects.
			static Connection con = null;
			static Statement stmt = null;
			static ResultSet rs = null;
			
			public void displayStaff()
			{
				try
				{
					// Establish the connection.  
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			        con = DriverManager.getConnection(connectionUrl);  
			        
			     // Create and execute an SQL statement that returns some data.
			        String queryCheck = "SELECT FName, LName, S.Role, T.Tname"
			        		+ " FROM GBSports.Staff AS S, GBSports.Person AS P, GBSports.Team AS T WHERE "
			        		+ "S.StaffID=P.ID And T.Tnumber= S.Team_Num "
			        		+ "ORDER BY T.Tname";
			        		 
			        		
					stmt = con.createStatement();
					rs = stmt.executeQuery(queryCheck);	
					System.out.println("Staff List:\n");
						while(rs.next())
						{
							System.out.println("Name: " + rs.getString(1) + " " + rs.getString(2) + " | Role: " + rs.getString(3)
									+ " | Team: " + rs.getString(4));
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
