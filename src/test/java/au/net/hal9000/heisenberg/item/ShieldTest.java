package au.net.hal9000.heisenberg.item;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Shield;

public class ShieldTest {

    @Test
    public void shieldAdd() {
        Shield shield = new Shield();
        Human human = new Human();
        human.add(shield);
    }

}
