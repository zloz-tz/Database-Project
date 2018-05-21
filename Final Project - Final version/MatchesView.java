import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MatchesView {
	
	// Create a variable for the connection string.
				static final String connectionUrl = "jdbc:sqlserver://ICSSQL-1:1433;"
						+ "databaseName=AdventureWorksS18;user=StudentS18;password=Booisfat10";

				String connectionUrlIntegrated = "jdbc:sqlserver://ICSSQL-1:1433;"
						+ " databaseName=AdventureWorks; integratedSecurity=true;";
				// Declare the JDBC objects.
				static Connection con = null;
				static Statement stmt = null;
				static ResultSet rs = null;
				
				Scanner input = new Scanner(System.in);
				
				public void displayMatches()
				{
					System.out.println("What team are you looking for?: ");
					System.out.println("1: Soccer\n2: Basketball\n3: Football");
					int choice = input.nextInt();
					
					switch (choice) {
					
					case 1:
						String name = "GB Soccer";
						teamMatches(name);
						break;
						
					case 2:
						String name1 = "GB  Basketball";
					teamMatches(name1);
					break;
					
					case 3: 
						String name2 = "GB Football";
						teamMatches(name2);
						break;
					}	
			}
				public void teamMatches(String teamName){
					
					try
					{
						// Establish the connection.  
				        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				        con = DriverManager.getConnection(connectionUrl);  
				        
				     // Create and execute an SQL statement that returns some data.
				        String queryCheck = "SELECT T.Tname,Opponent, Location, Result, Date"
				        		+ " FROM GBSports.Team AS T, GBSports.Matches AS M WHERE "
				        		+ "T.Tnumber=M.T_Num AND T.Tname LIKE '%" + teamName +"%' ";
				        		 
				        		
						stmt = con.createStatement();
						rs = stmt.executeQuery(queryCheck);	
						System.out.println("Staff List:\n");
							while(rs.next())
							{
								System.out.println("Team: " + rs.getString(1) + " | Opponent: " + rs.getString(2) + " | Location: " + rs.getString(3)
										+ " | Result: " + rs.getString(4)+ " | Date: " + rs.getString(5));
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
