package au.net.hal9000.dnd.item;
import static org.junit.Assert.*;

import org.junit.Test;

public class CurrencyTest {

	
	@Test
	public void testCoinCollectionConstructorNoParams() {
		Currency cc = new Currency();
		assertEquals("pp", 0, cc.getPp());
		assertEquals("gp", 0, cc.getGp());
		assertEquals("sp", 0, cc.getSp());
		assertEquals("cp", 0, cc.getCp());
	}

	@Test
	public void testCoinCollectionConstructorWithCoinParams() {
		Currency cc = new Currency(1,2,4,8);
		assertEquals("pp", 1, cc.getPp());
		assertEquals("gp", 2, cc.getGp());
		assertEquals("sp", 4, cc.getSp());
		assertEquals("cp", 8, cc.getCp());
	}
	
	@Test
	public void testCoinCollectionConstructorWithCoinCollection() {
		Currency cc = new Currency(1,2,4,8);
		Currency cc2 = new Currency(cc);
		assertEquals("pp", 1, cc2.getPp());
		assertEquals("gp", 2, cc2.getGp());
		assertEquals("sp", 4, cc2.getSp());
		assertEquals("cp", 8, cc2.getCp());
	}

	
	@Test
	public void testPp() {
		Currency cc = new Currency();
		cc.setPp(10);
		assertEquals("10pp", 10.0F, cc.getPp(), 0.00001F);
	}

	
	@Test
	public void testGp() {
		Currency cc = new Currency();
		cc.setGp(10);
		assertEquals("10gp", 10.0F, cc.getGp(), 0.00001F);
	}

	@Test
	public void testSp() {
		Currency cc = new Currency();
		cc.setSp(10);
		assertEquals("10sp", 10.0F, cc.getSp(), 0.00001F);
	}

	@Test
	public void testCp() {
		Currency cc = new Currency();
		cc.setCp(10);
		assertEquals("10cp", 10.0F, cc.getCp(), 0.00001F);
	}


	@Test
	public void testGetGpEquiv() {
		Currency cc = new Currency(1,2,4,8);
		assertEquals("1pp,2gp,4sp,8cp", 12.48F, cc.getGpEquiv(), 0.00001F);
	}

	// Merging two piles of coins
	@Test
	public void testTransferCoinCollection(){
		Currency cc = new Currency(1,2,4,8);
		Currency cc2 = new Currency(2,4,6,3);
		cc.transfer(cc2);
		assertEquals("transfer cc", 37.11F, cc.getGpEquiv(), 0.00001F);
		assertEquals("transfer cc2", 0F, cc2.getGpEquiv(), 0.00001F);
	}
	
	// Adding two piles of coins
	@Test
	public void testAddCoinCollection(){
		Currency cc = new Currency(1,2,4,8);
		Currency cc2 = new Currency(2,4,6,3);
		cc.add(cc2);
		assertEquals("add cc", 37.11F, cc.getGpEquiv(), 0.00001F);
		assertEquals("add cc2", 24.63F, cc2.getGpEquiv(), 0.00001F);
	}
	
	// TODO equals
	@Test
	public void testEquals() {
		Currency cc = new Currency(1,2,4,8);
		Currency cc2 = new Currency(1,2,4,8);
		assertTrue("equals", cc.equals(cc2));
	}

}