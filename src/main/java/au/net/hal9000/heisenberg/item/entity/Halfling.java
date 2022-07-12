package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.mixin.AnimalConsumeSustenance;
import jakarta.persistence.Entity;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Halfling extends Humanoid implements Animal {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Halfling. */
  public Halfling() {
    this("Halfling");
  }

  /**
   * Constructor for Halfling.
   *
   * @param name String
   */
  public Halfling(String name) {
    this(name, "A Halfling");
  }

  /**
   * Constructor for Halfling.
   *
   * @param name String
   * @param description String
   */
  public Halfling(String string, String description) {
    super(string, description);
  }

  @Override
  public void eat(Item food) {
    AnimalConsumeSustenance.eat(this, food);
  }

  @Override
  public void drink(Item food) {
    AnimalConsumeSustenance.drink(this, food);
  }
}
