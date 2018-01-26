package au.net.hal9000.heisenberg.fithed;

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

	public Set<Action> getAvailableActionSet() {
		Set<Action> actions = new HashSet<Action>();
		if (action instanceof ActionFree) {
			return;
		}
		if (action instanceof ActionFullRound) {
			if (this.isFullRoundActionTaken()) {
				throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
			} else {
				this.isFullRoundActionTaken = true;
			}
		}
		if (action instanceof ActionStandard) {
			if (this.isStandardActionTaken()) {
				throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
			} else {
				this.isStandardActionTaken = true;
			}
		}
		if ((action instanceof ActionSwift) || (action instanceof ActionImmediate)) {
			if (this.isSwiftOrImmediateActionTaken()) {
				throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
			} else {
				this.isSwiftOrImmediateActionTaken = true;
			}
		}

		if (action instanceof ActionMove) {
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
		actions.add(new 
		return actions;

	}

	public void takeAction(Action action) {
		if (action instanceof ActionFree) {
			return;
		}
		if (action instanceof ActionFullRound) {
			if (this.isFullRoundActionTaken()) {
				throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
			} else {
				this.isFullRoundActionTaken = true;
			}
		}
		if (action instanceof ActionStandard) {
			if (this.isStandardActionTaken()) {
				throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
			} else {
				this.isStandardActionTaken = true;
			}
		}
		if ((action instanceof ActionSwift) || (action instanceof ActionImmediate)) {
			if (this.isSwiftOrImmediateActionTaken()) {
				throw new RuntimeException("Action Not Permitted" + action.getClass().getName());
			} else {
				this.isSwiftOrImmediateActionTaken = true;
			}
		}

		if (action instanceof ActionMove) {
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
