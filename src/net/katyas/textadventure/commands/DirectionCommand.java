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

	@Override
	public GameState execute(String firstWord, Optional<String> secondWord, GameState gameState) {

		//suuntien synonyymit mäppiin
		Map<String, Exit> exits = new HashMap<>();
		exits.put("no", NORTH);
		exits.put("ea", EAST);
		exits.put("so", SOUTH);
		exits.put("we", WEST);
		
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
		// TODO Auto-generated method stub - tähän kahen kirjaimen komennot ja executessa tsekki suunnalle ja pääseekö sinne
		
		return firstWord.equals("no") || firstWord.equals("so") || firstWord.equals("we") || firstWord.equals("ea"); 

	}
	
	
	

}
