package au.net.hal9000.heisenberg.jbox2ddemo;

import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

import au.net.hal9000.heisenberg.util.FiringSolution;

/**
 * The start of a class to run in the JBox2D physics engine.<br>
 * Currently must be run from within the JBox2D test bed frame.<br>
 * TODO: <br>
 * This class will eventually have a cat chase a mouse around walls.<br>
 * The cat will us AI to plan the route.<br>
 * The walls will be seen using a raycast for vision to see walls before hitting
 * them.
 */
public class MazeCat extends TestbedTest {

    /** Perform vision update every Nth world step */
    private static final int CAT_VISION_RATE_BASE = 50;
    /** Cat's speed. */
    private static final float CAT_NORMAL_SPEED = 1f;
    /** Cat's tag */
    private static final long CAT_TAG = 100L;
    /** Rat's tag */
    private static final long RAT_TAG = 101L;
    /** Other wall's tag */
    private static final long OUTER_WALL_TAG = 102L;
    /** Barrier's tag */
    private static final long BARRIER_TAG = 103L;
    /** Cat world object. */
    private Body cat;
    /** Cat will do a vision update every Nth world step */
    private int catVisionCounter = 0;
    /** Rat world object. */
    private Body rat;

    /**
     * Called by the JBox2D physics engine to allow the game objects to interact
     * with the engine.
     * 
     * @param settings
     *            TestbedSettings
     */
    @Override
    public synchronized void step(TestbedSettings settings) {

        super.step(settings);

        // TODO make use of vision
        if ((catVisionCounter++ % CAT_VISION_RATE_BASE) == 0) {
            // vision();
        }

        // If possible, Cat to intercept the Rat.
        Vec2 catPos = cat.getPosition();
        // Calculate an intercept point for Cat to target.
        Vec2 catTarget = FiringSolution.calculate(catPos, rat.getPosition(),
                rat.getLinearVelocity(), CAT_NORMAL_SPEED);

        Vec2 catDirection = new Vec2();
        // Null target means rat moving to quickly to intercept.
        if (catTarget != null) {
            // convert a target point into a direction.
            catDirection = catTarget.sub(catPos);
            // convert a direction into a velocity.
            if (catDirection.length() > CAT_NORMAL_SPEED) {
                catDirection.normalize();
                catDirection.mulLocal(CAT_NORMAL_SPEED);
            }
        }
        cat.setLinearVelocity(catDirection);

        // Centre the camera on the Cat
        setCamera(cat.getPosition());
    }

