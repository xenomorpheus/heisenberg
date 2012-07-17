package au.net.hal9000.player.item;

import java.util.Vector;

public interface ItemVisitor {
	void visit(Item item);

	void visit(Vector<Item> items);

}