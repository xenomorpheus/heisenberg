package au.net.hal9000.heisenberg.jbox2d.demo;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

/**
 * The start of a class to run in the JBox2D physics engine.<br>
 * Currently must be run from within the JBox2D test bed frame.<br>
 * TODO: <br>
 * This class will eventually have a cat chase a mouse around walls.<br>
 * The cat will us AI to plan the route.<br>
 * The walls will be seen using a raycast for vision to see walls before hitting them.
 */
class HunterPreyTestBed extends TestbedTest {

  /** Perform vision update every Nth world step. */
  private static final int CAT_AI_UPDATE = 30;
  /** Cat's tag. */
  private static final long CAT_TAG = 100L;
  /** Rat's tag. */
  private static final long RAT_TAG = 101L;
  /** Other wall's tag. */
  private static final long BOUNDARY_TAG = 102L;
  /** Barrier's tag. */
  private static final long BARRIER_TAG = 103L;
  /** maze position. */
  private static final float BARRIER_OFFSET_X = 5.0f;
  /** maze position. */
  private static final float BARRIER_OFFSET_Y = 5.0f;
  /** Cat Jbox2d object. */
  private Body catBody;
  /** Cat will do a vision update every Nth world step. */
  private int catAiCounter = 0;
  /** Rat Jbox2d object. */
  private Body ratBody;
  /** AI. */
  private HunterPreyAi hunterPreyAi;
  /** JBox Physics Engine world. */
  private World world = null;
  /** Debug Graphics object for drawing in JBox Physics Engine world. */
  private DebugDraw debugDraw = null;

  /** Constructor */
  public HunterPreyTestBed() {
    super();
  }

  /**
   * Called by the JBox2D physics engine to allow the game objects to interact with the engine.
   *
   * @param settings TestbedSettings
   */
  @Override
  public synchronized void step(TestbedSettings settings) {

    if (hunterPreyAi.isComplete()) {
      reset();
    }
    super.step(settings);

    // TODO how best to combine AI planning and a moving target?
    // A* great for planning.
    // FiringSolution great for moving object.

    if ((catAiCounter++ % CAT_AI_UPDATE) == 0) {
      // TODO make use of vision
      // mazeCat.vision(world, debugDraw);
      // TODO update cat's memory of barriers
      // TODO planning in a different thread
      hunterPreyAi.aiPlan();
      // mazeCat.aiPrint();

    }
    hunterPreyAi.aiFringeDraw(debugDraw);
    hunterPreyAi.aiPathDraw(debugDraw);
    hunterPreyAi.hunterPerformAction();

    // Centre the camera on the Cat
    setCamera(catBody.getPosition());
  }

  /**
   * Initial setup of the objects in the physics engine.
   *
   * @param deserialized boolean
   */
  @Override
  public void initTest(boolean deserialized) {
    if (deserialized) {
      return;
    }
    world = getWorld();
    debugDraw = getDebugDraw();

    // Cancel gravity because we are looking from above.
    world.setGravity(new Vec2(0, 0));

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
      bd.userData = CAT_TAG;
      catBody = world.createBody(bd);
      catBody.createFixture(fd);
      // TODO cat.setUserData(mazeCat);
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
      // TODO y=5.5
      bd.userData = RAT_TAG;
      ratBody = world.createBody(bd);
      ratBody.createFixture(fd);
    }

    hunterPreyAi = new HunterPreyAi(catBody, ratBody);

    // TODO remove cheat and use vision to see boundary and barriers.
    // Outer Boundary Walls
    {
      Vec2[] boundary_shape = MazeUtil.getBoundaryShape();

      ChainShape shape = new ChainShape();
      shape.createChain(boundary_shape, boundary_shape.length);

      FixtureDef fd = new FixtureDef();
      fd.shape = shape;
      fd.density = 0.5f;
      fd.friction = 0.3f;
      fd.restitution = 0.2f;

      // body definition
      BodyDef bd = new BodyDef();
      bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y);
      bd.type = BodyType.STATIC;
      bd.userData = BOUNDARY_TAG;
      Body body = world.createBody(bd);

      body.createFixture(fd);

      // Learn boundary.
      hunterPreyAi.learnBarrierArray(
          boundary_shape, new Vec2(BARRIER_OFFSET_X, BARRIER_OFFSET_Y), "boundary");
    }

    // Internal Barrier "n" shape - three lines.
    {
      Vec2[] barrier_shape = MazeUtil.getBarrierShape();
      ChainShape shape = new ChainShape();
      shape.createChain(barrier_shape, barrier_shape.length);

      FixtureDef fd = new FixtureDef();
      fd.shape = shape;
      fd.density = 0.5f;
      fd.friction = 0.3f;
      fd.restitution = 0.2f;

      // body definition
      BodyDef bd = new BodyDef();
      bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y);
      bd.type = BodyType.STATIC;
      bd.userData = BARRIER_TAG;
      Body body = world.createBody(bd);

      body.createFixture(fd);

      // Learn internal barrier.
      hunterPreyAi.learnBarrierArray(
          barrier_shape, new Vec2(BARRIER_OFFSET_X, BARRIER_OFFSET_Y), "barrier");
    }
  }

  /** @return Return the name of this test. */
  @Override
  public String getTestName() {
    return "Hunter Prey";
  }

  /** @return Return the default camera scale. */
  @Override
  public float getDefaultCameraScale() {
    return 25;
  }

  /**
   * Return the numerical value for the supplied body.
   *
   * @param body Body we want the numerical value for.
   * @return numerical value
   * @see org.jbox2d.serialization.JbSerializer$ObjectSigner#getTag(Body)
   */
  @Override
  public Long getTag(Body body) {
    if (body == catBody) {
      return CAT_TAG;
    }
    if (body == ratBody) {
      return RAT_TAG;
    }
    return super.getTag(body);
  }

  /**
   * Method processBody.
   *
   * @param body Body
   * @param tag Long
   * @see org.jbox2d.serialization.JbDeserializer$ObjectListener#processBody(Body, Long)
   */
  @Override
  public void processBody(Body body, Long tag) {
    if (tag == CAT_TAG) {
      catBody = body;
    } else if (tag == RAT_TAG) {
      ratBody = body;
    } else {
      super.processBody(body, tag);
    }
  }

  /**
   * Method isSaveLoadEnabled.
   *
   * @return boolean
   */
  @Override
  public boolean isSaveLoadEnabled() {
    return true;
  }
}
;
