import java.util.Scanner;

public class Minesweeper {

	private static MineField field;
	private static Ranking rank;	
	
	//initializes the ranking, prints out how to play and starts the game
	public static void main(String[] args) {
		rank = new Ranking();
		mainMessage();
		while(gameCountinue());
		System.out.println("\nThank you for playing :) Have a nice day!");
	}	
	
	//plays game, checks input for move or commands
	private static boolean gameCountinue() {
		field = new MineField();
		int result = 0;
		while (true) {
			field.show();
			System.out.print("\nPlease enter your move(row col): ");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();

			//if input is top, it shows the ranking and continues game
			if (input.equals("top")) {
				rank.show();
				continue;
			}
			//if input is restart, it ads the current result to the ranking and starts a new game
			else if (input.equals("restart")) {
				rank.recordName(result);
				return true;
			}
			//if input is exit, it ads the current result to the ranking and ends the game
			else if (input.equals("exit")) {
				rank.recordName(result);
				return false;
			}
			//if the input is a legal move it ads 1 to the number of turns
			//and if the number of turns is 35, the field is cleared and it prints you won
			else if (field.legalMoveString(input)) {
				result++;
				if (result == 35) {
					System.out.println("Congratulations you WON the game!");
					{
						rank.recordName(result);
						{
							return true;
						}
					}
				}
				continue;
			}
			//if the input you chose has a mine, it prints out boooom and records your name with the current turn
			else if (field.getBoom()) {
				System.out.println("\nBooooooooooooooooooooooooooooom!You stepped on a mine!You survived " + result + " turns");
				rank.recordName(result);
				return true;
			}
		}
	}
	//prints the instructions to play this game
	private static void mainMessage(){
		System.out.println("Welcome to Minesweeper!");
		System.out.println("To play just input some coordinates and try not to step ont mine :)");
		System.out.println("Usefull commands:");
		System.out.println("restart- Starts a new game.");
		System.out.println("exit- Quits the game.");
		System.out.println("top- Reveals the top scoreboard.");
		System.out.println("Have Fun !");
	}
}