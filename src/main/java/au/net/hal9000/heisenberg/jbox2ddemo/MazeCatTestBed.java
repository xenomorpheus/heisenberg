package au.net.hal9000.heisenberg.jbox2ddemo;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.callbacks.DebugDraw;
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

import au.net.hal9000.heisenberg.ai.ModelStateAgentGoal;
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
class MazeCatTestBed extends TestbedTest {

    /** Perform vision update every Nth world step */
    private static final int CAT_VISION_RATE_BASE = 60;
    /** Cat's speed. */
    private static final float CAT_NORMAL_SPEED = 1f;
    /** Cat's tag */
    private static final long CAT_TAG = 100L;
    /** Rat's tag */
    private static final long RAT_TAG = 101L;
    /** Other wall's tag */
    private static final long BOUNDARY_TAG = 102L;
    /** Barrier's tag */
    private static final long BARRIER_TAG = 103L;
    /** maze position */
    private static final float BARRIER_OFFSET_X = 5.0f;
    /** maze position */
    private static final float BARRIER_OFFSET_Y = 5.0f;
    /** Cat Jbox2d object. */
    private Body cat;
    /** Cat will do a vision update every Nth world step */
    private int catVisionCounter = 0;
    /** Rat Jbox2d object. */
    private Body rat;

    private List<Position> catPositionPath;

    private MazeCat mazeCat;

    public MazeCatTestBed() {
        super();
    }

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

        // TODO how best to combine AI planning and a moving target?
        // A* great for planning.
        // FiringSolution great for moving object.

        // TODO make use of vision
        // TODO planning in a different thread
        if ((catVisionCounter++ % CAT_VISION_RATE_BASE) == 0) {
            // TODO update cat's memory of barriers
            // vision();
            catPositionPath = mazeCat.aiPlan();

        }
        aiDaw();
        catMove();
        // Centre the camera on the Cat
        setCamera(cat.getPosition());
    }

    private void catMove() {
        if (catPositionPath != null) {
            // TODO if we are at the first target point, remove it and move to
            // next.
            Vec2 catPos = cat.getPosition();
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
            cat = world.createBody(bd);
            cat.createFixture(fd);
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
            bd.position.set(BARRIER_OFFSET_X, BARRIER_OFFSET_Y + 1.0f); // TODO
                                                                        // y=5.5
            bd.userData = RAT_TAG;
            rat = world.createBody(bd);
            rat.createFixture(fd);
        }

        mazeCat = new MazeCat(cat, rat);

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
            mazeCat.learnBarrierArray(boundary_shape, new Vec2(
                    BARRIER_OFFSET_X, BARRIER_OFFSET_Y), "boundary");

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
            mazeCat.learnBarrierArray(barrier_shape, new Vec2(BARRIER_OFFSET_X,
                    BARRIER_OFFSET_Y), "barrier");

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
        return 25;
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
     * Look for obstacles starting at Cat, ending at Rat.<br>
     * TODO update cat's memory of barriers.
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

    private void aiDaw() {

        DebugDraw debugDraw = getDebugDraw();

        // Draw the fringe
        List<MyCircle> myCircleList = fringeToCircles(mazeCat.getFringeAdded());
        Vec2 fringeVec2 = new Vec2();
        for (MyCircle myCircle : myCircleList) {
            fringeVec2.x = (float) myCircle.position.getX();
            fringeVec2.y = (float) myCircle.position.getY();
            debugDraw.drawSolidCircle(fringeVec2, 0.1f, null, myCircle.colour);
        }

        // Draw the Cat's planned path
        if (catPositionPath != null) {

            // Start is Cat's position.
            Vec2 catPosLast = cat.getPosition();
            Vec2 catPosNew = new Vec2();
            // Draw the planned path
            Color3f color = Color3f.BLUE;

            for (Position position : catPositionPath) {
                catPosNew.x = (float) position.getX();
                catPosNew.y = (float) position.getY();
                debugDraw.drawSolidCircle(catPosNew, 0.15f, null, color);
                debugDraw.drawSegment(catPosLast, catPosNew, color);
                catPosLast = catPosNew;
            }
        }

    }

    /** convert the fringe to a list of circles **/
    private List<MyCircle> fringeToCircles(List<ModelState> fringeAdded) {
        List<MyCircle> circleList = new ArrayList<>();
        if (fringeAdded != null) {
            int size = fringeAdded.size();
            float colourDiff = 1.0f / size;
            float colour = 1.0f;
            for (ModelState modelState : fringeAdded) {
                colour -= colourDiff;
                ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) modelState;
                circleList.add(new MyCircle(modelStateAgentGoal
                        .getAgentPosition(),
                        new Color3f(colour, colour, colour)));
            }
        }
        return circleList;
    }

    /**
     * Hold the position and colour of a circle shown on the JBox2D Testbed.
     *
     */
    private class MyCircle {
        /** middle of circle */
        public Position position;
        /** colour of circle */
        public Color3f colour;

        /**
         * Constructor
         * 
         * @param position
         *            of circle
         * @param colour
         *            of circle
         */
        public MyCircle(Position position, Color3f colour) {
            this.position = position;
            this.colour = colour;
        }

    }

};
