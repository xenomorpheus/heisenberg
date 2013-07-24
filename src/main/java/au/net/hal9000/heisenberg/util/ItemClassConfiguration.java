package au.net.hal9000.heisenberg.util;
/**
 * Holds configuration information about each concrete Item subclass.
 */
public class ItemClassConfiguration {
	/**
	 * primary key - The simple class name e.g. Arrow.
	 */
    private String id;
    /**
     * The index in the sprite sheet that holds the default icon to display in the UI.
     */
    private int iconOpenId;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIconOpenId() {
        return iconOpenId;
    }

    public void setIconOpenId(int iconOpenId) {
        this.iconOpenId = iconOpenId;
    }

}
