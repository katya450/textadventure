package net.katyas.textadventure.commands;

import java.util.Optional;

import net.katyas.textadventure.GameState;

public class TakeCommand implements Command {

	@Override
	public GameState execute(String firstWord, Optional<String> secondWord, GameState gameState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean is(String firstWord) {
		return firstWord.equals("ta") || firstWord.equals("ge"); 
	}

}
