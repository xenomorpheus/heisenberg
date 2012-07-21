package au.net.hal9000.player.ui;

import javax.swing.*;
import au.net.hal9000.player.item.*;
import au.net.hal9000.player.item.Box;

public class WorldForge {

	public static void main(String[] args) {

		// Ad-hoc world
		Location world = new Location("World");
		world.setWeightMax(100000);
		world.setVolumeMax(100000);
		world.add(new Sword());

		// Scabbard
		Scabbard scabbard = new Scabbard();
		scabbard.add(new Sword());

		// a bag of stuff
		Bag bag = new Bag("Bag1");
		bag.setWeightMax(100000);
		bag.setVolumeMax(100000);
		bag.add(new Arrow("Arrow1"));
		bag.add(new Backpack("Backpack1"));
		bag.add(new Bag("Bag1"));
		bag.add(new BagOfHolding(1));
		bag.add(new Box("Box1"));
		bag.add(new Candle());
		bag.add(new Cloak());
		bag.add(new Cookie("Cookie1"));
		bag.add(new Crossbow());
		bag.add(new CrossbowBolt());
		bag.add(new Hand());
		bag.add(new Horse());
		bag.add(new Human());
		bag.add(new Location("Location"));
		bag.add(new MagicRing());
		bag.add(new Quiver());
		bag.add(new Ring());
		bag.add(scabbard);
		bag.add(new Shield());
		bag.add(new Torch());
		world.add(bag);

		Bag bag3 = new Bag("Bag3");
		bag3.add(new Cookie("Cookie9"));
		bag3.add(new Cookie("Cookie10"));
		bag3.add(new Cookie("Cookie11"));
		world.add(bag3);

		// a human with a bag of cookies
		Human human = new Human("Human1");
		// human.setWeightMax(100000);
		Bag bag2 = new Bag("Bag2");
		bag2.add(new Cookie("Cookie6"));
		bag2.add(new Cookie("Cookie7"));
		bag2.add(new Cookie("Cookie8"));

		human.equip(bag2);
		world.add(human);

		// Create a TreeModel object to represent our tree of files
		ItemTreeModel model = new ItemTreeModel(world);

		// Create a JTree and tell it to display our model
		JTree tree = new JTree();
		tree.setModel(model);

		// The JTree can get big, so allow it to scroll.
		JScrollPane scrollpane = new JScrollPane(tree);

		// Display it all in a window and make the window appear
		JFrame frame = new JFrame("Item Tree Demo");
		frame.addWindowListener(new ExitListener());
		frame.getContentPane().add(scrollpane, "Center");
		frame.setSize(400, 600);
		frame.setVisible(true);
	}
}