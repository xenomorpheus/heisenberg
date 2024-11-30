package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.mixin.AnimalConsumeSustenance;
import au.net.hal9000.heisenberg.util.PcClass;
import jakarta.persistence.Entity;

/**
 * A common human.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Human extends Humanoid implements Animal {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Human. */
  public Human() {
    super();
  }

  /**
   * Constructor for Human.
   *
   * @param name String
   */
  public Human(String name) {
    this();
    setName(name);
  }

  /**
   * Constructor for Human.
   *
   * @param name String
   * @param description String
   */
  Human(String name, String description) {
    this(name);
    setDescription(description);
  }

  /**
   * Constructor for Human.
   *
   * @param name String
   * @param pcClass PcClass
   */
  Human(String name, PcClass pcClass) {
    this(name);
    setPcClass(pcClass);
  }

  /**
   * Constructor for Human.
   *
   * @param name String
   * @param description String
   * @param pcClass PcClass
   */
  Human(String name, String description, PcClass pcClass) {
    this(name, description);
    setPcClass(pcClass);
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
