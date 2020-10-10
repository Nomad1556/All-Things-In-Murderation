package all_things_in_murderation;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//images courtesy of https://www.pexels.com/
/**
 * Extend JPanel and implements ActionListener for GUI  and Observer for better interaction between various game elements
 * Has a Scanner that get the player's input
 * The Player class
 * And the Game
 * And a boolean to determine if the game is running 
 * Generates the appropriate Swing components for the GUI
 * @author Nodar Sotkilava, Hal Stewart, Emily Pochet
 */
public class UI extends JPanel implements ActionListener, Observer{

	private Player p;
	private JTextArea textArea = new JTextArea("What is your name?");
	private JTextField textField = new JTextField();
	private JFrame frame = new JFrame();
	private JPanel Gpane = new JPanel(new BorderLayout());
	private JPanel Leftpane = new JPanel(new BorderLayout());
	private JLabel Glabel = new JLabel();
	private Boolean talking = false;
	private Game g;
	private Mob x;
	private Boolean running;
	/**
	 * Generates the UI and the appropriate Swing components with the passed Player class
	 * @param p the passed Player
	 * @param g the passed game
	 * @param r the game running status
	 */
	public UI(Player p, Game g, Boolean r) {
		this.p = p;
		
		textField.addActionListener(this);
		textField.setBorder(BorderFactory.createCompoundBorder(
                       BorderFactory.createTitledBorder("User Input"),
                       BorderFactory.createEmptyBorder(2,2,2,2)));
		textArea.setPreferredSize(new Dimension(350, 155));
		textArea.setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createTitledBorder("User Output"),
							BorderFactory.createEmptyBorder(2,2,2,2)));
		Gpane.setPreferredSize(new Dimension(200,300));
		Gpane.setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createTitledBorder("Graphics"),
							BorderFactory.createEmptyBorder(2,2,2,2)));
		
	
		Glabel.setIcon(p.get_current_room_icon());

		textArea.setEditable(false);
		textArea.setLineWrap(true);
		
		Leftpane.add(textField, BorderLayout.PAGE_START);
		Leftpane.add(textArea, BorderLayout.CENTER);
		
		Gpane.add(Glabel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(Leftpane, BorderLayout.LINE_START);
		frame.add(Gpane, BorderLayout.LINE_END);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		
		this.g = g;
		this.running = r;
	}
	/**
	 * Simple function which prints the possible commands to the console
	 */
	public void help() {
		textArea.setText("Commands: \n go <dir>"
				+ "\n get <item> "
				+ "\n drop <item>"
				+ "\n items <to get current items in the room>"
				+ "\n room <to get current room>"
				+ "\n exits <to get exits in the room> "
				+ "\n inv <to get current inventory>"
				+ "\n name <to get current player name>"
				+ "\n mob <to get current mobs in the room>"
				+ "\n talk <to interact with the mobs in the room>"
				+ "\n exitgame <to exit the game>"
				+ "\n help <to view commands again>");
	}
	/**
	 * Simple function which prints the starting room's description
	 */
	public void splash_screen() {
		textArea.setText(p.get_current_room_disc() + 
				"\nType help to view commands");
		
	}
	
	/**
	 * The main meat function of the UI
	 * Gets the proper commands from the player using Scanner and 
	 * Calls the Player methods to interact with the other classes
	 * So this is where the game runs
	 * Uses switch cases for easier management
	 * Calls different Player methods depending on the player's input
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//If the player has changed their name in the beginning
		//if they are not talking to a Mob and if the end game conditions are not met then the main game commands are allowed to be used
		if (p.name_set && !this.talking) {
			Scanner newScanner = new Scanner(textField.getText());
			textField.setText("");
			String command;
			command = newScanner.next();
			if(p.get_inv() != null) {
				if(p.get_inv().contains("strange flashlight") && p.get_inv().contains("blank paper")) {
				g.endgame( "You caught the murderer! It was the Jester!\n" + "Jester: Don’t you all just want to have a little fun making people laugh drove me crazy you all did especially her it was time for her to go.\n"
						+ "I couldn’t handle it anymore disappointment after disappointment with being with her.");
				}
			}
			switch (command.toLowerCase()) {
			case "exitgame":
				g.endgame("You exited the game!\n" +
						"Thank you for playing!"); //Currently the only game end condition but more will be added 
				break;
			case "go":
				if (p.go(newScanner.next())) {
					textArea.setText(p.get_current_room_disc());
					Glabel.setIcon(p.get_current_room_icon());
					g.setchange("go", p.get_name());
				}
				else 
					textArea.setText("No exit that way!");
				break;
			case "items":
				if (p.look_items() != null)
					textArea.setText("The current items in the room are: " + p.look_items());
				else textArea.setText("There are no items in the room.");
				break;
			case "exits":
				if(p.look_exits() != null)
					textArea.setText("The current exits are:" + p.look_exits());
				else textArea.setText("There are no exits here." +
										"\nWait, how did you even get it then?");
				break;
			case "room":
				textArea.setText("You're in " + p.look_room());
				break;
			case "inv":
				if (p.get_inv() != null)
					textArea.setText(p.get_inv());
				else textArea.setText("Your inventory is empty!");
				break;
			case "get":
				String n = newScanner.next();
				if (newScanner.hasNext()) {
					n = n + " " + newScanner.next();
					if(!p.Pickup_Item(n)) {
						textArea.setText("Couldn't get");
						break;
					}
					else g.setchange("get", p.get_name());
					break;
				}
				else if (!p.Pickup_Item(n)) {
					textArea.setText("Couldn't get");
					break;
				}
				else g.setchange("get", p.get_name());
				break;
			case "drop":
				if (!p.Drop_Item(newScanner.next())) {
					 textArea.setText("Couldn't drop");
					 break;
				}
				else g.setchange("drop", p.get_name());
				break;
			case "name":
				textArea.setText("Your name is " + p.get_name() + ", how could you forget?");
				break;
			case "help":
				this.help();
				break;
			case "talk":
				if (p.get_mobs_in_room() != null) {
					textArea.setText("Who do you want to talk to? " + 
								"The current people in the room are: " 
								+ p.get_mobs_in_room() + "\nType the name of the person\n" 
								+ "And a command to interact with them\n" +
								"Commands:\nMob name + get <gives you an item>"
								+ "\nMob name + speak <to talk>"
 								+ "\nstop <to stop talking>" 
								+"\nhelp <to view commands again>");
					this.talking = true; //Talking to a Mob now
					break;
				}
				else textArea.setText("There's no one in the room to talk to!");
				break;
			case "mob":
				if (p.get_mobs_in_room() != null)
					textArea.setText("The current people in the room are: " + p.get_mobs_in_room());
				else textArea.setText("There's no one in the room!");
				break;
			default:
				textArea.setText("Unrecognized command!");
				break;
		    }
		newScanner.close();
		}
		//Changes the player name in the beginning of the game
		else if(!p.name_set) {
			Scanner newScanner = new Scanner(textField.getText());
			textField.setText("");
			if(newScanner.hasNext()) {
				p.change_name(newScanner.next());
				this.splash_screen();
				p.name_set = true;
				textField.setText("");
				newScanner.close();
			}
		}
		//Player is now talking to a Mob
		else if (talking) {
			Scanner newScanner = new Scanner(textField.getText());
			textField.setText("");
			if(newScanner.hasNext()) {
				String name;
				String command;
				name = newScanner.next();
				if(newScanner.hasNext()) {
						command = newScanner.next();
						this.x = p.get_mob(name); //Gets the desired Mob
						if(x != null) {
							this.talk(command, x); 
							if (!x.talking()) {
								x.negate_talk(); //Stop the Mob from moving when the Player is interacting with them
							}
						}
						else{
							textArea.setText("Unrecognized person's name!");
						}
					}
				else if(name.equals("help")) {
					textArea.setText("Type the name of the person\n" 
								+ "And a command to interact with them\n" +
								"Commands:\nMob name + get <gives you an item>"
								+ "\nMob name + speak <to talk>"
 								+ "\nstop <to stop talking>" 
								+"\nhelp <to view commands again>");
				}
				else if(name.equals("stop")) { //Stop talking and returns to the main game commands
					this.talking = false;
					if (x != null) { //Because the player could first stop talking without getting a desired Mob a simple check is added
						x.negate_talk();
						textArea.setText("You've stopped talking!");
					}
					textArea.setText("You've stopped talking!");
				}
				else {
					textArea.setText("Unrecognized command!");
				}
				newScanner.close();
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(!((Game)o).get_running()) { //Checks for the end game condition every time a game condition changes
			
				this.frame.setVisible(false); //properly closes the JFrame
				this.frame.dispose();
				
				JFrame exit_screen = new JFrame(); //A simple end game screen is added for flavor
				JTextArea exit_text = new JTextArea((String)arg);
				JLabel exit_imgame = new JLabel();
				
				exit_text.setLineWrap(true);
				
				exit_imgame.setIcon(new ImageIcon("src/graphics/IMG_3129.jpeg"));
				exit_text.setEditable(false);
				exit_screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				exit_screen.setLayout(new GridLayout(2,1));
				exit_screen.add(exit_text);
				exit_screen.add(exit_imgame, BorderLayout.LINE_END);
				exit_screen.setPreferredSize(new Dimension(300,300));
				exit_screen.pack();
				exit_screen.setResizable(false);
				exit_screen.setVisible(true);
		}
		else { //If the game is still running then notify the UI of the game condition changes
			this.textArea.append("\n" + (String)arg);
		}
		
		
	}
	/**
	 * Main function to interact with a Mob
	 * @param n Desired command
	 * @param x Mob currently interacting with
	 */
	public void talk(String n, Mob x) {
		textField.setText("");
		switch(n.toLowerCase()) {
		//Grabs an item from the Mob and adds it to the player inventory
		case"get": 
			if(x.get_inv() != null) {
				Item i = x.get_item("");
				p.add_item(i);
				textArea.setText(x.get_name() + " gave you " + "a " + i.getName() + "!");
				break;
			}
			else {
				textArea.setText(x.get_name() + " has nothing to give you!");
				break;
			}
		//Gets the dialogue from the Mob
		case"speak": 
			String d = x.get_dialogue();
			if(d !=  null) {
				textArea.setText(x.get_name() + ": " + d);
				break;
			}
			else {
				textArea.setText("Something went vewy vewy wong");
				break;
			}
		default:
			textArea.setText("Unrecognized command!");
			break;
		}
	}
	
}
