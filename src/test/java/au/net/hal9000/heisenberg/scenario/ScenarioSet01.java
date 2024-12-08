package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Backpack;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Candle;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Crossbow;
import au.net.hal9000.heisenberg.item.CrossbowBolt;
import au.net.hal9000.heisenberg.item.FlintAndTinder;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.Quiver;
import au.net.hal9000.heisenberg.item.Scabbard;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.Torch;
import au.net.hal9000.heisenberg.item.entity.Halfling;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ScenarioSet01 {

  @Before
  public void initialize() {
    DemoEnvironment.setup();
  }

  /**
   * Method println.
   *
   * @param debugText String
   */
  private void println(String debugText) {
    // System.out.println(debugText);
  }

  /** Method swordIntoScabbard. */
  @Test
  public void swordIntoScabbard() {
    println(System.lineSeparator() + "** A sword is placed in a scabbard.");
    Sword sword = new Sword();
    println("Sword created with name: " + sword);
    Scabbard scabbard = new Scabbard();
    println("Scabbard created with name: " + scabbard);
    scabbard.add(sword);
    println("Sword now in scabbard.");
    println("Sword location: " + sword.getContainer());
  }

  /** Method fullBackpackIntoBox. */
  @Test
  public void fullBackpackIntoBox() {
    println(System.lineSeparator() + "** A full backpack is placed in a box.");
    Backpack backpack = new Backpack();
    println("Backpack created with name: " + backpack);
    Box box = new Box();
    println("Box created with name: " + box);
    // TODO fail("TEST not yet implemented");
    box.add(backpack);
    println("Full backpack placed into box.");
    println("Backpack location: " + backpack.getContainer());
  }

  /** Method crossbowIsLoaded. */
  @Test
  public void crossbowIsLoaded() {
    println(System.lineSeparator() + "** A crossbow is loaded.");
    Crossbow crossbow = new Crossbow();
    println("Crossbow created with name: " + crossbow);
    CrossbowBolt bolt = new CrossbowBolt();
    println("CrossbowBolt created with name: " + bolt);
    Location ground = new Location();
    bolt.setContainer(ground);
    crossbow.setLoadedBolt(bolt);
    CrossbowBolt got = crossbow.getLoadedBolt();
    assertEquals("bow's bolt", bolt, got);
    assertEquals("bolt's location", ground, got.getContainer());
    println("Crossbow has been loaded with the bolt");
  }

  /** Method torchIsLit. */
  @Test
  public void torchIsLit() {
    println(System.lineSeparator() + "** A torch is lit.");
    Torch torch = new Torch();
    println("Torch description:" + System.lineSeparator() + "" + torch.getDescription());
    println("Light the torch.");
    FlintAndTinder flintAndTinder = new FlintAndTinder();
    assertFalse("torch unlit", torch.isLit());
    torch.lightWith(flintAndTinder);
    assertTrue("torch unlit", torch.isLit());
    println("Torch description:" + System.lineSeparator() + "" + torch.getDescription());
  }

  /** Method shieldAdd. */
  @Test
  public void shieldAdd() {
    println(System.lineSeparator() + "** A Shield is equipped.");
    Shield shield = new Shield();
    Human human = new Human();
    println("Equip the shield.");
    human.add(shield);
    println("Shield location: " + shield.getContainer());
  }

  /** Method quiverIsFilled. */
  @Test
  public void quiverIsFilled() {
    println(System.lineSeparator() + "** A quiver is filled.");
    Quiver quiver = new Quiver();
    println("Quiver created.");
    println("TODO - set quiver size");
    println("Arrow created.");
    quiver.add(new Arrow());
    println("Arrow created.");
    quiver.add(new Arrow());
    println("Arrow created.");
    quiver.add(new Arrow());
    println("Arrow created.");
    quiver.add(new Arrow());
    println("Arrow created.");
    quiver.add(new Arrow());
    println("Arrow created.");
    quiver.add(new Arrow());
    println("Quiver Description: " + quiver.detailedDescription());
  }

  // TODO candle runs out
  /** Method candleRunsOut. */
  @Test
  public void candleRunsOut() {
    println(System.lineSeparator() + "** A candle runs out.");
    Candle candle = new Candle();
    println("Candle description:" + System.lineSeparator() + "" + candle.getDescription());
    // TODO candle.run_out();
    println("Torch description:" + System.lineSeparator() + "" + candle.getDescription());
  }

  /**
   * Method halflingEatsACookie.
   *
   * @throws InvalidTypeException
   */
  @Test
  public void halflingEatsACookie() throws InvalidTypeException {
    println(System.lineSeparator() + "** A halfling eats a cookie.");
    Halfling halfling = new Halfling();
    halfling.getPlayableState().setActionPoints(3);
    Cookie cookie = new Cookie();
    halfling.eat(cookie);
    println("Halfling ate a cookie");
  }
}
