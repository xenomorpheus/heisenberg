package au.net.hal9000.heisenberg.jbox2d.demo;

import static org.junit.Assert.assertNotNull;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.junit.Before;
import org.junit.Test;

/**
 * The start of a class to run in the JBox2D physics engine.<br>
 * Currently must be run from within the JBox2D test bed frame.<br>
 * TODO: <br>
 * This class will eventually have a cat chase a mouse around walls.<br>
 * The cat will us AI to plan the route.<br>
 * The walls will be seen using a raycast for vision to see walls before hitting them.
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
    // Cancel gravity because we are looking from above.
    World world = new World(new Vec2(0, 0));

    // Cat
    {
      CircleShape shape = new CircleShape();
      shape.m_radius = 0.2f;

      FixtureDef fd = new FixtureDef();
      fd.shape = shape;
      fd.density = 0.5f;
      fd.friction = 0.3f;
      fd.restitution = 0.2f;

      // body definition
      BodyDef bd = new BodyDef();
      bd.type = BodyType.DYNAMIC;
      bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y - 8.0f);
      hunterBody = world.createBody(bd);
      hunterBody.createFixture(fd);
    }

    // Rat
    {
      CircleShape shape = new CircleShape();
      shape.m_radius = 0.1f;

      FixtureDef fd = new FixtureDef();
      fd.shape = shape;
      fd.density = 0.5f;
      fd.friction = 0.3f;
      fd.restitution = 0.2f;

      // body definition
      BodyDef bd = new BodyDef();
      bd.type = BodyType.DYNAMIC;
      bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y + 1.0f);

      preyBody = world.createBody(bd);
      preyBody.createFixture(fd);
    }
    hunterPrey = new HunterPreyAi(hunterBody, preyBody);
  }

  /** Test construction of the HunterPreyAI. */
  @Test
  public void ConstructorTest() {
    assertNotNull(hunterPrey);
  }

  /** Test learning where walls are. */
  @Test
  public void learnMemoryOfBarrierArrayTest() {
    // Setup and AI object to test with.
    ConstructorTest();

    // Outer Boundary Walls
    Vec2[] boundaryShape = MazeUtil.getBoundaryShape();
    hunterPrey.learnMemoryOfBarrierArray(
        boundaryShape, new Vec2(BARRIER_OFFSET_X, BARRIER_OFFSET_Y), "boundary");

    // Internal Barrier "n" shape - three lines.

    Vec2[] barrierShape = MazeUtil.getBarrierShape();
    hunterPrey.learnMemoryOfBarrierArray(
        barrierShape, new Vec2(BARRIER_OFFSET_X, BARRIER_OFFSET_Y), "barrier");
  }

  /** Test planning to get from hunter to prey. */
  @Test
  public void aiPlanTest() {
    // Setup and AI object to test with.
    learnMemoryOfBarrierArrayTest();
    hunterPrey.aiPlan(); // TODO add tests
  }

  /** Test planning to get from hunter to prey. */
  @Test
  public void toStringTest() {
    String string = hunterPrey.toString();
    assertNotNull(string);
  }
}
;
