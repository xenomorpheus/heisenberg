package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.LightSource;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Torch extends Item implements LightSource {

    /**
     * A stick with some kind of combustable material on the end e.g. oiled rag.
     */
    private static final long serialVersionUID = 1L;
    private boolean lit = false;

    // TODO time the burn by use of fuel or rounds.

    public Torch() {
        this("Torch", "A short wooden rod tipped with cloth soaked in oil");
    }

    public Torch(String pName, String pDescription) {
        super(pName, pDescription);
    }

    public Torch(String pName) {
        this(pName, "A short wooden rod tipped with cloth soaked in oil");
    }

    /**
     * Set the lit/unlit status of this torch
     * 
     * @param lit
     *            the lit/unlit status of this torch
     */
    public void setLit(boolean lit) {
        this.lit = lit;
    }

    /**
     * Get the lit/unlit status of this torch
     * 
     * @return the lit/unlit status of this torch
     */
    public boolean getLit() {
        return lit;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isLit() {
        return lit;
    }

    /**
     * Light the torch. <br>
     * TODO require some flame source or flint and tinder.<br>
     * TODO require the torch to have remaining fuel.
     */
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

    // TODO rename / remove
    /** {@inheritDoc} */
    //@Override
    public String getDescription2() {
        String full_desc;

        // Try to get the base description first.
        String desc = super.getDescription();
        String name = super.getName();

        // Otherwise try to get the name.
        if (desc.length() > 0) {
            full_desc = desc;
            full_desc += ". ";
        } else if (name.length() > 0) {
            full_desc = name;
            full_desc += ". ";
        } else {
            full_desc = new String("");
        }
        if (lit) {
            full_desc += "Is lit";
        } else {
            full_desc += "Not lit";
        }

        return full_desc;
    }
}
