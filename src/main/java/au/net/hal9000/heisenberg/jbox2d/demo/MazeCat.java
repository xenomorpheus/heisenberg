package au.net.hal9000.heisenberg.jbox2d.demo;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import au.net.hal9000.heisenberg.ai.MemorySetImpl;
import au.net.hal9000.heisenberg.ai.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.ai.ModelStateAgentGoalMemorySet;
import au.net.hal9000.heisenberg.ai.ModelStateEvaluatorAgentGoal;
import au.net.hal9000.heisenberg.ai.SearchAStar;
import au.net.hal9000.heisenberg.ai.TransitionFunctionAgentGoalImpl;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionAgentMoveAbsolute;
import au.net.hal9000.heisenberg.ai.api.ActionAgentMoveRelative;
import au.net.hal9000.heisenberg.ai.api.MemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.item.EntitySuccessorFunction;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.FiringSolution;

public class MazeCat {

    /** Cat's speed. */
    private static final float CAT_NORMAL_SPEED = 4f;
    /** Cat's directions count. */
    private static final int CAT_DIRECTION_COUNT = 6;
    /** Cat radius. Used for distance from obstacles */
    private static final float CAT_RADIUS = 0.2f;
    /**
     * Cat's step size.<br>
     * Must be greater than POSITON_TOLERANCE
     */
    private static final float CAT_STEP_SIZE = 1f;
    /**
     * How close together two points are to be considered same.<br>
     * Must be less than CAT_STEP_SIZE
     */
    private static final float POSITON_TOLERANCE = 0.5f;
    /** Max number of points to consider when planning a path to goal. */
    private static final int FRINGE_MAX = 1000;

    // Game Entity objects
    /** JBox2d game object - cat */
    private Body catBody;
    /** JBox2d game object - target e.g. rat */
    private Body targetBody;
    /** AI - path search */
    private SearchAStar search;

    private List<Position> positionPath = null;

    private MemorySet memorySet = new MemorySetImpl();

    MazeCat(Body catBody, Body targetBody) {
        super();
        this.catBody = catBody;
        this.targetBody = targetBody;
        // Initialise AI
        aiInit();
    }

    /**
     * @return the path to the goal.
     */
    List<Position> getPositionPath() {
        return positionPath;
    }

    private void aiInit() {

        // AI - Planning movement

        // Setup how we evaluate the worth of a new model state.
        final ModelStateEvaluatorAgentGoal modelStateEvaluator = new ModelStateEvaluatorAgentGoal();

        // Setup how to transition (move) to a new state.
        final TransitionFunction transitionFunction = new TransitionFunctionAgentGoalImpl();

        // Setup how to generate new successor states.
        final EntitySuccessorFunction successorFunction = new EntitySuccessorFunction(
                transitionFunction, CAT_STEP_SIZE, CAT_DIRECTION_COUNT);

        modelStateEvaluator.setPositionTolerance(POSITON_TOLERANCE);
        successorFunction.setEntityRadiusMax(CAT_RADIUS);

        search = new SearchAStar(successorFunction, modelStateEvaluator);

    }

    void learnBarrierArray(Vec2[] boundary_shape, Vec2 position,
            Object barrierObject) {
        MazeUtil.learnBarrierArray(memorySet, boundary_shape, position,
                barrierObject);
    }

