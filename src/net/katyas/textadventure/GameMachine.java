package net.katyas.textadventure;

import static net.katyas.textadventure.Room.Exit;
import static net.katyas.textadventure.Room.Exit.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import net.katyas.textadventure.CommandParser;
import net.katyas.textadventure.commands.Command;
import net.katyas.textadventure.commands.ExitCommand;
import net.katyas.textadventure.commands.TakeCommand;

public class GameMachine {
	
	public static void main (String[] args) {
		
		GameState gameState = new GameState();
		
		gameState.inventory = new Inventory();
		
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
		
		gameState.gameOn = true;
		gameState.location = hallway;
		
		final Scanner input = new Scanner(System.in);
		
		while (gameState.gameOn) {
			System.out.println("You are in the " + gameState.location.getName());
			if (gameState.location.getItems().size() > 0) {
				System.out.println("There are following items in this room: " + gameState.location.getItems());
			}
			System.out.println("Exits: " + gameState.location.getExits().toString());
			System.out.printf("> ");
			String command = input.nextLine();
			
			List<String> parsedCommand = CommandParser.parse(command);	
			String firstWord = parsedCommand.get(0).toLowerCase();

			
			//refaktoroi ennenkun teet mitään!
			//first word and shit
			//lue 7, 9, 23 pattern -kirjasta (7!)
			
			Command exitCommand = new ExitCommand();
			Command takeCommand = new TakeCommand();
			
			if (exitCommand.is(firstWord)) {
				gameState = exitCommand.execute(firstWord, Optional.empty(), gameState);
				
			} else {

				Exit maybeDirection = exits.get(firstWord);
				if (maybeDirection != null) { 				//tsekkaa, että annettu sana on exits mapissä oleva suunta
					if (gameState.location.getExit(maybeDirection).isPresent()) {
						gameState.location = gameState.location.getExit(maybeDirection).get();
					} else {
						System.out.println("That's not a valid direction, there appears to be a wall you cannot break.");
					}
				} else if (takeCommand.is(firstWord)) {
					if (parsedCommand.size() < 2) {
						System.out.println("Take what?");
					} else {
						String secondWord = parsedCommand.get(1).toLowerCase();		

						Optional<Item> maybeItem = gameState.location.findItem(secondWord);
						if (maybeItem.isPresent()) {
							gameState.inventory.addItem(maybeItem.get());
							System.out.println("You have taken the " + maybeItem.get().getName() + ".");
							gameState.location.removeItem(maybeItem.get());
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
}
	
	
	

