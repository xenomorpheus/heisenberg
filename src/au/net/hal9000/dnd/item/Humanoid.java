package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.units.*;

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
	public HumanoidHead getHead() {
		return head;
	}

	public Hand getLeftHand() {
		return leftHand;
	}

	public Hand getRightHand() {
		return rightHand;
	}

	// Misc
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

	
	public boolean equals(Humanoid pHumanoid) {
		// TODO
		throw new RuntimeException("Can't do humanoid equals yet"
				+ this.getName());
	}

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
