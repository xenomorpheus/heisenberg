package au.net.hal9000.player.item.property;

import au.net.hal9000.player.item.Item;
import au.net.hal9000.player.item.Sword;
import au.net.hal9000.player.item.exception.ExceptionCantRemove;
import au.net.hal9000.player.item.exception.ExceptionCantWear;

public interface SwordSheath {
	void add(Sword s) throws ExceptionCantWear;

	Sword remove(Item l) throws ExceptionCantRemove;
}
