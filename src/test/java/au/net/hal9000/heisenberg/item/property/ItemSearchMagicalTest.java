package au.net.hal9000.heisenberg.item.property;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Item;

public class ItemSearchMagicalTest {

    @Test
    public void testAccept() {

        // Ordinary non-magical, non-container
        {
            Cookie cookie = new Cookie();
            ItemSearch searchCookie = new ItemSearchMagical();
            cookie.accept(searchCookie);
            assertEquals(0, searchCookie.getMatchingItemsCount());
        }

        // Ordinary non-magical, container
        {
            Box box = new Box();
            ItemSearch searchBox = new ItemSearchMagical();
            box.accept(searchBox);
            assertEquals("count", 0, searchBox.getMatchingItemsCount());
        }

        // Empty magical container
        {
            BagOfHolding boh = new BagOfHolding(1);
            ItemSearch searchBoh = new ItemSearchMagical();
            boh.accept(searchBoh);
            assertEquals("count", 1, searchBoh.getMatchingItemsCount());
        }

        // Ordinary container with non-magical inside
        {
            Box box = new Box();
            Cookie cookie = new Cookie();
            box.setWeightMax(cookie.getWeight());
            box.setVolumeMax(cookie.getVolume());
            box.add(cookie);
            ItemSearch searchBox2 = new ItemSearchMagical();
            box.accept(searchBox2);
            assertEquals("count", 0, searchBox2.getMatchingItemsCount());
        }

        // Ordinary container with magical inside
        {
            Box box = new Box();
            BagOfHolding boh = new BagOfHolding(1);
            box.setWeightMax(boh.getWeight());
            box.setVolumeMax(boh.getVolume());
            box.add(boh);
            ItemSearch searchBox = new ItemSearchMagical();
            box.accept(searchBox);
            assertEquals("count", 1, searchBox.getMatchingItemsCount());
            assertEquals("found", boh, searchBox.getMatchingItems()
                    .elementAt(0));
        }

        // Ordinary container with magical and non-magical inside
        {
            Box box = new Box();
            BagOfHolding boh = new BagOfHolding(1);
            Cookie cookie = new Cookie();
            box.setWeightMax(boh.getWeight() + cookie.getWeight());
            box.setVolumeMax(boh.getVolume() + cookie.getVolume());
            box.add(boh);
            box.add(cookie);
            ItemSearch searchBox = new ItemSearchMagical();
            box.accept(searchBox);
            assertEquals("count", 1, searchBox.getMatchingItemsCount());
            assertEquals("found", boh, searchBox.getMatchingItems()
                    .elementAt(0));
        }

        // java Container with magical and non-magical inside
        {
            Box box = new Box();
            BagOfHolding boh = new BagOfHolding(1);
            Cookie cookie = new Cookie();
            Vector<Item> container = new Vector<Item>();
            container.add(box);
            container.add(boh);
            container.add(cookie);

            ItemSearch search = new ItemSearchMagical();
            search.visit(container);

            box.accept(search);
            assertEquals("count", 1, search.getMatchingItemsCount());
            assertEquals("found", boh, search.getMatchingItems().elementAt(0));
        }

    }

}
