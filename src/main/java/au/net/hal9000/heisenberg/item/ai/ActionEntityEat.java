package au.net.hal9000.heisenberg.item.ai;

import au.net.hal9000.heisenberg.ai.action.ActionBase;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateHunterPrey;
import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;

/**
 * Action to eat something.<br>
 * Check super classes but this class is likely to be immutable.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class ActionEntityEat extends ActionBase {

	/** Entity to eat */
	private Entity consumer;
	/** Item to be eaten */
	private Item sustenance;

	/**
	 * Constructor.
	 * 
	 * @param sustenance
	 *            That to be eaten or drunk.
	 * @param cost
	 *            The cost of performing this action.
	 */

	public ActionEntityEat(Entity consumer, Item sustenance, double cost) {
		super(cost);
		this.consumer = consumer;
		this.sustenance = sustenance;
	}

	// Getters and Setters

	// Misc
	@Override
	public void apply(ModelState modelState) {
		// TODO alter the model, don't perform the action
		if (modelState instanceof ModelStateAnimalEat) {
			ModelStateAnimalEat modelStateEat = (ModelStateAnimalEat) modelState;
			Animal animal = modelStateEat.getAnimal();
			Item sustenance = modelStateEat.getFood();
			// animal.eat(sustenance);
		} else if (modelState instanceof ModelStateHunterPrey) {
			ModelStateHunterPrey modelStateHunterPrey = (ModelStateHunterPrey) modelState;
			Entity hunter = modelStateHunterPrey.getHunter();
			Item prey = modelStateHunterPrey.getPrey();
			// hunter.consume(prey);
		} else {
			throw new RuntimeException("Bad type of model state " + modelState);
		}
		throw new RuntimeException("Not implemented");
	}

	// Misc
	/**
	 * Method toString.
	 * 
	 * @see au.net.hal9000.heisenberg.ai.api.Action#toString()
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(12);
		sb.append("ActionAnimalEat=[entity=").append(consumer).append(",food=").append(sustenance).append(']');
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((consumer == null) ? 0 : consumer.hashCode());
		result = prime * result + ((sustenance == null) ? 0 : sustenance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionEntityEat other = (ActionEntityEat) obj;
		if (consumer == null) {
			if (other.consumer != null)
				return false;
		} else if (!consumer.equals(other.consumer))
			return false;
		if (sustenance == null) {
			if (other.sustenance != null)
				return false;
		} else if (!sustenance.equals(other.sustenance))
			return false;
		return true;
	}

}
