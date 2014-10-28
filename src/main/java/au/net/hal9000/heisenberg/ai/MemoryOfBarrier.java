package au.net.hal9000.heisenberg.ai;

import java.util.Date;

import au.net.hal9000.heisenberg.ai.api.Barrier;

/**
 * Holds a memory of a Barrier.
 * 
 * @author bruins
 *
 */

public class MemoryOfBarrier extends Memory {
    /** the barrier this memory is about. */
    private Barrier barrier;

    /**
     * Constructor.
     * 
     * @param createdDate
     *            date memory created.
     * @param expiryDate
     *            date memory expires.
     * @param barrier
     *            the barrier this memory is about.
     */
    public MemoryOfBarrier(Date createdDate, Date expiryDate, Barrier barrier) {
        super(createdDate, expiryDate);
        this.barrier = barrier;
    }

    /**
     * @return get the barrier.
     */
    public Barrier getBarrier() {
        return barrier;
    }

}
