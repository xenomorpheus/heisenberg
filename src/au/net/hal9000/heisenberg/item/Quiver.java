package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Quiver extends ItemContainer  {


    private static final long serialVersionUID = 1L;
    // TODO quiver volume default
    // 20 times volume of an arrow ?
    private static final float VOLUME_MAX_DFT = 20; 

    public Quiver() {
        this("Quiver");
    }

    public Quiver(final String name) {
        super(name);
        this.setVolumeMax(VOLUME_MAX_DFT);
        ItemProperty.setClothing(this, true);
    }

	public void add(final Arrow arrow) {
        super.add(arrow);
    }

    public void add(final Object object) {
        throw new IllegalArgumentException("Only Arrows accepted");
    }
}
