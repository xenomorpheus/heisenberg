package au.net.hal9000.heisenberg.item.action;

import au.net.hal9000.heisenberg.ai.ActionBase;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ModelStateDrink;
import au.net.hal9000.heisenberg.item.entity.Entity;

/**
 * Action to eat something
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class ActionAnimalDrink extends ActionBase {

    /** Entity to eat */
    private Entity consumer;
    /** Item to be drunk */
    private Item liquid;

    /**
     * Constructor.
     * 
     * @param liquid
     *            That to be drunk.
     * @param cost
     *            The cost of performing this action.
     */

    public ActionAnimalDrink(Entity consumer, Item liquid, double cost) {
        super(cost);
        this.consumer = consumer;
        this.liquid = liquid;
    }

    // Getters and Setters

    public Item getFood() {
        return liquid;
    }

    public void setFood(Item liquid) {
        this.liquid = liquid;

    }

   public Item getConsumer() {
        return consumer;
    }

    // Misc
    @Override
    public void apply(ModelState modelState) {
        ModelStateDrink modelStateDrink = (ModelStateDrink) modelState;
        Animal animal = modelStateDrink.getAnimal();
        Item liquid = modelStateDrink.getLiquid();
        animal.eat(liquid);
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
        sb.append("ActionAnimalDrink=[entity=").append(consumer).append(",liquid=")
                .append(liquid).append(']');
        return sb.toString();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((consumer == null) ? 0 : consumer.hashCode());
		result = prime * result + ((liquid == null) ? 0 : liquid.hashCode());
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
		ActionAnimalDrink other = (ActionAnimalDrink) obj;
		if (consumer == null) {
			if (other.consumer != null)
				return false;
		} else if (!consumer.equals(other.consumer))
			return false;
		if (liquid == null) {
			if (other.liquid != null)
				return false;
		} else if (!liquid.equals(other.liquid))
			return false;
		return true;
	}


}
