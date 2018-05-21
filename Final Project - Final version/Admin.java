import java.util.Scanner;

public class Admin {
	
	View view = new View();
	APlayer player = new APlayer();

			Scanner input = new Scanner(System.in);
			
			public void ask()
			{
				System.out.println("What do you want to do?: ");
				System.out.println("1: View Tables\n2: Create Information\n3: Update Information\n4: Delete Information \n5: EXIT");
				int choice = input.nextInt();
				switch (choice) {
				case 1:
					view.question();
					break;
				case 2:
					create();
					break;
				case 3:
					update();
					break;
				case 4:
					delete();
					break;
				case 5:
					System.exit(5);
					break;
				default:
					System.out.println("Yet to be implemented");
					break;
				}
			}
			
			public void create()
			{
				System.out.println("\nWhat do you want to create?: ");
				System.out.println("1: New Player\n2: RETURN");
				int choice = input.nextInt();
				switch (choice) {
				case 1:
					player.createPlayer();
					break;
				case 2:
					ask();
					break;
				default:
					System.out.println("Yet to be implemented");
					break;
				}
			}
			
			public void update()
			{
				System.out.println("\nWhat do you want to update?: ");
				System.out.println("1: Player Information\n2: RETURN");
				int choice = input.nextInt();
				switch (choice) {
				case 1:
					player.updatePlayer();;
					break;
				case 2:
					ask();
					break;
				default:
					System.out.println("Yet to be implemented");
					break;
				}
				
			}
			
			public void delete()
			{
				System.out.println("\nWhat do you want to delete?: ");
				System.out.println("1: Existing Player\n2: RETURN");
				int choice = input.nextInt();
				switch (choice) {
				case 1:
					player.deletePlayer();
					break;
				case 2:
					ask();
					break;
				default:
					System.out.println("Yet to be implemented");
					break;
				}
			}

}
