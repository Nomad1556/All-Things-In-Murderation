package all_things_in_murderation;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Main player class
 * Has a name
 * list of item the player has
 * the current room the player is in
 * and if player changed the name at the beginning of the game
 * @author Nodar Sotkilava, Hal Stewart, Emily Pochet
 */

public class Player {
	private String Player_Name;
	protected  ArrayList<Item>player_items = new ArrayList<Item>();
	protected Room Current_Room;
	protected Boolean name_set = false;
	
	/**
	 * Simple constructor; usually a placeholder name and then player changes the name in UI
	 * @param r The room player starts in
	 * @param name The name player is given upon creation. 
	 * 
	 */
	public Player(Room r, String name) {
		this.Current_Room = r;
		this.Player_Name = name;
	}
	/**
	 * Changes the player's name
	 * @param s New Player name
	 * Changes player's name
	 */
	public void change_name(String s) {
		this.Player_Name = s;
	}
	/**
	 * Returns the current items in the room
	 * @return The current items in the room
	 */
	public String look_items() {
		return  Current_Room.getItemsList();
	}
	/**
	 * Returns the current exits in the room
	 * @return The current exits in the room
	 */
	public String look_exits() {
		return Current_Room.getExitsList();
	}
	/**
	 * Returns the current room's name
	 * @return The current room's name
	 */
	public String look_room() {
		return Current_Room.getName();
	}
	/**
	 * Gets the inventory of the player
	 * @return Gets current inventory of the player if empty returns null
	 */
	
	public String get_inv() {
		String s = "";
		if (player_items.size() == 0)
			return null;
		else {
			for (Item item: player_items) {
			s = s + "\n" + item.getName();
		}
		return s;
	}}
	/**
	 * Simply adds an item to the inventory
	 * @param i The item passed in
	 */
	public void add_item(Item i) {
		this.player_items.add(i);
	}
	/**
	 * Picks up an item from the room
	 * @param s The item's name
	 * @return True if the player has picked up an item from the room else false
	 */
	public Boolean Pickup_Item(String s) {
		Item b = this.Current_Room.get_item(s);
		if ( b != null) {
			player_items.add(b);
			return true;
		}
		else return false;
	}
	/**
	 * Drops an item from that player's inventory
	 * @param s The item's name
	 * @return True if the player has dropped an item else false
	 */
	public Boolean Drop_Item(String s) {
		Item b = this.get_item(s);
		if ( b != null) {
			this.Current_Room.addItem(b);
			return true;
	}
		else return false;
}
	/**
	 * Moves the player into specified room by the user
	 * @param s The direction that the player wants to go
	 * @return True if the player is able to go to the other room else false
	 */
	public Boolean go(String s) {
		Exit b = Current_Room.get_exit(s);
		if (b != null) {
			Current_Room = b.getToRoom();
			return true;
		}
		else return false;
	}	
	/**
	 * Gets the player's current name
	 * @return The player's current name
	 */
	public String get_name() {
		return this.Player_Name;
	}
	/**
	 * Gets an item from the player's inventory
	 * @param s Item's name
	 * @return Gets the item in the player inventory if empty then returns null
	 */
	
	public Item get_item(String s) {
		for (Item item: player_items) {
			if (s.equals(item.getName())) {
				System.out.println("removed item: " + item.getName());
				player_items.remove(item);
				return item;
			}
		}
		return null;
	}
	/**
	 * @return Gets the current room's description
	 */
	public String get_current_room_disc() {
		return Current_Room.getDescription();
	}
	/**
	 * 
	 * @return Gets the current room's image icon
	 */
	public ImageIcon get_current_room_icon() {
		return Current_Room.get_icon();
	}
	/**
	 * Returns the desired Mob of the given name in the room
	 * @param n Name of the desired Mob
	 * @return Mob otherwise null
	 */
	public Mob get_mob(String n) {
		return this.Current_Room.get_mob(n);
	}
	/**
	 * Gets all the Mobs in the room
	 * @return all the Mobs in the room
	 */
	public String get_mobs_in_room() {
		return this.Current_Room.get_mobs_in_room();
	}
}	


