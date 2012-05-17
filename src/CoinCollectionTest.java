import static org.junit.Assert.*;

import org.junit.Test;

public class CoinCollectionTest {

	
	@Test
	public void testCoinCollectionNoParams() {
		CoinCollection cc = new CoinCollection();
		assertEquals("pp", 0, cc.getPp());
		assertEquals("gp", 0, cc.getGp());
		assertEquals("sp", 0, cc.getSp());
		assertEquals("cp", 0, cc.getCp());
	}

	@Test
	public void testCoinCollectionFullParams() {
		CoinCollection cc = new CoinCollection(1,2,4,8);
		assertEquals("pp", 1, cc.getPp());
		assertEquals("gp", 2, cc.getGp());
		assertEquals("sp", 4, cc.getSp());
		assertEquals("cp", 8, cc.getCp());
	}
	
	@Test
	public void testPp() {
		CoinCollection cc = new CoinCollection();
		cc.setPp(10);
		assertEquals("10pp", 10.0F, cc.getPp(), 0.00001F);
	}

	
	@Test
	public void testGp() {
		CoinCollection cc = new CoinCollection();
		cc.setGp(10);
		assertEquals("10gp", 10.0F, cc.getGp(), 0.00001F);
	}

	@Test
	public void testSp() {
		CoinCollection cc = new CoinCollection();
		cc.setSp(10);
		assertEquals("10sp", 10.0F, cc.getSp(), 0.00001F);
	}

	@Test
	public void testCp() {
		CoinCollection cc = new CoinCollection();
		cc.setCp(10);
		assertEquals("10cp", 10.0F, cc.getCp(), 0.00001F);
	}


	@Test
	public void testGetGpEquiv() {
		CoinCollection cc = new CoinCollection(1,2,4,8);
		assertEquals("1pp,2gp,4sp,8cp", 12.48F, cc.getGpEquiv(), 0.00001F);
	}

}
