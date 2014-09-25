package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Very simple actions - Agent movement only.
 * 
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */

public interface ActionMove extends Action {

    /**
     * 
     * 
     * @return the amount of movement.
     */
    public Point3d getDelta();

}
