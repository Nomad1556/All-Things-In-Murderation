package all_things_in_murderation;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * A simple main menu where the two players or one player are picked
 * @author Nodar Sotkilava, Hal Stewart, and Emily Pochet
 *
 */
public class MainMenu extends JFrame implements MouseListener {
	private JButton player1;
	private JButton player2;
	private JLabel label;
	
	/**
	 * Making our Main Menu using Swing for the GUI.
	 */
	public MainMenu() {
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(3,1));
		player1 = new JButton("One Player");
		player2 = new JButton("Two Players");
		label = new JLabel("Select number of Players");
		cp.add(label);
		cp.add(player1);
		cp.add(player2);
		
		player1.addMouseListener(this);
		player2.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//Once a button is clicked, properly dispose of the main menu and run Game
		if (e.getSource() == player1){
			this.setVisible(false);
			new Game(1);
			this.dispose();
		}
		else if(e.getSource() == player2) {
			this.setVisible(false);
			new Game(2);
			this.dispose();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		MainMenu m = new MainMenu();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setSize(300,300);
		m.setResizable(false);
		m.setVisible(true);
	}

}
