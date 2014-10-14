package au.net.hal9000.heisenberg.units;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class Keyword implements Comparable<Keyword> {
    /** Main Id. */
    private String id;

    /**
     * The word that is invoked as part of a spell.
     * 
     * @param id
     *            the keyword id
     */
    public Keyword(String id) {
        super();
        this.id = id;
    }

    /**
     * Method getId.
    
     * @return String */
    public final String getId() {
        return id;
    }

    // TODO Will objects of different sub-classes with same String still be
    // different?
    /**
     * Method compareTo.
     * @param other Keyword
    
     * @return int */
    public int compareTo(Keyword other) {
        return id.compareTo(other.getId());
    }

    /**
     * Method toString.
    
     * @return String */
    public String toString() {
        return id;
    }

}
