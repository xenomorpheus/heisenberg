package au.net.hal9000.heisenberg.item.property;

import static org.junit.Assert.assertEquals;

import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.Box;
import org.junit.Test;

/** */
public class ItemSearchMagicalTest {

  /** Method testAccept. Ordinary non-magical, non-container. */
  @Test
  public void testAccept1() {
    Biscuit biscuit = new Biscuit();
    ItemSearch searchBiscuit = new ItemSearchMagical();
    biscuit.accept(searchBiscuit);
    assertEquals(0, searchBiscuit.getMatchingItemsCount());
  }

  /** Method testAccept. Ordinary non-magical, container. */
  @Test
  public void testAccept2() {
    Box box = new Box();
    ItemSearch searchBox = new ItemSearchMagical();
    box.accept(searchBox);
    assertEquals("count", 0, searchBox.getMatchingItemsCount());
  }

  /** Method testAccept. Empty magical container. */
  @Test
  public void testAccept3() {
    BagOfHolding boh = new BagOfHolding(1);
    ItemSearch searchBoh = new ItemSearchMagical();
    boh.accept(searchBoh);
    assertEquals("count", 1, searchBoh.getMatchingItemsCount());
  }

  /** Method testAccept. Ordinary container with non-magical inside. */
  @Test
  public void testAccept4() {
    Box box = new Box();
    Biscuit biscuit = new Biscuit();
    box.setWeightMax(biscuit.totalWeight());
    box.setVolumeMax(biscuit.totalVolume());
    box.add(biscuit);
    ItemSearch searchBox2 = new ItemSearchMagical();
    box.accept(searchBox2);
    assertEquals("count", 0, searchBox2.getMatchingItemsCount());
  }

  /** Method testAccept. Ordinary container with magical inside. */
  @Test
  public void testAccept5() {
    Box box = new Box();
    BagOfHolding boh = new BagOfHolding(1);
    box.setWeightMax(boh.totalWeight());
    box.setVolumeMax(boh.totalVolume());
    box.add(boh);
    ItemSearch searchBox = new ItemSearchMagical();
    box.accept(searchBox);
    assertEquals("count", 1, searchBox.getMatchingItemsCount());
    assertEquals("found", boh, searchBox.getMatchingItems().get(0));
  }

  /** Method testAccept. Ordinary container with magical and non-magical inside. */
  @Test
  public void testAccept6() {
    Box box = new Box();
    BagOfHolding boh = new BagOfHolding(1);
    Biscuit biscuit = new Biscuit();
    box.setWeightMax(boh.totalWeight() + biscuit.totalWeight());
    box.setVolumeMax(boh.totalVolume() + biscuit.totalVolume());
    box.add(boh);
    box.add(biscuit);
    ItemSearch searchBox = new ItemSearchMagical();
    box.accept(searchBox);
    assertEquals("count", 1, searchBox.getMatchingItemsCount());
    assertEquals("found", boh, searchBox.getMatchingItems().get(0));
  }

}
