import java.util.Vector;

import au.net.hal9000.dnd.item.*;
import au.net.hal9000.dnd.item.property.*;

public class Mike01 {

	public static void main(String arg[]) {
		Vector<Item> items = new Vector<Item>();
		items.add(new BagOfHolding(1));
		items.add(new Cookie());
		items.add(new Human());

		for (Item item : items) {
			System.out.print("Item " + item + " is ");
			if (item instanceof Magical) {
				System.out.println("magical!");
			} else {
				System.out.println("not magical.");
			}
		}
	}
}
