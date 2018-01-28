package au.net.hal9000.heisenberg.fifthed;

import java.util.HashSet;
import java.util.Set;

/**
 * https://rpg.stackexchange.com/questions/16726/what-can-i-do-in-one-round
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
	private boolean isFullRoundActionAvailable = true;
	private boolean isStandardActionAvailable = true;
	private boolean isSwiftOrImmediateActionAvailable = true;
	private boolean isMoveActionAvailable = true;
	private boolean isFiveFootStepActionAvailable = true;

	public TimerRound() {
		super();
	}

	private boolean actionAvailableHelper(ActionDuration action, boolean takeActionDuration) {
		boolean retVal = false;
		if (action == ActionDuration.FREE) {
			retVal =  true;
		}
		if (action == ActionDuration.FULLROUND) {
			if (this.isFullRoundActionAvailable) {
				if (takeActionDuration) {
					this.isFullRoundActionAvailable = false;
					this.isStandardActionAvailable = false;
					this.isMoveActionAvailable = false;
				}
				retVal =  true;
			} else {
				retVal =  false;
			}
		}
		if (action == ActionDuration.STANDARD) {
			if (this.isStandardActionAvailable) {
				if (takeActionDuration) {
					this.isStandardActionAvailable = false;
					this.isFullRoundActionAvailable = false;
				}
				retVal =  true;
			} else {
				retVal =  false;
			}
		}
		if (action == ActionDuration.MOVE) {
			if (this.isMoveActionAvailable) {
				if (takeActionDuration) {
					this.isMoveActionAvailable = false;
					this.isFullRoundActionAvailable = false;
					this.isFiveFootStepActionAvailable = false;
				}
				retVal =  true;

			} else {
				// Can we take StandardAction as MoveAction?
				// Double move action.
				// But not if 5ft step taken
				if (this.isStandardActionAvailable && this.isFiveFootStepActionAvailable) {
					if (takeActionDuration) {
						this.isStandardActionAvailable = false;
						this.isFullRoundActionAvailable = false;
					}
					retVal =  true;
				} else {
					retVal =  false;
				}
			}
		}
		if ((action == ActionDuration.SWIFT) || (action == ActionDuration.IMMEDIATE)) {
			if (this.isSwiftOrImmediateActionAvailable) {
				if (takeActionDuration) {
					this.isSwiftOrImmediateActionAvailable = false;
				}
				retVal =  true;
			} else {
				retVal =  false;
			}
		}
		if (action == ActionDuration.FIVEFOOTSTEP) {
			if (this.isFiveFootStepActionAvailable) {
				if (takeActionDuration) {
					this.isMoveActionAvailable = false;
					this.isFiveFootStepActionAvailable = false;
				}
				retVal =  true;
			} else {
				retVal =  false;
			}
		}
		return retVal;
	}

	/**
	 * @return a list of valid ActionDuration objects.
	 */
	public Set<ActionDuration> getAvailableActionDurationSet() {
		Set<ActionDuration> actionDurations = new HashSet<ActionDuration>();
		for (ActionDuration actionDuration : ActionDuration.values()) {
			if (actionAvailableHelper(actionDuration, false)) {
				actionDurations.add(actionDuration);
			}
		}
		return actionDurations;
	}

	/**
	 * @param actionDuration
	 * @return true iff actionDuration is permitted.
	 */
	public boolean isActionDurationAvailable(ActionDuration actionDuration) {
		return actionAvailableHelper(actionDuration, false);
	}

	/**
	 * Take the actionDuration out of the timer. Will be used by an Action that
	 * takes this duration.
	 * 
	 * @param actionDuration
	 *            to be removed from the timer.
	 */
	public void takeActionDuration(ActionDuration actionDuration) {
		if (!actionAvailableHelper(actionDuration, true)) {
			throw new RuntimeException("Action Not Permitted" + actionDuration.getClass().getSimpleName());
		}
	}

}
