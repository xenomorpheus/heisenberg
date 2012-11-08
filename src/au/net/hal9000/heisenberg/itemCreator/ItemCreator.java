package au.net.hal9000.heisenberg.itemCreator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.item.Box;

public class ItemCreator {
	Location location = null;

	// Create a TreeModel object to represent our m_tree of files
	ItemTreeModel model = null;
	JTree m_tree = null;

	public ItemCreator(Location pLocation) {
		location = pLocation;
		// Create a TreeModel object to represent our m_tree of files
		model = new ItemTreeModel(location);
		m_tree = new JTree();

		// Create a JTree and tell it to display our model
		m_tree.setModel(model);
		m_tree.setEditable(true);
		m_tree.setSelectionRow(0);

		// The JTree can get big, so allow it to scroll.
		JScrollPane scrollpane = new JScrollPane(m_tree);

		// Display it all in a window and make the window appear
		JFrame frame = new JFrame("Item Creator");
		frame.addWindowListener(new ExitListener());
		frame.getContentPane().add(scrollpane, BorderLayout.CENTER);

		JPanel addPanel = new JPanel();
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				IItem selNode = (IItem) m_tree.getLastSelectedPathComponent();
				// TODO also check for Item Container
				if (selNode != null) {
					// TODO Temp - Change the type of the new node to a Bag.
					// TODO Later - Use a popup window to choose the type of
					// item.
					IItem newNode = new Bag("New Node");
					model.insertNodeInto(newNode, selNode,
							selNode.getChildCount());
					TreeNode[] nodes = model.getPathToRoot(newNode);
					TreePath path = new TreePath(nodes);
					m_tree.scrollPathToVisible(path);
					m_tree.setSelectionPath(path);
					m_tree.startEditingAtPath(path);
				}
			}
		});
		addPanel.add(addButton);
		frame.getContentPane().add(addPanel, BorderLayout.SOUTH);

		frame.setSize(400, 600);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new ItemCreator(getWorld());
	}

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

}