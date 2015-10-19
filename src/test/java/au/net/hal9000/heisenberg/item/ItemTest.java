package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
// Custom
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.ItemClassConfiguration;
import au.net.hal9000.heisenberg.util.ItemIcon;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 */
public class ItemTest {
    /**
     * Field WITHIN_MARGIN. (value is 9.0E-5)
     */
    private static final float WITHIN_MARGIN = 0.00009F;

    private Configuration config = null;

    @Before
    public void initialize() {
        DemoEnvironment.setup();
        config = Configuration.lastConfig();
    }

    @Test
    public void testItem() {
        Item i = new Cookie();
        assertEquals("Item() name", "Cookie", i.getName());
        assertEquals("Item() description", null, i.getDescription());
        // assertTrue("Item() weightBase", i.getWeightBase().equals(0F));
        // assertTrue("Item() weightMax", i.getWeightMax().equals(0F));
        // assertTrue("Item() volumeBase", i.getVolumeBase().equals(0F));
        assertEquals("Item() location", null, i.getContainer());
    }

    @Test
    public void testItemWithName() {
        Item i = new Cookie("The Name");
        assertEquals("Item() name", "The Name", i.getName());
    }

    @Test
    public void testItemWithNameAndDescription() {
        Item i = new Cookie("The Name", "The Description");
        assertEquals("Item() name", "The Name", i.getName());
        assertEquals("Item() description", "The Description",
                i.getDescription());
    }

    @Test
    public void testId() {
        Item i = new Cookie();
        UUID id = i.getId();
        assertNotNull("id", id);
    }

    @Test
    public void testGetJpaId() {
        Item i = new Cookie();
        long jpaId = i.getJpaId();
        assertEquals("jpaId", 0L, jpaId);
    }

    @Test
    public void testHitPoints() {
        Item i = new Cookie();
        float expectHitPoints = 1.23f;
        i.setHitPoints(expectHitPoints);
        assertEquals("hit Points", expectHitPoints, i.getHitPoints(),
                WITHIN_MARGIN);
    }

    @Test
    public void testName() {
        Item i = new Cookie("A Name");
        assertEquals("name", "A Name", i.getName());

        i.setName("fred");
        assertEquals("setname & getname", "fred", i.getName());
    }

    @Test
    public void testDescription() {
        Item i = new Cookie();
        i.setDescription("A Description");
        assertEquals("description", "A Description", i.getDescription());
    }

    @Test
    public void testBaseWeight() {
        final float weight = 0.123F;
        Item i = new Cookie();
        i.setWeightBase(weight);
        assertEquals("weightBase", i.getWeightBase(), weight, WITHIN_MARGIN);
    }

    @Test
    public void testWeight() {
        final float weight = 0.123F;
        Item i = new Cookie();
        i.setWeightBase(weight);
        assertEquals("weight", i.getWeight(), weight, WITHIN_MARGIN);
    }

    /** test valueBase and value */
    @Test
    public void testValueBase() {
        Item i = new Cookie();
        Currency currency = new Currency();
        i.setValueBase(currency);
        assertTrue("valueBase", currency == i.getValueBase());
        assertTrue("value", currency == i.getValue());
    }

    @Test
    public void testLocation() {
        Item item = new Cookie();
        Box box = new Box();
        item.setContainer(box);
        assertEquals("location", box, item.getContainer());
    }

    @Test
    public void testToString() throws ConfigurationError {
        for (ItemClassConfiguration itemClassConfiguration : config
                .getItemClasses().values()) {
            String itemClass = itemClassConfiguration.getId();
            String string = Factory.createItem(itemClass).toString();
            assertTrue(itemClass + ".toString not null", null != string);
            assertTrue(itemClass + ".toString length", string.length() > 0);
        }
    }

    @Test
    public void testSetVolumeMax() {
        float volumeBase = 20F;
        Item item = new Cookie();
        item.setVolumeBase(volumeBase);
        float v = item.getVolumeBase();
        assertEquals("getVolumeBase=", volumeBase, v, 0.0001F);
    }

    @Test
    public void testEquals() {
        Item first = new Cookie();
        Item second = new Cookie();
        assertTrue("equals true for self", first.equals(first));
        assertFalse("equals false for other", first.equals(second));
    }

    @Test
    public void testProperties() {
        Item i = new Cookie();
        Properties p = new Properties();
        i.setProperties(p);
        assertEquals("description", p, i.getProperties());
    }

    @Test
    public void testProperty() {
        Item item = new Cookie();
        item.setProperty("myKey", "myVal");
        assertEquals("get property", "myVal", item.getProperty("myKey"));
        item.removeProperty("myKey");
    }

    @Test
    public void testItemIcon() {
        Item item = new Cookie();
        ItemIcon ii = new ItemIcon(item);
        ItemIcon ii2 = item.getItemIcon();
        assertEquals("ItemIcon", ii, ii2);
    }

