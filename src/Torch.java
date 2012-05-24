public class Torch extends Item {

	boolean lit = false;

	public Torch() {
		super("Torch");
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

	public String getDescription1() {
		String desc = new String(description);

		if (desc.length() > 0){
			desc = desc.concat(" ");
		}
		if (lit) {
			desc = desc.concat("is lit");
		} else {
			desc = desc.concat("is unlit");
		}
		return desc;
	}
}
