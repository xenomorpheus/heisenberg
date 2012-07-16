package au.net.hal9000.player.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Location extends ItemContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Location(String pName) {
		super(pName);
	}
	// Static
	public static Location thawFromFile(String filename) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fis);
		Location newObj = (Location) in.readObject();
		in.close();
		return newObj;
	}
}
