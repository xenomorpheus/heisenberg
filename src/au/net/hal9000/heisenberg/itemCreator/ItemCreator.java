package au.net.hal9000.heisenberg.itemCreator;

import javax.swing.*;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.item.Box;

public class ItemCreator {

	@SuppressWarnings("deprecation")
	public static Location getWorld() {
		// Ad-hoc world
		Location world = new Location("World");
		world.setWeightMax(100000);
		world.setVolumeMax(100000);

		// Scabbard
		Scabbard scabbard = new Scabbard();
		scabbard.add(new Sword());

		Scabbard scabbard2 = new Scabbard("Scabbard2");
		scabbard2.add(new Sword());

		Bag boh = new BagOfHolding(1);
		boh.add(new Bag("Bag1"));
		boh.add(new Box("Box1"));
		boh.add(new Candle());
		boh.add(new Cloak());
		boh.add(new Cookie("Cookie1"));
		boh.add(new Crossbow());
		boh.add(new CrossbowBolt());
		boh.add(new MagicRing());
		boh.add(new Ring());
		boh.add(scabbard);
		boh.add(new Torch());

		// a backpack of stuff
		Bag backpack = new Backpack("Backpack1");
		backpack.setWeightMax(100000);
		backpack.setVolumeMax(100000);
		backpack.add(boh);

		Quiver quiver = new Quiver();
		quiver.add(new Arrow());
		quiver.add(new Arrow());
		quiver.add(new Arrow());

		Bag bag2 = new Bag("Bag2");
		bag2.add(new Cookie("Cookie6"));
		bag2.add(new Cookie("Cookie7"));
		bag2.add(new Cookie("Cookie8"));
		backpack.add(bag2);

		// a human with a bag of cookies
		Human human = new Human("Human1");
		world.add(human);
		human.setWeightMax(100000);
		human.setVolumeMax(100000);

		human.add(new Shield());
		human.add(scabbard2);
		human.add(quiver);
		human.add(backpack);

		world.add(new Sword());
		world.add(new Horse());

		// bag3
		Bag bag3 = new Bag("Bag3");
		bag3.add(new Cookie("Cookie9"));
		bag3.add(new Cookie("Cookie10"));
		bag3.add(new Cookie("Cookie11"));
		world.add(bag3);
		return world;
	}

	public static void main(String[] args) {
		Location location = getWorld();

		// Create a TreeModel object to represent our tree of files
		ItemTreeModel model = new ItemTreeModel(location);

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