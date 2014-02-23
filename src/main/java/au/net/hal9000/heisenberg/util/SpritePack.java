package au.net.hal9000.heisenberg.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Read sprites from a packed sprites file.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class SpritePack {
    /** so we can find the resource files. **/
    private static ClassLoader ClassLoader = SpritePack.class.getClassLoader();

    /** a collection of sprites unpacked from the image. **/
    private BufferedImage[] sprites;

    /** Constructor. **/
    public SpritePack() {
        super();
    }

    /**
     * Load sprites from spritepack file.
     * 
     * @param spritePackFilename
     *            filename of sprite pack
     * @param width
     *            of one sprite
     * @param height
     *            of one sprite
     * @param rows
     *            in sprite pack
     * @param cols
     *            in sprite pack
     */
    public void init(String spritePackFilename, int width, int height,
            int rows, int cols) {
        BufferedImage bigImg = null;
        try {
            bigImg = ImageIO.read(ClassLoader.getResource(spritePackFilename));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        sprites = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sprites[(i * cols) + j] = bigImg.getSubimage(j * width, i
                        * height, width, height);
            }
        }
    }

    /**
     * get one buffered image from the sprite pack.
     * 
     * @param number
     *            the position in the sprite pack, starting at zero for top
     *            left.
    
     * @return the buffered image at that position */
    public BufferedImage getSprite(int number) {
        return sprites[number];
    }

}
