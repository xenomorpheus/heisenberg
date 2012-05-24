import static org.junit.Assert.*;

import org.junit.Test;

public class CoinCollectionTest {

	
	@Test
	public void testCoinCollectionConstructorNoParams() {
		CoinCollection cc = new CoinCollection();
		assertEquals("pp", 0, cc.getPp());
		assertEquals("gp", 0, cc.getGp());
		assertEquals("sp", 0, cc.getSp());
		assertEquals("cp", 0, cc.getCp());
	}

	@Test
	public void testCoinCollectionConstructorWithCoinParams() {
		CoinCollection cc = new CoinCollection(1,2,4,8);
		assertEquals("pp", 1, cc.getPp());
		assertEquals("gp", 2, cc.getGp());
		assertEquals("sp", 4, cc.getSp());
		assertEquals("cp", 8, cc.getCp());
	}
	
	@Test
	public void testCoinCollectionConstructorWithCoinCollection() {
		CoinCollection cc = new CoinCollection(1,2,4,8);
		CoinCollection cc2 = new CoinCollection(cc);
		assertEquals("pp", 1, cc2.getPp());
		assertEquals("gp", 2, cc2.getGp());
		assertEquals("sp", 4, cc2.getSp());
		assertEquals("cp", 8, cc2.getCp());
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

	// Adding two piles of coins
	@Test
	public void testAddCoinCollection(){
		CoinCollection cc = new CoinCollection(1,2,4,8);
		CoinCollection cc2 = new CoinCollection(2,4,6,3);
		cc.add(cc2);
		assertEquals("add", 37.11F, cc.getGpEquiv(), 0.00001F);
		assertEquals("add", 0F, cc2.getGpEquiv(), 0.00001F);
	}
	
	// TODO equals
	@Test
	public void testEquals() {
		CoinCollection cc = new CoinCollection(1,2,4,8);
		CoinCollection cc2 = new CoinCollection(1,2,4,8);
		assertTrue("equals", cc.equals(cc2));
	}

}
