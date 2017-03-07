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

import au.net.hal9000.heisenberg.ai.ModelStateEvaluatorAgentGoal;
import au.net.hal9000.heisenberg.ai.ModelStateHunterPreyAgentGoalMemorySet;
import au.net.hal9000.heisenberg.ai.SearchAStar;
import au.net.hal9000.heisenberg.ai.SuccessorFunctionImpl;
import au.net.hal9000.heisenberg.ai.TransitionFunctionAgentGoalImpl;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionAgentMoveAbsolute;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.item.entity.Rat;
import au.net.hal9000.heisenberg.units.Position;

public class HunterPreyAi {

	/** Cat's speed. */
	private static final float HUNTER_NORMAL_SPEED = 4f;
	/** Cat's directions count. */
	private static final int HUNTER_DIRECTION_COUNT = 6;

	/**
	 * Cat's step size.<br>
	 * Must be greater than POSITON_TOLERANCE
	 */
	private static final float HUNTER_STEP_SIZE = 1f;
	/**
	 * How close together two points are to be considered same.<br>
	 * Must be less than HUNTER_STEP_SIZE
	 */
	private static final float POSITON_TOLERANCE = 0.5f;
	/** Max number of points to consider when planning a path to goal. */
	private static final int FRINGE_MAX = 1000;

	// Game Entity objects
	/** JBox2d game object - hunter */
	private Body hunterBody;
	/** JBox2d game object - target e.g. prey */
	private Body preyBody;
	/** AI - path search method */
	private SearchAStar search;
	/** AI - search results. List of actions */
	private Path hunterPath;
	/** hunter - Thinking game item */
	private Entity hunter;
	/** prey - Generic game item */
	private Item prey;
	/** world */
	private Location world;

	HunterPreyAi(Body hunterBody, Body preyBody) {
		super();
		this.hunterBody = hunterBody;
		this.preyBody = preyBody;		
		
		// Initialise AI
		aiInit();
	}

	// Getters and Setters

	private void aiInit() {

		// AI - Planning movement

		// Setup how we evaluate the worth of a new model state.
		ModelStateEvaluatorAgentGoal modelStateEvaluator = new ModelStateEvaluatorAgentGoal();

		// Setup how to transition (move) to a new state.
		final TransitionFunction transitionFunction = new TransitionFunctionAgentGoalImpl();

		ActionGenerator actionGenerator = new ActionGeneratorHunterPrey(HUNTER_STEP_SIZE, HUNTER_DIRECTION_COUNT,
				hunterBody.getFixtureList().getShape().m_radius, preyBody.getFixtureList().getShape().m_radius);
		// Setup how to generate new successor states.
		final SuccessorFunctionImpl successorFunction = new SuccessorFunctionImpl(transitionFunction);

		modelStateEvaluator.setPositionTolerance(POSITON_TOLERANCE);

		search = new SearchAStar(successorFunction, modelStateEvaluator, actionGenerator);
		search.setFringeExpansionMax(FRINGE_MAX);

		hunter = new Cat();
		prey = new Rat();
		world = new Location();
		world.add(hunter);
		world.add(prey);
	}

	public void learnBarrierArray(Vec2[] boundary_shape, Vec2 position, Object barrierObject) {
		MazeUtil.learnBarrierArray(hunter.getMemorySetValid(), boundary_shape, position, barrierObject);
	}

	void aiPlan() {
		Vec2 hunterPosVec2 = hunterBody.getPosition();
		Vec2 preyPosVec2 = preyBody.getPosition();

		// Build a model state.
		// Start is Cat's position. Goal is the Rat's position.
		Position hunterPos = new Position(hunterPosVec2.x, hunterPosVec2.y);

		ModelState modelState = new ModelStateHunterPreyAgentGoalMemorySet(hunter, prey, hunterPos.duplicate(),
				new Position(preyPosVec2.x, preyPosVec2.y), hunter.getMemorySetValid().duplicate(), false);

		// Find a path to the goal e.g. Rat position.
		hunterPath = search.findPathToGoal(modelState);
	}

	/**
	 * Move the hunter by following the path planned by the AI, or other means.
	 */
	void hunterPerformAction() {
		float effortRemaining = HUNTER_NORMAL_SPEED;

		//System.out.println(hunterPath.toString());

		// Follow the Path the AI search produced.
		while (hunterPath != null && hunterPath.size() > 0 && effortRemaining >0 ) {
            Action action = hunterPath.get(0);
			if (action instanceof ActionAgentMoveAbsolute) {
				ActionAgentMoveAbsolute absol = (ActionAgentMoveAbsolute) action;
				Position hunterTarget = absol.getAgentTarget();
				Vec2 hunterPos = hunterBody.getPosition();
				Vec2 directionToPathHead = new Vec2((float) hunterTarget.getX() - hunterPos.x,
						(float) hunterTarget.getY() - hunterPos.y);
				// if we are at the first point in the path, remove it and move
				// to next.
				float cost = directionToPathHead.length();
				if ((cost < POSITON_TOLERANCE) ) {
					hunterPath.remove(0);
				}
				effortRemaining -= cost;

				directionToPathHead.normalize();
				directionToPathHead.mulLocal(HUNTER_NORMAL_SPEED); // TODO cut speed when we reach goal
				hunterBody.setLinearVelocity(directionToPathHead);
			}
			else{
				// effortRemaining -= cost;
				throw new RuntimeException("Unknown action to perform ="+action.toString());
			}
		}
	}

	public String toString() {
		return this.getClass().toString();
	}

	/**
	 * Look for obstacles starting at Cat, ending at Rat.<br>
	 * TODO update hunter's memory of barriers.
	 * 
	 * @param world
	 *            JBox2d world
	 * @param debugDraw
	 */
	void vision(World world, DebugDraw debugDraw) {
		RayCastMultipleCallback mcallback = new RayCastMultipleCallback();
		mcallback.init();
		Vec2 hunterPos = hunterBody.getPosition();
		Vec2 targetPos = preyBody.getPosition();

		world.raycast(mcallback, hunterPos, targetPos);

		debugDraw.drawSegment(hunterPos, targetPos, new Color3f(0.8f, 0.8f, 0.8f));

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
		public float reportFixture(Fixture fixture, Vec2 point, Vec2 normal, float fraction) {
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
		if (hunterPath != null) {

			// Start is Cat's position.
			Vec2 hunterPosLast = hunterBody.getPosition();
			Vec2 hunterPosNew = new Vec2();
			// Draw the planned path
			Color3f color = Color3f.BLUE;

			for (Action action : hunterPath) {
				if (action instanceof ActionAgentMoveAbsolute) {
					ActionAgentMoveAbsolute absol = (ActionAgentMoveAbsolute) action;
					Position position = absol.getAgentTarget();
					hunterPosNew.x = (float) position.getX();
					hunterPosNew.y = (float) position.getY();
					debugDraw.drawSolidCircle(hunterPosNew, 0.15f, null, color);
					debugDraw.drawSegment(hunterPosLast, hunterPosNew, color);
					hunterPosLast = hunterPosNew;
				} else {
					System.out.println("Skipping action " + action);
				}
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
				circleList
						.add(new MyCircle(modelStateAgentGoal.getAgentPosition(), new Color3f(colour, colour, colour)));
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
