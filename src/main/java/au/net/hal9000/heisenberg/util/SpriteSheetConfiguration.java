package au.net.hal9000.heisenberg.util;

/**
 * Configuration about sprite sheets - e.g. images that hold a grid of icons.
 */
public class SpriteSheetConfiguration {
    /**
     * primary key e.g. "item" for all the sheet of default icons for Item
     * objects.
     * 
     */
    private String id;
    /** the filename of the sprite sheet, relative the the resourse root **/
    private String filename;
    /** the width of a single icon within the sprite sheet **/
    private int width;
    /** the height of a single icon within the sprite sheet **/
    private int height;
    /** the number of rows of icons in the sprite sheet **/
    private int rows;
    /** the number of columns of icons in the sprite sheet **/
    private int columns;

    public SpriteSheetConfiguration() {
        super();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

}
