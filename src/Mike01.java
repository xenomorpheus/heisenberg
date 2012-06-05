import java.util.Vector;

import au.net.hal9000.dnd.item.*;
import au.net.hal9000.dnd.item.property.*;

public class Mike01 {

	public static void main(String arg[]) {
		Bag bag = new Bag();
		bag.add(new BagOfHolding(1));
		bag.add(new Cookie());
		bag.add(new Human());

		ItemSearch search = new ItemSearchMagical();
		bag.searchHelper(search);
		Vector <Item>matching = search.getMatchingItems();
		System.out.println("\nMagical Items:");
		for (Item item : matching) {
			System.out.println("Item " + item);
		}

		search = new ItemSearchExtraDimensional();
		bag.searchHelper(search);
		matching = search.getMatchingItems();
		System.out.println("\nExtraDimensional Items:");
		for (Item item : matching) {
			System.out.println("Item " + item);
		}
	
	}
}
