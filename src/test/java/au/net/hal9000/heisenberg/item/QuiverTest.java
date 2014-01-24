package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

    @SuppressWarnings("deprecation")
    @Test(expected = RuntimeException.class)
    public void quiverAddNonArrow() {
        Quiver quiver = new Quiver();
        quiver.add(new Cookie());
    }

}
