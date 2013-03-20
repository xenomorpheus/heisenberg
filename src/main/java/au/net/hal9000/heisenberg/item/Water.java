package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.util.ItemSplitByWeight;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.SplitByWeight;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Water extends Item implements SplitByWeight {
    /**
     * Some plain water.
     */
    private static final long serialVersionUID = 1L;

    public Water() {
        this("Water", "water");
    }

    public Water(String pName) {
        this(pName, "water");
    }

    public Water(String pName, String pDescription) {
        super(pName, pDescription);
        ItemProperty.setHumanoidFood(this, true);
    }

    // Methods

    // Static

    /** {@inheritDoc} */
    @Override
    public Water splitByWeight(float newItemWeight) {
        return (Water) ItemSplitByWeight.splitByWeight(this, newItemWeight);
    }

}
