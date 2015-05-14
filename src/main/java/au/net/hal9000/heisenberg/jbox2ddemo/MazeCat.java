package au.net.hal9000.heisenberg.jbox2ddemo;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
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

import au.net.hal9000.heisenberg.ai.BarrierLine;
import au.net.hal9000.heisenberg.ai.MemoryImpl;
import au.net.hal9000.heisenberg.ai.MemoryOfBarrier;
import au.net.hal9000.heisenberg.ai.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.ai.ModelStateAgentGoalMemorySet;
import au.net.hal9000.heisenberg.ai.ModelStateEvaluatorAgentGoal;
import au.net.hal9000.heisenberg.ai.SearchAStar;
import au.net.hal9000.heisenberg.ai.TransitionFunctionAgentGoalImpl;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionMove;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.item.Cat;
import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.EntitySuccessorFunction;
import au.net.hal9000.heisenberg.units.Position;
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
class MazeCat extends TestbedTest {

    /** Perform vision update every Nth world step */
    private static final int CAT_VISION_RATE_BASE = 3;
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

    /** maze x factor */
    private static final float mazeX = 5;
    /** maze y factor */
    private static final float mazeY = 5;
    /** maze shape */
    Vec2 bottomLeft = new Vec2(-mazeX, -mazeY);
    Vec2 topLeft = new Vec2(-mazeX, 0);
    Vec2 topRight = new Vec2(+mazeX, 0);
    Vec2 bottomRight = new Vec2(+mazeX, -mazeY);
    Vec2[] barrierShape = { bottomLeft, topLeft, topRight, bottomRight };
    /** way points */
    float wayVertDelta = 1;
    float wayDelta = 0.5f; // (float) Math.sqrt(2 * wayVertDelta *
                           // wayVertDelta);
    private static float MAZE_OFFSET_X = 5.0f;
    private static float MAZE_OFFSET_Y = 5.0f;
    Vec2[] wayPoints = {
            new Vec2(MAZE_OFFSET_X + bottomLeft.x - wayDelta, MAZE_OFFSET_Y
                    + bottomLeft.y - wayDelta),
            new Vec2(MAZE_OFFSET_X + bottomLeft.x + wayDelta, MAZE_OFFSET_Y
                    + bottomLeft.y - wayDelta),
            new Vec2(MAZE_OFFSET_X + topLeft.x - wayDelta, MAZE_OFFSET_Y
                    + topLeft.y + wayDelta),
            new Vec2(MAZE_OFFSET_X + topRight.x + wayDelta, MAZE_OFFSET_Y
                    + topRight.y + wayDelta),
            new Vec2(MAZE_OFFSET_X + bottomRight.x - wayDelta, MAZE_OFFSET_Y
                    + bottomRight.y - wayDelta),
            new Vec2(MAZE_OFFSET_X + bottomRight.x + wayDelta, MAZE_OFFSET_Y
                    + bottomRight.y - wayDelta) };

    /** Cat world object. */
    private Body cat;
    /** Cat will do a vision update every Nth world step */
    private int catVisionCounter = 0;
    /** Rat world object. */
    private Body rat;
    /** AI - path search */
    private SearchAStar search;

    // Game Entity objects
    /** Cat Game object. */
    Entity catEntity;

    public MazeCat() {
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

        // If possible, Cat to intercept the Rat.
        Vec2 catPosVec2 = cat.getPosition();

        // TODO make use of vision
        if ((catVisionCounter++ % CAT_VISION_RATE_BASE) == 0) {
            // TODO update cat's memory of barriers
            // vision();
            aiRun();
        }

        // If possible, Cat to intercept the Rat.
        // Calculate an intercept point for Cat to target.
        Vec2 catTarget = FiringSolution.calculate(catPosVec2,
                rat.getPosition(), rat.getLinearVelocity(), CAT_NORMAL_SPEED);

        Vec2 catDirection = new Vec2();
        // Null target means rat moving too quickly to intercept.
        if (catTarget != null) {
            // convert a target point into a direction relative to cat.
            catDirection = catTarget.sub(catPosVec2);
            // convert a direction into a velocity.
            if (catDirection.length() > 0.1f) {
                catDirection.normalize();
                catDirection.mulLocal(CAT_NORMAL_SPEED);
            }
        }
        // cat.setLinearVelocity(catDirection);
        cat.setLinearVelocity(new Vec2());

        // Centre the camera on the Cat
        setCamera(cat.getPosition());
    }

    /**
     * Add to Entity memory a single barrier line.
     * 
     * @param entity
     *            entity that can learn memories.
     * @param point1
     *            an end of barrier line.
     * @param point2
     *            other end of barrier line.
     * @param body
     *            the object that is the barrier.
     */
    private static void learnBarrier(Entity entity, Point2D point1,
            Point2D point2, Object body) {

        // Simulate the results after seeing a wall.
        Line2D line = new Line2D.Double(point1, point2);
        Barrier barrier = new BarrierLine(line, body);
        MemoryImpl memory = new MemoryOfBarrier(null, 0, barrier);
        entity.memoryAdd(memory);
    }

