package au.net.hal9000.heisenberg.ai;

public interface TransitionFunction {
    
    ModelState transition(ModelState modelState, Action action);

}
