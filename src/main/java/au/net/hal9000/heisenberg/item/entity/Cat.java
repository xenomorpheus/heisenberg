package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.mixin.AnimalConsumeSustenance;
import jakarta.persistence.Entity;

/** Cat. */
@Entity
public class Cat extends EntityItem implements Animal {

  /** serial id. */
  private static final long serialVersionUID = 1L;

  /** Constructor for Cat. */
  public Cat() {
    super();
  }

  // Implement Animal

  @Override
  public void eat(Item food) {
    AnimalConsumeSustenance.eat(this, food);
  }

  @Override
  public void drink(Item food) {
    AnimalConsumeSustenance.drink(this, food);
  }
}
