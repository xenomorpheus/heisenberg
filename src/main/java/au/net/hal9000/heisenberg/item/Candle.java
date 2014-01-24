package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.LightSource;

/**
 * A simple tallow candle.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Candle extends Item implements LightSource {

    /** serialisation version. */
    private static final long serialVersionUID = 1L;

    /** default weight. */
    private static final float WEIGHT_DEFAULT = 0.02f;
    /** default volume. */
    private static final float VOLUME_DEFAULT = 0.02f;

    /** true if candle is lit. */
    private boolean lit = false;

    // TODO time the burn by use of fuel or rounds.

    /**
     * Constructor.
     */
    public Candle() {
        this("Candle");
    }

    /**
     * Constructor.
     * 
     * @param name
     *            name to call the item.
     * @param description
     *            description of item.
     */
    public Candle(final String name, final String description) {
        super(name, description);
        this.setType(1);
    }

    /**
     * Constructor.
     * 
     * @param name
     *            name to call the item.
     */
    public Candle(final String name) {
        this(name, "a simple tallow candle");
    }

    // Getters and Setters
    /**
     * Set the lit/unlit status of this torch.
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
    /**
     * Set the type of the Candle.
     * 
     * @param type
     *            candle type.
     */
    public void setType(final int type) {
        if (type == 1) {
            this.setVolumeBase(VOLUME_DEFAULT);
            this.setWeightBase(WEIGHT_DEFAULT);
        } else {
            throw new RuntimeException("Invalid Candle type=" + type);
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
        // OrbOfLight (or sub-class) won't lite it.
        // Candle (or sub-class) and MUST BE LIT.
        else if ((ignighter instanceof Candle)
                && !(ignighter instanceof OrbOfLight)) {
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

    /** {@inheritDoc} */
    @Override
    public String detailedDescription() {
        String fullDescription = super.detailedDescription() + "\n";
        fullDescription += "The " + this.getClass().getSimpleName();

        if (lit) {
            fullDescription += " is lit.";
        } else {
            fullDescription += " is extinguished.";
        }
        return fullDescription;
    }

}
