package au.net.hal9000.player.item;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Location extends ItemContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Location(String pName) {
		super(pName);
	}

	// Store the object in a file
	public void freezeToFile(String filename) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(this);
		out.close();
	}
	
// Just experimenting
//	private void writeObject(ObjectOutputStream out) throws IOException {
//		throw new NotSerializableException("Not today!");
//	}

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
