package au.net.hal9000.heisenberg.jbox2ddemo;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

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

    /** Cat's speed. */
    private static final float CAT_NORMAL_SPEED = 4f;
    /** Cat's tag */
    private static final long CAT_TAG = 100L;
    /** Rat's tag */
    private static final long RAT_TAG = 101L;
    /** maze position */
    private static final float BARRIER_OFFSET_X = 5.0f;
    /** maze position */
    private static final float BARRIER_OFFSET_Y = 5.0f;
    /** Cat Jbox2d object. */
    private Body cat;
    /** Rat Jbox2d object. */
    private Body rat;

    private List<Position> catPositionPath;

    private MazeCat mazeCat;

    public MazeCatTest() {
        super();
    }

    /**
     * Called by the JBox2D physics engine to allow the game objects to interact
     * with the engine.
     * 
     */
    public void step() {
        catPositionPath = mazeCat.aiPlan();
        aiPrint();
        catMove();
    }

    private void catMove() {
        // change to a while.
        if (catPositionPath != null) {
            // TODO check that we can reach that target point without hitting barrier.
            // TODO if we are at the first target point, remove it and move to
            // next.
            Vec2 catPos = cat.getPosition();
            // TODO replace the following with code to move to the target point
            Position catTarget = catPositionPath.get(0);
            Vec2 catDirection = new Vec2((float) catTarget.getX() - catPos.x,
                    (float) catTarget.getY() - catPos.y);
            catDirection.normalize();
            catDirection.mulLocal(CAT_NORMAL_SPEED);
            cat.setLinearVelocity(catDirection);
        }
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
            cat = new Body(bd, null);
        }

        // Rat
        {

            // body definition
            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y + 1.0f); // TODO
                                                                        // y=5.5
            bd.userData = RAT_TAG;
            rat = new Body(bd, null);
        }

        mazeCat = new MazeCat(cat, rat);

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

    private void aiPrint() {
        List<ModelState> modelStateList = mazeCat.getFringeAdded();
        for (ModelState modelState : modelStateList) {
            System.out.println("fringe=" + modelState);
        }

        // Draw the Cat's planned path
        if (catPositionPath != null) {

            // Start is Cat's position.
            Vec2 catPosLast = cat.getPosition();
            Vec2 catPosNew = new Vec2();
            // Draw the planned path

            for (Position position : catPositionPath) {
                catPosNew.x = (float) position.getX();
                catPosNew.y = (float) position.getY();
                System.out.println("From=" + catPosLast + " to " + catPosNew);
                catPosLast = catPosNew;
            }
        }

    }

};
