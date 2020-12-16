package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.Memory;
import java.util.Date;
import org.junit.Test;

public class MemoryOfBarrierTest {

  @Test
  public void testMemoryOfBarrier() {
    Date createdDate = null;
    double decayRate = 0;
    Barrier barrier = null;
    Memory memory = new MemoryOfBarrier(createdDate, decayRate, barrier);
    assertNotNull(memory);
  }

  @Test
  public void testGetBarrier() {
    Date createdDate = null;
    double decayRate = 0;
    String blocker = "foo";
    Barrier barrier = new BarrierShape(null, blocker);
    MemoryOfBarrier memory = new MemoryOfBarrier(createdDate, decayRate, barrier);
    Barrier barrier2 = memory.getBarrier();
    assertTrue(barrier.equals(barrier2));
  }

  @Test
  public void testToString() {

    Date createdDate = null;
    double decayRate = 0;
    Barrier barrierShape = new BarrierShape(null, "barrierObject");
    MemoryOfBarrier memoryOfBarrier = new MemoryOfBarrier(createdDate, decayRate, barrierShape);
    String expect = ", " + barrierShape + "]";
    String got = memoryOfBarrier.toString();
    assertTrue(got.endsWith(expect));
  }
}
