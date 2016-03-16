package seikkailupeli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import seikkailupeli.CommandParser;
import static seikkailupeli.Room.Exit.*;
import static seikkailupeli.Room.Exit;

public class GameMachine {

	private static Scanner input;

	public static void main (String[] args) {
		
		Map<String, Exit> exits = new HashMap<>();
		exits.put("no", NORTH);
		exits.put("ea", EAST);
		exits.put("so", SOUTH);
		exits.put("we", WEST);
		
		Room hallway = new Room("Hallway", "");
		Room kitchen = new Room("Kitchen", "");
		hallway.setExit(WEST, kitchen);
		kitchen.setExit(EAST, hallway);

		boolean gameOn = true;
		Room location = hallway;
		
		input = new Scanner(System.in);
		
		while (gameOn) {
			System.out.println(location.getName());
			System.out.println("Exits: " + location.getExits().toString());
			System.out.printf("> ");
			String command = input.nextLine();
			
			List<String> parsedCommand = CommandParser.parse(command);	
			String firstWord = parsedCommand.get(0).toLowerCase();
			
			if (firstWord.equals("ex") || firstWord.equals("qu")) {
				gameOn = false;
			} 
			
			Exit maybeDirection = exits.get(firstWord);
			if (maybeDirection != null) { 				//tsekkaa, että annettu sana on exits mapissä oleva suunta
				if (location.getExit(maybeDirection).isPresent()) {
					location = location.getExit(maybeDirection).get();
				} else {
					System.out.println("That's not a valid direction, there appears to be a wall you cannot break.");
				}
			} else {
				System.out.println("WTF. I don't understand you. Retry. Type a direction for example?");
			}
			
			
		} 			
		
	}
	
}
	
	
	

