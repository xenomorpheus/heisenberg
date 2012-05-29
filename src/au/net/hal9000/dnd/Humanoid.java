package au.net.hal9000.dnd;

import java.util.Iterator;
import java.util.Vector;

public class Humanoid extends Animal {
	Item head = new Item("Head");
	Hand leftHand = new Hand("Left Hand");
	Hand rightHand = new Hand("Right Hand");
	Vector<Clothing> clothes = new Vector<Clothing>();
	Shield shield = null;

	public Humanoid() {
		super("Humanoid");
	}

	public Humanoid(String string) {
		super(string);
	}

	public void wear(Clothing clothing) throws ExceptionCantWear {
		if (!clothes.add(clothing)) {
			throw new ExceptionCantWear("cant wear");
		}
		clothing.setLocation(this);
	}

	@Override
	public float getWeight() {
		float total = weightBase;
		total += head.getWeight();
		if (leftHand != null) {
			total += leftHand.getWeight();
		}
		if (rightHand != null) {
			total += rightHand.getWeight();
		}
		Iterator<Clothing> itr = this.clothes.iterator();
		while (itr.hasNext()) {
			total += itr.next().getWeight();
		}
		return total;
	}
	
	public void dropItem(Item item) throws Exception{
		if (item == null) {
			// TODO
			throw new Exception("TODO: null item passed");
		}
        item.setLocation(this.location);
	}

	public void setShield (Shield pShield) throws Exception {
		if (pShield == null) {
			// TODO
			throw new Exception("TODO: null shield passed");
		}
		if (shield != null) {
			this.dropItem(shield);
		}
		pShield.setLocation(this);
		this.shield = pShield;
	}

	public Shield getShield() {
		return this.shield;
	}
}
