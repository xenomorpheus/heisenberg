package au.net.hal9000.heisenberg.jbox2d.demo;

import static org.junit.Assert.assertNotNull;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.junit.Before;
import org.junit.Test;

/**
 * The start of a class to run in the JBox2D physics engine.<br>
 * Currently must be run from within the JBox2D test bed frame.<br>
 * TODO: <br>
 * This class will eventually have a cat chase a mouse around walls.<br>
 * The cat will us AI to plan the route.<br>
 * The walls will be seen using a raycast for vision to see walls before hitting
 * them.
 */
public class HunterPreyAiTest {

	/** maze position */
	private static final float BARRIER_OFFSET_X = 5.0f;
	/** maze position */
	private static final float BARRIER_OFFSET_Y = 5.0f;
	/** update Cat AI Plan only every nth step */
	/** Cat Jbox2d object. */
	private Body hunterBody;
	/** Rat Jbox2d object. */
	private Body preyBody;

	/* Hunter Prey AI object */
	private HunterPreyAi hunterPrey;

	@Before
	public void setup() {

		// Cat
		{

			// body definition
			BodyDef bd = new BodyDef();
			bd.type = BodyType.DYNAMIC;
			bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y - 8.0f);
			hunterBody = new Body(bd, null);
		}

		// Rat
		{

			// body definition
			BodyDef bd = new BodyDef();
			bd.type = BodyType.DYNAMIC;
			// TODO set offset y=5.5
			bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y + 1.0f);
			preyBody = new Body(bd, null);
		}

	}

	/**
	 * Test construction of the HunterPreyAI.
	 */
	@Test
	public void ConstructorTest() {
		hunterPrey = new HunterPreyAi(hunterBody, preyBody);
		assertNotNull(hunterPrey);
	}

	/**
	 * Test learning where walls are.
	 */
	@Test
	public void learnBarrierArrayTest() {
		// Setup and AI object to test with.
		ConstructorTest();

		// Outer Boundary Walls
		Vec2[] boundary_shape = MazeUtil.getBoundaryShape();
		hunterPrey.learnBarrierArray(boundary_shape, new Vec2(BARRIER_OFFSET_X, BARRIER_OFFSET_Y), "boundary");

		// Internal Barrier "n" shape - three lines.

		Vec2[] barrier_shape = MazeUtil.getBarrierShape();
		hunterPrey.learnBarrierArray(barrier_shape, new Vec2(BARRIER_OFFSET_X, BARRIER_OFFSET_Y), "barrier");

	}

	/**
	 * Test planning to get from hunter to prey.
	 */
	@Test
	public void aiPlanTest() {
		// Setup and AI object to test with.
		learnBarrierArrayTest();
		hunterPrey.aiPlan(); // TODO add tests
	}

	/**
	 * Test planning to get from hunter to prey.
	 */
	@Test
	public void toStringTest() {
		// Setup and AI object to test with.
		aiPlanTest();
		String string = hunterPrey.toString();
		assertNotNull(string);  // TODO add tests
	}


};
