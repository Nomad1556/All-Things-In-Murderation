package all_things_in_murderation;
import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 * The Room class has a name, description, list of items in it and list of exits leading 
 * To other rooms and the associated ImageIcon
 * @author Nodar Sotkilava, Hal Stewart, Emily Pochet
 */
public class Room {
	private String Room_name;
	private String description;
	private ArrayList<Item> room_items = new ArrayList<Item>();
	private ArrayList<Exit> room_exits = new ArrayList<Exit>();
	private ArrayList<Mob>room_mobs = new ArrayList<Mob>();
	private ImageIcon img;
	
	/**
	 * Constructor function here. The room name and description are passed to it
	 * @param name Room name
	 * @param desc Room desc
	 * @param img Room image
	 */
	
  public Room(String name, String desc, ImageIcon img)
    {
    	this.Room_name = name;
    	this.description = desc;
    	this.img = img;
        
    }
  /**
   * returns The room's name
   * @return Room name
   */
 public String getName() {
		return Room_name;
		
	}
 /**
  * Adds an exit to the room
  * @param r Adds a passed Exit to the room into the room_exits list
  */
public void addExit(Exit r) {
	  room_exits.add(r);
	  
  }
/**
 * Adds an item into the room
* @param i Adds a passed item to the room's item list
*/
  public void addItem(Item i) {
	  room_items.add(i);
  }
  /**
   * Gets the Room's description
 * @return Returns the room's description
 */
  public String getDescription() {
	  return this.description;
  }
  /**
   * Returns items' names in the room
  * @return If no items in the room, returns null else returns the list of items
  */
 public String getItemsList() {
	 String s = " ";
	 if (room_items.size() == 0)
		 return null;
	 
	 else {
		 for (Item item: room_items) {
		 s = s + "\n " + item.getName();
	 }
	 return s;
 }}
 /**
  * Returns the exits in the room
 * @return If no exits in the room, returns null else returns list of exits with their description and direction
 */
 public String getExitsList() {
	 String s = " ";
	 
	 if (room_exits.size() == 0)
		 return null;
	 else {
		 for (Exit exit: room_exits) {
		 s= s + " \n " + exit.getDescription() + "(dir: " + exit.getDirection() + ")";
	 } 
	 return s;
 }}
 /**
  * Returns an item from the room and removes in from the room's item list
 * @param s Passed item's name
 * @return If item's name matches item's name in the room then returns that item and deletes it from 
 * room's list of items else null
 */
 public Item get_item(String s) {
	for (Item item: room_items) {
		if (s.equals(item.getName())) {
			room_items.remove(item);
			return item;
		} 
	}
	return null;

 }
 /**
  * Returns the exit in the room
 * @param s Passed exit's name
 * @return If exit's name matches the exit's name in the room then returns that exit else null
 */
 public Exit get_exit(String s) {
	 for (Exit exit: room_exits) {
		 if (s.equals(exit.getDirection())){
			 return exit;
		 }
	 } 
	 return null;
 } 
 /**
  * Returns the room's associated image icon
  * @return returns the room's associated image icon
  */
 public ImageIcon get_icon() {
	 return this.img;
 }
 
 /**
  * Returns all the Mobs in the room
  * @return names of Mobs in the room
  */
 public String get_mobs_in_room() {
	 String s = "";
	 if (room_mobs.size() == 0) {
		 return null;
	 }
	 else {
		 for(Mob m: room_mobs) {
		  s = s + m.get_name() + "\n";
		}
	}
	return s;
 }
 /**
  * Returns the given Mob with the name
  * @param n Name of the Mob
  * @return Mob otherwise null
  */
 public Mob get_mob(String n) {
	 String x  = n.toLowerCase();
	 if (room_mobs.size() == 0) {
		 return null;
	 }
	 else {
		 for(Mob m: room_mobs) {
		  if (x.equals(m.get_name().toLowerCase())) {
			  return m;
		  	}
		  }
	}
	 return null;
 }
 /**
  * Adds a Mob to the room
  * @param x Mob
  */
 public void remove_mob(Mob x) {
	 this.room_mobs.remove(x);
 	}
 
 /**
  * Adds a Mob to the room
  * @param x Mob
  */
 public void add_mob(Mob x) {
	  this.room_mobs.add(x);
 	}
 }
 
