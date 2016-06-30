package seikkailupeli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

	final private List<Item> inventory = new ArrayList<>(); 

	public void addItem(Item item) {
		inventory.add(item);
	}
	
	public void removeItem(Item item) {
		inventory.remove(item);
	}
	
	public List<Item> getItems() {
		return Collections.unmodifiableList(inventory); 
	}
}
