package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.Memory;

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
        MemoryOfBarrier memory = new MemoryOfBarrier(createdDate, decayRate,
                barrier);
        Barrier barrier2 = memory.getBarrier();
        assertTrue(barrier.equals(barrier2));
    }

    @Test
    public void testToString() {

        Date createdDate = null;
        double decayRate = 0;
        Barrier barrier = new BarrierShape(null, "barrierObject");
        MemoryOfBarrier memoryOfBarrier = new MemoryOfBarrier(createdDate, decayRate,
                barrier);
        String expect = "MemoryOfBarrier=["+barrier.toString()+", created=null, decay=0.0]]";
        String got = memoryOfBarrier.toString();
        assertTrue(expect.equals(got));
    }

}
