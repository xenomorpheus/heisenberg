package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.LightSource;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Candle extends Item implements LightSource {

    /**
     * A simple tallow candle
     */
    private static final long serialVersionUID = 1L;
    private boolean lit = false;

    // TODO time the burn by use of fuel or rounds.

    public Candle() {
        this("Candle");
    }

    public Candle(final String pName, final String pDescription) {
        super(pName, pDescription);
        this.setType(1);
    }

    public Candle(final String pString) {
        this(pString, "a simple tallow candle");
    }

    // Getters and Setters
    /**
     * Set the lit/unlit status of this torch
     * 
     * @param lit
     *            the lit/unlit status of this torch
     */
    public void setLit(boolean lit) {
        this.lit = lit;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isLit() {
        return lit;
    }

    // Methods
    public void setType(final int type) {
        if (type == 1) {
            this.setVolumeBase(0.5f); // TODO what about litres vs. gallons
            this.setWeightBase(0.5f); // TODO what about kilos vs. pounds ?
        }
    }

    /**
     * Light the torch. <br>
     * TODO require the torch to have remaining fuel.
     */
    /** {@inheritDoc} */
    @Override
    public boolean lightWith(Object ignighter) {
        if (ignighter instanceof FlintAndTinder) {
            this.setLit(true);
        }
        // Order important, before Candle.
        // OrbOfLight (or sub-class) won't light it.
        else if (ignighter instanceof OrbOfLight) {
            ; // NOP
        }
        // Candle (or sub-class) and MUST BE LIT.
        else if (ignighter instanceof Candle) {
            Candle candle = (Candle) ignighter;
            if (candle.isLit()) {
                this.setLit(true);
            }
        }
        // Note may already be lit.
        return isLit();
    }

    /** {@inheritDoc} */
    @Override
    public void extinguish() {
        this.setLit(false);

    }

    public String getDetailedDescription() {
        String full_desc;

        // Try to get the base description first.
        final String desc = super.getDescription();
        final String name = super.getName();

        // Otherwise try to get the name.
        if ((desc != null) && (desc.length() > 0)) {
            full_desc = desc;
        } else if ((name != null) && (name.length() > 0)) {
            full_desc = name;
        } else {
            full_desc = this.getClass().getSimpleName();
        }

        if (lit) {
            full_desc += ". Is lit";
        } else {
            full_desc += ". Not lit";
        }

        return full_desc;
    }

}
