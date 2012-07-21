package au.net.hal9000.player.ui;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;

import au.net.hal9000.player.item.Item;

/**
 * The methods in this class allow the JTree component to traverse the file
 * system tree, and display the files and directories.
 **/
class ItemTreeModel implements TreeModel {
	// We specify the root directory when we create the model.
	protected Item root;

	public ItemTreeModel(Item root) {
		this.root = root;
	}

	// The model knows how to return the root object of the tree
	public Object getRoot() {
		return root;
	}

	// Tell JTree whether an object in the tree is a leaf or not
	public boolean isLeaf(Object node) {
		return  ((Item) node).isLeaf();
	}

	// Tell JTree how many children a node has
	public int getChildCount(Object node) {
		return ((Item) node).getChildCount();
	}

	// Fetch any numbered child of a node for the JTree.
	// Our model returns Item objects for all nodes in the tree. The
	// JTree displays these by calling the Item.toString() method.
	public Object getChild(Object parent, int index) {
		return ((Item) parent).getChild(index);
	}

	// Figure out a child's position in its parent node.
	public int getIndexOfChild(Object parent, Object child) {
		return ((Item) parent).getIndexOfChild((Item)child);
	}

	// This method is only invoked by the JTree for editable trees.
	// This TreeModel does not allow editing, so we do not implement
	// this method. The JTree editable property is false by default.
	public void valueForPathChanged(TreePath path, Object newvalue) {
	}

	// Since this is not an editable tree model, we never fire any events,
	// so we don't actually have to keep track of interested listeners.
	public void addTreeModelListener(TreeModelListener l) {
	}

	public void removeTreeModelListener(TreeModelListener l) {
	}
}
