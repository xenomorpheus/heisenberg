package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Backpack extends Bag  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Backpack() {
		this("Backpack");
	}

	public Backpack(final String pString) {
		super(pString);
		ItemProperty.setClothing(this, true);
	}

}
