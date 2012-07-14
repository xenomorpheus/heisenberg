package au.net.hal9000.dnd.units;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CurrencyTest.class, UnitTest.class, VolumeTest.class,
		WeightTest.class })
public class AllTests {

}