    void aiPlan() {
        Vec2 catPosVec2 = catBody.getPosition();
        Vec2 ratPosVec2 = targetBody.getPosition();

        // Build a model state.
        // Start is Cat's position. Goal is the Rat's position.
        Position catPos = new Position(catPosVec2.x, catPosVec2.y);
        Position ratPos = new Position(ratPosVec2.x, ratPosVec2.y);
        ModelStateAgentGoalMemorySet modelState = new ModelStateAgentGoalMemorySet(
                catPos.duplicate(), ratPos.duplicate(), memorySet);

        // Find a path to the goal e.g. Rat position.
        search.setFringeExpansionMax(FRINGE_MAX);
        // Work out the points to head for.
        Path path = search.findPathToGoal(modelState);

        // If AI planning succeeded then follow the path to goal.
        if (path != null && path.size() > 0) {
            // The path is relative to the state at which the actor performed
            // the search.
            // Since the actor has moved from that point, the relative path
            // would be wrong.
            // To solve this we convert the path to a list of absolute
            // locations.
            Position catPosIteration = catPos.duplicate();
            List<Position> catPathTemp = new ArrayList<>();
            for (Action action : path) {
                if (action instanceof ActionAgentMoveRelative) {
                    ActionAgentMoveRelative actionMove = (ActionAgentMoveRelative) action;
                    catPosIteration.applyDelta(actionMove.getPositionDelta());
                    catPathTemp.add(catPosIteration.duplicate());
                }
                else if (action instanceof ActionAgentMoveAbsolute) {
                    ActionAgentMoveAbsolute actionMove = (ActionAgentMoveAbsolute) action;
                    catPosIteration.set(actionMove.getAgentTarget());
                    catPathTemp.add(catPosIteration.duplicate());
                }
            }
            positionPath = catPathTemp;
        }
        // If AI planning failed,
        else {

            positionPath = new ArrayList<>();
            // Try calculating a firing solution.
            Vec2 solution = FiringSolution.calculate(catBody.getPosition(),
                    targetBody.getPosition(), targetBody.getLinearVelocity(),
                    CAT_NORMAL_SPEED);

            // We have a firing solution, so use it.
            if (solution != null) {
                positionPath.add(new Position(solution.x, solution.y));
            } else {
                // All else failed, just then aim directly towards target and
                // hope for the best.
                positionPath.add(new Position(targetBody.getPosition().x,
                        targetBody.getPosition().y));
            }
        }
    }

    /**
     * Move the cat by following the path planned by the AI.
     */
    void move() {
        // change to a while.
        Vec2 catPos = catBody.getPosition();
        Vec2 directionToPathHead;

        // Follow the Path the AI search produced.
        if (positionPath != null && positionPath.size() > 0) {

            Position catTarget = positionPath.get(0);
            directionToPathHead = new Vec2((float) catTarget.getX() - catPos.x,
                    (float) catTarget.getY() - catPos.y);
            // if we are at the first point in the path, remove it and move to
            // next.
            while ((directionToPathHead.length() < POSITON_TOLERANCE)
                    && (positionPath.size() > 1)) {
                positionPath.remove(0);
                catTarget = positionPath.get(0);
                directionToPathHead.x = (float) catTarget.getX() - catPos.x;
                directionToPathHead.y = (float) catTarget.getY() - catPos.y;
            }

            directionToPathHead.normalize();
            directionToPathHead.mulLocal(CAT_NORMAL_SPEED);
            catBody.setLinearVelocity(directionToPathHead);
        }
    }

    public void aiPrint() {
        List<ModelState> modelStateList = search.getFringeAdded();
        for (ModelState modelState : modelStateList) {
            System.out.println("fringe=" + modelState);
        }

        // Draw the Cat's planned path
        if (positionPath != null) {

            // Start is Cat's position.
            Vec2 catPosLast = catBody.getPosition();
            Vec2 catPosNew = new Vec2();
            // Draw the planned path

            for (Position position : positionPath) {
                catPosNew.x = (float) position.getX();
                catPosNew.y = (float) position.getY();
                System.out.println("From=" + catPosLast + " to " + catPosNew);
                catPosLast = catPosNew;
            }
        }

    }

    /**
     * Look for obstacles starting at Cat, ending at Rat.<br>
     * TODO update cat's memory of barriers.
     * 
     * @param world
     *            JBox2d world
     * @param debugDraw
     */
    void vision(World world, DebugDraw debugDraw) {
        RayCastMultipleCallback mcallback = new RayCastMultipleCallback();
        mcallback.init();
        Vec2 catPos = catBody.getPosition();
        Vec2 targetPos = targetBody.getPosition();

        world.raycast(mcallback, catPos, targetPos);

        debugDraw.drawSegment(catPos, targetPos, new Color3f(0.8f, 0.8f, 0.8f));

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

    void aiFringeDraw(DebugDraw debugDraw) {

        // Draw the fringe
        List<MyCircle> myCircleList = fringeToCircles(search.getFringeAdded());
        Vec2 fringeVec2 = new Vec2();
        for (MyCircle myCircle : myCircleList) {
            fringeVec2.x = (float) myCircle.position.getX();
            fringeVec2.y = (float) myCircle.position.getY();
            debugDraw.drawSolidCircle(fringeVec2, 0.1f, null, myCircle.colour);
        }
    }

    void aiPathDraw(DebugDraw debugDraw) {

        // Draw the Cat's planned path
        if (positionPath != null) {

            // Start is Cat's position.
            Vec2 catPosLast = catBody.getPosition();
            Vec2 catPosNew = new Vec2();
            // Draw the planned path
            Color3f color = Color3f.BLUE;

            for (Position position : positionPath) {
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
}
