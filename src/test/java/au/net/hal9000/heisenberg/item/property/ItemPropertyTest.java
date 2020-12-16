package au.net.hal9000.heisenberg.item.property;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.entity.Entity;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/** */
public class ItemPropertyTest {
  /** comparison tolerance. */
  static final float TOLERANCE = 0.00001f;

  /** Method testAeration. */
  @Test
  public void testAeration() {
    final float startValue = 12.3f;
    final float propertyDelta = 1.6f;
    Entity entity = new Cat();
    ItemProperty.setAeration(entity, startValue);
    ItemProperty.alterAeration(entity, propertyDelta);
    assertEquals(
        "Aeration alter", startValue + propertyDelta, ItemProperty.getAeration(entity), TOLERANCE);
    ItemProperty.setAeration(entity, startValue);
    assertEquals("Aeration after", startValue, ItemProperty.getAeration(entity), TOLERANCE);
  }

  /** Method testClothing. */
  @Test
  public void testClothing() {
    Entity entity = new Cat();
    ItemProperty.setClothing(entity, true);
    assertTrue("Clothing set", ItemProperty.isClothing(entity));
    ItemProperty.setClothing(entity, false);
    assertFalse("Clothing unset", ItemProperty.isClothing(entity));
  }

  /** Method testEntertainment. */
  @Test
  public void testEntertainment() {
    final float startValue = 12.3f;
    final float propertyDelta = 1.6f;
    Entity entity = new Cat();
    ItemProperty.setEntertainment(entity, startValue);
    ItemProperty.alterEntertainment(entity, propertyDelta);
    assertEquals(
        "Entertainment alter",
        startValue + propertyDelta,
        ItemProperty.getEntertainment(entity),
        TOLERANCE);
    ItemProperty.setEntertainment(entity, startValue);
    assertEquals(
        "Entertainment after", startValue, ItemProperty.getEntertainment(entity), TOLERANCE);
  }

  /** Method testHumanoidFood. */
  @Test
  public void testHumanoidFood() {
    Entity entity = new Cat();
    ItemProperty.setHumanoidFood(entity, true);
    assertTrue("HumanoidFood set", ItemProperty.isHumanoidFood(entity));
    ItemProperty.setHumanoidFood(entity, false);
    assertFalse("HumanoidFood unset", ItemProperty.isHumanoidFood(entity));
  }

  /** Method testNourishment. */
  @Test
  public void testNourishment() {
    final float startValue = 12.3f;
    final float propertyDelta = 1.6f;
    Entity entity = new Cat();
    ItemProperty.setNourishment(entity, startValue);
    ItemProperty.alterNourishment(entity, propertyDelta);
    assertEquals(
        "Nourishment alter",
        startValue + propertyDelta,
        ItemProperty.getNourishment(entity),
        TOLERANCE);
    ItemProperty.setNourishment(entity, startValue);
    assertEquals("Nourishment after", startValue, ItemProperty.getNourishment(entity), TOLERANCE);
  }

  /** Method testLiving. */
  @Test
  public void testLiving() {
    Entity entity = new Cat();
    ItemProperty.setLiving(entity, true);
    assertTrue("Living set", ItemProperty.isLiving(entity));
    ItemProperty.setLiving(entity, false);
    assertFalse("Living unset", ItemProperty.isLiving(entity));
  }

  /** Method testMagical. */
  @Test
  public void testMagical() {
    Entity entity = new Cat();
    ItemProperty.setMagical(entity, true);
    assertTrue("Magical set", ItemProperty.isMagical(entity));
    ItemProperty.setMagical(entity, false);
    assertFalse("Magical unset", ItemProperty.isMagical(entity));
  }

