package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemClassConfigurationTest {

	@Test
	public void testItemClasses() {
		ItemClassConfiguration itemClassConf = new ItemClassConfiguration();
		itemClassConf.setId("theId");
		itemClassConf.setIconOpenId(23);
		itemClassConf.setIconClosedId(2323);
		itemClassConf.setIconLeafId(123);
		assertEquals("id", "theId", itemClassConf.getId());
		assertEquals("getIconOpenId", 23, itemClassConf.getIconOpenId());
		assertEquals("getIconClosedId", 2323, itemClassConf.getIconClosedId());
		assertEquals("getIconLeafId", 123, itemClassConf.getIconLeafId());
	}

}