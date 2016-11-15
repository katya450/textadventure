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
import net.katyas.textadventure.commands.DirectionCommand;

public class GameMachine {
	
	public static void main (String[] args) {
		
		GameState gameState = new GameState();
		
		gameState.inventory = new Inventory();
		
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
			String firstWord = parsedCommand.get(0);
			// tsekkaa, onko toista sanaa. Jos ei, se on optional (empty) jos on niin sitten se on se sana.
			Optional<String> secondWord = parsedCommand.size() == 1 ? 
					Optional.empty() : 
					Optional.of(parsedCommand.get(1));
			
			Command exitCommand = new ExitCommand();
			Command takeCommand = new TakeCommand();
			Command directionCommand = new DirectionCommand();

			if (exitCommand.is(firstWord)) {
				gameState = exitCommand.execute(firstWord, secondWord, gameState);
				
			} else if (directionCommand.is(firstWord)) {
				gameState = directionCommand.execute(firstWord, secondWord, gameState);
			
			} else if (takeCommand.is(firstWord)) {
				gameState = takeCommand.execute(firstWord, secondWord, gameState);
						
			} else {
					System.out.println("WTF. I don't understand you. Retry. Type a direction for example or take an item?");	
			}
		}
	} 				
	
}
	
	
	

