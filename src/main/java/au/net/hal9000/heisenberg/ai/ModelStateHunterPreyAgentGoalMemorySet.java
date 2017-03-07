package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.MemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoalMemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelStateConsumerConsumable;
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
        implements ModelStateConsumerConsumable, ModelStateAgentGoalMemorySet {

    /** consider immutable */
    private Object hunter;
    /** consider immutable */
    private Object prey;
    /** consider mutable */
    private Position agentPosition;
    /** consider mutable */
    private Position goalPosition;
    /** consider mutable */
    private MemorySet memorySet;
    /** consider mutable */
    private boolean preyConsumed;

    public ModelStateHunterPreyAgentGoalMemorySet(Object hunter, Object prey,
            Position agentPosition, Position goalPosition, MemorySet memorySet,
            boolean preyConsume) {
        this.hunter = hunter;
        this.prey = prey;
        this.agentPosition = agentPosition;
        this.goalPosition = goalPosition;
        this.memorySet = memorySet;
        this.preyConsumed = preyConsume;
    }

    @Override
    public Object getConsumer() {
        return hunter;
    }

    @Override
    public Object getConsumable() {
        return prey;
    }

    @Override
    public ModelState duplicate() {
        return new ModelStateHunterPreyAgentGoalMemorySet(hunter, prey,
                agentPosition.duplicate(), goalPosition.duplicate(),
                memorySet.duplicate(), preyConsumed);
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
    public Position getEntityPosition() {
        return agentPosition;
    }

    @Override
    public Position getConsumablePosition() {
        return goalPosition;
    }

	@Override
	public void setConsumed() {
		preyConsumed = true;	
	}

	@Override
	public boolean getConsumed() {
		return preyConsumed;
	}

}
