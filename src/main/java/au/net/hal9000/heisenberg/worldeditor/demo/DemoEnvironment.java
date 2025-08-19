package au.net.hal9000.heisenberg.worldeditor.demo;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Backpack;
import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Candle;
import au.net.hal9000.heisenberg.item.Cloak;
import au.net.hal9000.heisenberg.item.Crossbow;
import au.net.hal9000.heisenberg.item.CrossbowBolt;
import au.net.hal9000.heisenberg.item.FlintAndTinder;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.MagicRing;
import au.net.hal9000.heisenberg.item.OrbOfLight;
import au.net.hal9000.heisenberg.item.Purse;
import au.net.hal9000.heisenberg.item.Quiver;
import au.net.hal9000.heisenberg.item.Ring;
import au.net.hal9000.heisenberg.item.Scabbard;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.Torch;
import au.net.hal9000.heisenberg.item.Water;
import au.net.hal9000.heisenberg.item.entity.Horse;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import org.apache.log4j.Logger;

/** Utility class for setting up test environment and building test Item objects. */
public final class DemoEnvironment {
  /** Field LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(DemoEnvironment.class.getName());

  /** file containing test configuration. */
  private static String DEMO_CONFIG_FILE = "src/test/resources/config.xml";

  /** test level. */
  private static final int DEMO_LEVEL = 3;

  /** test weight or volume. */
  private static final int DEMO_WEIGHT_VOLUME = 100000;

  /** Constructor. */
  private DemoEnvironment() {
    super();
  }

  /** Setup demo environment. */
  public static void setup() {
    LOGGER.info("Demo Env setup");
    try {
      new Configuration(DEMO_CONFIG_FILE);
    } catch (ConfigurationError e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Get Demo CharacterSheet object with a few simple properties.
   *
   * @return CharacterSheet of PC/NPC.
   * @throws ConfigurationError if there is an issue with the configuration setup.
   */
  public static CharacterSheet getCharacterSheet() throws ConfigurationError {
    Configuration config = Configuration.lastConfig();
    CharacterSheet characterSheet = new CharacterSheet();
    characterSheet.setName("Jane");
    characterSheet.setDescription("The Paladin");
    characterSheet.setPcClass(config.getPcClasses().get("Paladin"));
    characterSheet.setSpecies(config.getSpecies().get(0));
    characterSheet.setGender(config.getGenders().get(0));
    characterSheet.setSize(config.getSizes().get(0));
    characterSheet.setLevel(DEMO_LEVEL);
    characterSheet.setSkills(config.getSkillDetails().keySet());
    characterSheet.setRecipes(config.getRecipes().keySet());
    // TODO modify ability-scores by character level.
    characterSheet.setAbilityScores(
        config.getPcClasses().get(characterSheet.getPcClass().getId()).getAbilityScores());
    return characterSheet;
  }

  /**
   * Create a demo world with some Item objects.
   *
   * @return a demo world of Item objects.
   */
  public static Location getDemoWorld() {
    // Ad-hoc test world
    Location world = new Location();
    world.setName("World");
    world.setWeightMax(DEMO_WEIGHT_VOLUME);
    world.setVolumeMax(DEMO_WEIGHT_VOLUME);

    Scabbard scabbard = new Scabbard();
    scabbard.add(new Sword());

    Bag boh = new BagOfHolding(1);
    boh.add(new Bag());
    boh.add(new Box());
    boh.add(new Candle());
    boh.add(new Cloak());
    boh.add(new Biscuit());
    boh.add(new Crossbow());
    boh.add(new CrossbowBolt());
    boh.add(scabbard);
    boh.add(new Torch());

    var wineskin = new Bag();
    wineskin.setName("Wineskin");
    wineskin.add(new Water());

    // a backpack of stuff
    Bag backpack = new Backpack();
    backpack.setWeightMax(DEMO_WEIGHT_VOLUME);
    backpack.setVolumeMax(DEMO_WEIGHT_VOLUME);
    backpack.add(new Candle());
    backpack.add(new Ring());
    backpack.add(new FlintAndTinder());
    backpack.add(new OrbOfLight());
    backpack.add(new Purse());
    backpack.add(wineskin);
    backpack.add(boh);

    Quiver quiver = new Quiver();
    for (int i = 0; i < 18; i++) {
      quiver.add(new Arrow());
    }

    // a human with a bag of Biscuits
    Human human = new Human();
    human.setName("Fred");
    human.setWeightMax(DEMO_WEIGHT_VOLUME);
    human.setVolumeMax(DEMO_WEIGHT_VOLUME);

    human.getRightHand().add(new Sword());
    human.getLeftHand().add(new Shield());
    human.getCore().add(new Scabbard());
    var humansScabbard = new Scabbard();
    humansScabbard.add(new Sword());
    human.getCore().add(humansScabbard);
    human.getCore().add(new Cloak());

    // Automatic placement
    human.add(quiver);
    human.add(backpack);
    human.add(new MagicRing());
    world.add(human);

    world.add(new Sword());
    world.add(new Horse());

    Bag bag3 = new Bag();
    bag3.add(new Biscuit());
    bag3.add(new Biscuit());
    bag3.add(new Biscuit());
    world.add(bag3);

    return world;
  }
}
