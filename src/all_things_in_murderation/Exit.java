package all_things_in_murderation;
/**
 * Has a direction to the next room,
 * Has a descriptions,
 * And which room it goes to.
 * @author Nodar Sotkilava, Hal Stewart, Emily Pochet
 */
public class Exit {
	
	private String direction;
	private String description;
	private Room toRoom;
	/**
	 * Constructs Exit with passed direction of the room, description and 
	 * \nthe Room exit leads to
	 * @param direction Passed direction
	 * @param desc Passed description
	 * @param r Passed Room
	 */
	public Exit(String direction, String desc, Room r) {
		
		this.direction = direction;
		this.description = desc;
		this.toRoom = r;
	}
	/**
	 * Returns the room that exit leads to
	 * @return The Room the exit leads to
	 */
public Room getToRoom() {
		  return this.toRoom;
	  }
	/**
	 * Returns the Exit's direction
	 * @return The Exit's direction
	 */
public String getDirection() {
		  return this.direction;
	  }
	/**
	 * Returns the Exit's description
	 * @return The Exit's description
	 */
public String getDescription() {
		  return this.description;
	  }

	}
	


