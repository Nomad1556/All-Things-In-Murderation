package all_things_in_murderation;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * Generates all the needed things for the game to function
 * Such as the Rooms, Items, Exits, Player, UI and ImageIcons to be used as graphics
 * Game is now an Observable for more efficient Mob and Player interactions
 * @author Nodar Sotkilava, Hal Stewart, Emily Pochet
 */
public class Game extends Observable {
	
	private Boolean running;
	//Each Mob in the game is its own thread
	private ExecutorService service = Executors.newFixedThreadPool(5);
	private int g;
	private ArrayList<Player>players = new ArrayList<Player>();
	private ArrayList<UI>uis = new ArrayList<UI>();
	
	
	Game(int g) {
		//Making rooms...
		Room lr = new Room("Living Room", "You begin the game in the living room.\n"
				+ "The party guests have all arrived here enjoying drinks and \nmeals.\n" +
				"A loud crash is heard outside\n" + 
				"The entire party travels outside and sees the queen hanging dead from the balcony.\n" +
				"Everyone begins to panic and accuse you of killing her \nsince you were the last invited guest.\n" +
				"Now you must prove your innocence\n", new ImageIcon("src/graphics/living room.jpeg"));
		Room lb= new Room("Library", "You enter the library.\n You find books on the floor"
				+ "and on the shelves\n. On the ground you see a letter.", new ImageIcon("src/graphics/library.jpeg"));
		Room dr = new Room("Dining Room", "You enter the dining room. \n You find"
				+ "some of the party guests still in shock.", new ImageIcon("src/graphics/dining room.jpeg") );
		Room hb = new Room("Half Bath", "You find the half bath. \n You go to open the door."
				+ "\n The door is locked.", new ImageIcon("src/graphics/half_bath.jpeg"));
		Room pr = new Room("Party Room", "You enter the party room. \n"
				+ "Some guests are also gathered in here. \nYou find some ballons and a"
				+ " hankerchief", new ImageIcon("src/graphics/party_room.jpeg"));
		Room ip = new Room("Indoor Pool", "You enter the indoor pool. \n You see a"
				+ " giant inflatable 42.", new ImageIcon("src/graphics/inside_pool.jpeg"));
		Room ulr = new Room("Upper Living Room", "You enter the upstairs living room. \n"
				+ "Tucked in the corner you see part of a rope", new ImageIcon("src/graphics/living_room2.jpeg") );
		Room b3 = new Room("Bedroom 3", "You try to enter the 3rd Bedroom, but the door is locked." , 
				new ImageIcon("src/graphics/bedroom3.jpeg"));
		Room mbth = new Room("Master Bath", "You enter the Master Bath. \n"
				+ "You see a few towels on the floor with blood on one of them.", 
				new ImageIcon("src/graphics/master_bath.jpeg"));
		Room mbd = new Room("Master Bedroom", "You enter the Master Bedroom. \n"
				+ "The bedroom is a mess with pillows on the ground. A vase is broken."
				+ "\n You also find a piece of paper with the name of the queen on it.", 
				new ImageIcon("src/graphics/master_bedroom.jpeg"));
		Room b1 = new Room("Bedroom 1", "You enter the 1st Bedroom. \n"
				+ "You find the room to be empty.", new ImageIcon("src/graphics/bedroom1.jpeg"));
		Room b2 = new Room("Bedroom 2", "You enter the 2nd Bedroom. \n "
				+ "You find a piece of string on the ground.", new ImageIcon("src/graphics/bedroom2.jpeg"));
		Room bth1 = new Room("Bathroom 1", "You enter the 1st Bathroom. \n"
				+ "The bathroom is meticulous almost too meticulous.", new ImageIcon("src/graphics/half_bath.jpeg"));
		Room gr = new Room("Guest Room", "You enter the Guest Room. \n "
				+ "You find a pile of empty papers on the ground. \n", new ImageIcon("src/graphics/guest_room.jpeg"));
		Room at = new Room("Attic", "You enter the attic. \n"
				+ "You find a strange flashlight which is different than yours."
				, new ImageIcon("src/graphics/attic.jpeg"));
		Room bs = new Room("Basement", "You enter the basement. \n"
				+ "You find a hammer, a mousetrap, and a skeleton.", new ImageIcon("src/graphics/basement.jpeg"));
		Room wc = new Room("Wine Cellar", "You enter the wine cellar. \n "
				+ "You find 42 bottles of wine.", new ImageIcon("src/graphics/winecellar.jpeg"));
		Room os = new Room("Outside", "You enter the outside of the mansion. \n"
				+ "You find a battery on the ground.", new ImageIcon("src/graphics/outside.jpeg"));
		Room op = new Room("Outdoor Pool", "You enter to the outdoor pool. \n"
				+ "The pool is empty.", new ImageIcon("src/graphics/pool.jpeg"));
		Room wds = new Room("Woods", "You enter the dark damp woods and find a flashlight on the ground.", 
				new ImageIcon("src/graphics/outside.jpeg"));
		
		Room test = new Room("Test Room", "\nYou find yourself in an empty room" + 
											"\nThe glorious developers placed you here!", 
											new ImageIcon("src/graphics/placeholder_img.jpeg"));
		
		//Making exits...
		Exit lre1 = new Exit("northwest", "To Library", lb);
		Exit lre2 = new Exit("northeast", "To Dining Room", dr);
		Exit lre3 = new Exit("west", "To Party Room", pr);
		Exit lbe1 = new Exit("south", "To Living Room", lr);
		Exit lbe2 = new Exit("downstairs", "To Basement", bs);
		Exit dre = new Exit("south", "To Living Room", lr);
		Exit pre1 = new Exit("northwest", "To Pool", ip);
		Exit pre2 = new Exit("southwest", "To Half Bath", hb);
		Exit pre3 = new Exit("northeast", "To Outside", os);
		Exit pre4 = new Exit("upstairs", "To Upper Living Room", ulr);
		Exit pre5 = new Exit("east", "To Living Room", lr);
		Exit ipe = new Exit("south", "To Party Room", pr);
		Exit hbe = new Exit("north", "To Party Room", pr);
		Exit ose1 = new Exit("south", "To Party Room", pr);
		Exit ose2 = new Exit("west", "To Outdoor Pool", op);
		Exit ose3 = new Exit("east", "To Woods", wds);
		Exit wdse = new Exit("west", "To Outside", os);
		Exit ope = new Exit("east", "To Outside", os);
		Exit ulre1 = new Exit("guest", "To Guest Room", gr);
		Exit ulre2 = new Exit("bath1", "To Bathroom 1 ", bth1);
		Exit ulre3 = new Exit("bed1", "To Bedroom 1", b1);
		Exit ulre4 = new Exit("bed2", "To Bedroom 2", b2);
		Exit ulre5 = new Exit("masterbed", "To Master Bedroom", mbd);
		Exit ulre6 = new Exit("masterbath", "To Master Bath", mbth);
		Exit ulre7 = new Exit("bed3", "To Bedroom 3", b3);
		Exit ulre8 = new Exit("upstairs", "To Attic", at);
		Exit ulre9 = new Exit("downstairs", "To Party Room", pr);
		Exit gre = new Exit("upperliving", "To Upper Living Room", ulr);
		Exit bath1e = new Exit("upperliving", "To Upper Living Room", ulr);
		Exit b1e = new Exit("upperliving", "To Upper Living Room", ulr);
		Exit b2e = new Exit("upperliving", "To Upper Living Room", ulr);
		Exit mbde = new Exit("upperliving", "To Upper Living Room", ulr);
		Exit mbthe = new Exit("upperliving", "To Upper Living Room", ulr);
		Exit b3e = new Exit("upperliving", "To Upper Living Room", ulr);
		Exit ate = new Exit("upperliving", "To Upper Living Room", ulr);
		Exit bse1 = new Exit("winecellar", "To Wine Cellar", wc);
		Exit bse2 = new Exit("upstairs", "To Library", lb);
		Exit wce = new Exit("basement", "To Basement", bs);
		
		//Making items...
		Item flashlight = new Item("flashlight");
		Item letter = new Item("letter");
		Item dagger = new Item("dagger");
		//Item key = new Item ("key");
		Item handkerchief = new Item ("handkerchief");
		Item rope = new Item ("rope");
		Item bloody_towel = new Item ("bloody towel");
		Item paper = new Item ("paper");
		Item string = new Item ("string");
		Item blank_papers = new Item ("blank papers");
		Item strange_flashlight = new Item ("strange flashlight");
		Item photo_album = new Item ("photo album");
		Item hammer = new Item ("hammer");
		Item mousetrap = new Item ("mousetrap");
		Item battery = new Item ("battery");
		
		
	//adding items all in one room for testing
//		lr.addItem(flashlight);
//		lr.addItem(key);
//		lr.addItem(handkerchief);
//		lr.addItem(rope);
//		lr.addItem(bloody_towel);
//		lr.addItem(string);
		//lr.addItem(blank_paper);
		//lr.addItem(strange_flashlight);
//		lr.addItem(hammer);
//		lr.addItem(mousetrap);
		//lr.addItem(battery);
		
		//Adding items...
		lb.addItem(letter);
		
		ulr.addItem(rope);
		
		pr.addItem(handkerchief);
		
		at.addItem(strange_flashlight);
		
		mbd.addItem(paper);
		
		b2.addItem(string);
		
		gr.addItem(blank_papers);
		
		bs.addItem(hammer);
		bs.addItem(mousetrap);
		
		os.addItem(battery);
		
		mbth.addItem(bloody_towel);
		
		wds.addItem(flashlight);
		
		
		//Adding exits...
		lr.addExit(lre1);
		lr.addExit(lre2);
		lr.addExit(lre3);
		
		lb.addExit(lbe1);
		lb.addExit(lbe2);
		
		dr.addExit(dre);
		
		pr.addExit(pre1);
		pr.addExit(pre2);
		pr.addExit(pre3);
		pr.addExit(pre4);
		pr.addExit(pre5);
		
		ip.addExit(ipe);
		
		hb.addExit(hbe);
		
		os.addExit(ose1);
		os.addExit(ose2);
		os.addExit(ose3);
		
		wds.addExit(wdse);
		
		op.addExit(ope);
		
		ulr.addExit(ulre1);
		ulr.addExit(ulre2);
		ulr.addExit(ulre3);
		ulr.addExit(ulre4);
		ulr.addExit(ulre5);
		ulr.addExit(ulre6);
		ulr.addExit(ulre7);
		ulr.addExit(ulre8);
		ulr.addExit(ulre9);
		
		gr.addExit(gre);
		
		bth1.addExit(bath1e);
		
		b1.addExit(b1e);
		b2.addExit(b2e);
		b3.addExit(b3e);
		
		mbd.addExit(mbde);
		mbth.addExit(mbthe);
		
		at.addExit(ate);
		bs.addExit(bse1);
		bs.addExit(bse2);
		wc.addExit(wce);
		
		//Sets the game state to be true
		this.running = true;
		
		//Number of Players in our game
		this.g = g;
		
		//Making Players...
		for(int i = 1; i <= this.g; i++) {
			this.players.add(new Player(lr, "Bob"));
		}
		//Making UIs...
		int f = 0;
		for(Player i: this.players) {
			this.uis.add(new UI(i, this, this.running));
			this.addObserver(uis.get(f));
			f++;
		}
		
		//Making Mobs and adding dialogue...
		Mob m1 = new Mob(ulr, "Maiden", true, this, false);
		
		m1.add_dialogue(1, "I have been told that there are\n" + 
				"certain things that people know about the Queen. I�ve heard a few rumors she was cheating on\n" + 
				"the king. You didn�t hear it from me but there are some photos I�ve seen for proof.");
		
		Mob m2 = new Mob(os, "Knight", true, this, false);
		
		m2.add_dialogue(1, "Well I can give you this dagger I found. It might have been used to cut something the blade\n" + 
				"doesn�t seem that sharp.");
		m2.add_dialogue(2, "Has that dagger come into good use? I really\n" + 
				"hope you are able to find who killed her. I don�t believe it to be you but you never know who\n" + 
				"you may come across.");
		
		Mob m3 = new Mob(lb, "Jester", true, this, true);
		
		m3.add_dialogue(1, "I actually\n" + 
				"think the Knight may have done it. I mean he�s the only one strong enough. I don�t think its you,\n" + 
				"but it could have been the king he is a jealous type and all.");
		m3.add_dialogue(2, "Hmm I found this photo album with all of\n" + 
				"us in it except for you. Maybe there is some evidence in there.");
		
		Mob m4 = new Mob(lr, "King", false,this,false);
		
		m4.add_dialogue(1, "...");
		
		Mob m5 = new Mob(mbd, "Nurse", false, this, false);
		
		m5.add_dialogue(1, "I don�t know why anyone would do this. She was such a kindhearted soul.");
		
		m2.add_item(dagger);
		m3.add_item(photo_album);
		
		
		//Adding Mobs to our Executor Service...
		this.addObserver(m1);
		this.addObserver(m2);
		this.addObserver(m3);
		this.addObserver(m4);
		this.addObserver(m5);
		
		//Running them...
		service.execute(m1);
		service.execute(m2);
		service.execute(m3);
		service.execute(m4);
		service.execute(m5);
		
	}
	/**
	 * When an end-game condition is met, this method is called
	 * @param x The appropriate end game message
	 */
	public void endgame(String x) {
		this.running = false;
		this.setChanged();
		this.notifyObservers(x);
		service.shutdown();
	}
	
	/**
	 * Notifies all the current UIs which Player did what action
	 * @param x Player action
	 * @param y Player name
	 */
	public void setchange(String x, String y) {
		String z;
		switch(x) {
		case "get":
			z = y + " got an item!";
			break;
		case "go":
			z = y + " went to another room";
			break;
		case "drop":
			z = y + " dropped an item!";
			break;
		default:
			z = "Error";
			break;
		}
		this.setChanged();
		this.notifyObservers(z);
	}
	/**
	 * Gets if the game is running
	 * @return if the game is running
	 */
	public Boolean get_running() {
		return this.running;
	}

}
