package au.net.hal9000.heisenberg.jbox2ddemo;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import au.net.hal9000.heisenberg.ai.MemorySetImpl;
import au.net.hal9000.heisenberg.ai.ModelStateAgentGoalMemorySet;
import au.net.hal9000.heisenberg.ai.ModelStateEvaluatorAgentGoal;
import au.net.hal9000.heisenberg.ai.SearchAStar;
import au.net.hal9000.heisenberg.ai.TransitionFunctionAgentGoalImpl;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionMove;
import au.net.hal9000.heisenberg.ai.api.MemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.item.EntitySuccessorFunction;
import au.net.hal9000.heisenberg.units.Position;

public class MazeCat {

    // Game Entity objects
    /** AI - path search */
    private SearchAStar search;
    /** JBox2d game object - cat */
    private Body cat;
    /** JBox2d game object - rat */
    private Body rat;

    MemorySet memorySet = new MemorySetImpl();

    MazeCat(Body cat, Body rat) {
        super();
        this.cat = cat;
        this.rat = rat;
        // Initialise AI
        aiInit();
    }

    private void aiInit() {

        // AI - Planning movement

        // Setup how we evaluate the worth of a new model state.
        final ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorAgentGoal();

        // Setup how to transition (move) to a new state.
        final TransitionFunction transitionFunction = new TransitionFunctionAgentGoalImpl();

        // Setup how to generate new successor states.
        final EntitySuccessorFunction successorFunction = new EntitySuccessorFunction(
                transitionFunction);

        successorFunction.setEntityRadiusMax(0.2f);

        search = new SearchAStar(successorFunction, modelStateEvaluator);

    }

    List<Position> aiPlan() {
        List<Position> catPositionPath = null;
        Vec2 catPosVec2 = cat.getPosition();
        Vec2 ratPosVec2 = rat.getPosition();

        // Build a model state.
        // Start is Cat's position. Goal is the Rat's position.
        Position catPos = new Position(catPosVec2.x, catPosVec2.y);
        Position ratPos = new Position(ratPosVec2.x, ratPosVec2.y);
        ModelStateAgentGoalMemorySet modelState = new ModelStateAgentGoalMemorySet(
                catPos.duplicate(), ratPos.duplicate(), memorySet);

        // Find a path to the goal e.g. Rat position.
        search.setFringeExpansionMax(3000);
        Path path = search.findPathToGoal(modelState);

        // Work out the points to head for.
        if (path != null) {
            Position catPosIteration = catPos.duplicate();
            List<Position> catPathTemp = new ArrayList<>();
            for (Action action : path) {
                if (action instanceof ActionMove) {
                    ActionMove actionMove = (ActionMove) action;
                    catPosIteration.applyDelta(actionMove.getPositionDelta());
                    catPathTemp.add(catPosIteration.duplicate());
                }
            }
            catPositionPath = catPathTemp;
        }
        return catPositionPath;
    }

    public List<ModelState> getFringeAdded() {
        return search.getFringeAdded();
    }

    public void learnBarrierArray(Vec2[] boundary_shape, Vec2 position,
            Object barrierObject) {
        MazeUtil.learnBarrierArray(memorySet, boundary_shape, position,
                barrierObject);
    }
}
