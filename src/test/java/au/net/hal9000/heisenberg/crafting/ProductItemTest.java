package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductItemTest {

    @Test
    public void productItemStringTest() {
        new ProductItem("someId");
    }

    @Test
    public void productItemStringStringTest() {
        new ProductItem("SomeId", "SomeType");
    }

    @Test
    public void getTypeTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType");
        assertTrue("type", "SomeType".equals(productItem.getType()));
    }

    @Test
    public void getDescriptionTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType");
        assertTrue("type",
                "Id: SomeId, item type of SomeType".equals(productItem
                        .getDescription()));
    }

    @Test
    public void meetsRequirementsTest() {
        // ProductItem productItem = new ProductItem("SomeId");
        // Cooker cooker = new Cooker();
        // String string = productItem.meetsRequirements(cooker);
    }

    @Test
    public void createProductTest() {
    }
}
