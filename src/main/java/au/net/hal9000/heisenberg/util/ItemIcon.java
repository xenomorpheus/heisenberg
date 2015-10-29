package au.net.hal9000.heisenberg.util;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import au.net.hal9000.heisenberg.item.api.Item;

public class ItemIcon implements Serializable {
    /** serialisation version. */
    private static final long serialVersionUID = 1L;

    /** For each class of Item, the icon to show when open. */
    private static final Map<String, Icon> ICON_OPEN_DEFAULT_FOR_CLASS = new TreeMap<String, Icon>();

    /**
     * If this item is a container and shown in the UI in tree view, then this
     * is the icon to show when open.
     **/
    private Icon iconClosed = null;
    /**
     * If this item is a container and shown in the UI in tree view, then this
     * is the icon to show when closed.
     **/
    private Icon iconOpen = null;
    /**
     * If this item is NOT a container and shown in the UI in tree view, then
     * this is the icon to show.
     **/
    private Icon iconLeaf = null;

    private ItemIcon() {
        super();
    }

    public ItemIcon(Item item) {
        this();
        Icon icon = getIconOpenDefault(item);
        setIconOpen(icon);
        setIconClosed(icon);
        setIconLeaf(icon);
    }

    /**
     * Return the Icon to draw when displaying this Item in the tree view.
     * 
     * 
     * @return the Icon.
     */
    public Icon getIconClosed() {
        return iconClosed;
    }

    /**
     * Set the Icon to draw when displaying this Item in the tree view.
     * 
     * @param iconClosed
     *            the Icon to show when the container is closed.
     */
    public void setIconClosed(Icon iconClosed) {
        this.iconClosed = iconClosed;
    }

    /**
     * Return the Icon to draw when displaying this Item in the tree view.
     * 
     * 
     * @return the Icon.
     */
    public Icon getIconOpen() {
        return iconOpen;
    }

    /**
     * Set the Icon to draw when displaying this Item in the tree view.
     * 
     * @param iconOpen
     *            the Icon to show when the container is open.
     */
    public void setIconOpen(Icon iconOpen) {
        this.iconOpen = iconOpen;
    }

    /**
     * Return the Icon to draw when displaying this Item in the tree view.
     * 
     * 
     * @return the Icon.
     */
    public Icon getIconLeaf() {
        return iconLeaf;
    }

    /**
     * Set the Icon to draw when displaying this Item in the tree view.
     * 
     * @param iconLeaf
     *            the Icon to show when the non-container item is displayed.
     */
    public void setIconLeaf(Icon iconLeaf) {
        this.iconLeaf = iconLeaf;
    }

    /**
     * For this Item type, get the default Icon to show when this item is open.
     * 
     * 
     * @return The Icon.
     */
    private Icon getIconOpenDefault(Item item) {
        return ICON_OPEN_DEFAULT_FOR_CLASS.get(item.getSimpleClassName());
    }

    /**
     * Set the Icon to show in the UI when collection is opened.
     * 
     * @param simpleClassName
     *            Simple Item name e.g. Cookie.
     * @param imageIcon
     *            Icon to show.
     */
    public static void setIconOpenDefaultForClass(String simpleClassName,
            ImageIcon imageIcon) {
        ICON_OPEN_DEFAULT_FOR_CLASS.put(simpleClassName, imageIcon);
    }

    /**
     * Clear the Icon to show in the UI when collection is opened.
     */
    public static void clearIconOpenDefaultForClass() {
        ICON_OPEN_DEFAULT_FOR_CLASS.clear();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((iconClosed == null) ? 0 : iconClosed.hashCode());
        result = prime * result
                + ((iconLeaf == null) ? 0 : iconLeaf.hashCode());
        result = prime * result
                + ((iconOpen == null) ? 0 : iconOpen.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemIcon other = (ItemIcon) obj;
        if (iconClosed == null) {
            if (other.iconClosed != null)
                return false;
        } else if (!iconClosed.equals(other.iconClosed))
            return false;
        if (iconLeaf == null) {
            if (other.iconLeaf != null)
                return false;
        } else if (!iconLeaf.equals(other.iconLeaf))
            return false;
        if (iconOpen == null) {
            if (other.iconOpen != null)
                return false;
        } else if (!iconOpen.equals(other.iconOpen))
            return false;
        return true;
    }

    /**
     * Set the UI icons on the Item classes.
     * 
     * @param config
     *            the config to use.
     */
    public static void setIcon(Configuration config) {
        // Find the sprite sheet for the Item types.
        SpriteSheetConfiguration spritePackDetail = config
                .getSpriteSheet("item");
        SpritePack spritePack = new SpritePack();
        // Load the sprite sheet.
        spritePack.init(spritePackDetail.getFilename(),
                spritePackDetail.getWidth(), spritePackDetail.getHeight(),
                spritePackDetail.getRows(), spritePackDetail.getColumns());

        // For each type of Item, set the default Icon.
        for (ItemClassConfiguration itemClassConfiguration : config
                .getItemClasses().values()) {
            int iconOpenId = itemClassConfiguration.getIconOpenId();
            if (0 != iconOpenId) {
                ICON_OPEN_DEFAULT_FOR_CLASS.put(itemClassConfiguration.getId(),
                        new ImageIcon(spritePack.getSprite(iconOpenId)));
            }
        }
    }

    public ItemIcon dupicate() {
        ItemIcon itemIcon = new ItemIcon();
        itemIcon.setIconClosed(getIconClosed());
        itemIcon.setIconLeaf(getIconLeaf());
        itemIcon.setIconOpen(getIconOpen());
        return itemIcon;
    }
}
