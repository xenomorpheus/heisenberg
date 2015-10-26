package au.net.hal9000.heisenberg.jbox2d.demo;

import au.net.hal9000.heisenberg.ai.api.MemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoalMemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelStateHunterPrey;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Holds a model of the world at any instant.<br>
 * This ModelState understands the following:<br>
 * ModelStateAgentGoalMemorySet - moving towards the goal (prey) but around
 * barriers that it has memories of.<br>
 * ModelStateHunterPrey - eating prey. <br>
 * 
 * @author bruins
 *
 */
public class ModelStateHunterPreyAgentGoalMemorySet
        implements ModelStateHunterPrey, ModelStateAgentGoalMemorySet {

    /** consider immutable */
    private Entity hunter;
    /** consider immutable */
    private Item prey;
    /** consider mutable */
    private Position agentPosition;
    /** consider mutable */
    private Position goalPosition;
    /** consider mutable */
    private MemorySet memorySet;
    /** consider mutable */
    private boolean preyEaten;

    public ModelStateHunterPreyAgentGoalMemorySet(Entity hunter, Item prey,
            Position agentPosition, Position goalPosition, MemorySet memorySet,
            boolean preyEaten) {
        this.hunter = hunter;
        this.prey = prey;
        this.agentPosition = agentPosition;
        this.goalPosition = goalPosition;
        this.memorySet = memorySet;
        this.preyEaten = preyEaten;
    }

    @Override
    public Entity getHunter() {
        return hunter;
    }

    @Override
    public Item getPrey() {
        return prey;
    }

    @Override
    public ModelState duplicate() {
        return new ModelStateHunterPreyAgentGoalMemorySet(hunter, prey,
                agentPosition.duplicate(), goalPosition.duplicate(),
                memorySet.duplicate(), preyEaten);
    }

    @Override
    public Position getAgentPosition() {
        return agentPosition;
    }

    @Override
    public Position getGoalPosition() {
        return goalPosition;
    }

    @Override
    public void setAgentPosition(Position agentPositionTarget) {
        agentPosition.set(agentPositionTarget);
    }

    @Override
    public MemorySet getMemorySet() {
        return memorySet;
    }

    @Override
    public Position getHunterPosition() {
        return agentPosition;
    }

    @Override
    public Position getPreyPosition() {
        return goalPosition;
    }

}
