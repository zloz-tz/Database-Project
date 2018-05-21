import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class APlayer {
	
	
	View view = new View();
	
	// Create a variable for the connection string.
	static final String connectionUrl = "jdbc:sqlserver://ICSSQL-1:1433;"
			+ "databaseName=AdventureWorksS18;user=StudentS18;password=Booisfat10";

	String connectionUrlIntegrated = "jdbc:sqlserver://ICSSQL-1:1433;"
			+ " databaseName=AdventureWorks; integratedSecurity=true;";
	
	// Declare the JDBC objects.
	static Connection con = null;
	static Statement stmt = null;
	static Statement stmt2 = null;
	static ResultSet rs = null;
	static ResultSet rs2 = null;
	
	Scanner input = new Scanner(System.in);
	
	

	// Create player method
	public void createPlayer()
	{
		try
		{
			// Establish the connection.  
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	        con = DriverManager.getConnection(connectionUrl);  
	        
	        
	        //Create a Person in person table
	        System.out.println("\nEnter PERSON Information First separated by commas: \nBY THIS ORDER:"
	    			+ "'ID', 'First Name', 'Last Name', 'Address', 'DOB': ");
	        System.out.println("\nEXAMPLE: '300000000', 'Micahael', 'Dapaah', '4401 Skraaa ave, Green Bay, WI 54311' , '1991-10-11'");
			String person = input.nextLine();

			String personCheck = "Insert INTO GBSports.Person (ID, FName, LName, Address, DOB)"
					+ " VALUES ("+ person +")";
			
			stmt = con.createStatement();
			
			boolean gotResults = stmt.execute(personCheck);
			if(!gotResults)
			{
				System.out.println("New Person Created");
				
			} else 
				{
				   rs = stmt.getResultSet();
				}
			
			
			// Create a Player in the Player table
			System.out.println("\nEnter PLAYER Information separated by commas: \nBY THIS ORDER:"
	    			+ "'Position', 'Player number', 'Weight', 'Height', 'Class', 'Major', 'ID', 'TeamNumber': ");
			System.out.println("\nEXAMPLE: 'Center Fielder', '90', '185', '5''9', 'Freshman', 'Nutrition', '300000000', '1'");
			String player = input.nextLine();
			
			
			String playerCheck = "Insert INTO GBSports.PLAYER(Position, Pnumber, Weight, Height, Class, Major, PlayerID, TeamNumber)"
					+ " VALUES ("+ player +")";
			
			
			boolean gotResults2 = stmt.execute(playerCheck);
			if(!gotResults)
			{
				System.out.println("New Player Created");
				
			} else 
				{
				   rs = stmt.getResultSet();
				}
			

			/// To create a player in the PLAYS_FOR TABLE 
			System.out.println("\nAdding a new player to the team requries re-entering information for security. Enter Player ID now: ");
			String replayerID = input.nextLine();
			
			System.out.println("Now, re-enter the player's team number: ");
			String replayerTeamNum = input.nextLine();
			
			
			String replayerCheck = "Insert INTO GBSports.PLAYS_FOR(Player_Start_date, PID, Tnum)"
					+ " VALUES (CURRENT_TIMESTAMP,'"+ replayerID +"','"+ replayerTeamNum +"')";
					
			
			boolean gotResults3 = stmt.execute(replayerCheck);
			if(!gotResults3)
			{
				System.out.println("New Player has been added to a team. Thank you.");
				System.out.println("To view updated team List, enter 1 or to create another player, enter 2.");
				int choice = input.nextInt();
				switch(choice)
				{
					case 1:
						view.Players();
						break;
					case 2:
						createPlayer();
						break;
					default:
						System.out.println("Yet to be implemented");
						break;
				}
			} else 
				{
				   rs = stmt.getResultSet();
				}
			
		}
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

	
	//Update Player Method
	public void updatePlayer()
	{
		    System.out.println("For updating player's personal info enter 1, for updating player's "
		        + "stats enter 2 : ");
		    int adminChoice = input.nextInt();
			
			// Gets what the administrator wants to update Person(1)/Player(2) table
			switch(adminChoice) 
			{
				case 1:
					updatePersonInfo();
					break;
					
				case 2:
					updatePlayerInfo();
					break;
					
				default:
					System.out.println("Yet to be implemented");
					break;
					
			}
	}
	
	public void updatePersonInfo()
	{
		Scanner input = new Scanner(System.in);
		
		 try
			{
				// Establish the connection.  
			    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			    con = DriverManager.getConnection(connectionUrl);

			    // Gets the PlayerID of player & specific column & value to update
			    System.out.println("Enter the ID of the player you want to update: ");
				String personID = input.nextLine();
			    
				System.out.println("Type what option you would like to change:\n"
				    	+ "ID / FName / LName / Address / DOB");
				    String personColumn = input.nextLine();
				    
				    System.out.println("Enter the value you want to insert: ");
					String updValue = input.nextLine();
				    
				    String personCheck = "Update GBSports.Person Set "
						+ ""+ personColumn +" = "  + "'"+ updValue +"' WHERE "
							+ "ID = " + personID + "";
				    
				    stmt = con.createStatement();
					
					boolean gotResults2 = stmt.execute(personCheck);
					if(!gotResults2)
					{
						System.out.println("Person updated");
						
					} 
					else 
					{
						rs = stmt.getResultSet();
					}
			}
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
	
	public void updatePlayerInfo()
	{
		Scanner input = new Scanner(System.in);
		
		 try
			{
				// Establish the connection.  
			    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			    con = DriverManager.getConnection(connectionUrl);

				 // Gets the ID of person and specific column to update
				    System.out.println("Enter the playerID of the player you want to update: ");
					String playerID = input.nextLine();
			    
			    System.out.println("Type what option you would like to change:\n"
				    	+ "Position / Pnumber / Weight / Height / Class / Major ");
				    String playerColumn = input.nextLine();
				    
				    System.out.println("Enter the value you want to insert: ");
					String playerValue = input.nextLine();
				    
				    String playerCheck = "Update GBSports.Player Set "
						+ ""+ playerColumn +" = "  + "'"+ playerValue +"' WHERE "
							+ "playerID = " + playerID + "";
				    
				    stmt = con.createStatement();
					//rs = stmt.executeQuery(personCheck);
				
					boolean gotResults = stmt.execute(playerCheck);
					if(!gotResults)
					{
						System.out.println("Player updated");
						System.out.println("To view updated team List, enter 1 or to update another player, enter 2.");
						int choice = input.nextInt();
						switch(choice)
						{
							case 1:
								view.Players();
								break;
							case 2:
								updatePlayer();
								break;
							default:
								System.out.println("Yet to be implemented");
								break;
						}
						
					} 
					else 
					{
						rs = stmt.getResultSet();
					}
			}
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
	
	
	// Delete player method
	public void deletePlayer()
	{
		try
		{
			// Establish the connection.  
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	        con = DriverManager.getConnection(connectionUrl);  
	        
	        
	        //Ask for Person id
			System.out.println("Enter Player ID: "
	    			+ "'ID': ");
			String id = input.nextLine();
			
			String personDel = "DELETE from GBSports.Person WHERE"
					+ " ID = '" + id +"'";

			stmt = con.createStatement();
			
			boolean gotResults = stmt.execute(personDel);
			if(!gotResults)
			{
				System.out.println("Player Deleted");
				System.out.println("To view updated team List, enter 1 or to delete another player, enter 2.");
				int choice = input.nextInt();
				switch(choice)
				{
					case 1:
						view.Players();
						break;
					case 2:
						deletePlayer();
						break;
					default:
						System.out.println("Yet to be implemented");
						break;
				}
			} else 
				{
				   rs = stmt.getResultSet();
				}

		}
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