  /** Method testHydration. */
  @Test
  public void testHydration() {
    final float startValue = 12.3f;
    final float propertyDelta = 1.6f;
    Entity entity = new Cat();
    ItemProperty.setHydration(entity, startValue);
    ItemProperty.alterHydration(entity, propertyDelta);
    assertEquals(
        "Hydration alter",
        startValue + propertyDelta,
        ItemProperty.getHydration(entity),
        TOLERANCE);
    ItemProperty.setHydration(entity, startValue);
    assertEquals("Hydration after", startValue, ItemProperty.getHydration(entity), TOLERANCE);
  }

  /** Method testRest. */
  @Test
  public void testRest() {
    final float startValue = 12.3f;
    final float propertyDelta = 1.6f;
    Entity entity = new Cat();
    ItemProperty.setRest(entity, startValue);
    ItemProperty.alterRest(entity, propertyDelta);
    assertEquals("Rest alter", startValue + propertyDelta, ItemProperty.getRest(entity), TOLERANCE);
    ItemProperty.setRest(entity, startValue);
    assertEquals("Rest after", startValue, ItemProperty.getRest(entity), TOLERANCE);
  }

  /** Shows that these properties are independent of each other. */
  @Test
  public void testAlterPropertyByName() {
    List<String> properties = new ArrayList<String>();
    properties.add(ItemProperty.AERATION);
    properties.add(ItemProperty.ENTERTAINMENT);
    properties.add(ItemProperty.NOURISHMENT);
    properties.add(ItemProperty.HYDRATION);
    properties.add(ItemProperty.REST);
    final float startValue = 12.3f;
    for (String propertyName : properties) {
      final float propertyDelta = 1.6f;
      Entity entity = new Cat();
      ItemProperty.setAeration(entity, startValue);
      ItemProperty.setEntertainment(entity, startValue);
      ItemProperty.setHydration(entity, startValue);
      ItemProperty.setNourishment(entity, startValue);
      ItemProperty.setRest(entity, startValue);

      if (ItemProperty.AERATION.equals(propertyName)) {
        ItemProperty.alterAeration(entity, propertyDelta);
        assertEquals(
            "Aeration alter",
            startValue + propertyDelta,
            ItemProperty.getAeration(entity),
            TOLERANCE);
        ItemProperty.setAeration(entity, startValue);
      } else if (ItemProperty.ENTERTAINMENT.equals(propertyName)) {
        ItemProperty.alterEntertainment(entity, propertyDelta);
        assertEquals(
            "Entertainment alter",
            startValue + propertyDelta,
            ItemProperty.getEntertainment(entity),
            TOLERANCE);
        ItemProperty.setEntertainment(entity, startValue);
      } else if (ItemProperty.HYDRATION.equals(propertyName)) {
        ItemProperty.alterHydration(entity, propertyDelta);
        assertEquals(
            "Hydration alter",
            startValue + propertyDelta,
            ItemProperty.getHydration(entity),
            TOLERANCE);
        ItemProperty.setHydration(entity, startValue);
      } else if (ItemProperty.NOURISHMENT.equals(propertyName)) {
        ItemProperty.alterNourishment(entity, propertyDelta);
        assertEquals(
            "Nourishment alter",
            startValue + propertyDelta,
            ItemProperty.getNourishment(entity),
            TOLERANCE);
        ItemProperty.setNourishment(entity, startValue);
      } else if (ItemProperty.REST.equals(propertyName)) {
        ItemProperty.alterRest(entity, propertyDelta);
        assertEquals(
            "Rest alter", startValue + propertyDelta, ItemProperty.getRest(entity), TOLERANCE);
        ItemProperty.setRest(entity, startValue);
      }

      assertEquals("Aeration after", startValue, ItemProperty.getAeration(entity), TOLERANCE);
      assertEquals(
          "Entertainment after", startValue, ItemProperty.getEntertainment(entity), TOLERANCE);
      assertEquals("Hydration after", startValue, ItemProperty.getHydration(entity), TOLERANCE);
      assertEquals("Nourishment after", startValue, ItemProperty.getNourishment(entity), TOLERANCE);
      assertEquals("Rest after", startValue, ItemProperty.getRest(entity), TOLERANCE);
    }
  }
}
