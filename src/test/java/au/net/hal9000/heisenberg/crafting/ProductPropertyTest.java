package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductPropertyTest {

    @Test
    public void testProductProperty() {
        new ProductProperty("SomeId", "SomeProperty", 0.0f);
    }

    @Test
    public void testGetPropertyName() {
        ProductProperty property = new ProductProperty("SomeId",
                "SomeProperty", 2.0f);
        assertEquals("SomeProperty", property.getPropertyName());
    }

    @Test
    public void testGetPropertyDelta() {
        ProductProperty property = new ProductProperty("SomeId",
                "SomeProperty", 2.0f);
        assertEquals(2.0f, property.getPropertyDelta(), 0.00f);
    }

    @Test
    public void testGetDescription() {
        ProductProperty property = new ProductProperty("SomeId",
                "SomeProperty", 2.0f);
        assertEquals("Id: SomeId type SomeProperty, delta is 2.0",
                property.getDescription());
    }

    @Test
    public void testMeetsRequirements() {
        // TODO fail("Not yet implemented");
    }

    @Test
    public void testCreateProduct() {
        // TODO fail("Not yet implemented");
    }

}
