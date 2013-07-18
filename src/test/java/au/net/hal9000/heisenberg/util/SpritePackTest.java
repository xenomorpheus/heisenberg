package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

public class SpritePackTest {

    @Test
    public void testSpritePack() {
        SpritePack sp = new SpritePack();
        assertNotNull(sp);
    }

    @Test
    public void testInitStringIntIntIntInt() {
        SpritePack sp = new SpritePack();
        String spritePackFilename = "images/icon/IconSet-01.png";
        sp.init(spritePackFilename, 24, 24, 33, 16);
    }

    @Test
    public void testGetSpriteInt() {
        SpritePack sp = new SpritePack();
        String spritePackFilename = "images/icon/IconSet-01.png";
        sp.init(spritePackFilename, 24, 24, 33, 16);
        BufferedImage bi = sp.getSprite(0);
        assertNotNull(bi);
    }

}
