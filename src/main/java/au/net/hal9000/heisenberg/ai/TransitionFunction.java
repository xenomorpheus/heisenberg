package au.net.hal9000.heisenberg.ai;

/**
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
