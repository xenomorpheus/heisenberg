package au.net.hal9000.heisenberg.item;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 */
public class ShieldTest {

    /**
     * Method shieldAdd.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void shieldAdd() throws InvalidTypeException, CantWearException, TooHeavyException, TooLargeException {
        Shield shield = new Shield();
        Human human = new Human();
        human.add(shield);
    }

}
