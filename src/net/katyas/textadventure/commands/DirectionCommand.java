package net.katyas.textadventure.commands;

import static net.katyas.textadventure.Room.Exit.EAST;
import static net.katyas.textadventure.Room.Exit.NORTH;
import static net.katyas.textadventure.Room.Exit.SOUTH;
import static net.katyas.textadventure.Room.Exit.WEST;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import net.katyas.textadventure.GameState;
import net.katyas.textadventure.Room.Exit;

public class DirectionCommand implements Command {

	private static Map<String, Exit> exits = new HashMap<>();
	//static block, eli kun näitä ei ajeta konstruktorissa eikä metodissa niin tällä saadaan nämä ajettua kun luokka ladataan 
	static {
		exits.put("no", NORTH);
		exits.put("ea", EAST);
		exits.put("so", SOUTH);
		exits.put("we", WEST);		
	}

	@Override
	public GameState execute(String firstWord, Optional<String> secondWord, GameState gameState) {
		
		Exit maybeDirection = exits.get(firstWord);
		if (maybeDirection != null) { 										//tsekkaa, että annettu sana on exits mapissä oleva suunta
			if (gameState.location.getExit(maybeDirection).isPresent()) { 	//tsekataan, onko huoneella haluttua exitiä
				gameState.location = gameState.location.getExit(maybeDirection).get();
			} else {
				System.out.println("That's not a valid direction, there appears to be a wall you cannot break.");
			}
		}
		return gameState;
	}

	@Override
	public boolean is(String firstWord) {
		
		return exits.keySet().contains(firstWord);

	}
}
