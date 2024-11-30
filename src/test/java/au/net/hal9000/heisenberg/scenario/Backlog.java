package au.net.hal9000.heisenberg.scenario;

import au.net.hal9000.heisenberg.item.Cloak;
import au.net.hal9000.heisenberg.item.entity.Human;
import org.junit.Test;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class Backlog {

  /**
   * Method println.
   *
   * @param debugText String
   */
  private void println(String debugText) {
    // System.out.println(debugText);
  }

  // Scenario 01
  /** Method characterMountsHorse. */
  @Test
  public void characterMountsHorse() {
    println(System.lineSeparator() + "** A character mounts a horse.");
    // Halfling hobbit = new Halfling();
    // Horse horse = new Horse();
    // TODO hobbit.setMount(horse);
  }

  // Scenario 01
  /** Method characterPutsOnCloak. */
  @Test
  public void characterPutsOnCloak() {
    println(System.lineSeparator() + "** A character puts on a cloak.");
    Human human = new Human();
    println("Human created with name: " + human);
    Cloak cloak = new Cloak();
    println("Cloak created with name: " + cloak);
    // TODO human.wear(cloak);
    println("Cloak location: " + cloak.getContainer());
  }
}
