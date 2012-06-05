import java.util.Vector;

import au.net.hal9000.dnd.item.*;
import au.net.hal9000.dnd.item.property.*;

public class Mike01 {

	public static void main(String arg[]) {
		Bag bag = new Bag();
		BagOfHolding boh = new BagOfHolding(1);
		bag.add(boh);
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
	
		System.out.println("\nName: "+boh);
        System.out.println("BOH ExtraDimensional ="+boh.isExtraDimensional());
        System.out.println("BOH Magical ="+boh.isMagical());
        System.out.println("BOH Class ="+boh.getClass().getName());
		System.out.println("\nName: "+bag);
        System.out.println("Bag ExtraDimensional ="+bag.isExtraDimensional());
        System.out.println("Bag Magical ="+bag.isMagical());
        System.out.println("Bag Class ="+bag.getClass().getName());
	}
}
