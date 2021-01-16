package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.mixin.AnimalConsumeSustenance;
import javax.persistence.Entity;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Cat extends EntityItem implements Animal {

  /** */
  private static final long serialVersionUID = 1L;

  /** Constructor for Cat. */
  public Cat() {
    this("Cat");
  }

  /**
   * Constructor for Cat.
   *
   * @param name String
   */
  Cat(String name) {
    this(name, "A cat");
  }

  /**
   * Constructor for Cat.
   *
   * @param name String
   * @param description String
   */
  Cat(String name, String description) {
    super(name, description);
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