    @Test
    public void testSetAllFrom() {
        Item candle = new Candle();
        ItemIcon ii = new ItemIcon(candle);
        Item item = new Cookie();
        Item other = new Cookie();
        ItemContainer bag = new Bag();
        other.setContainer(bag);
        other.setDescription("other");
        other.setHitPoints(123f);
        other.setItemIcon(ii);
        other.setName("the cookie of awesomeness");
        other.setPosition(new Position(1, 2));
        other.setValueBase(new Currency(1, 2, 3, 4));
        other.setVolumeBase(145);
        other.setWeightBase(345);
        item.setAllFrom(other);
        assertEquals("container", item.getContainer(), other.getContainer());
        assertEquals("description", item.getDescription(),
                other.getDescription());
        assertEquals("hp", item.getHitPoints(), other.getHitPoints(),
                WITHIN_MARGIN);
        assertEquals("ItemIon", item.getItemIcon(), other.getItemIcon());
        assertEquals("name", item.getName(), other.getName());
        assertEquals("position", item.getPosition(), other.getPosition());
        assertEquals("properties", item.getProperties(), other.getProperties());
        assertEquals("valueBase", item.getValueBase(), other.getValueBase());
        assertEquals("volumeBase", item.getVolumeBase(), other.getVolumeBase(),
                WITHIN_MARGIN);
        assertEquals("weightBase", item.getWeightBase(), other.getWeightBase(),
                WITHIN_MARGIN);
    }

    @Test
    public void testHashCode() {

        Item item = new Cookie();
        ItemIcon ii = new ItemIcon(item);
        ItemContainer bag = new Bag();
        item.setContainer(bag);
        item.setDescription("other");
        item.setHitPoints(123f);
        item.setItemIcon(ii);
        item.setName("the cookie of awesomeness");
        item.setPosition(new Position(1, 2));
        item.setValueBase(new Currency(1, 2, 3, 4));
        item.setVolumeBase(145);
        item.setWeightBase(345);
        assertTrue("hashcode", 0 != item.hashCode());

    }

    /** Move without a container. */
    @Test(expected = UnsupportedOperationException.class)
    public void testMoveNoContainer() {
        Item item = new Cookie();

        // No container - No Movement
        Position expectedPosition = new Position(10, 20, 30);
        // Before test we place in a known position.
        item.setPosition(expectedPosition);
        try {
            // Try to move, but will fail as not in an ItemContainer.
            item.moveWithinContainer(new Position(1, 2, 3));
        } catch (UnsupportedOperationException e) {

            Position actualPosition = item.getPosition();
            assertTrue("No ItemContainer - final pos",
                    expectedPosition.equals(actualPosition));
            throw e;
        }
    }

    /** Move within a container. */
    @Test
    public void testMoveContainer() {
        Item item = new Cookie();

        Location container = new Location();
        item.setContainer(container);
        Position expectedPosition = new Position(2, 4, 8);
        item.moveWithinContainer(expectedPosition);
        Position actualPosition = item.getPosition();
        assertTrue("Has ItemContainer - final pos",
                expectedPosition.equals(actualPosition));

    }

    /** check the instanceof results. */
    @Test
    public void testInstanceOf() {
        Item item = new Cookie();
        assertTrue("item", item.instanceOf("Cookie"));
        assertFalse("Sword", item.instanceOf("Sword"));
        assertFalse("Unknown", item.instanceOf("Unknown"));
    }

    @Test
    public void testApplyDelta() {
        Item item = new Cookie();
        ItemContainer bag = new Bag();
        bag.add(item);
        Position delta = new Position(1, 2);
        item.applyDelta(delta);

    }

    @Test
    public void testBeNot() {
        ItemContainer container = new Bag();
        Item item = new Cookie();
        container.add(item);
        assertTrue(container.contains(item));
        item.beNot();
        assertFalse(container.contains(item));
    }

    @Test
    public void testMoveItemContainerPosition() {
        ItemContainer container = new Bag();
        Item item = new Cookie();
        Position position = new Position(4, 5);
        item.move(container, position);
        assertTrue(container.contains(item));
        assertEquals(container, item.getContainer());
        assertEquals(position, item.getPosition());
    }

    @Test
    public void testMoveItemContainer() {
        ItemContainer container = new Bag();
        Item item = new Cookie();
        item.move(container);
        assertTrue(container.contains(item));
        assertEquals(container, item.getContainer());
    }

    @Test
    public void testDetailedDescription() {

        Item item = new Cookie();
        item.setDescription("other");
        item.setHitPoints(123f);
        item.setName("the cookie of awesomeness");
        item.setPosition(new Position(1, 2));
        item.setValueBase(new Currency(1, 2, 3, 4));
        item.setVolumeBase(145);
        item.setWeightBase(345);
        assertNotNull(item.detailedDescription());

    }

    private class MyItemVisitor implements ItemVisitor {
        boolean visited = false;

        @Override
        public void visit(Item item) {
            visited = true;
        }

        @Override
        public void visit(List<Item> items) {
        }
    }

    @Test
    public void testAcceptItemVisitor() {
        Item item = new Cookie();
        ItemVisitor visitor = new MyItemVisitor();
        item.accept(visitor);
        MyItemVisitor myItemVisitor = (MyItemVisitor) visitor;
        assertTrue(myItemVisitor.visited);
    }

}
