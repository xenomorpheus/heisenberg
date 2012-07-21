package au.net.hal9000.player.ui;

import javax.swing.*;
import au.net.hal9000.player.item.*;

public class WorldForge {
	public static void main(String[] args) {

		// Ad-hoc world
		Location world = new Location("World");
		world.add(new Cookie("Cookie1"));
		world.add(new Cookie("Cookie2"));
		Bag bag = new Bag("Bag1");
		bag.add(new Cookie("Cookie3"));
		bag.add(new Cookie("Cookie4"));
		bag.add(new Cookie("Cookie5"));
		world.add(bag);

		// Create a TreeModel object to represent our tree of files
		ItemTreeModel model = new ItemTreeModel(bag);

		// Create a JTree and tell it to display our model
		JTree tree = new JTree();
		tree.setModel(model);

		// The JTree can get big, so allow it to scroll.
		JScrollPane scrollpane = new JScrollPane(tree);

		// Display it all in a window and make the window appear
		JFrame frame = new JFrame("Item Tree Demo");
		frame.getContentPane().add(scrollpane, "Center");
		frame.setSize(400, 600);
		frame.setVisible(true);
	}
}