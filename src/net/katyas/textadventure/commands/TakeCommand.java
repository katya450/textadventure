package net.katyas.textadventure.commands;

import java.util.Optional;

import net.katyas.textadventure.GameState;
import net.katyas.textadventure.Item;

public class TakeCommand implements Command {

	@Override
	public GameState execute(String firstWord, Optional<String> secondWord, GameState gameState) {
		
		if (!secondWord.isPresent()) {
			System.out.println("Take what?");
		} else {
			Optional<Item> maybeItem = gameState.location.findItem(secondWord.get()); //tarkistaa, onko huoneessa haluttua esinettä
			if (maybeItem.isPresent()) {
				gameState.inventory.addItem(maybeItem.get());
				System.out.println("You have taken the " + maybeItem.get().getName() + ".");
				gameState.location.removeItem(maybeItem.get());
			} else {
				System.out.println("No such item");
			}
		}
		return gameState;
	}

	@Override
	public boolean is(String firstWord) {
		
		return firstWord.equals("ta") || firstWord.equals("ge"); 
	}

}
