package au.net.hal9000.heisenberg.item;

public class Torch extends Item {

	/**
	 * A stick with some kind of combustible material on the end e.g. oiled rag.
	 */
	private static final long serialVersionUID = 1L;
	private boolean lit = false;
	// TODO time the burn by use of fuel or rounds.

	public Torch() {
		super("Torch", "A short wooden rod tipped with cloth soaked in oil");
	}

	public Torch(String pName, String pDescription) {
		super(pName, pDescription);
	}

	public Torch(String pName) {
		super(pName);
	}

	/**
	 * Is this torch lit?
	 * @return true if lit
	 */
	public boolean isLit() {
		return lit;
	}

	/**
	 * Set the lit/unlit status of this torch
	 * @param lit the lit/unlit status of this torch
	 */
	public void setLit(boolean lit) {
		this.lit = lit;
	}

	/**
	 * Light the torch.
	 * TODO require some flame source or flint and tinder
	 * TODO require the torch to have remaining fuel.
	 */
	public void light() {
		this.setLit(true);

	}
	
    /**
     * The torch stops burning.
     */
	public void extinghish() {
		this.setLit(false);

	}

    /** {@inheritDoc} */
    @Override
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
