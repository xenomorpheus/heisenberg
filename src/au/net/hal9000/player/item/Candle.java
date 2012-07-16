package au.net.hal9000.player.item;

public class Candle extends ItemImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean lit = false;

	public Candle() {
		super("Candle");
		this.setType(1);
	}

	public Candle(String pString) {
		super(pString);
		this.setType(1);
	}

	public void setType(int type) {
		if (type == 1) {
			this.setVolumeBase(0.5f); // TODO what about liters vs. gallons
			this.setWeightBase(0.5f); // TODO what about kilos vs. pounds ?
		}
	}

	public String getDescription() {
		String full_desc;

		// Try to get the base description first.
		String desc = super.getDescription();
		String name = super.getName();

		// Otherwise try to get the name.
		if (desc.length() > 0) {
			full_desc = desc;
			full_desc = full_desc.concat(". ");
		} else if (name.length() > 0) {
			full_desc = name;
			full_desc = full_desc.concat(". ");
		} else {
			full_desc = new String("");
		}
		if (lit) {
			full_desc = full_desc.concat("Is lit");
		} else {
			full_desc = full_desc.concat("Not lit");
		}

		return full_desc;
	}
}
