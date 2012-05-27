package au.net.hal9000.dnd;
public interface SwordSheath {
	void add(Sword s) throws ExceptionCantWear;

	Sword remove(Location l) throws ExceptionCantRemove;
}
