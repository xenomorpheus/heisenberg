package au.net.hal9000.heisenberg.item.action;

import au.net.hal9000.heisenberg.ai.ActionBase;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.ModelStateEat;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;

/**
 * Action to eat something
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class ActionAnimalEat extends ActionBase {

    /** Entity to eat */
    private Entity consumer;
    /** Item to be eaten */
    private Item food;

    /**
     * Constructor.
     * 
     * @param food
     *            That to be eaten.
     * @param cost
     *            The cost of performing this action.
     */

    public ActionAnimalEat(Entity consumer, Item food, double cost) {
        super(cost);
        this.consumer = consumer;
        this.food = food;
    }

    // Getters and Setters

    public Item getFood() {
        return food;
    }

    public void setFood(Item food) {
        this.food = food;

    }

   public Item getConsumer() {
        return consumer;
    }

    // Misc
    @Override
    public void apply(ModelState modelState) {
        ModelStateEat modelStateEat = (ModelStateEat) modelState;
        Animal animal = modelStateEat.getAnimal();
        Item food = modelStateEat.getFood();
        animal.eat(food);
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
        sb.append("ActionAnimalEat=[entity=").append(consumer).append(",food=")
                .append(food).append(']');
        return sb.toString();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((consumer == null) ? 0 : consumer.hashCode());
		result = prime * result + ((food == null) ? 0 : food.hashCode());
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
		ActionAnimalEat other = (ActionAnimalEat) obj;
		if (consumer == null) {
			if (other.consumer != null)
				return false;
		} else if (!consumer.equals(other.consumer))
			return false;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		return true;
	}


}
