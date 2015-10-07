package au.net.hal9000.heisenberg.jbox2d.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.junit.Test;

import au.net.hal9000.heisenberg.jbox2d.demo.MazeCat;
import au.net.hal9000.heisenberg.jbox2d.demo.MazeUtil;
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
public class MazeCatTest {

    /** maze position */
    private static final float BARRIER_OFFSET_X = 5.0f;
    /** maze position */
    private static final float BARRIER_OFFSET_Y = 5.0f;
    /** update Cat AI Plan only every nth step */
    /** Cat Jbox2d object. */
    private Body catBody;
    /** Rat Jbox2d object. */
    private Body ratBody;

    private MazeCat mazeCat;

    /**
     * Called by the JBox2D physics engine to allow the game objects to interact
     * with the engine.
     * 
     */
    @Test
    public void testAiPlan() {
        initTest();
        mazeCat.aiPlan();
        List<Position> catPositionPath = mazeCat.getPositionPath();
        assertNotNull("path not null", catPositionPath );
        assertTrue("path size",catPositionPath.size() > 0);
    }

    /**
     * Initial setup of the objects in the physics engine.
     * 
     */
    private void initTest() {

        // Cat
        {

            // body definition
            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y - 8.0f);
            catBody = new Body(bd, null);
        }

        // Rat
        {

            // body definition
            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y + 1.0f); // TODO
                                                                        // y=5.5
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
