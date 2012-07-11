package au.net.hal9000.dnd.item;


// I haven't made up my mind if this class should exist at all.
// Or perhaps an abstract class for other locations

public class Location extends ItemImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Location() {
		super("Location");

	}

	public Location(String pString) {
		super(pString);

	}
    // static methods
	public static boolean null_safe_compare(Location location,
			Location locationOther) {
		if (location == null) {
			if (locationOther == null) {
				return true;
			}
			return false;
		}

		if (locationOther == null) {
			return false;
		}

		return location.equals(locationOther);
	}

	// instance methods
}
