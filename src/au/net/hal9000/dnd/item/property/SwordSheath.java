package au.net.hal9000.dnd.item.property;

import au.net.hal9000.dnd.item.Item;
import au.net.hal9000.dnd.item.Sword;
import au.net.hal9000.dnd.item.exception.ExceptionCantRemove;
import au.net.hal9000.dnd.item.exception.ExceptionCantWear;

public interface SwordSheath {
	void add(Sword s) throws ExceptionCantWear;

	Sword remove(Item l) throws ExceptionCantRemove;
}
