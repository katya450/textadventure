package net.katyas.textadventure;

import java.util.Optional;

public class GameState {

	public Room location;
	public boolean gameOn;
	public Inventory inventory;
	public Optional<String> message;

	public GameState gameMessage(String message) {
		this.message = Optional.of(message);
		return this;
	}
}
