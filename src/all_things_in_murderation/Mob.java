package all_things_in_murderation;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

/**
 * Main Mob class. Extends the Player class and implements Observer and Runnable.
 * Has a Hashtable for the dialogue
 * Has the Game
 * Has an ArrayList of direction
 * Has three Boolean values if they can walk around the game,whether they're talking to the Player, and if they're the antagonist
 * Has a "kill timer" so that if they're the antagonist then when it runs out the game is over
 * @author Nodar Sotkilava, Hal Stewart, Emily Pochet
 *
 */

public class Mob extends Player implements Observer, Runnable{
	
	private Hashtable<Integer, String> dialogue = new Hashtable<Integer, String>();
	private ArrayList<String>direction = new ArrayList<String>();
	private Game g;
	private Boolean run;
	private Boolean talk = false;
	private Boolean antagonist;
	private int kill_timer = 600000;
	//600000
	
	/**
	 * Uses Player's constructor, on top lists of all possible directions are added
	 * And whether they can run
	 * @param r Starting Room
	 * @param name Name of the Mob
	 * @param run if the Mob can move around the room
	 * @param g passed game
	 * @param f If antagonist pass in true
	 */
	public Mob(Room r, String name, Boolean run, Game g, Boolean f) {
		super(r, name);
		direction.add("north");
		direction.add("south");
		direction.add("west");
		direction.add("east");
		direction.add("northwest");
		direction.add("northeast");
		direction.add("southwest");
		direction.add("southeast");
		direction.add("upstairs");
		direction.add("downstairs");
		this.run = run;
		this.Current_Room.add_mob(this);
		System.out.println(this.get_name() + " started in " + this.Current_Room.getName()); //debuggning purposes
		this.g = g;
		this.antagonist = f;
	}
	
	/**
	 * Gets the appropriate dialogue for the appropriate Mob
	 * @return Dialogue String
	 */
	public String get_dialogue() {
		switch(this.get_name()) {
		case"Maiden":
			return this.dialogue.get(1);
		case"Jester":
			if (this.dialogue.size() == 2) {
				String x  = this.dialogue.get(1);
				this.dialogue.remove(1);
				return x;
			}
			else {
				return this.dialogue.get(2);
			}
		case"Knight":
			if (this.dialogue.size() == 2) {
				String x  = this.dialogue.get(1);
				this.dialogue.remove(1);
				return x;
			}
			else  return this.dialogue.get(2);
		case"King":
			return this.dialogue.get(1);
		case"Nurse":
			return this.dialogue.get(1);
		default:
			return null;
		}
	}
	
	/**
	 * Adds the dialogue to a hashtable
	 * @param i key for the specific dialogue line
	 * @param s the dialogue ine
	 */
	
	public void add_dialogue(int i, String s) {
		this.dialogue.put(i, s);
	}
	
	/**
	 * A simple function for the mob to walk in the game
	 * If the mob is allowed to walk and if they are not talking to the Player
	 * Then they can move to another room
	 * Print statements are added for debugging and testing 
	 */
	public void go() {
		if (this.run && !this.talk) {
			double i = (Math.random() * 10) - 1;
			String s = direction.get((int)i);
			Exit b = Current_Room.get_exit(s);
			if (b != null) {
				this.Current_Room.remove_mob(this);
				Current_Room = b.getToRoom();
				this.Current_Room.add_mob(this);
				System.out.println(this.get_name() + " moved to room " + Current_Room.getName());
				}
			else {
				System.out.println(this.get_name() + " did not move to another room");
				} 
			}
			else {
			System.out.println(this.get_name() + " is currently talking!");
		}
	}
	/**
	 * Negates the talk boolean
	 */
	public void negate_talk() {
		this.talk = !this.talk;
	}
	/**
	 * Returns the talk boolean
	 * True if the Mob is talking to the Player
	 * False if the Mob is not talking to the Player
	 * @return talk
	 */
	public Boolean talking() {
		return this.talk;
	}
	/**
	 * When the game is finished, all Mobs are not allowed to move
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(!g.get_running()) {
			this.run = false;
		}
	}

	/**
	 * Overrides the Player's get_item method.
	 * Each Mob only has one item
	 */
	@Override
	public Item get_item(String s) {
		Item x = this.player_items.get(0);
		System.out.println( this.get_name() + " gave you an item!");
		this.player_items.remove(0);
		return x;
	}
	/**
	 * Checks if the Mob is allowed to walk if so go() method is called
	 * If not then they stay in their starting rooms
	 */
	@Override
	public void run() {
		if(this.run) {
			do {
				this.go();
				try {
					Thread.sleep(20000);
					this.kill_timer = this.kill_timer - 20000;
					if(this.kill_timer == 0 && this.antagonist) {
						g.endgame("You ran out of time!\n" +
								"The murderer killed you!\n" +
								"Thank you for playing.");
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(this.run);
			
		}
	}
}
