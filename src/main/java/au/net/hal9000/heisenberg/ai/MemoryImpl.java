package au.net.hal9000.heisenberg.ai;

import java.util.Date;

import au.net.hal9000.heisenberg.ai.api.Memory;

/**
 * Holds a memory for a period of time.
 * 
 * @author bruins
 *
 */
public abstract class MemoryImpl implements Memory {
    /** When the memory was created. */
    private Date createdDate;

    /**
     * Memories fade over time. Walls fade slowly. Memory objects of transient
     * objects fade quickly.
     */
    private double decayRate;

    /**
     * Constructor.
     */
    MemoryImpl() {
        super();
        createdDate = new Date();
    }

    /**
     * Constructor.
     * 
     * @param createdDate
     *            date memory created.
     */
    MemoryImpl(Date createdDate) {
        super();
        this.createdDate = createdDate;
    }

    /**
     * Constructor.
     * 
     * @param createdDate
     *            date memory created.
     * @param decayRate
     *            date memory expires.
     */
    MemoryImpl(Date createdDate, double decayRate) {
        this(createdDate);
        this.decayRate = decayRate;
    }

    /** {@inheritDoc} */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    /** {@inheritDoc} */
    @Override
    public double getDecayRate() {
        return decayRate;
    }

}
