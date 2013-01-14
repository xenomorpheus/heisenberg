package au.net.hal9000.heisenberg.item;

// MagicRing
// is a Ring except:
// It is Magical.

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.LightSource;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class OrbOfLight extends Item implements  LightSource {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private boolean lit = false;

    // Constructor
    public OrbOfLight() {
        this("Orb Of Light");
    }

    public OrbOfLight(String pName) {
        this(pName,"Orb of light");
    }
    public OrbOfLight(String pName, String pDescription) {
        super(pName, pDescription);
        ItemProperty.setMagical(this,true);
    }

    // getters and setters

    /**
     * @param lit
     *            the lit to set
     */
    public final void setLit(boolean lit) {
        this.lit = lit;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isLit() {
        return lit;
    }

    /** {@inheritDoc} */
    @Override
    public void light() {
        this.setLit(true);
    }

    /** {@inheritDoc} */
    @Override
    public void extinghish() {
        this.setLit(false);
    }

}
