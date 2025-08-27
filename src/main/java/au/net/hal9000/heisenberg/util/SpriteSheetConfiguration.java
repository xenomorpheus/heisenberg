package au.net.hal9000.heisenberg.util;

import lombok.Getter;
import lombok.Setter;

/** Configuration about sprite sheets - e.g. images that hold a grid of icons. */
public class SpriteSheetConfiguration {
  /** primary key e.g. "item" for all the sheet of default icons for Item objects. */
  @Getter @Setter
  private String id;

  /** the filename of the sprite sheet, relative the resource root. * */
  @Getter @Setter
  private String filename;

  /** the width of a single icon within the sprite sheet. * */
  @Getter @Setter
  private int width;

  /** the height of a single icon within the sprite sheet. * */
  @Getter @Setter
  private int height;

  /** the number of rows of icons in the sprite sheet. * */
  @Getter @Setter
  private int rows;

  /** the number of columns of icons in the sprite sheet. * */
  @Getter @Setter
  private int columns;

  /** Constructor for SpriteSheetConfiguration. */
  public SpriteSheetConfiguration() {
    super();
  }
}
