package au.net.hal9000.dnd.item;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.dnd.item.exception.ExceptionCantWear;
import au.net.hal9000.dnd.item.exception.ExceptionInvalidType;
import au.net.hal9000.dnd.item.exception.ExceptionTooBig;
import au.net.hal9000.dnd.item.exception.ExceptionTooHeavy;
import au.net.hal9000.dnd.item.property.*;

public abstract class Humanoid extends Animal {

	HumanoidHead head = new HumanoidHead();
	Hand leftHand = new Hand("Left Hand");
	Hand rightHand = new Hand("Right Hand");
	ItemContainer clothing = new ItemContainer("Clothing"); // TODO
															// setWeightMax()
	Shield shield = null;
	Item mount = null; // Mounts are worn :-)

	public Humanoid(String pName) {
		super(pName);
		// TODO Auto-generated constructor stub
	}

	public void eat(Item pFood) throws ExceptionInvalidType {
		if (pFood.isHumanoidFood()) {
			throw new ExceptionInvalidType(this.getName() + " can't eat "
					+ pFood.getName());
		}
		pFood.beNot();
	}

	public void wear(Item pClothing) throws ExceptionCantWear,
			ExceptionInvalidType, ExceptionTooHeavy, ExceptionTooBig {
		if (!pClothing.isClothing()) {
			throw new ExceptionInvalidType(this.getName() + " can't wear "
					+ pClothing.getName());
		}
		clothing.add(pClothing);
		pClothing.setLocation(this);
	}

	public float getWeight() {
		float total = this.getWeightBase();
		total += head.getWeight();
		if (leftHand != null) {
			total += leftHand.getWeight();
		}
		if (rightHand != null) {
			total += rightHand.getWeight();
		}
		total += clothing.getWeight();
		if (shield != null) {
			total += shield.getWeight();
		}
		if (mount != null) {
			total += mount.getWeight();
		}
		return total;
	}

	public void setShield(Shield pShield) {
		if (shield != null) {
			shield.setLocation(this.getLocation());
			// in case pShield.setLocation throws exception.
			this.shield = null;
		}
		if (pShield != null) {
			pShield.setLocation(this);
			this.shield = pShield;
		}
	}

	public Shield getShield() {
		return this.shield;
	}

	public void setMount(Item pMount) throws ExceptionInvalidType,
			ExceptionCantWear {
		if (! pMount.isHumanoidMount()) {
			throw new ExceptionInvalidType(pMount.getName()
					+ " doesn't implement Mount");
		}
		if (mount != null) {
			throw new ExceptionCantWear("already mounted");
		}
		pMount.setLocation(this);
		this.mount = pMount;
	}

	public boolean equals(Humanoid pHumanoid) {
		// TODO
		throw new RuntimeException("Can't do humanoid equals yet"
				+ this.getName());
	}

}
