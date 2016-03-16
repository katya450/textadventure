package seikkailupeli;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Room {

	private String name;
	private String description;
		
	private Map<Exit, Room> exits = new HashMap<>();	//Hashmap, jossa on exitit ja siellä sijaitsevat huoneet.
	public static enum Exit { NORTH, EAST, SOUTH, WEST };
	
	public Room(String name, String description) {
		super();
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
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
