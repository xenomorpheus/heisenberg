package au.net.hal9000.heisenberg.unused;

import java.util.Date;

/**
 * Holds a memory for a period of time.
 * Try to consider immutable.
 * 
 * @author bruins
 *
 */
public abstract class Memory {
    /** when the memory was created. */
    private Date createdDate;
    // TODO decay rate
    /** when the memory ceases to exist. */
    private Date expiryDate;

    /**
     * Constructor.
     */
    Memory() {
        super();
        createdDate = new Date();
    }

    /**
     * Constructor.
     * 
     * @param createdDate
     *            date memory created.
     */
    Memory(Date createdDate) {
        super();
        this.createdDate = createdDate;
    }

    /**
     * Constructor.
     * 
     * @param createdDate
     *            date memory created.
     * @param expiryDate
     *            date memory expires.
     */
    Memory(Date createdDate, Date expiryDate) {
        this(createdDate);
        this.expiryDate = expiryDate;
    }

    /**
     * 
     * @return created date.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 
     * @return expiry date.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

}
