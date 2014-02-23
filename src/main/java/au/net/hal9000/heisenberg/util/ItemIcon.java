package au.net.hal9000.heisenberg.util;

import java.util.Vector;

import javax.swing.ImageIcon;

import au.net.hal9000.heisenberg.item.Item;

/**
 * Use the config to update the default Icon for each type of Item.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public final class ItemIcon {

    /** Constructor. */
    private ItemIcon() {
    }

    /**
     * Set the UI icons on the Item classes.
     * 
     * @param config the config to use.
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
        Vector<ItemClassConfiguration> itemClassConfigurations = config
                .getItemClasses();
        for (ItemClassConfiguration itemClassConfiguration : itemClassConfigurations) {
            int iconOpenId = itemClassConfiguration.getIconOpenId();
            if (0 != iconOpenId) {
                Item.setIconOpenDefaultForClass(itemClassConfiguration.getId(),
                        new ImageIcon(spritePack.getSprite(iconOpenId)));
            }
        }
    }

}
