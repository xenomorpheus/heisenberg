package au.net.hal9000.heisenberg.item;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;

/**
 */
public class ShieldTest {

    /**
     * Method shieldAdd.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void shieldAdd() throws InvalidTypeException, CantWearException {
        Shield shield = new Shield();
        Human human = new Human();
        human.add(shield);
    }

}
