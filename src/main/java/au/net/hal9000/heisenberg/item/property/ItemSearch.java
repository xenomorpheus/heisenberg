package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.*;

import java.util.Vector;

public abstract class ItemSearch implements ItemVisitor {

	private Vector<IItem> matchingItems;

	public ItemSearch() {
		super();
		this.matchingItems = new Vector<IItem>();
	}

	public void addMatchingItems(IItem iItem) {
		matchingItems.add(iItem);
	}
	public Vector<IItem> getMatchingItems() {
		return matchingItems;
	}

	public int getMatchingItemsCount() {
		return matchingItems.size();
	}
	
	public abstract void visit(IItem iItem);

	public abstract void visit(Vector<IItem> iItem);
}
