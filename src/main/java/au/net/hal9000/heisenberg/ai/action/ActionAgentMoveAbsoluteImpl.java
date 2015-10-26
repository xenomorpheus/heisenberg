package au.net.hal9000.heisenberg.ai.action;

import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.ai.api.ActionAgentMoveAbsolute;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Very simple actions - Agent movement only.<br>
 * Position is the desired position.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class ActionAgentMoveAbsoluteImpl extends ActionBase implements
		ActionAgentMoveAbsolute {

	/** Agent's desired position. Absolute Position, not relative. */
	private Position agentPositionTarget;

	/**
	 * Constructor.
	 * 
	 * @param agentPostionTarget
	 *            desired position.
	 * @param cost
	 */

	public ActionAgentMoveAbsoluteImpl(final Position agentPostionTarget,
			double cost) {
		super(cost);
		this.agentPositionTarget = agentPostionTarget;
	}

	// Getters and Setters
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position getAgentTarget() {
		return agentPositionTarget;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAgentTarget(Position position) {
		agentPositionTarget = position;

	}

	// Misc
	@Override
	public void apply(ModelState modelState) {
		if (!(modelState instanceof ModelStateAgentGoal)) {
			throw new RuntimeException("Expecting ModelStateAgentGoal");
		}
		ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) modelState;
		modelStateAgentGoal.setAgentPosition(agentPositionTarget);
		// TODO consider reduce energy of Entity
	}

	/**
	 * Method toString.
	 * 
	 * @see au.net.hal9000.heisenberg.ai.api.Action#toString()
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(3);
		sb.append("ActionMove=[target=");
		sb.append(agentPositionTarget);
		sb.append(']');
		return sb.toString();
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(getCost());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((agentPositionTarget == null) ? 0 : agentPositionTarget
						.hashCode());
		return result;
	}

	/**
	 * Method equals.
	 * 
	 * @param obj
	 *            Object
	 * 
	 * @return boolean
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionAgentMoveAbsoluteImpl other = (ActionAgentMoveAbsoluteImpl) obj;
		if (Double.doubleToLongBits(getCost()) != Double.doubleToLongBits(other
				.getCost()))
			return false;
		if (agentPositionTarget == null) {
			if (other.getAgentTarget() != null)
				return false;
		} else if (!agentPositionTarget.equals(other.getAgentTarget()))
			return false;
		return true;
	}

}
