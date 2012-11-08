package au.net.hal9000.heisenberg.units;

/**
 * Base class of {@link SkillDetail}.
 * 
 * <P>
 * Developer Notes:Lets try making objects immutable and see how it goes.
 * </P>
 * 
 * @author bruins
 */


public class KeywordDetail implements Comparable<KeywordDetail> {
	/**
	 * the id
	 */
	private String id;
	/**
	 * The description of the id.
	 */
	private String description;

	public KeywordDetail(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public final String getId() {
		return id;
	}

	public final String getDescription() {
		return description;
	}

	public int compareTo(KeywordDetail other) {
		return id.compareTo(other.id);
	}

	public String toString() {
		return "Id: "+this.getId()+ ": " + this.getDescription();
	}

}
