package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;

/**
 * Generate new ModelState objects from current ModelState object and
 * transitionFunction.<br>
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class SuccessorFunctionImpl implements SuccessorFunction {

    /** A Transition Function allows movement from one model state to another. */
    private TransitionFunction transitionFunction;

    /**
     * Constructor.
     * 
     * @param transitionFunction
     *            a Transition Function.
     */
    public SuccessorFunctionImpl(TransitionFunction transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    @Override
    public Queue<Successor> generateSuccessors(ModelState currentModelState, Path actions) {
        Queue<Successor> successors = new LinkedList<>();

        for (Action action : actions) {
            ModelState modelStateNew = transitionFunction.transition(
                    currentModelState, action);
            successors.add(new SuccessorImpl(modelStateNew, action, action
                    .getCost()));
        }
        return successors;
    }

}
