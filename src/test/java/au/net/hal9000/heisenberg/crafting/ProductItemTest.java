package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProductItemTest {

    @Test
    public void productItemStringTest() {
        new ProductItem("someId");
    }

    @Test
    public void productItemStringStringTest() {
        new ProductItem("SomeId", "SomeType", 0);
    }

    @Test
    public void getIdTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType", 1.2f);
        assertTrue("id", "SomeId".equals(productItem.getId()));
    }

    @Test
    public void getTypeTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType", 1.2f);
        assertTrue("type", "SomeType".equals(productItem.getType()));
    }
    
    @Test
    public void getWeightBaseTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType", 1.2f);
        assertEquals("weightBase", 1.2f, productItem.getWeightBase(), 0.0001f);
    }
    
    
    
    
    
    
    @Test
    public void getDescriptionTest() {
        ProductItem productItem = new ProductItem("SomeId", "SomeType", 1.2f);
        assertEquals("type",
                "Id: SomeId, item type of SomeType, weightBase 1.2",productItem
                        .getDescription());
    }

    @Test
    public void meetsRequirementsTest() {
        // TODO complete
        // ProductItem productItem = new ProductItem("SomeId");
        // Cooker cooker = new Cooker();
        // String string = productItem.meetsRequirements(cooker);
    }

    @Test
    public void createProductTest() {
        // TODO complete
    }
}
