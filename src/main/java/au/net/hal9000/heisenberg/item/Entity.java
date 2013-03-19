package au.net.hal9000.heisenberg.item;

/**
 * Entity is the bases of conscious entities. <br>
 * May or may not be living.
 * 
 * @author bruins
 * 
 */

public abstract class Entity extends ItemContainer {
    private static final long serialVersionUID = 1L;

    String gender;
    String size;

    // Constructor
    public Entity(String pName) {
        super(pName);
    }

    // Methods

    // Getters and Setters

    /**
     * @return the gender
     */
    public final String getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public final void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the size
     */
    public final String getSize() {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public final void setSize(String size) {
        this.size = size;
    }

    /**
     * Shallow copy properties from one object to another.
     * 
     * @param entity
     *            source
     */
    public void setAllFrom(Entity entity) {
        setAllFrom((ItemContainer) entity);
        setGender(entity.getGender());
        setSize(entity.getSize());
    }

}
