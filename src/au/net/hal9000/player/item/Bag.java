package au.net.hal9000.player.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import au.net.hal9000.player.item.exception.*;

public class Bag extends Box {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bag() {
		super("Bag");
		setDescription("A common cloth sack about 2 feet by 4 feet in size.");

	}

	public Bag(String pName) {
		super(pName);
		setDescription("A common cloth sack about 2 feet by 4 feet in size.");
	}

	// TODO finish rupture
	public void rupture() {
		// System.out.println("Ordinary rupture");
	}

	public void add(ItemImpl item) throws ExceptionTooHeavy, ExceptionTooBig,
			ExceptionInvalidType {
		// Look for sharp items. Wrapped sharp items are safe.
		if (item.isSharp()) {
			this.rupture();
			throw new ExceptionInvalidType("Sharp");
		}
		super.add(item);
	}

	// Methods
	// Static
	public static Bag thawFromFile(String filename) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fis);
		Bag newObj = (Bag) in.readObject();
		in.close();
		return newObj;
	}
}
