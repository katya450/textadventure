package net.katyas.textadventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Room {

	final private String name;
	final private String description;
	final private List<Item> items = new ArrayList<>(); //final, jotta osoitetaan aina tähän kyseiseen
		
	private Map<Exit, Room> exits = new HashMap<>();	//Hashmap, jossa on exitit ja siellä sijaitsevat huoneet.
	public static enum Exit { NORTH, EAST, SOUTH, WEST };
	
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Optional<Room> getExit(Exit exit) {
		return Optional.ofNullable(exits.get(exit)); //jos ei oo optional huonetta niin tulee empty! tadaa!
	}
	
	public void setExit(Exit exit, Room room) { //kutsu; setExit(Exit.NORTH, toilet)
		exits.put(exit, room);					//exit "north" vie vaikkapa makkariin
	}
	
	public Set<Exit> getExits() {	//ei parametreja, koska hakee kaikki k.o. huoneen exitit
		return exits.keySet();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public List<Item> getItems() {
		return Collections.unmodifiableList(items); //tämä siksi, että listaa ei voi muokata. Muuten kävisi pelkkä "return items".
	}
	
	//tee metodi, joka palauttaa vastauksen siihen, onko huoneella tällainen item.
	public Optional<Item> findItem(String askedItemName) {
		return items.stream().filter(item -> item.getName().toLowerCase().startsWith(askedItemName)).findAny();
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
