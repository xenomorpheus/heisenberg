package au.net.hal9000.player.item.property;

import au.net.hal9000.player.item.*;

import java.util.Vector;

public abstract class ItemSearch implements ItemVisitor {

	private Vector<Item> matchingItems;

	public ItemSearch() {
		super();
		this.matchingItems = new Vector<Item>();
	}

	public void addMatchingItems(Item item) {
		matchingItems.add(item);
	}
	public Vector<Item> getMatchingItems() {
		return matchingItems;
	}

	public int getMatchingItemsCount() {
		return matchingItems.size();
	}
	
	public abstract void visit(Item item);

	public abstract void visit(Vector<Item> item);
}
