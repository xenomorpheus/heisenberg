package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.mixin.AnimalConsumeSustenance;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * A common rat.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Rat extends au.net.hal9000.heisenberg.item.entity.Entity implements Animal {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Rat. */
  public Rat() {
    this("Rat");
  }

  /**
   * Constructor for Rat.
   *
   * @param name String
   */
  public Rat(String name) {
    this(name, "A Rat");
  }

  /**
   * Constructor for Rat.
   *
   * @param name String
   * @param description String
   */
  public Rat(String name, String description) {
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
