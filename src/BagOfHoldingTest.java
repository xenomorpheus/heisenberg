import static org.junit.Assert.*;

import org.junit.Test;

public class BagOfHoldingTest {

	@Test
	public void typeTest() {
		Bag ordinaryBag = new Bag();
		for (int type = 1; type <= 4; type++) {
			float weightBase = 0F;
			float weightMax = 0F;
			float volumeMax = 0F;
			CoinCollection cost = new CoinCollection(0, 0, 0, 0);

			if (type == 1) {
				weightBase = 15F;
				weightMax = 250F;
				volumeMax = 30F;
				cost = new CoinCollection(0, 2500, 0, 0);
			}
			if (type == 2) {
				weightBase = 25F;
				weightMax = 500F;
				volumeMax = 70F;
				cost = new CoinCollection(0, 5000, 0, 0);
			}
			if (type == 3) {
				weightBase = 35F;
				weightMax = 1000F;
				volumeMax = 150F;
				cost = new CoinCollection(0, 7400, 0, 0);
			}
			if (type == 4) {
				weightBase = 60F;
				weightMax = 1500F;
				volumeMax = 150F;
				cost = new CoinCollection(0, 10000, 0, 0);
			}
			BagOfHolding boh = new BagOfHolding(type);
			assertEquals("type", boh.getType(), type);
			assertEquals("type=" + type + ", weight", boh.getWeight(),
					weightBase, 0.001F);
			assertEquals("type=" + type + ", weightBase", boh.weightBase,
					weightBase, 0.001F);
			assertEquals("type=" + type + ", volume", boh.getVolume(), 2F,
					0.001F);
			assertEquals("type=" + type + ", volumeBase", boh.volumeBase, 2F,
					0.001F);
			assertTrue("type=" + type + ", cost", boh.getCost().equals(cost));
			// Should look like an ordinary bag :-)
			assertEquals("type=" + type + ", description",
					boh.getDescription(), ordinaryBag.getDescription());
			Item i = new Item();
			i.setVolumeBase(volumeMax);
			i.setWeightBase(weightMax);
			try {
				boh.add(i);
			} catch (ExceptionTooHeavy e) {
				fail("type=" + type + ", too heavy :"+e.getMessage());
			} catch (ExceptionTooBig e) {
				fail("type=" + type + ", too big :"+e.getMessage());
			}
		}

	}

}
