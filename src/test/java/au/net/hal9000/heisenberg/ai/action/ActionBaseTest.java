package au.net.hal9000.heisenberg.ai.action;

import static org.junit.Assert.*;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class ActionBaseTest {

  private static final float TOLERANCE = 0.001f;

  @Before
  public void setUp() throws Exception {}

  private class MyAction extends ActionBase {
    MyAction(float cost) {
      super(cost);
    }

    @Override
    public void apply(ModelState modelState) {
      // Auto-generated method stub
    }
  }

  // Constructor and getCost
  @Test
  public void testActionBase() {
    float cost = 1.23f;
    Action action = new MyAction(cost);
    assertEquals(cost, action.getCost(), TOLERANCE);
  }

  // equals and hashCode
  @Test
  public void testEqualsHashCode() {
    // retrieval/contains() from Set will call hashcode and equals
    Set<Action> set = new HashSet<>();
    Action action = new MyAction(12.3f);
    Action action2 = new MyAction(12.3f);
    set.add(action);
    set.add(action2);
    assertTrue(set.contains(action));
  }
}