    /**
     * Add to Entity memory an array of barrier lines.
     * 
     * @param entity
     *            entity.
     * @param point
     *            an array of Vec2 points.
     * @param position
     *            the position of this shape.
     * @param body
     *            the object that is the barrier.
     */
    private static void learnBarrierArray(Entity entity, Vec2[] point,
            Vec2 position, Object body) {

        for (int index = 1; index < point.length; index++) {
            Point2D point1 = new Point2D.Double(
                    point[index - 1].x + position.x, point[index - 1].y
                            + position.y);
            Point2D point2 = new Point2D.Double(point[index].x + position.x,
                    point[index].y + position.y);
            learnBarrier(entity, point1, point2, body);
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

        // Outer Boundary Walls
        {

            float x = 10;
            float y = 15;
            Vec2[] vs = { new Vec2(-x, -y), new Vec2(-x, +y), new Vec2(+x, +y),
                    new Vec2(+x, -y), new Vec2(-x, -y) };
            ChainShape shape = new ChainShape();
            shape.createChain(vs, vs.length);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.position.set(MAZE_OFFSET_X, MAZE_OFFSET_Y);
            bd.type = BodyType.STATIC;
            bd.userData = OUTER_WALL_TAG;
            Body body = world.createBody(bd);

            body.createFixture(fd);
        }

        // Internal Barrier "n" shape - three lines.
        {

            ChainShape shape = new ChainShape();
            shape.createChain(barrierShape, barrierShape.length);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.position.set(MAZE_OFFSET_X, MAZE_OFFSET_Y);
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
            bd.position.set(MAZE_OFFSET_X, MAZE_OFFSET_Y - 8.0f);
            bd.userData = CAT_TAG;
            cat = world.createBody(bd);
            cat.createFixture(fd);
            // TODO cat.setUserData(catEntity);
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
            bd.position.set(MAZE_OFFSET_X, MAZE_OFFSET_Y + 1.0f); // TODO y=5.5
            bd.userData = RAT_TAG;
            rat = world.createBody(bd);
            rat.createFixture(fd);
        }

        // Initialise AI
        aiInit();
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

    private void aiInit() {
        catEntity = new Cat();

        // AI - Planning movement

        // Setup how we evaluate the worth of a new model state.
        final ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorAgentGoal();

        // Setup how to transition (move) to a new state.
        final TransitionFunction transitionFunction = new TransitionFunctionAgentGoalImpl();

        // Setup how to generate new successor states.
        final SuccessorFunction successorFunction = new EntitySuccessorFunction(
                transitionFunction);

        search = new SearchAStar(successorFunction, modelStateEvaluator);
        // searchAStar.setDebugDraw(getDebugDraw());

        // Learn barrier
        // TODO remove cheat and use vision to see barriers.
        // TODO learnBarrierArray(catEntity, vs, bd.position, body);
        learnBarrierArray(catEntity, barrierShape, new Vec2(5, 5),
                "some barrier");

    }

    private void aiRun() {

        Vec2 catPosVec2 = cat.getPosition();
        Vec2 ratPosVec2 = rat.getPosition();

        // Build a model state.
        Position catPos = new Position(catPosVec2.x, catPosVec2.y);
        Position ratPos = new Position(ratPosVec2.x, ratPosVec2.y);
        ModelStateAgentGoalMemorySet modelState = new ModelStateAgentGoalMemorySet(
                catPos.duplicate(), ratPos.duplicate(), catEntity
                        .getMemorySet().duplicate());

        search.setFringeExpansionMax(30);
        Path path = search.findPathToGoal(modelState);

        DebugDraw debugDraw = getDebugDraw();

        /* Draw way points */
        for (Vec2 wayPoint : wayPoints) {
            debugDraw.drawSolidCircle(wayPoint, 0.1f, null, Color3f.WHITE);
        }

        if (path == null) {
            List<ModelState> fringeAdded = search.getFringeAdded();
            List<MyCircle> myCircleList = fringeToCircles(fringeAdded);
            for (MyCircle myCircle : myCircleList) {
                debugDraw.drawSolidCircle(
                        new Vec2((float) myCircle.position.getX(),
                                (float) myCircle.position.getY()), 0.1f, null,
                        myCircle.color);
            }
        } else {

            // TODO Draw planned path
            // DebugDraw debugDraw = getDebugDraw();
            Color3f color = Color3f.BLUE;
            Position catPosIteration = catPos.duplicate();
            for (Action action : path) {
                if (action instanceof ActionMove) {
                    ActionMove actionMove = (ActionMove) action;
                    Position lastPosition = catPosIteration.duplicate();
                    Vec2 lastPosVec2 = new Vec2((float) lastPosition.getX(),
                            (float) lastPosition.getY());
                    catPosIteration.applyDelta(actionMove.getPositionDelta());
                    debugDraw.drawSolidCircle(lastPosVec2, 0.15f, null, color);
                    debugDraw.drawSegment(lastPosVec2, new Vec2(
                            (float) catPosIteration.getX(),
                            (float) catPosIteration.getY()), color);
                }

            }
        }
    }

    private List<MyCircle> fringeToCircles(List<ModelState> fringeAdded) {
        List<MyCircle> circleList = new ArrayList<>();
        for (ModelState modelState : fringeAdded) {
            ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) modelState;
            circleList.add(new MyCircle(modelStateAgentGoal.getAgentPosition(),
                    Color3f.GREEN));
        }
        return circleList;
    }
};
