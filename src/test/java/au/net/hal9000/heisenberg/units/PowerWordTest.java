package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.units.PowerWord;

public class PowerWordTest {

    @Test
    public void testPowerWordString() {
        try {
        	PowerWord powerWord = new PowerWord("theId");
            assertEquals("id","theId",powerWord.getId());
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}
