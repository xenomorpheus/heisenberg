package au.net.hal9000.heisenberg.jbox2ddemo;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

/**
 * The start of a class to run in the JBox2D physics engine.<br>
 * Currently must be run from within the JBox2D test bed frame.<br>
 * TODO: <br>
 * This class will eventually have a cat chase a mouse around walls.<br>
 * The cat will us AI to plan the route.<br>
 * The walls will be seen using a raycast for vision to see walls before hitting
 * them.
 */
class MazeCatTest {

    /** Cat's tag */
    private static final long CAT_TAG = 100L;
    /** Rat's tag */
    private static final long RAT_TAG = 101L;
    /** maze position */
    private static final float BARRIER_OFFSET_X = 5.0f;
    /** maze position */
    private static final float BARRIER_OFFSET_Y = 5.0f;
    /** update Cat AI Plan only every nth step */
    private static final int CAT_AI_UPDATE = 60;
    /** Cat Jbox2d object. */
    private Body catBody;
    /** Rat Jbox2d object. */
    private Body ratBody;

    private MazeCat mazeCat;
    private int stepCount = 0;

    public MazeCatTest() {
        super();
    }

    /**
     * Called by the JBox2D physics engine to allow the game objects to interact
     * with the engine.
     * 
     */
    public void step() {
        if ((stepCount % CAT_AI_UPDATE) == 0) {
            mazeCat.aiPlan();
            mazeCat.aiPrint();
        }
        stepCount++;
        mazeCat.move();
    }

    /**
     * Initial setup of the objects in the physics engine.
     * 
     */
    public void initTest() {

        // Cat
        {

            // body definition
            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y - 8.0f);
            bd.userData = CAT_TAG;
            catBody = new Body(bd, null);
        }

        // Rat
        {

            // body definition
            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y + 1.0f); // TODO
                                                                        // y=5.5
            bd.userData = RAT_TAG;
            ratBody = new Body(bd, null);
        }

        mazeCat = new MazeCat(catBody, ratBody);

        // Outer Boundary Walls
        {

            Vec2[] boundary_shape = MazeUtil.getBoundaryShape();
            mazeCat.learnBarrierArray(boundary_shape, new Vec2(
                    BARRIER_OFFSET_X, BARRIER_OFFSET_Y), "boundary");

        }

        // Internal Barrier "n" shape - three lines.
        {

            Vec2[] barrier_shape = MazeUtil.getBarrierShape();
            mazeCat.learnBarrierArray(barrier_shape, new Vec2(BARRIER_OFFSET_X,
                    BARRIER_OFFSET_Y), "barrier");

        }
    }

};
