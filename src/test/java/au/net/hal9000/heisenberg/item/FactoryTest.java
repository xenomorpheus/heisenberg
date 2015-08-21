package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.TestEnvironment;

/**
 */
public class FactoryTest {
    /**
     * Field config.
     */
    private Configuration config;

    @Before
    public void initialize() {
        TestEnvironment.setup();
        config = Configuration.lastConfig();
    }

    /**
     * Method testBagOfHolding.
     * 
     * @param object
     *            Object
     */
    private static void testBagOfHolding(Object object) {
        assertTrue("instanceof Item", object instanceof Item);
        assertEquals("simple class", "BagOfHolding", object.getClass()
                .getSimpleName());
    }

    /**
     * Create all the products of the recipe.
     */
    @Test
    public void createItemsAll() {
        List<String> classes = config.getItemClassIds();
        for (String type : classes) {
            Object item = Factory.createItem(type);
            assertTrue("instanceof Item", item instanceof Item);
            assertEquals("simple class", type, item.getClass().getSimpleName());

        }
    }

    /**
     * Create item with params passed to createItem.
     */
    @Test
    public void createItemWithParams() {
        testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding"));
        testBagOfHolding((BagOfHolding) Factory
                .createItem("BagOfHolding", null));
        testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding",
                new Object[] {}));
        testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding",
                new Object[] { 2 }));
        testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding",
                new Object[] { 4, "TEST" }));
    }

    /**
     * Create Item objects and test the instanceof.
     */
    @Test
    public void createItemTestInstance() {

        // Check the sub-class
        Item cookie = Factory.createItem("Cookie");
        assertTrue("instanceof", cookie instanceof Cookie);
        Item human = Factory.createItem("Human");
        assertTrue("instanceof", human instanceof Human);

    }
}
