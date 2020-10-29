package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 */
public class AbilityScoresTableTest {

	@Before
	public void initialize() {
		DemoEnvironment.setup();
	}

	/**
	 * test basic operations.
	 * 
	 * @throws ConfigurationError
	 */
	@Test
	public void testBasicOperations() throws ConfigurationError {
		PcRace pc = DemoEnvironment.getPcRace();
		AbilityScoresTable basicPanel = new AbilityScoresTable();
		basicPanel.setPcRace(pc);
		assertNotNull("BasicPanel not null", basicPanel);
	}

	/**
	 * Method testGetRowCount.
	 */
	@Test
	public void testGetRowCount() {

		AbilityScoresTable abilityScoresTable = new AbilityScoresTable();
		assertEquals(0, abilityScoresTable.getRowCount());
	}

}
