package au.net.hal9000.dnd.item;

public class Torch extends Item {

	boolean lit = false;

	public Torch() {
		super("Torch", "A short wooden rod tipped with cloth soaked in oil");
	}

	public Torch(String pName, String pDescription) {
		super(pName, pDescription);
	}

	public Torch(String pName) {
		super(pName);
	}

	public boolean isLit() {
		return lit;
	}

	public void setLit(boolean lit) {
		this.lit = lit;
	}

	public void light() {
		this.setLit(true);

	}

	public void extinghish() {
		this.setLit(false);

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
