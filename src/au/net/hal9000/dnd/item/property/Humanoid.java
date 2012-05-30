package au.net.hal9000.dnd.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.dnd.item.ExceptionCantWear;
import au.net.hal9000.dnd.item.Hand;
import au.net.hal9000.dnd.item.Item;
import au.net.hal9000.dnd.item.Shield;

public interface Humanoid extends Animal {
	Item head = new Item("Head");
	Hand leftHand = new Hand("Left Hand");
	Hand rightHand = new Hand("Right Hand");
	Vector<Clothing> clothes = new Vector<Clothing>();
	Shield shield = null;


	public void wear(Clothing clothing) throws ExceptionCantWear {
		if (!clothes.add(clothing)) {
			throw new ExceptionCantWear("cant wear");
		}
		clothing.setLocation(this);
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
		if (shield != null) {
			total += shield.getWeight();
		}
		// clothing
		Iterator<Clothing> itr = this.clothes.iterator();
		while (itr.hasNext()) {
			total += itr.next().getWeight();
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
}
