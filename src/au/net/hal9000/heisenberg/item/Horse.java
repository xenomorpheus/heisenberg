package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class Horse extends Entity implements HumanoidMount {
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
