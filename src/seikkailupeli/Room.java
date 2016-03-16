package seikkailupeli;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Room {

	private String name;
	private String description;
	private Optional<Room> exitNorth = Optional.empty();
	private Optional<Room> exitEast = Optional.empty();
	private Optional<Room> exitSouth = Optional.empty();
	private Optional<Room> exitWest = Optional.empty();
	
	private Map<Exit, Room> exits = new HashMap<>();
	public enum Exit { NORTH, EAST, SOUTH, WEST };
	
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
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Optional<Room> getExitNorth() {
		return exitNorth;
	}

	public Optional<Room> getExitEast() {
		return exitEast;
	}

	public Optional<Room> getExitSouth() {
		return exitSouth;
	}

	public Optional<Room> getExitWest() {
		return exitWest;
	}

	public void setExitNorth(Room room) {
		exitNorth = Optional.of(room);
	}

	public void setExitEast(Room room) {
		exitEast = Optional.of(room);
	}
	
	public void setExitSouth(Room room) {
		exitSouth = Optional.of(room);
	}
	
	public void setExitWest(Room room) {
		exitWest = Optional.of(room);
	}
}
