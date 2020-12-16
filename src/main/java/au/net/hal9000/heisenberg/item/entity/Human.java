package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.mixin.AnimalConsumeSustenance;
import au.net.hal9000.heisenberg.util.PcClass;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * A common human.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Human extends Humanoid implements Animal {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Human. */
  public Human() {
    super("Human");
  }

  /**
   * Constructor for Human.
   *
   * @param string String
   */
  public Human(String string) {
    super(string);
  }

  /**
   * Constructor for Human.
   *
   * @param string String
   * @param description String
   */
  Human(String string, String description) {
    super(string, description);
  }

  /**
   * Constructor for Human.
   *
   * @param name String
   * @param pcClass PcClass
   */
  Human(String name, PcClass pcClass) {
    super(name, pcClass);
  }

  /**
   * Constructor for Human.
   *
   * @param name String
   * @param description String
   * @param pcClass PcClass
   */
  Human(String name, String description, PcClass pcClass) {
    super(name, description, pcClass);
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
