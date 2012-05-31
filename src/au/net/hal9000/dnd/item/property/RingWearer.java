package au.net.hal9000.dnd.item.property;

import au.net.hal9000.dnd.item.*;
import au.net.hal9000.dnd.item.exception.ExceptionCantRemove;
import au.net.hal9000.dnd.item.exception.ExceptionCantWear;

public interface RingWearer extends ItemProperty {
	void ringWear(Ring r) throws ExceptionCantWear;

	void ringRemove(Ring r, Item newLocation) throws ExceptionCantRemove;
}
