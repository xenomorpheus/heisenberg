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
		world.add(new Horse());

		// Scabbard
		Scabbard scabbard = new Scabbard();
		scabbard.add(new Sword());

		Bag bag3 = new Bag("Bag3");
		bag3.add(new Cookie("Cookie9"));
		bag3.add(new Cookie("Cookie10"));
		bag3.add(new Cookie("Cookie11"));
		world.add(bag3);

		// a human with a bag of cookies
		Human human = new Human("Human1");
		world.add(human);
		human.setWeightMax(100000);
		human.setVolumeMax(100000);
		
		// a backpack of stuff
		Bag backpack = new Backpack("Backpack1");
		backpack.setWeightMax(100000);
		backpack.setVolumeMax(100000);
		backpack.add(new Arrow("Arrow1"));
		backpack.add(new Backpack("Backpack1"));
		backpack.add(new Bag("Bag1"));
		backpack.add(new BagOfHolding(1));
		backpack.add(new Box("Box1"));
		backpack.add(new Candle());
		backpack.add(new Cloak());
		backpack.add(new Cookie("Cookie1"));
		backpack.add(new Crossbow());
		backpack.add(new CrossbowBolt());
		backpack.add(new Hand());
		backpack.add(new Human());
		backpack.add(new Location("Location"));
		backpack.add(new MagicRing());
		backpack.add(new Quiver());
		backpack.add(new Ring());
		backpack.add(scabbard);
		backpack.add(new Shield());
		backpack.add(new Torch());
		human.equip(backpack);

		Bag bag2 = new Bag("Bag2");
		human.equip(bag2);
		bag2.add(new Cookie("Cookie6"));
		bag2.add(new Cookie("Cookie7"));
		bag2.add(new Cookie("Cookie8"));

		Scabbard scabbard2 = new Scabbard("Scabbard2");
		scabbard2.add(new Sword());
		human.equip(scabbard2);
		
		// Create a TreeModel object to represent our tree of files
		ItemTreeModel model = new ItemTreeModel(world);

		// Create a JTree and tell it to display our model
		JTree tree = new JTree();
		tree.setModel(model);

		// The JTree can get big, so allow it to scroll.
		JScrollPane scrollpane = new JScrollPane(tree);

		// Display it all in a window and make the window appear
		JFrame frame = new JFrame("IItem Tree Demo");
		frame.addWindowListener(new ExitListener());
		frame.getContentPane().add(scrollpane, "Center");
		frame.setSize(400, 600);
		frame.setVisible(true);
	}
}