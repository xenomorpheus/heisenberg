package au.net.hal9000.dnd;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BagOfHoldingTest.class, CoinCollectionTest.class,
		HandTest.class, ItemContainerTest.class, ItemTest.class,
		MagicRingTest.class, RingTest.class })
public class AllTests {

}
