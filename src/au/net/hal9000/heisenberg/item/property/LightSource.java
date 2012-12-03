package au.net.hal9000.heisenberg.item.property;

/**
 * Sources of light, e.g. torches, lanterns etc.
 * @author bruins
 *
 */


public interface LightSource {

    /**
     * Start the light.
     */
    public void light();

    /**
     * Stop the light.
     */
    public void extinghish();

    /**
     * Is this torch lit?
     * 
     * @return true if lit
     */
    public boolean isLit();

}
