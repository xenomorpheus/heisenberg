package au.net.hal9000.dnd;

public interface RingWearer {
     void ringWear(Ring r) throws ExceptionCantWear;
     void ringRemove(Ring r, Location newLocation) throws ExceptionCantRemove;
}
