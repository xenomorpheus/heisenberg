package au.net.hal9000.heisenberg.util;

import java.util.Map;
import java.util.TreeMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;

public class ItemClassIcon {

  /** Logger. */
  private static final Logger LOGGER = Logger.getLogger(ItemClassIcon.class.getName());

  /**
   * For each class of Item, if it is a container and shown in the UI in tree view, then this is the
   * icon to show when open.
   */
  private static final Map<String, Icon> ICON_OPEN_DEFAULT_FOR_CLASS = new TreeMap<String, Icon>();

  /**
   * For each class of Item, if it is a container and shown in the UI in tree view, then this is the
   * icon to show when closed.
   */
  private static final Map<String, Icon> ICON_CLOSED_DEFAULT_FOR_CLASS =
      new TreeMap<String, Icon>();

  /**
   * For each class of Item, if it is NOT a container and shown in the UI in tree view, then this is
   * the icon to show.
   */
  private static final Map<String, Icon> ICON_LEAF_DEFAULT_FOR_CLASS = new TreeMap<String, Icon>();

  public ItemClassIcon(Configuration config) {
    super();
    setClassIconsFromConfig(config);
  }

  /**
   * For this Item type, get the default Icon to show when this item is open.
   *
   * @return The icon.
   */
  public Icon getClassIconOpenDefault(String simpleClassName) {
    return ICON_OPEN_DEFAULT_FOR_CLASS.get(simpleClassName);
  }

  /**
   * For this Item type, get the default Icon to show when this item is closed.
   *
   * @return The icon.
   */
  public Icon getClassIconClosedDefault(String simpleClassName) {
    return ICON_CLOSED_DEFAULT_FOR_CLASS.get(simpleClassName);
  }

  /**
   * For this Item type, get the default Icon to show when this item is a leaf node.
   *
   * @return The icon.
   */
  public Icon getClassIconLeafDefault(String simpleClassName) {
    return ICON_LEAF_DEFAULT_FOR_CLASS.get(simpleClassName);
  }

  /**
   * Set the UI icons on the Item classes.
   *
   * @param config the config to use.
   */
  private static void setClassIconsFromConfig(Configuration config) {
    // Find the sprite sheet for the Item types.
    SpriteSheetConfiguration spritePackDetail = config.getSpriteSheet("item");
    SpritePack spritePack = new SpritePack();
    // Load the sprite sheet.
    spritePack.init(
        spritePackDetail.getFilename(),
        spritePackDetail.getWidth(),
        spritePackDetail.getHeight(),
        spritePackDetail.getRows(),
        spritePackDetail.getColumns());

    // For each type of Item, set the default Icon.
    for (ItemClassConfiguration itemClassConfiguration : config.getItemClasses().values()) {
      int iconOpenId = itemClassConfiguration.getIconOpenId();
      if (0 != iconOpenId) {
        ICON_OPEN_DEFAULT_FOR_CLASS.put(
            itemClassConfiguration.getId(), new ImageIcon(spritePack.getSprite(iconOpenId)));
      } else {
        LOGGER.info(
            "Missing config for open icon for item class: " + itemClassConfiguration.getId());
      }
      int iconClosedId = itemClassConfiguration.getIconClosedId();
      if (0 != iconClosedId) {
        ICON_CLOSED_DEFAULT_FOR_CLASS.put(
            itemClassConfiguration.getId(), new ImageIcon(spritePack.getSprite(iconClosedId)));
      } else {
        LOGGER.info(
            "Missing config for closed icon for item class: " + itemClassConfiguration.getId());
      }
      int iconLeafId = itemClassConfiguration.getIconLeafId();
      if (0 != iconLeafId) {
        ICON_LEAF_DEFAULT_FOR_CLASS.put(
            itemClassConfiguration.getId(), new ImageIcon(spritePack.getSprite(iconLeafId)));
      } else {
        LOGGER.info(
            "Missing config for leaf icon for item class: " + itemClassConfiguration.getId());
      }
    }
  }
}
