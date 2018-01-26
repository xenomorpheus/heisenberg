package au.net.hal9000.heisenberg.fifthed;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Action Duration Round:
 * 
 * 1 Full-round action OR
 * 
 * 1 Standard action plus 1 Move action (in any order) OR
 * 
 * 2 Move actions (effectively trading your Standard action for a Move action)
 * 
 * Plus a combination of :
 * 
 * 1 Swift or Immediate Action AND
 * 
 * Any number of Free Actions
 * 
 * A few special cases The 5 ft. step Provided you don't take any other action
 * that actually involves using a move speed (walking/running/flying...) during
 * the same round, you can take a 5' step as a free action that does not provoke
 * attacks of opportunity once per round.
 * 
 * @author bruins
 *
 */
public class TimerRound {
	private boolean isFullRoundActionTaken = false;
	private boolean isStandardActionTaken = false;
	private boolean isSwiftOrImmediateActionTaken = false;
	private boolean isMoveActionTaken = false;
	private boolean isFiveFootStepActionTaken = false;

	public TimerRound() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * A set of avaialble actions. Only one may be taken at a time.
	 * @return
	 */
	public Set<ActionDuration> getAvailableActionSet() {
		Set<ActionDuration> actions = new HashSet<ActionDuration>();

		if (!this.isFullRoundActionTaken()) {
			actions.add(ActionDuration.FULLROUND);
		}
		if (!this.isStandardActionTaken()) {
			actions.add(ActionDuration.STANDARD);
		}
		if (!this.isSwiftOrImmediateActionTaken()) {
			actions.add(ActionDuration.SWIFT);
			actions.add(ActionDuration.IMMEDIATE);
		}
		if (!this.isMoveActionTaken()) {
			actions.add(ActionDuration.FREE);
		}
		actions.add(ActionDuration.FREE);
		return actions;

	}

	public void takeAction(ActionDuration action) {
		if (action == ActionDuration.FREE) {
			return;
		}
		if (action == ActionDuration.FULLROUND) {
			if (this.isFullRoundActionTaken()) {
				throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
			} else {
				this.isFullRoundActionTaken = true;
			}
		}
		if (action == ActionDuration.STANDARD) {
			if (this.isStandardActionTaken()) {
				throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
			} else {
				this.isStandardActionTaken = true;
			}
		}
		if ((action == ActionDuration.SWIFT) || (action == ActionDuration.IMMEDIATE)) {
			if (this.isSwiftOrImmediateActionTaken()) {
				throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
			} else {
				this.isSwiftOrImmediateActionTaken = true;
			}
		}

		if (action == ActionDuration.MOVE) {
			if (this.isMoveActionTaken()) {

				// Can we take StandardAction as MoveAction?
				if (this.isStandardActionTaken()) {
					throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
				} else {
					this.isStandardActionTaken = true;
				}
			} else {
				this.isMoveActionTaken = true;
			}
		}

	}

	/**
	 * @return the isFullRoundActionTaken
	 */
	public boolean isFullRoundActionTaken() {
		return isFullRoundActionTaken;
	}

	/**
	 * @return the isStandardActionTaken
	 */
	public boolean isStandardActionTaken() {
		return isStandardActionTaken;
	}

	/**
	 * @return the isMovementActionTaken
	 */
	public boolean isMoveActionTaken() {
		return isMoveActionTaken;
	}

	/**
	 * @return the isFiveFootStepActionTaken
	 */
	public boolean isSwiftOrImmediateActionTaken() {
		return isSwiftOrImmediateActionTaken;
	}

	/**
	 * @return the isFiveFootStepActionTaken
	 */
	public boolean isFiveFootStepActionTaken() {
		return isFiveFootStepActionTaken;
	}

}
