package au.net.hal9000.dnd.item;

import java.util.Iterator;
import java.util.Vector;
import au.net.hal9000.dnd.item.property.Clothing;
import au.net.hal9000.dnd.item.property.Mount;

public abstract class Humanoid extends Animal {

	HumanoidHead head = new HumanoidHead();
	Hand leftHand = new Hand("Left Hand");
	Hand rightHand = new Hand("Right Hand");
	ItemContainer clothing = new ItemContainer("Clothing"); // TODO setWeightMax()
	Shield shield = null;
	Item mount = null;  // Mounts are worn :-)

	public Humanoid(String pName) {
		super(pName);
		// TODO Auto-generated constructor stub
	}

	public void wear(Item pClothing) throws ExceptionCantWear, ExceptionInvalidType, ExceptionTooHeavy, ExceptionTooBig {
		if (! pClothing.implementsInterface(Clothing.class) ){
			throw new ExceptionInvalidType(pClothing.getName()+" doesn't implement Clothing");
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

	public void setMount(Item pMount) throws ExceptionInvalidType, ExceptionCantWear {
		if (! pMount.implementsInterface(Mount.class) ){
			throw new ExceptionInvalidType(pMount.getName()+" doesn't implement Mount");
        }
        if (mount != null){
			throw new ExceptionCantWear("already mounted");
        }
        pMount.setLocation(this);
        this.mount = pMount;
	}

}
