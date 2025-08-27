package au.net.hal9000.heisenberg.util;
import lombok.Getter;
import lombok.Setter;

/** Holds configuration information about each concrete Item subclass. */
public class ItemClassConfiguration {
  /** primary key - The simple class name e.g. Arrow. */
  @Getter @Setter
  private String id;

  /**
   * The index in the sprite sheet that holds the default icon to display in the UI, when closed in
   * tree view.
   */
  @Getter @Setter
  private int iconClosedId;

  /**
   * The index in the sprite sheet that holds the default icon to display in the UI, when open in
   * tree view.
   */
  @Getter @Setter
  private int iconOpenId;

  /**
   * The index in the sprite sheet that holds the default icon to display in the UI, when a leaf in
   * tree view.
   */
  @Getter @Setter
  private int iconLeafId;

  /**
   * The suffix of the java class name.<br>
   * e.g. for Elf it is "entity.Elf".
   */
  @Getter @Setter
  private String javaClass;

  ItemClassConfiguration() {
    super();
  }

}
