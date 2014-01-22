package au.net.hal9000.heisenberg.ai;

public interface ModelStateEvaluator {

    /*
     * Add some AI terms about acceptable function. Is there some magic AI value
     * to return when we evaluate a goal state ? e.g. The higher the value the
     * better.
     */
    double evaluate(Model state);
}
