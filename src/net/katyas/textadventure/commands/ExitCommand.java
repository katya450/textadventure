package net.katyas.textadventure.commands;

import java.util.Optional;

import net.katyas.textadventure.GameState;

public class ExitCommand implements Command {

	@Override
	public GameState execute(String firstWord, Optional<String> secondWord, GameState gameState) {
		
		gameState.gameOn = false;
		return gameState;
	}

	@Override
	public boolean is(String firstWord) {

		return firstWord.equals("ex") || firstWord.equals("qu"); 
			
	}
}
