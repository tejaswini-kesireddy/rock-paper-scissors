package RockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;

public class RockPaperScissors 
{
	BufferedReader bufferedReader = null;
	SecureRandom random = new SecureRandom();
	String message = "Welcome to Rock Paper Scissors Lizard and Spock!";
	
	public RockPaperScissors() {		
	}

	/**
	 * Play Game
	 * @throws Exception
	 */
	private void playGame() throws Exception {
		try {
			// Initialize the bufferedReader
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			// Game Loop
			while(true) {
				displayMainMenu();
				int menuOption = getMainMenuOption();
				System.out.println("You selected " + menuOption);
				processMainMenuOption(menuOption);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new RockPaperScissors().playGame();
	}
	
	/**
	 * Display Menu
	 * @param message
	 */
	public void displayMainMenu() {
		// Clear the console
		clearTheConsole();
		
		System.out.println("\n");
		System.out.println("***************************************");
		System.out.println("*  ROCK PAPER SCISSORS LIZARD SPOCK   *");
		System.out.println("***************************************");
		System.out.println("* 1. Play Rock Paper Scissors         *");
		System.out.println("* 2. Exit                             *");
		System.out.println("***************************************");
		
		// If a message exists, display it and then clear it
		if (message != null && message.length() > 0) {
			System.out.println(message);
			message = "";
		}
		System.out.println("\nPlease select a menu option: ");
	}
	
	/**
	 * Get Main Menu Option
	 * @return
	 * @throws Exception
	 */
	public int getMainMenuOption() throws Exception {
		int menuOption = -1;
		String inputString = bufferedReader.readLine();
		if (isValidMenuOption(inputString, new Integer[] {1, 2})) {
			menuOption = Integer.parseInt(inputString);
		} else {
			message = "Invalid Selection";
			displayMainMenu();
			getMainMenuOption();
		}
		return menuOption;
	}
	
	/**
	 * Is Valid Menu Option
	 * @param inputString
	 * @param integers
	 * @return
	 */
	private boolean isValidMenuOption(String inputString, Integer[] integers) {
		try {
			Integer input = Integer.parseInt(inputString);
			for (Integer integer : integers) {
				if (input.intValue() == integer.intValue()) {
					return true;
				}
			}
		} catch (NumberFormatException nfe) {
			return false;
		}
		return false;
	}
	
	/**
	 * Process Main Menu Option
	 * @param menuOption
	 * @throws Exception
	 */
	private void processMainMenuOption(int menuOption) throws Exception {
		switch(menuOption) {
			case 1: displayGameMenu();
				break;
			case 2: quit(0);
				break;
		}
	}
	
	/**
	 * Quit
	 * @param exitCode
	 */
	public void quit(int exitCode) {
		System.out.println("\n\nGood Bye!");
		try {bufferedReader.close();} catch (Exception e) {}
		System.exit(exitCode);
	}
	
	/**
	 * Display Game Menu
	 * @throws IOException
	 */
	public void displayGameMenu() throws IOException{
		// Clear the console
		clearTheConsole();
		
		System.out.println("\n\n");
		System.out.println("***************************************");
		System.out.println("*  ROCK PAPER SCISSORS LIZARD SPOCK   *");
		System.out.println("***************************************");
		System.out.println("* 1. Rock       					  *");
		System.out.println("* 2. Paper                            *");
		System.out.println("* 3. Scissors                         *");
		System.out.println("* 4. Lizard                           *");
		System.out.println("* 5. Spock                            *");
		System.out.println("***************************************");
		
		// If a message exists, display it and then clear it
		if (message != null && message.length() > 0) {
			System.out.println(message);
			message = "";
		}
		
		System.out.println("Please select Rock(1), Paper(2), Scissors(3), Lizard(4), or Spock(5): ");
		int gameMenuOption = getGameMenuOption();
  	processGameMenuOption(gameMenuOption);
	}
	
	/**
	 * Get Game Menu Option
	 * @return
	 * @throws IOException
	 */
	private int getGameMenuOption() throws IOException {
		int gameMenuOption = -1;
		String inputString = bufferedReader.readLine();
		if (isValidMenuOption(inputString, new Integer[] {1, 2, 3, 4, 5})) {
			gameMenuOption = Integer.parseInt(inputString);
		} else {
			message = "Invalid Selection";
			displayGameMenu();
			getGameMenuOption();
		}
		return gameMenuOption;
	}
	
	/**
	 * Process Game Menu Option
	 * @param gameMenuOption
	 * @throws IOException
	 */
	private void processGameMenuOption(int userSelection) throws IOException {
		int computerSelection = getRandomNumber(1, 5);
		boolean selectionsMatch = (userSelection == computerSelection);
		if (selectionsMatch) {
			message = "You both selected " + getLiteralSelection(userSelection) + "! Try again...";
			displayGameMenu();
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Your Selection: " + getLiteralSelection(userSelection) + "\n");
			sb.append("Computer Selection: " + getLiteralSelection(computerSelection) + "\n");
			
			switch(userSelection) {
				case(1): // Rock
					switch(computerSelection) {
						case(2): sb.append("Paper covers rock. You Lose!"); break;
						case(3): sb.append("Rock crushes scissors. You Win!"); break;
						case(4): sb.append("Rock crushes Lizard. You Win!"); break;
						case(5): sb.append("Spock vaporizes Rock. You Lose!"); break;	
					}
					break;
				case(2): // Paper
					switch(computerSelection) {
						case(1): sb.append("Paper covers rock. You Win!"); break;
						case(3): sb.append("Scissors cuts paper. You Lose!"); break;
						case(4): sb.append("Lizard eats paper. You Lose!"); break;
						case(5): sb.append("Paper disproves Spock. You Win!"); break;
					}
					break;
				case(3): // Scissors
					switch(computerSelection) {
						case(1): sb.append("Rock crushes scissors. You Lose!"); break;
						case(2): sb.append("Scissors cuts paper. You Win!"); break;
						case(4): sb.append("Scissors decapitates lizard. You Win!"); break;
						case(5): sb.append("Spock smashes scissors. You Lose!"); break;
					}
					break;
				case(4): // Lizard
					switch(computerSelection) {
						case(1): sb.append("Rock crushes lizard. You Lose!"); break;
						case(2): sb.append("Lizard eats paper. You Win!"); break;
						case(3): sb.append("Scissors decapitates lizard. You Lose!"); break;
						case(5): sb.append("Lizard poisons spock. You Win!"); break;
					}
					break;
				case(5): // Spock
					switch(computerSelection) {
						case(1): sb.append("Spock vaporizes rock. You Win!"); break;
						case(2): sb.append("Paper disproves spock. You Lose!"); break;
						case(3): sb.append("Spock smashes Scissors. You Win!"); break;
						case(4): sb.append("Lizard poisons spock. You Lose!"); break;
					}
					break;
			}
			
			message = sb.toString();
		}
	}
	
	
	/**
	 * Get Random Number
	 */
	private int getRandomNumber(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
	
	
	/**
	 * Get Literal Selection
	 * @param userSelection
	 * @return
	 */
	private String getLiteralSelection(int selection) {
		switch(selection) {
			case(1): return "Rock";
			case(2): return "Paper";
			case(3): return "Scissors";
			case(4): return "Lizard";
			case(5): return "Spock";
			default: return "";
		}
	}
	
	/**
	 * Clear the Console
	 */
	public static void clearTheConsole() {
		try {
			Thread.sleep(500);
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


