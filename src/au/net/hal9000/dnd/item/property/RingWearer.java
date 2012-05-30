package au.net.hal9000.dnd.item.property;

import au.net.hal9000.dnd.item.ExceptionCantRemove;
import au.net.hal9000.dnd.item.ExceptionCantWear;
import au.net.hal9000.dnd.item.Location;
import au.net.hal9000.dnd.item.Ring;

public interface RingWearer {
     void ringWear(Ring r) throws ExceptionCantWear;
     void ringRemove(Ring r, Location newLocation) throws ExceptionCantRemove;
}
