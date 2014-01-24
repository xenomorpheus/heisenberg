package au.net.hal9000.heisenberg.item;

import org.junit.Test;

public class ShieldTest {

    @Test
    public void shieldAdd() {
        Shield shield = new Shield();
        Human human = new Human();
        human.add(shield);
    }

}
