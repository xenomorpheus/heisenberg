package au.net.hal9000.heisenberg.item;

public class Candle extends Item {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private boolean lit = false;

    public Candle() {
        this("Candle");
    }

    public Candle(final String pString) {
        super(pString);
        this.setType(1);
    }

    public final void setType(final int type) {
        if (type == 1) {
            this.setVolumeBase(0.5f); // TODO what about liters vs. gallons
            this.setWeightBase(0.5f); // TODO what about kilos vs. pounds ?
        }
    }

    public boolean isLit() {
        return lit;
    }

    public void light() {
        // TODO check for remaining fuel etc.
        this.lit = true;
    }

    public void extinquish() {
        this.lit = false;
    }

    public String getDescription() {
        String full_desc = new String("");

        // Try to get the base description first.
        final String desc = super.getDescription();
        final String name = super.getName();

        // Otherwise try to get the name.
        if ((desc != null) && (desc.length() > 0)) {
            full_desc = desc;
            full_desc += ". ";
        } else if ((name != null) && (name.length() > 0)) {
            full_desc = name;
            full_desc += ". ";
        }

        if (lit) {
            full_desc += "Is lit";
        } else {
            full_desc += "Not lit";
        }

        return full_desc;
    }

}
