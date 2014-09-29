package au.net.hal9000.heisenberg.ai.api;

public interface GoalEstFunction {

    double estimatedCostToGoal(ModelState modelState);

}
