package all_things_in_murderation;
/**
 * Simple Item class,
 * Only has a name
 * @author Nodar Sotkilava, Hal Stewart, Emily Pochet
 */
public class Item {
	private String Item;
	/** 
	 * Simple constructor; only using passed item's name
	 * @param name The passed item's name
	 */
		public Item(String name)
		{
			this.Item = name;
		}
		/**
		 * Returns in the item's name
		 * @return Item's name
		 */
		public String getName() {
			return Item;
		}
}
