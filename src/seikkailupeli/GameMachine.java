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
		Room location = hallway;
		
		input = new Scanner(System.in);
		
		while (gameOn) {
			System.out.println("You enter the " + location.getName());
			if (location.getItems().size() > 0) {
				System.out.println("There are following items in this room: " + location.getItems());
			}
			System.out.println("Exits: " + location.getExits().toString());
			System.out.printf("> ");
			String command = input.nextLine();
			
			List<String> parsedCommand = CommandParser.parse(command);	
			String firstWord = parsedCommand.get(0).toLowerCase();
			
			if (firstWord.equals("ex") || firstWord.equals("qu")) {
				gameOn = false;
			} 
			
			if (firstWord.equals("ta") || firstWord.equals("ge")) {
				String secondWord = parsedCommand.get(1).toLowerCase();
				//TEE: jos ei ole toista sanaa niin ohita tjs, nyt kaatuu siihen
				//LISÄKSI sano mikä tavara on otettu. 
				//JA refaktoroi seuraava kohta, koska nyt narisee suunnista kun ottaa itemin!
				Optional<Item> maybeItem = location.findItem(secondWord);
				if (maybeItem.isPresent()) {
					adventurer.addItem(maybeItem.get());
					location.removeItem(maybeItem.get());
				} else {
					System.out.println("No such item");
				}
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
	
	
	

