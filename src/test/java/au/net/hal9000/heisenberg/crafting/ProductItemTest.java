package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 */
public class ProductItemTest {

    /**
     * Method productItemStringTest.
     */
    @Test
    public void productItemStringTest() {
        new ProductItem("someId");
    }

    /**
     * Method productItemStringStringTest.
     */
    @Test
    public void productItemStringStringTest() {
        new ProductItem("SomeId", "SomeType", 0);
    }

    /**
     * Method getIdTest.
     */
    @Test
    public void getIdTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType", 1.2f);
        assertTrue("id", "SomeId".equals(productItem.getId()));
    }

    /**
     * Method getTypeTest.
     */
    @Test
    public void getTypeTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType", 1.2f);
        assertTrue("type", "SomeType".equals(productItem.getType()));
    }

    /**
     * Method getWeightBaseTest.
     */
    @Test
    public void getWeightBaseTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType", 1.2f);
        assertEquals("weightBase", 1.2f, productItem.getWeightBase(), 0.0001f);
    }

    /**
     * Method getDescriptionTest.
     */
    @Test
    public void getDescriptionTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType", 1.2f);
        assertEquals("type",
                "Id: SomeId, item type of SomeType, weightBase 1.2",
                productItem.getDescription());
    }

    /**
     * Method meetsRequirementsTest.
     */
    @Test
    public void meetsRequirementsTest() {
        // TODO complete unit test
        // ProductItem productItem = new ProductItem("SomeId");
        // Cooker cooker = new Cooker();
        // String string = productItem.meetsRequirements(cooker);
    }

    /**
     * Method createProductTest.
     */
    @Test
    public void createProductTest() {
        // TODO complete unit test
    }
}
