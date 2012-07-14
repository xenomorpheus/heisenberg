package au.net.hal9000.dnd.item;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import au.net.hal9000.dnd.units.CurrencyTest;

@RunWith(Suite.class)
@SuiteClasses({ BagOfHoldingTest.class, BagTest.class, CrossbowTest.class,
		CurrencyTest.class, EntityTest.class, HandTest.class,
		HumanoidTest.class, ItemContainerTest.class, ItemImplTest.class,
		MagicRingTest.class, RingTest.class })
public class AllTests {

}
