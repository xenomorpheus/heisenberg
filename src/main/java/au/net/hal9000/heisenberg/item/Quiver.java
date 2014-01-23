package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Quiver extends ItemContainer {

    /** serial version. */
    private static final long serialVersionUID = 1L;
    /** Quiver volume default. 20 times volume of an arrow. */
    private static final float VOLUME_MAX_DFT = 20;

    /**
     * Constructor.
     */
    public Quiver() {
        this("Quiver");
    }

    /**
     * Constructor.
     * 
     * @param name
     *            name to use.
     */
    public Quiver(final String name) {
        super(name);
        this.setVolumeMax(VOLUME_MAX_DFT);
        ItemProperty.setClothing(this, true);
    }

    /**
     * Add arrows to the quiver.
     * 
     * @param arrow arrow Item to add to Quiver.
     */
    public void add(final Arrow arrow) {
        super.add(arrow);
    }

    /**
     * This method is required to override the parent class method.
     * 
     * @deprecated
     * @param item item to attempt (and fail) to add to quiver.
     */
    @Override
    public void add(final Item item) {
        throw new IllegalArgumentException("Only Arrows accepted");
    }
}
