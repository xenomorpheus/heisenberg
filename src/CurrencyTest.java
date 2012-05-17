import static org.junit.Assert.*;

import org.junit.Test;

public class CurrencyTest {

	public void testGp() {
		Currency currency = new Currency();
		currency.setGp(10);
		assertEquals("10gp", 10.0F, currency.getGp(), 0.00001F);
	}

	
	public void testGetGpEquiv() {
		Currency currency = new Currency();
		currency.setGp(10);
		assertEquals("10gp", 10.0F, currency.getGpEquiv(), 0.00001F);
	}

	@Test
	public void test() {
		testGp();
		testGetGpEquiv();
		//fail("Not yet implemented2");
	}

}
