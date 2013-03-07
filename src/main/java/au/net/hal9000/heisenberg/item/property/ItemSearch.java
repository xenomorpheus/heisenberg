package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.*;

import java.util.Vector;

public abstract class ItemSearch implements ItemVisitor {

	private Vector<Item> matchingItems;

	public ItemSearch() {
		super();
		this.matchingItems = new Vector<Item>();
	}

	public void addMatchingItems(Item Item) {
		matchingItems.add(Item);
	}
	public Vector<Item> getMatchingItems() {
		return matchingItems;
	}

	public int getMatchingItemsCount() {
		return matchingItems.size();
	}
	
	public abstract void visit(Item Item);

	public abstract void visit(Vector<Item> Item);
}
