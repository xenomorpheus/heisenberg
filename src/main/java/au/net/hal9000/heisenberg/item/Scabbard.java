package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.exception.ExceptionCantWear;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Scabbard extends Box {

    private static final long serialVersionUID = 1L;

    public Scabbard() {
        this("Scabbard");
    }

    public Scabbard(String pString) {
        super(pString);
        ItemProperty.setClothing(this, true);
        // TODO set max volume & weight to that of a sword.
    }

    /** {@inheritDoc} */
    @Override
    public void add(Item item) {
        // We need to accept all Items, not just swords,
        // otherwise our super will accept them for us
        // which is bad.
        // Currently there are no plans to allow low volume items
        // such a coins to be added instead of a sword.
        if (!(item instanceof Sword)) {
            throw new ExceptionCantWear("non sword");
        }
        if (getChildCount() > 0) {
            throw new ExceptionCantWear("scabbard full");
        }
        super.add(item);
    }

}
