package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.units.Position;
import org.junit.Test;

/**
 * Test ModelStateAgentGoal.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ModelStateAgentGoalImplTest {
  /** tolerance for comparing floats. */
  static final double DIFF = 0.00001f;

  /** test getGoalPosition. */
  @Test
  public void testGetGoalPosition() {
    Position agentPosition = new Position();
    Position goalPosition = new Position();
    ModelStateAgentGoal modelState = new ModelStateAgentGoalImpl(agentPosition, goalPosition);
    Position goalPositionGot = modelState.getGoalPosition();
    assertEquals(goalPosition, goalPositionGot);
  }

  /** test setAgentPosition. */
  @Test
  public void testSetAgentPosition() {
    Position agentPositionInitial = new Position();
    Position goalPosition = new Position();
    ModelStateAgentGoal modelStateAgentGoal =
        new ModelStateAgentGoalImpl(agentPositionInitial, goalPosition);
    Position agentPositionNew = new Position(0, 1);
    assertFalse(
        "test integrity. Possitions must be different",
        agentPositionInitial.equals(agentPositionNew));
    // Run test
    modelStateAgentGoal.setAgentPosition(agentPositionNew);
    Position agentPositionGot = modelStateAgentGoal.getAgentPosition();
    assertEquals("Position should be updated", agentPositionNew, agentPositionGot);
  }

  /** test duplicate. */
  @Test
  public void testDuplicate() {
    Position agentPosition = new Position(0, 1, 2);
    Position goalPosition = new Position(2, 1, 0);
    ModelStateAgentGoal modelState = new ModelStateAgentGoalImpl(agentPosition, goalPosition);
    // ModelState
    ModelStateAgentGoal newModelState = (ModelStateAgentGoalImpl) modelState.duplicate();
    assertFalse("ensure a new ModelStateAgentGoal is created", modelState == newModelState);
    assertTrue("ensure new ModelStateAgentGoal equals() old", modelState.equals(newModelState));

    // goal
    Position newGoalPosition = newModelState.getGoalPosition();
    assertFalse("ensure a new goalPosition is created", goalPosition == newGoalPosition);
    assertTrue("ensure a new goalPosition equals() old", goalPosition.equals(newGoalPosition));
  }

  /** test hashCode. */
  @Test
  public void testHashCode() {
    Position agentPosition = new Position(0, 1, 2);
    Position goalPosition = new Position();
    ModelStateAgentGoal modelState = new ModelStateAgentGoalImpl(agentPosition, goalPosition);

    Position agentPosition2 = new Position(0, 1, 2);
    Position goalPosition2 = new Position();
    ModelStateAgentGoal modelState2 = new ModelStateAgentGoalImpl(agentPosition2, goalPosition2);
    assertEquals(modelState.hashCode(), modelState2.hashCode());
  }
}
