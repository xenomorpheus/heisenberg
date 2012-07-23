package au.net.hal9000.player.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Cookie extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cookie() {
		super("Cookie");
	}

	public Cookie(String pName) {
		super(pName);
	}

	public Cookie(String pName, String pDescription) {
		super(pName, pDescription);
	}

	// Feature
	@Override
	public boolean isHumanoidFood() {
		return true;
	}

	// Methods

	// Static
	public static Cookie thawFromFile(String filename) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fis);
		Cookie newObj = (Cookie) in.readObject();
		in.close();
		return newObj;
	}

}
