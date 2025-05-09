package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/** Unit tests for the FringeElementImpl class. */
public class FringeElementImplTest {

  /** Sets up the demo environment before each test. */
  @Before
  public void initialize() {
    DemoEnvironment.setup();
  }

  @Test
  public void testCompareTo() {
    FringeElementImpl fe1 = new FringeElementImpl(null, null, 0, 1);
    FringeElementImpl fe2 = new FringeElementImpl(null, null, 0, 2);
    assertEquals("less than", -1, fe1.compareTo(fe2));
    assertEquals("greater than", 1, fe2.compareTo(fe1));
    assertEquals("equals", 0, fe1.compareTo(fe1));
  }

  @Test
  public void testToString() {
    ModelState modelState = new ModelStateAgentGoalImpl(new Position(), new Position());
    Path pathSoFar = new PathImpl();
    double costSoFar = 1.23;
    double estimatedTotalCost = 45.6;
    FringeElementImpl fe1 =
        new FringeElementImpl(modelState, pathSoFar, costSoFar, estimatedTotalCost);
    String expected =
        "FringeElementImpl=[costSoFar=1.23, estimatedTotalCost=45.6, "
            + modelState.toString()
            + ", "
            + pathSoFar.toString()
            + "]";
    String got = fe1.toString();
    assertEquals(expected, got);
  }
}
