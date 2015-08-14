package au.net.hal9000.heisenberg.units;

/**
 * Base class of {@link SkillDetail}.
 * 
 * <P>
 * Developer Notes:Lets try making objects immutable and see how it goes.
 * </P>
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */

class KeywordDetail implements Comparable<KeywordDetail> {
    /**
     * the id.
     */
    private String id;
    /**
     * The description of the id.
     */
    private String description;

    /**
     * Constructor for KeywordDetail.
     * @param id String
     * @param description String
     */
    KeywordDetail(String id, String description) {
        super();
        this.id = id;
        this.description = description;
    }

    /**
     * Method getId.
     * @return String
     */
    public final String getId() {
        return id;
    }

    /**
     * Method getDescription.
     * @return String
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Method compareTo.
     * @param other KeywordDetail
     * @return int
     */
    public int compareTo(KeywordDetail other) {
        return id.compareTo(other.id);
    }

    /**
     * Method toString.
     * @return String
     */
    public String toString() {
        return "Id: " + this.getId() + ": " + this.getDescription();
    }

}
