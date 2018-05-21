import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class View 
{
	
	StaffView sView = new StaffView();
	MatchesView mView = new MatchesView();
	
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
	public void question()
	{
		System.out.println("What Table to do want to view?: ");
		System.out.println("1: Players\n2: Staff\n3: Matches\n4: EXIT");
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			Players();
			break;
		case 2:
			sView.displayStaff();
			break;
		case 3:
			mView.displayMatches();
			break;
		case 4:
			System.exit(4);;
			break;
		default:
			System.out.println("Yet to be implemented");
			break;
		}
	}
	
	public void Players()
	{
		//Scanner input = new Scanner(System.in);
		System.out.println("What team are you looking for?: ");
		System.out.println("1: Soccer\n2: Basketball\n3: Football\n4: RETURN");
		int choice1 = input.nextInt();
		switch (choice1) {
		
		case 1:
			
			String name = "GB Soccer";
			SelectTeam(name);
			
			System.out.println("\nTo view player's full Bio, type Last Name");
			
			Scanner input = new Scanner(System.in);
			String Spname = input.nextLine();
			SelectPlayer(Spname);
			System.out.println("To return to teams list press 1, to exit press 2");
			int choice2 = input.nextInt();
			switch(choice2)
			{
			case 1: Players(); break;  case 2: System.exit(2); break;
			default:
				System.out.println("Yet to be implemented");
				break;  
				}
			
			break;
		case 2:
			String name1 = "GB  Basketball";
			SelectTeam(name1);
			
			System.out.println("\nTo view player's full Bio, type Last Name");
			
			Scanner input1 = new Scanner(System.in);
			String Bpname = input1.nextLine();
			SelectPlayer(Bpname);
			System.out.println("To return to teams list press 1, to exit press 2");
			int choice3 = input1.nextInt();
			switch(choice3)
			{
			case 1: Players(); break;  case 2: System.exit(0); break;
			default:
				System.out.println("Yet to be implemented");
				break;  
				}
			break;
		case 3:
			String name2 = "GB Football";
			SelectTeam(name2);
			
			System.out.println("\nTo view player's full Bio, type Last Name");
			
			Scanner input2 = new Scanner(System.in);
			String Fpname = input2.nextLine();
			SelectPlayer(Fpname);
			System.out.println("To return to teams list press 1, to exit press 2");
			int choice4 = input2.nextInt();
			switch(choice4)
			{
			case 1: Players(); break;  case 2: System.exit(0); break;
			default:
				System.out.println("Yet to be implemented");
				break;  
				}
			break;
		case 4:
			question();
			break;
		default:
			System.out.println("Yet to be implemented");
			break;
		}
		
	}
	
	
	
	public void SelectTeam(String TeamName)
	{
		try
		{
			// Establish the connection.  
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	        con = DriverManager.getConnection(connectionUrl);  
	        
	     // Create and execute an SQL statement that returns some data.
	        String queryCheck = "SELECT FName, LName, T.Tname"
	        		+ " FROM GBSports.Person AS P, GBSports.Player AS PL, GBSports.PLAYS_FOR AS F, GBSports.Team AS T WHERE "
	        		+ "P.ID=PL.PlayerID AND PL.PlayerID=F.PID AND T.Tnumber=F.Tnum AND T.Tname LIKE '%" + TeamName + "%'";
	        		 
	        		
			stmt = con.createStatement();
			rs = stmt.executeQuery(queryCheck);	
			System.out.println(TeamName +  " MEN'S TEAM Players:\n");
				while(rs.next())
				{
					System.out.println("Name: " + rs.getString(1) + "  " + rs.getString(2) + " | Team: " + rs.getString(3));
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
		
	
	public void SelectPlayer(String PlayerName)
	{
		try
		{
			// Establish the connection.  
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	        con = DriverManager.getConnection(connectionUrl);  
	        
	     // Create and execute an SQL statement that returns some data.
	        String queryCheck = "SELECT P.FName,P.LName, PL.Position, PL.Pnumber, PL.Class, PL.Height, PL.Weight, PL.Major"
	        		+ " FROM GBSports.Person AS P, GBSports.Player AS PL WHERE "
	        		+ "P.ID=PL.PlayerID AND P.LName LIKE '%" + PlayerName + "%'";
	        //System.out.println(queryCheck); 
	        		
			stmt = con.createStatement();
			rs = stmt.executeQuery(queryCheck);	
			System.out.println("\n" + PlayerName +  "'s BIO:\n");
			
				while(rs.next())
				{
					System.out.println("Name: " + rs.getString(1) + "  " + rs.getString(2) + " | Position: " + rs.getString(3)+ " | Number: " + rs.getString(4)
							+ "| Class: " + rs.getString(5)+ " | Height: " + rs.getString(6)+ " | Weight: " + rs.getString(7));
					
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

