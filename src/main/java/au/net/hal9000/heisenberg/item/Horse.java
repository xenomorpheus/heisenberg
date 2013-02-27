package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Horse extends au.net.hal9000.heisenberg.item.Entity implements HumanoidMount {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public Horse() {
        this("Horse");
    }

    public Horse(String string) {
        super(string);
        ItemProperty.setLiving(this, true);
        this.setWeightMax(100); // TODO config
        this.setVolumeMax(100); // TODO config
    }

    protected String getRace() {
        return null;
    }
}
