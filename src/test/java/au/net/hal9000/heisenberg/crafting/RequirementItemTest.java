package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Cookie;

public class RequirementItemTest {

    @Test
    public void testGetDescription() {
        RequirementItem requirementItem = new RequirementItem("Bag", "Bag",
                true, 0);
        String description = "Id: Bag, consumed, item type Bag";
        assertEquals("just type", description, requirementItem.getDescription());
        requirementItem = new RequirementItem("Bag", "Bag", true, 3);
        description += ", weighing at least 3.0";
        assertEquals("weight", description, requirementItem.getDescription());
        requirementItem = new RequirementItem("Bag", "Bag", false, 3);
        description = "Id: Bag, not consumed, item type Bag, weighing at least 3.0";
        assertEquals("not consumed", description,
                requirementItem.getDescription());
    }

    @Test
    public void testIsConsumed() {
        RequirementItem requirementItem = new RequirementItem("Bag", "Bag",
                false, 0);
        assertFalse("not consumed", requirementItem.isConsumed());
        requirementItem = new RequirementItem("Bag", "Bag", true, 0);
        assertTrue("not consumed", requirementItem.isConsumed());
    }

    @Test
    public void testMeetsRequirements() {

        // The requirement
        RequirementItem requirementItem = new RequirementItem("Bag");
        assertEquals("meetsRequirements", null,
                requirementItem.meetsRequirements(new Bag()));
        assertEquals("meetsRequirements", "item must be a Bag",
                requirementItem.meetsRequirements(new Arrow()));

        // Class inheritance
        assertEquals("meetsRequirements", null,
                requirementItem.meetsRequirements(new BagOfHolding()));
        assertEquals("meetsRequirements", "item must be a Bag",
                requirementItem.meetsRequirements(new Box()));
    }

    @Test
    public void testMeetsRequirementsMinWeight() {
        // The requirement
        RequirementItem requirementItem = new RequirementItem("Cookie",
                "Cookie", true, 3);

        // Correct type and weight
        Cookie cookie = new Cookie();
        cookie.setWeightBase(3);

        // Correct type, too lite.
        Cookie cookieLite = new Cookie();
        cookieLite.setWeightBase(2.95f);

        // Correct weight, wrong type
        Arrow arrow = new Arrow();
        arrow.setWeightBase(3);

        // Tests
        assertEquals("correct type, just meets weight", null,
                requirementItem.meetsRequirements(cookie));
        assertEquals("incorrect type, meets weight", "item must be a Cookie",
                requirementItem.meetsRequirements(arrow));
        assertEquals("correct type, too lite", "item must weigh at least 3.0",
                requirementItem.meetsRequirements(cookieLite));
    }

    @Test
    public void testToString() {
        RequirementItem requirementItem = new RequirementItem("Cookie");
        assertEquals("toString", "Id: Cookie, consumed, item type Cookie",
                requirementItem.toString());
    }

}
