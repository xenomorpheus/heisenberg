package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.item.Item;

public class ModelStateV1 implements ModelState {
    /*
     * A very simple model that only holds two Item objects, the agent and the
     * goal.
     */

    /* the agent moving to goal */
    private Item agent;
    /* where the agent wants to be */
    private Item goal;

    public Item getAgent() {
        return agent;
    }

    public void setAgent(Item agent) {
        this.agent = agent;
    }

    public Item getGoal() {
        return goal;
    }

    public void setGoal(Item goal) {
        this.goal = goal;
    }

}
