public interface SwordSheath {
	void add(Sword s) throws ExceptionCantWear;

	Sword remove(Location l) throws ExceptionCantRemove;
}
