package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Quiver;

public class QuiverTest {

    @Test
    public void quiverAddArrow() {
        Quiver quiver = new Quiver();
        Arrow arrow = new Arrow();
        quiver.add(arrow);
        quiver.add(new Arrow());
        quiver.add(new Arrow());
        quiver.add(new Arrow());
        quiver.add(new Arrow());
        quiver.add(new Arrow());

        assertEquals("Arrow location ", quiver, arrow.getContainer());
    }

}
