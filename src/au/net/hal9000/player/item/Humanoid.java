package au.net.hal9000.player.item;

import au.net.hal9000.player.units.*;

public abstract class Humanoid extends Entity {

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
	public Weight getWeight() {
		Weight total = super.getWeight();
		total.add(head.getWeight());
		if (leftHand != null) {
			total.add(leftHand.getWeight());
		}
		if (rightHand != null) {
			total.add(rightHand.getWeight());
		}
		return total;
	}

	/** {@inheritDoc} */
	@Override
	public Volume getVolume() {
		Volume total = super.getVolume();
		total.add(head.getVolume());
		if (leftHand != null) {
			total.add(leftHand.getVolume());
		}
		if (rightHand != null) {
			total.add(rightHand.getVolume());
		}
		return total;
	}

	public boolean equals(Humanoid other) {
		if (!head.equals(other.getHead()))
			return false;
		if (leftHand != null) {
			if (!leftHand.equals(other.getLeftHand()))
				return false;
		}
		if (rightHand != null) {
			if (!rightHand.equals(other.getRightHand()))
				return false;
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
}
