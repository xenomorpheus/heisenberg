package au.net.hal9000.heisenberg.units;

public class Keyword implements Comparable<Keyword> {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String id;

	/**
	 * The word that is invoked as part of a spell.
	 */
	public Keyword(String id) {
		super();
		this.id = id;
	}

	public final String getId() {
		return id;
	}

	// TODO Will objects of different sub-classes with same String still be different?
	public int compareTo(Keyword other) {
		return id.compareTo(other.getId());
	}

	public String toString() {
		return id;
	}

}
