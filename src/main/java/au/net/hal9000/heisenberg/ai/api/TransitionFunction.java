package au.net.hal9000.heisenberg.ai.api;


/**
 * The TransitionFunction takes a ModelState and applies the Action which results in a new ModelState object.<br>
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public interface TransitionFunction {
    
    /**
     * Method transition.
     * @param modelState ModelState
     * @param action Action
     * @return ModelState
     */
    ModelState transition(ModelState modelState, Action action);

}
