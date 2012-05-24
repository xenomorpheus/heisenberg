
public interface RingWearer {
     void ringWear(Ring r) throws ExceptionCantWear;
     void ringRemove(Ring r, Location newLocation) throws ExceptionCantRemove;
}
