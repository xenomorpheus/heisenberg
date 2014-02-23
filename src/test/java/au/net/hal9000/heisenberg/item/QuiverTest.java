package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 */
public class QuiverTest {

    /**
     * Method quiverAddArrow.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void quiverAddArrow() throws InvalidTypeException, CantWearException, TooHeavyException, TooLargeException {
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
