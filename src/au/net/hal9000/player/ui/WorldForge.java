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
		
		
        // some cookies		
		world.add(new Arrow("Arrow1"));
		world.add(new Backpack("Backpack1"));
		world.add(new Bag("Bag1"));
		world.add(new BagOfHolding(1));
		world.add(new Box("Box1"));
		world.add(new Candle());
		world.add(new Cloak());
		world.add(new Crossbow());
		world.add(new CrossbowBolt());
        world.add(new Hand());
        world.add(new Horse());
        world.add(new Human());
        world.add(new Location("Location"));
        world.add(new MagicRing());
        world.add(new Quiver());
        world.add(new Ring());
        world.add(new Scabbard());
        world.add(new Shield());
        world.add(new Sword());
        world.add(new Torch());
		
		
		// a bag of cookies
		Bag bag = new Bag("Bag1");
		bag.add(new Cookie("Cookie3"));
		bag.add(new Cookie("Cookie4"));
		bag.add(new Cookie("Cookie5"));
		world.add(bag);
		
		// a human with a bag of cookies
		Human human = new Human("Human1");
	//	human.setWeightMax(100000);
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