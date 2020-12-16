package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.units.Position;

public interface ModelStateConsumerConsumable {

  /** Treat immutable */
  Object getConsumer();

  /** Treat immutable */
  Object getConsumable();

  /** Treat mutable */
  Position getEntityPosition();

  /** Treat mutable */
  Position getConsumablePosition();

  /** consumable been consumed */
  void setConsumed();

  /** has consumable been consumed? */
  boolean getConsumed();
}
