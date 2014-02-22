package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;

/**
 */
public class FactoryTest {
    /**
     * Field config.
     */
    Configuration config;

    /**
     * Method setUp.
     * @throws ConfigurationError
     */
    @Before
    public void setUp() throws ConfigurationError {
        config = new Configuration("src/test/resources/config.xml");
    }

    /**
     * Method testBagOfHolding.
     * @param object Object
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
        Vector<String> classes = config.getItemClassIds();
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
