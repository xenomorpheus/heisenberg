package au.net.hal9000.heisenberg.item.exception;

import org.junit.Test;

public class ItemNotPresentExceptionTest {

    @Test(expected = ItemNotPresentException.class)
    public void test() throws ItemNotPresentException {
        throw new ItemNotPresentException("Can't find foo");
    }

}
