package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FringeElementImplTest {

    @Test
    public void testCompareTo() {
        FringeElementImpl fe1 = new FringeElementImpl(null, null, 1);
        FringeElementImpl fe2 = new FringeElementImpl(null, null, 2);
        assertEquals("less than", -1, fe1.compareTo(fe2));
        assertEquals("greater than", 1, fe2.compareTo(fe1));
        assertEquals("equals", 0, fe1.compareTo(fe1));

    }

}
