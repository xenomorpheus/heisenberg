package au.net.hal9000.heisenberg.ai;

import java.util.Date;

import au.net.hal9000.heisenberg.ai.api.Barrier;

/**
 * Holds a memory of a Barrier.
 * 
 * @author bruins
 *
 */

public class MemoryOfBarrier extends MemoryImpl {
    /** the barrier this memory is about. */
    private Barrier barrier;

    /**
     * Constructor.
     * 
     * @param createdDate
     *            date memory created.
     * @param decayRate
     *            rate memory decays.
     * @param barrier
     *            the barrier this memory is about.
     */
    MemoryOfBarrier(Date createdDate, double decayRate, Barrier barrier) {
        super(createdDate, decayRate);
        this.barrier = barrier;
    }

    /**
     * @return get the barrier.
     */
    public Barrier getBarrier() {
        return barrier;
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(11);
        return string.append(getClass().getSimpleName() + "=[").append(barrier)
                .append(getCreatedDate()).append(",").append(getDecayRate()).append(']')
                .append(']').toString();
    }
}
