package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.exception.ExceptionCantRemove;
import au.net.hal9000.dnd.item.exception.ExceptionCantWear;

public interface SwordSheath {
	void add(Sword s) throws ExceptionCantWear;

	Sword remove(Location l) throws ExceptionCantRemove;
}
