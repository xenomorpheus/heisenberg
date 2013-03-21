package au.net.hal9000.heisenberg.item.property;

/**
 * Sources of light, e.g. torches, lanterns etc.
 * @author bruins
 *
 */


public interface LightSource {

    /**
     * Start the light.
     * @param ignighter TODO
     * @return TODO
     */
    public boolean lightWith(Object ignighter);

    /**
     * Stop the light.
     */
    public void extinguish();

    /**
     * Is this torch lit?
     * 
     * @return true if lit
     */
    public boolean isLit();

}
