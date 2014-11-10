package au.net.hal9000.heisenberg.ai.api;

import java.util.Date;

/**
 * Holds a memory for a period of time.
 * 
 * @author bruins
 *
 */
public interface Memory {

    /**
     * 
     * @return created date.
     */
    Date getCreatedDate();

    /**
     * 
     * @return the rate of decay of this Memory.
     */
    double getDecayRate();

}
