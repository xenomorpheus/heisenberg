package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 */
public class ProductEntityPropertyTest {

    /**
     * Method testProductProperty.
     */
    @Test
    public void testProductProperty() {
        new ProductEntityProperty("SomeId", "SomeProperty", 0.0f);
    }

    /**
     * Method testGetPropertyName.
     */
    @Test
    public void testGetPropertyName() {
        ProductEntityProperty property = new ProductEntityProperty("SomeId",
                "SomeProperty", 2.0f);
        assertEquals("SomeProperty", property.getPropertyName());
    }

    /**
     * Method testGetPropertyDelta.
     */
    @Test
    public void testGetPropertyDelta() {
        ProductEntityProperty property = new ProductEntityProperty("SomeId",
                "SomeProperty", 2.0f);
        assertEquals(2.0f, property.getPropertyDelta(), 0.00f);
    }

    /**
     * Method testGetDescription.
     */
    @Test
    public void testGetDescription() {
        ProductEntityProperty property = new ProductEntityProperty("SomeId",
                "SomeProperty", 2.0f);
        assertEquals("Id: SomeId type SomeProperty, delta is 2.0",
                property.getDescription());
    }

    /**
     * Method testMeetsRequirements.
     */
    @Test
    public void testMeetsRequirements() {
        // TODO fail("Not yet implemented");
    }

    /**
     * Method testCreateProduct.
     */
    @Test
    public void testCreateProduct() {
        // TODO fail("Not yet implemented");
    }

}
