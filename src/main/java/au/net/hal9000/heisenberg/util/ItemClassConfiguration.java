package au.net.hal9000.heisenberg.util;

/** Holds configuration information about each concrete Item subclass. */
public class ItemClassConfiguration {
  /** primary key - The simple class name e.g. Arrow. */
  private String id;

  /**
   * The index in the sprite sheet that holds the default icon to display in the UI, when closed in
   * tree view.
   */
  private int iconClosedId;

  /**
   * The index in the sprite sheet that holds the default icon to display in the UI, when open in
   * tree view.
   */
  private int iconOpenId;

  /**
   * The index in the sprite sheet that holds the default icon to display in the UI, when a leaf in
   * tree view.
   */
  private int iconLeafId;

  /**
   * The suffix of the java class name.<br>
   * e.g. for Elf it is "entity.Elf".
   */
  private String javaClass;

  ItemClassConfiguration() {
    super();
  }

  // Getters and Setters
  /**
   * Method getId.
   *
   * @return String
   */
  public String getId() {
    return id;
  }

  /**
   * Method setId.
   *
   * @param id String
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Method getIconClosedId.
   *
   * @return int
   */
  public int getIconClosedId() {
    return iconClosedId;
  }

  /**
   * Method setIconClosedId.
   *
   * @param iconClosedId int
   */
  public void setIconClosedId(int iconClosedId) {
    this.iconClosedId = iconClosedId;
  }

  /**
   * Method getIconOpenId.
   *
   * @return int
   */
  public int getIconOpenId() {
    return iconOpenId;
  }

  /**
   * Method setIconOpenId.
   *
   * @param iconOpenId int
   */
  public void setIconOpenId(int iconOpenId) {
    this.iconOpenId = iconOpenId;
  }

  /**
   * Method getIconLeafId.
   *
   * @return int
   */
  public int getIconLeafId() {
    return iconLeafId;
  }

  /**
   * Method setIconLeafId.
   *
   * @param iconLeafId int
   */
  public void setIconLeafId(int iconLeafId) {
    this.iconLeafId = iconLeafId;
  }

  public String getJavaClass() {
    return javaClass;
  }

  public void setJavaClass(String javaClass) {
    this.javaClass = javaClass;
  }
}
