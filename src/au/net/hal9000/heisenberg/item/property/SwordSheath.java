package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.IItem;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.exception.ExceptionCantRemove;
import au.net.hal9000.heisenberg.item.exception.ExceptionCantWear;

public interface SwordSheath {
	void add(Sword s) throws ExceptionCantWear;

	Sword remove(IItem l) throws ExceptionCantRemove;
}
