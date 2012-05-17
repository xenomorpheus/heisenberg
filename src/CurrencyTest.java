import static org.junit.Assert.*;

import org.junit.Test;

public class CurrencyTest {

	@Test
	public void testPp() {
		Currency currency = new Currency();
		currency.setPp(10);
		assertEquals("10pp", 10.0F, currency.getPp(), 0.00001F);
	}

	
	@Test
	public void testGp() {
		Currency currency = new Currency();
		currency.setGp(10);
		assertEquals("10gp", 10.0F, currency.getGp(), 0.00001F);
	}

	@Test
	public void testSp() {
		Currency currency = new Currency();
		currency.setSp(10);
		assertEquals("10sp", 10.0F, currency.getSp(), 0.00001F);
	}

	@Test
	public void testCp() {
		Currency currency = new Currency();
		currency.setCp(10);
		assertEquals("10cp", 10.0F, currency.getCp(), 0.00001F);
	}


	@Test
	public void testGetGpEquiv() {
		Currency currency = new Currency();
		currency.setPp(1);
		currency.setGp(2);
		currency.setSp(4);
		currency.setCp(8);
		assertEquals("1pp,2gp,4sp,8cp", 12.48F, currency.getGpEquiv(), 0.00001F);
	}

}