    /**
     * Initial setup of the objects in the physics engine.
     * 
     * @param deserialized
     *            boolean
     */
    @Override
    public void initTest(boolean deserialized) {
        if (deserialized) {
            return;
        }
        World world = getWorld();

        // Cancel gravity because we are looking from above.
        world.setGravity(new Vec2(0, 0));

        // Boundary Walls
        {

            float x = 10;
            float y = 15;
            Vec2[] vs = new Vec2[5];

            vs[0] = new Vec2(-x, -y);
            vs[1] = new Vec2(-x, +y);
            vs[2] = new Vec2(+x, +y);
            vs[3] = new Vec2(+x, -y);
            vs[4] = new Vec2(-x, -y);
            ChainShape shape = new ChainShape();
            shape.createChain(vs, 5);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.position.set(5, 5);
            bd.type = BodyType.STATIC;
            bd.userData = OUTER_WALL_TAG;
            Body body = world.createBody(bd);

            body.createFixture(fd);
        }

        // Internal Wall
        {

            float x = 5;
            float y = 5;
            Vec2[] vs = new Vec2[4];

            vs[0] = new Vec2(-x, -y);
            vs[1] = new Vec2(-x, 0);
            vs[2] = new Vec2(+x, 0);
            vs[3] = new Vec2(+x, -y);
            ChainShape shape = new ChainShape();
            shape.createChain(vs, 4);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.position.set(5, 5);
            bd.type = BodyType.STATIC;
            bd.userData = BARRIER_TAG;
            Body body = world.createBody(bd);

            body.createFixture(fd);
        }

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
            bd.position.set(5.0f, -5.0f);
            bd.userData = CAT_TAG;
            cat = world.createBody(bd);
            cat.createFixture(fd);
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
            bd.position.set(5.0f, 5.0f);
            bd.userData = RAT_TAG;
            rat = world.createBody(bd);
            rat.createFixture(fd);
        }
    }

    /**
     * @return Return the name of this test.
     */
    @Override
    public String getTestName() {
        return "Maze Cat";
    }

    /**
     * 
     * 
     * @return Return the default camera scale.
     */
    @Override
    public float getDefaultCameraScale() {
        return 35;
    }

    /**
     * Return the numerical value for the supplied body.
     * 
     * @param body
     *            Body we want the numerical value for.
     * @return numerical value
     * @see org.jbox2d.serialization.JbSerializer$ObjectSigner#getTag(Body)
     */
    @Override
    public Long getTag(Body body) {
        if (body == cat) {
            return CAT_TAG;
        }
        if (body == rat) {
            return RAT_TAG;
        }
        return super.getTag(body);
    }

    /**
     * Method processBody.
     * 
     * @param body
     *            Body
     * @param tag
     *            Long
     * @see org.jbox2d.serialization.JbDeserializer$ObjectListener#processBody(Body,
     *      Long)
     */
    @Override
    public void processBody(Body body, Long tag) {
        if (tag == CAT_TAG) {
            cat = body;
        } else if (tag == RAT_TAG) {
            rat = body;
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

    /**
     * Look for obstacles starting at Cat, ending at Rat.
     */
    private void vision() {
        RayCastMultipleCallback mcallback = new RayCastMultipleCallback();
        mcallback.init();
        Vec2 catPos = cat.getPosition();
        Vec2 ratPos = rat.getPosition();

        getWorld().raycast(mcallback, catPos, ratPos);

        getDebugDraw().drawSegment(catPos, ratPos,
                new Color3f(0.8f, 0.8f, 0.8f));

        for (int i = 0; i < mcallback.m_count; ++i) {
            Vec2 point = mcallback.m_points[i];
            // Vec2 normal = mcallback.m_normals[i];
            Long tag = (Long) mcallback.m_user_data[i];
            System.out.println("Saw tag=" + tag + ", at pos=" + point);
        }

    }

    // This ray cast collects multiple hits along the ray. Polygon 0 is
    // filtered.
    /**
     */
    private class RayCastMultipleCallback implements RayCastCallback {
        /**
         * Field RAYCAST_TERMINATE. (value is 0.0)
         */
        static final float RAYCAST_TERMINATE = 0f;
        // static final float RAYCAST_FILTER = -1f;
        /**
         * Field RAYCAST_CONTINUE. (value is 1.0)
         */
        static final float RAYCAST_CONTINUE = 1f;

        /**
         * Field E_MAX_COUNT. (value is 30)
         */
        static final int E_MAX_COUNT = 30;
        /**
         * Field m_points.
         */
        private Vec2[] m_points = new Vec2[E_MAX_COUNT];
        /**
         * Field m_normals.
         */
        private Vec2[] m_normals = new Vec2[E_MAX_COUNT];
        /**
         * Field m_user_data.
         */
        private Object[] m_user_data = new Object[E_MAX_COUNT];
        /**
         * Field m_count.
         */
        private int m_count;

        /**
         * Method init.
         */
        public void init() {
            for (int i = 0; i < E_MAX_COUNT; i++) {
                m_points[i] = new Vec2();
                m_normals[i] = new Vec2();
                m_user_data[i] = null;
            }
            m_count = 0;
        }

        /**
         * A callback for a raycast process. Method is called as the ray hits a
         * fixture.
         * 
         * @param fixture
         *            Fixture
         * @param point
         *            Vec2
         * @param normal
         *            Vec2
         * @param fraction
         *            float
         * @return float
         * @see org.jbox2d.callbacks.RayCastCallback#reportFixture(Fixture,
         *      Vec2, Vec2, float)
         */
        @Override
        public float reportFixture(Fixture fixture, Vec2 point, Vec2 normal,
                float fraction) {
            Body body = fixture.getBody();

            m_points[m_count].set(point);
            m_normals[m_count].set(normal);
            m_user_data[m_count] = body.getUserData();
            ++m_count;

            if (m_count == E_MAX_COUNT) {
                return RAYCAST_TERMINATE;
            }

            return RAYCAST_CONTINUE;
        };

    }
};
