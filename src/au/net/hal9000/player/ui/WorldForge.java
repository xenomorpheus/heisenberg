package au.net.hal9000.player.ui;

import javax.swing.*;
import java.io.File;

public class WorldForge {
	public static void main(String[] args) {
		// Figure out where in the file-system to start displaying
		File root;
		if (args.length > 0)
			root = new File(args[0]);
		else
			root = new File(System.getProperty("user.home"));

		// Create a TreeModel object to represent our tree of files
		ItemTreeModel model = new ItemTreeModel(root);

		// Create a JTree and tell it to display our model
		JTree tree = new JTree();
		tree.setModel(model);

		// The JTree can get big, so allow it to scroll.
		JScrollPane scrollpane = new JScrollPane(tree);

		// Display it all in a window and make the window appear
		JFrame frame = new JFrame("FileTreeDemo");
		frame.getContentPane().add(scrollpane, "Center");
		frame.setSize(400, 600);
		frame.setVisible(true);
	}
}