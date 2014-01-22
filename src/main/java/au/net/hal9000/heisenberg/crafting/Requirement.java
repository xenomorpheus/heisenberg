package au.net.hal9000.heisenberg.crafting;

/**
 * 
 * This is an abstract class.<br>
 * Requirement objects hold details about a requirement for a Recipe objects.<br>
 * A Recipe may have any number of Requirement objects.
 * 
 * e.g.
 * <ul>
 * <li>Item - Wood with a minimum of 3 weight units.
 * <li>Item - FlintAndTinder, not consumed.
 * <ul>
 * 
 * @author bruins
 * 
 */
public abstract class Requirement {

    /**
     * Each requirement should have a unique ID, per recipe.
     */
    private String id;

    /**
     * Will the item be consumed as part of the cooking process.<br>
     * default is true.
     */
    private boolean isConsumed = true;

    public Requirement(String id) {
        super();
        this.id = id;
    }

    public Requirement(String id, boolean isConsumed) {
        this(id);
        this.isConsumed = isConsumed;
    }

    // setters and getters
    /**
     * Get the id.
     * @return the id.
     */
    public String getId() {
        return id;
    }

    // isConsumed
    public boolean isConsumed() {
        return isConsumed;
    }

    /**
     * @return Return a description of the object.
     */
    String getDescription() {
        String string = "Id: " + id;
        if (isConsumed) {
            string += ", consumed";
        } else {
            string += ", not consumed";
        }
        return string;
    };

}
