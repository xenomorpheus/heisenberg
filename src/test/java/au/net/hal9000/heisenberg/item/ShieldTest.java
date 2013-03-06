package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Shield;

public class ShieldTest {

    @Test
    public void shieldAdd() {
        Shield shield = new Shield();
        Human human = new Human();
        try {
            human.add(shield);
        } catch (Exception e) {
            fail("Humanoid could not wear shield");
        }
    }

}
