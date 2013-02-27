package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.exception.ExceptionCantWear;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Scabbard extends Box  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Scabbard() {
		this("Scabbard");
	}

	public Scabbard(String pString) {
		super(pString);
        ItemProperty.setClothing(this, true);
		// TODO set max volumen&weight to that of a sword.
	}

	/** {@inheritDoc} */
	@Override
	public void add(IItem item) throws ExceptionCantWear {
	// We need to accept all IItems, not just swords,
	// otherwise our super will accept them for us 
	// with is bad.
		if (!(item instanceof Sword)){
			throw new ExceptionCantWear("non sword");
		}
		if (getChildCount() > 0) {
			throw new ExceptionCantWear("scabbard full");
		}
		super.add(item);
	}

}
