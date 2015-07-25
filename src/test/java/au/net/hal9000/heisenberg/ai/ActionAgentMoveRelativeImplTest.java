package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.units.Position;

/**
 */
public class ActionAgentMoveRelativeImplTest {

	/**
	 * test equals.
	 */
	@Test
	public void testEquals() {
		// compare both label and xyz.
		Action action1 = new ActionAgentMoveRelativeImpl(new Position(1, 2),
				3.0f);
		Action action2 = new ActionAgentMoveRelativeImpl(new Position(1, 2),
				3.0f);
		Action action2DiffPosition = new ActionAgentMoveRelativeImpl(
				new Position(4, 2), 3.0f);
		Action action2DiffCost = new ActionAgentMoveRelativeImpl(new Position(
				1, 2), 4.0f);
		// Equals, including reflective
		assertTrue(action1.equals(action2));
		assertTrue(action2.equals(action1));
		// Position, including reflective
		assertFalse(action1.equals(action2DiffPosition));
		assertFalse(action2DiffPosition.equals(action1));
		// Cost, including reflective
		assertFalse(action1.equals(action2DiffCost));
		assertFalse(action2DiffCost.equals(action1));

	}

}
