package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;

/**
 */
public class QuiverTest {

    /**
     * Method quiverAddArrow.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void quiverAddArrow() throws InvalidTypeException, CantWearException {
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

    /**
     * Method quiverAddNonArrow.
     */
    @SuppressWarnings("deprecation")
    @Test(expected = RuntimeException.class)
    public void quiverAddNonArrow() {
        Quiver quiver = new Quiver();
        quiver.add(new Cookie());
    }

}
