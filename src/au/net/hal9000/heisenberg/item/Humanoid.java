package au.net.hal9000.heisenberg.item;

// TODO Redesign.  I don't think all Humanoids should be PcRace.
public abstract class Humanoid extends PcRace {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HumanoidHead head = new HumanoidHead();
	private Hand leftHand = new Hand("Left Hand");
	private Hand rightHand = new Hand("Right Hand");

	// Constructors
	public Humanoid(String pName) {
		super(pName);
	}

	// Getters and Setters
	// Head
	public HumanoidHead getHead() {
		return head;
	}

	public void setHead(HumanoidHead head) {
		this.head = head;
	}

	// left Hand
	public Hand getLeftHand() {
		return leftHand;
	}

	public void setLeftHand(Hand hand) {
		this.leftHand = hand;
	}

	// right Hand
	public Hand getRightHand() {
		return rightHand;
	}

	public void setRightHand(Hand hand) {
		this.rightHand = hand;
	}

	// Misc
	/** {@inheritDoc} */
	@Override
	public float getWeight() {
		float total = super.getWeight();
		total += head.getWeight();
		if (leftHand != null) {
			total += leftHand.getWeight();
		}
		if (rightHand != null) {
			total += rightHand.getWeight();
		}
		return total;
	}

	/** {@inheritDoc} */
	@Override
	public float getVolume() {
		float total = super.getVolume();
		total += head.getVolume();
		if (leftHand != null) {
			total += leftHand.getVolume();
		}
		if (rightHand != null) {
			total += rightHand.getVolume();
		}
		return total;
	}

	public boolean equals(Humanoid other) {
		if (!head.equals(other.getHead())) {
			return false;
		}
		if (leftHand != null) {
			if (!leftHand.equals(other.getLeftHand())) {
				return false;
			}
		}
		if (rightHand != null) {
			if (!rightHand.equals(other.getRightHand())) {
				return false;
			}
		}
		return super.equals(other);
	}

	/** {@inheritDoc} */
	@Override
	public void beNot() {
		// Call beNot on the Items directly declared in this class.
		head.beNot();
		if (leftHand != null) {
			leftHand.beNot();
		}
		if (rightHand != null) {
			rightHand.beNot();
		}
		// Get super to do the rest.
		super.beNot();
	}

	// Traverse Tree
	/** {@inheritDoc} */
	@Override
	public boolean isLeaf() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public int getChildCount() {
		int count = 1; // head
		if (leftHand != null) {
			count++;
		}
		if (rightHand != null) {
			count++;
		}
		count += super.getChildCount();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public Item getChild(int index) {
		// index is zero offset.
		// head
		if (index == 0) {
			return head;
		}
		index--;

		// leftHand
		if (leftHand != null) {
			if (index == 0) {
				return leftHand;
			}
			index--;
		}

		// rightHand
		if (rightHand != null) {
			if (index == 0) {
				return rightHand;
			}
			index--;
		}

		// Child is on super.
		return super.getChild(index);
	}

	/** {@inheritDoc} */
	@Override
	public int getIndexOfChild(IItem child) {
		// TODO getIndexOfChild
		return -1;
	}

	// Find items that match the criteria
	/** {@inheritDoc} */
	@Override
	public void accept(ItemVisitor visitor) {
		// TODO visit head, optional hands then super
		visitor.visit(this);
	}
	@Override
	public
	String getRace() {
		return this.getClass().getSimpleName();
	}
}
