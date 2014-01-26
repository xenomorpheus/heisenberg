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
     * The index in the sprite sheet that holds the default icon to display in
     * the UI, when closed in tree view.
     */
    private int iconClosedId;
    /**
     * The index in the sprite sheet that holds the default icon to display in
     * the UI, when open in tree view.
     */
    private int iconOpenId;

    /**
     * The index in the sprite sheet that holds the default icon to display in
     * the UI, when a leaf in tree view.
     */
    private int iconLeafId;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIconClosedId() {
        return iconClosedId;
    }

    public void setIconClosedId(int iconClosedId) {
        this.iconClosedId = iconClosedId;
    }

    public int getIconOpenId() {
        return iconOpenId;
    }

    public void setIconOpenId(int iconOpenId) {
        this.iconOpenId = iconOpenId;
    }

    public int getIconLeafId() {
        return iconLeafId;
    }

    public void setIconLeafId(int iconLeafId) {
        this.iconLeafId = iconLeafId;
    }

}
