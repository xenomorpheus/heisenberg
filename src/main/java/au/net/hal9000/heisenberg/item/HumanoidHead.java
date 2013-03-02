package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class HumanoidHead extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HumanoidHead(String pName) {
		super(pName);
        ItemProperty.setLiving(this, true);
	}
	public HumanoidHead() {
		this("Head");
	}

}
