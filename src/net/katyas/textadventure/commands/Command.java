package net.katyas.textadventure.commands;

import java.util.Optional;

import net.katyas.textadventure.GameState;

public interface Command {

	public GameState execute(String firstWord, Optional<String> secondWord, GameState gameState);
	public boolean is(String firstWord);
	
}
