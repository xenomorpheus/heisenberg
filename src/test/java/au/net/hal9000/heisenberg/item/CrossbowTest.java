package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import org.junit.Test;

class MyItemVisitor implements ItemVisitor {

  double weight = 0.0;

  public MyItemVisitor() {
  }

  public double getWeight() {
    return weight;
  }

  @Override
  public void visit(Item item) {
    weight += item.getWeightBase();
    if (item instanceof ItemContainer) {
      var container = (ItemContainer) item;
      for (var child : container.getContents()) {
        child.accept(this);
      }
    }
  }
}

/** */
public class CrossbowTest {

  /** Method testSetGetLoadedBolt. */
  @Test
  public void testSetGetLoadedBolt() {
    Crossbow crossbow = new Crossbow();
    CrossbowBolt bolt = new CrossbowBolt();
    Location newLocation = new Location();
    bolt.setContainer(newLocation);
    crossbow.setLoadedBolt(bolt);
    CrossbowBolt got = crossbow.getLoadedBolt();
    assertEquals("getLoadedBolt - bolt", bolt, got);
    assertEquals("getLoadedBolt - bolt location", newLocation, got.getContainer());
  }

  /** visit */
  @Test
  public void testGetWeight() {
    var crossbow = new Crossbow();
    crossbow.setWeightBase(1.0f);
    var bolt = new CrossbowBolt();
    bolt.setWeightBase(0.3f);
    crossbow.setLoadedBolt(bolt);
    assertEquals("weight: ", crossbow.getWeightBase() + bolt.getWeightBase(), crossbow.totalWeight(), 0.01);
  }

  /** visit */
  @Test
  public void testVisit() {
    var visitor = new MyItemVisitor();
    var crossbow = new Crossbow();
    crossbow.setWeightBase(1.0f);
    var bolt = new CrossbowBolt();
    bolt.setWeightBase(0.3f);
    crossbow.setLoadedBolt(bolt);
    crossbow.visit(visitor);
    assertEquals("visit weight: ", crossbow.getWeightBase() + bolt.getWeightBase(), visitor.getWeight(), 0.01);
  }

  /** visit */
  @Test
  public void testVisitNoBolt() {
    var visitor = new MyItemVisitor();
    var crossbow = new Crossbow();
    crossbow.setWeightBase(1.0f);
    crossbow.visit(visitor);
    assertEquals("visit weight: ", crossbow.getWeightBase(), visitor.getWeight(), 0.01);
  }
}
