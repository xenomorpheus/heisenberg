package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.Sharp;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Sword extends Item implements Sharp {

    private static final long serialVersionUID = 1L;

    public Sword() {
        this("Sword");
    }

    public Sword(final String pString) {
        super(pString);
    }

}
