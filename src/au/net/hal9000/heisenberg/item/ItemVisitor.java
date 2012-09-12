package au.net.hal9000.heisenberg.item;

import java.util.Vector;

public interface ItemVisitor {
	void visit(IItem iItem);

	void visit(Vector<IItem> iItems);

}
