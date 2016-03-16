package seikkailupeli;

import java.util.List;
import java.util.Scanner;
import seikkailupeli.CommandParser;

public class GameMachine {

	private static Scanner input;
	private static String direction;

	public static void main (String[] args) {
		
		Room hallway = new Room("Hallway", "");
		Room kitchen = new Room("Kitchen", "");
		hallway.setExitWest(kitchen);
		kitchen.setExitEast(hallway);

		boolean gameOn = true;
		Room location = hallway;
		input = new Scanner(System.in);
		
		while (gameOn) {
			System.out.println(location.getName());
			StringBuffer buffer = new StringBuffer();
			if (location.getExitNorth().isPresent()) {
				buffer.append("North ");
			}
			if (location.getExitEast().isPresent()) {
				buffer.append("East ");
			}
			if (location.getExitSouth().isPresent()) {
				buffer.append("South ");
			}
			if (location.getExitWest().isPresent()) {
				buffer.append("West ");
			}
			System.out.println("Exits: " + buffer.toString());

			
			System.out.printf("> ");
			String command = input.nextLine();
			
			List<String> parsedCommand = CommandParser.parse(command);	
			String firstWord = parsedCommand.get(0).toLowerCase();
			
			if (firstWord.equals("ex") || firstWord.equals("qu")) {
				gameOn = false;
			} 
			
			if (firstWord.equals("no") || firstWord.equals("ea") || firstWord.equals("so") || firstWord.equals("we")){
				switch (firstWord) {
					case "no":
						if (location.getExitNorth().isPresent()) {
							location = location.getExitNorth().get();
						} else {
							System.out.println("That's not a valid direction, there appears to be a wall you cannot break.");
						}
						break;
					case "ea":
						if (location.getExitEast().isPresent()) {
							location = location.getExitEast().get();
						} else {
							System.out.println("That's not a valid direction, there appears to be a wall you cannot break.");
						}
						break;
					case "so":
						if (location.getExitSouth().isPresent()) {
							location = location.getExitSouth().get();
						} else {
							System.out.println("That's not a valid direction, there appears to be a wall you cannot break.");
						}
						break;
					case "we":
						if (location.getExitWest().isPresent()) {
							location = location.getExitWest().get();
						} else {
							System.out.println("That's not a valid direction, there appears to be a wall you cannot break.");
						}
						break;
				}
				
			} else {
				System.out.println("WTF. I don't understand you. Retry. Type a direction for example?");
			}
		} 			
		
	}
	
}
	
	
	

