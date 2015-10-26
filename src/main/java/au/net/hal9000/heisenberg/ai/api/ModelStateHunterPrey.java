package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.units.Position;

public interface ModelStateHunterPrey {

    /** Treat immutable */
    Entity getHunter();

    /** Treat immutable */
    Item getPrey();

    /** Treat mutable */
    Position getHunterPosition();

    /** Treat mutable */
    Position getPreyPosition();

}
