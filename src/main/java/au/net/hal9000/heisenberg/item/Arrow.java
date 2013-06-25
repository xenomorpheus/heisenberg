package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.swing.Icon;
import javax.swing.ImageIcon;
// import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.Sharp;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @PrimaryKeyJoinColumn(name="tableid", referencedColumnName="tableid")
public class Arrow extends Item implements Sharp {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private static final float VOLUME_BASE = 1; // TODO arrow volume default

    private static ClassLoader cl = Arrow.class.getClassLoader();
    private static Icon openIcon = new ImageIcon(
            cl.getResource("images/icon/S_Bow01.png"));
    private static Icon closedIcon = openIcon;
    private static Icon leafIcon = openIcon;

    // Constructor
    public Arrow() {
        this("Arrow");
    }

    public Arrow(final String pString) {
        super(pString);
        this.setVolumeBase(VOLUME_BASE);
    }

    /** {@inheritDoc} */
    @Override
    public Icon getOpenIcon() {
        return openIcon;
    }

    /** {@inheritDoc} */
    @Override
    public Icon getClosedIcon() {
        return closedIcon;
    }

    /** {@inheritDoc} */
    @Override
    public Icon getLeafIcon() {
        return leafIcon;
    }
}
