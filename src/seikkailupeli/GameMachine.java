package seikkailupeli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import seikkailupeli.CommandParser;
import static seikkailupeli.Room.Exit.*;
import static seikkailupeli.Room.Exit;

public class GameMachine {

	private static Scanner input;

	public static void main (String[] args) {
		
		Player adventurer = new Player();
		
		Map<String, Exit> exits = new HashMap<>();
		exits.put("no", NORTH);
		exits.put("ea", EAST);
		exits.put("so", SOUTH);
		exits.put("we", WEST);
		
		Room hallway = new Room("hallway", "");
		Room kitchen = new Room("kitchen", "");
		
		Item sock = new Item("sock", "black, dirty.");
		Item coffeeMug = new Item("coffee mug","contains milk coffee leftovers and a silver spoon.");
		
		kitchen.addItem(coffeeMug);
		
		hallway.setExit(WEST, kitchen);
		kitchen.setExit(EAST, hallway);
		
		boolean gameOn = true;
		Room location = hallway; //siirrä ulkopuolelle ja tee staattinen! Sitten voi käyttää muuallakin.
		
		input = new Scanner(System.in);
		
		while (gameOn) {
			System.out.println("You are in the " + location.getName());
			if (location.getItems().size() > 0) {
				System.out.println("There are following items in this room: " + location.getItems());
			}
			System.out.println("Exits: " + location.getExits().toString());
			System.out.printf("> ");
			String command = input.nextLine();
			
			List<String> parsedCommand = CommandParser.parse(command);	
			String firstWord = parsedCommand.get(0).toLowerCase();
			
			
			//refaktoroi ennenkun teet mitään!
			//first word and shit
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
			} else if (firstWord.equals("ta") || firstWord.equals("ge")) {
				if (parsedCommand.size() < 2) {
					System.out.println("Take what?");
				} else {
					String secondWord = parsedCommand.get(1).toLowerCase();		
					Optional<Item> maybeItem = location.findItem(secondWord);
					if (maybeItem.isPresent()) {
						adventurer.addItem(maybeItem.get());
						System.out.println("You have taken the " + maybeItem.get().getName() + ".");
						location.removeItem(maybeItem.get());
					} else {
						System.out.println("No such item");
					}
				}
			} else {
				System.out.println("WTF. I don't understand you. Retry. Type a direction for example or take an item?");	
			}
		} 				
	}	
}
	
	
	

