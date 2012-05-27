package au.net.hal9000.dnd;
import java.util.Iterator;
import java.util.Vector;

public class Humanoid extends Item {
	Item head = new Item("Head");
	Hand leftHand = new Hand("Left Hand");
	Hand rightHand = new Hand("Right Hand");
	Vector<Clothing> clothes = new Vector<Clothing>();

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
}
