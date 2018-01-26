package au.net.hal9000.heisenberg.fifthed.spell;

public abstract class Spell {
	private String name = null;
	private boolean rangeTouch = false;

	public String getName() {
		return name;
	}
	protected Spell setRangeTouch(boolean rangeTouch) {
		this.rangeTouch = rangeTouch;
		return this;
	}

}
