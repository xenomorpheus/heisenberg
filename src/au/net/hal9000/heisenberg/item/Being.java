package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.exception.ExceptionInvalidType;
import au.net.hal9000.heisenberg.item.property.Clothing;
import au.net.hal9000.heisenberg.item.property.HumanoidFood;
import au.net.hal9000.heisenberg.item.property.Living;

/**
 * Being is the bases of conscious entities.
 * 
 * @author bruins
 * 
 */

public abstract class Being extends ItemContainer implements Living {
	private static final long serialVersionUID = 1L;

	String gender;
	String size;

	// Constructor
	public Being(String pName) {
		super(pName);
	}

	// Methods

	// Getters and Setters

	/**
	 * @return the gender
	 */
	public final String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public final void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the size
	 */
	public final String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public final void setSize(String size) {
		this.size = size;
	}


	/**
	 * Wear an item
	 * 
	 * @param item
	 * @throws ExceptionInvalidType
	 */
	public void add(IItem item) throws ExceptionInvalidType {
		if (!(item instanceof Clothing)) {
			throw new ExceptionInvalidType(this.getName() + " can't wear "
					+ item.getName());
		}
	}

	/**
	 * Eat an item
	 * 
	 * @param pFood
	 * @throws ExceptionInvalidType
	 */
	public void eat(IItem pFood) throws ExceptionInvalidType {
		if (!(pFood instanceof HumanoidFood)) {
			throw new ExceptionInvalidType(this.getName() + " can't eat "
					+ pFood.getName());
		}
		pFood.beNot();
	}

	// Find items that match the criteria
	/** {@inheritDoc} */
	@Override
	public void accept(ItemVisitor visitor) {
		// TODO visit equipment then super
		visitor.visit(this);
	}

	public Item search(Item item) {
		// TODO Auto-generated method stub
		return null;
	}
	
    /**
     * Shallow copy properties from one object to another.
     * @param item
     */
	public void setAllFrom(Being being) {
        setAllFrom((ItemContainer) being);
    	setGender(being.getGender());
    	setSize(being.getSize());
	}

}
