package au.net.hal9000.heisenberg.util;

/**
 * Configuration about sprite sheets - e.g. images that hold a grid of icons.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SpriteSheetConfiguration {
  /** primary key e.g. "item" for all the sheet of default icons for Item objects. */
  private String id;

  /** the filename of the sprite sheet, relative the resource root. * */
  private String filename;

  /** the width of a single icon within the sprite sheet. * */
  private int width;

  /** the height of a single icon within the sprite sheet. * */
  private int height;

  /** the number of rows of icons in the sprite sheet. * */
  private int rows;

  /** the number of columns of icons in the sprite sheet. * */
  private int columns;

  /** Constructor for SpriteSheetConfiguration. */
  public SpriteSheetConfiguration() {
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
   * Method getFilename.
   *
   * @return String
   */
  public String getFilename() {
    return filename;
  }

  /**
   * Method setFilename.
   *
   * @param filename String
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * Method getWidth.
   *
   * @return int
   */
  public int getWidth() {
    return width;
  }

  /**
   * Method setWidth.
   *
   * @param width int
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Method getHeight.
   *
   * @return int
   */
  public int getHeight() {
    return height;
  }

  /**
   * Method setHeight.
   *
   * @param height int
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Method getRows.
   *
   * @return int
   */
  public int getRows() {
    return rows;
  }

  /**
   * Method setRows.
   *
   * @param rows int
   */
  public void setRows(int rows) {
    this.rows = rows;
  }

  /**
   * Method getColumns.
   *
   * @return int
   */
  public int getColumns() {
    return columns;
  }

  /**
   * Method setColumns.
   *
   * @param columns int
   */
  public void setColumns(int columns) {
    this.columns = columns;
  }
}
