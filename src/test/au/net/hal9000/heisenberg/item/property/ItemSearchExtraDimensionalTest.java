package test.au.net.hal9000.heisenberg.item.property;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.item.property.ItemSearch;
import au.net.hal9000.heisenberg.item.property.ItemSearchExtraDimensional;

public class ItemSearchExtraDimensionalTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAccept() {

		// Ordinary non-ED, non-container
		{
			Cookie cookie = new Cookie();
			ItemSearch searchCookie = new ItemSearchExtraDimensional();
			cookie.accept(searchCookie);
			assertEquals("count",0, searchCookie.getMatchingItemsCount());
		}

		// Ordinary non-ED, container
		{
			Box box = new Box();
			ItemSearch searchBox = new ItemSearchExtraDimensional();
			box.accept(searchBox);
			assertEquals("count",0, searchBox.getMatchingItemsCount());
		}

		// Empty ED container
		{
			BagOfHolding boh = new BagOfHolding(2);
			ItemSearch searchBoh = new ItemSearchExtraDimensional();
			boh.accept(searchBoh);
			assertEquals("count",1, searchBoh.getMatchingItemsCount());
		}

		// Ordinary container with ED inside
		{
			Box box = new Box();
			BagOfHolding boh = new BagOfHolding(1);
			box.setWeightMax(boh.getWeight());
			box.setVolumeMax(boh.getVolume());
			box.add(boh);
			ItemSearch searchBox2 = new ItemSearchExtraDimensional();
			box.accept(searchBox2);
			assertEquals("count",1, searchBox2.getMatchingItemsCount());
		}

		// Ordinary container with non-ED inside
		{
			Box box = new Box();
			BagOfHolding boh = new BagOfHolding(1);
			box.setWeightMax(boh.getWeight());
			box.setVolumeMax(boh.getVolume());
			box.add(boh);
			ItemSearch searchBox3 = new ItemSearchExtraDimensional();
			box.accept(searchBox3);
			assertEquals("count",1, searchBox3.getMatchingItemsCount());
		}

		// java Container with ED and non-ED inside
		{
			Box box = new Box();
			BagOfHolding boh = new BagOfHolding(1);
			Cookie cookie = new Cookie();
			Vector<IItem> container = new Vector<IItem>();
			container.add(box);
			container.add(boh);
			container.add(cookie);

			ItemSearch search = new ItemSearchExtraDimensional();
			search.visit(container);

			box.accept(search);
			assertEquals("count", 1, search.getMatchingItemsCount());
			assertEquals("found", boh, search.getMatchingItems().elementAt(0));
		}
	}

}
