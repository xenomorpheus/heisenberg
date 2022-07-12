package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.mixin.AnimalConsumeSustenance;
import au.net.hal9000.heisenberg.util.PcClass;
import jakarta.persistence.Entity;

/**
 * Elf.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Elf extends Humanoid implements Animal {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Elf. */
  public Elf() {
    this("Elf");
  }

  /**
   * Constructor for Elf.
   *
   * @param string String
   */
  private Elf(String string) {
    super(string);
  }

  /**
   * Constructor for Elf.
   *
   * @param string String
   * @param description String
   */
  public Elf(String string, String description) {
    super(string, description);
  }

  /**
   * Constructor for Elf.
   *
   * @param name String
   * @param description String
   * @param pcClass PcClass
   */
  public Elf(String name, String description, PcClass pcClass) {
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
