package au.net.hal9000.heisenberg.ai;

/**
 * Evaluate the state of the model and give an evaluation rating on how close we
 * are to the goal state.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */

public interface ModelStateEvaluator {

    /**
     * How close are we to the goal?
     * 
     * @param modelState
     *            the state of the model.
    
     * @return How the goal? */
    double evaluate(ModelState modelState);

    /**
     * Are we at the goal?
     * 
     * @param modelState
     *            the state of the model.
    
     * @return Are we at the goal? */

    boolean atGoal(ModelState modelState);
}
