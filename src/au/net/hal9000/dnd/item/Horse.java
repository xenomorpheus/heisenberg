package au.net.hal9000.dnd.item;

public class Horse extends Entity {
	public Horse() {
		super("Horse");
	}

	public Horse(String string) {
		super(string);
	}

	// Feature
	public boolean isHumanoidMount() {
		return true;
	}

}
