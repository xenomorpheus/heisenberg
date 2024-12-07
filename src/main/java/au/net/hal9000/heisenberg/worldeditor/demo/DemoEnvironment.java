package au.net.hal9000.heisenberg.worldeditor.demo;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Backpack;
import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Candle;
import au.net.hal9000.heisenberg.item.Cloak;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Crossbow;
import au.net.hal9000.heisenberg.item.CrossbowBolt;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.MagicRing;
import au.net.hal9000.heisenberg.item.Quiver;
import au.net.hal9000.heisenberg.item.Ring;
import au.net.hal9000.heisenberg.item.Scabbard;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.Torch;
import au.net.hal9000.heisenberg.item.entity.Horse;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;

/**
 * Utility class for setting up test environment and building test Item objects.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class DemoEnvironment {
  /** Field LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(DemoEnvironment.class.getName());

  /** file containing test configuration. */
  private static String DEMO_CONFIG_FILE = "src/test/resources/config.xml";

  /** test skills. */
  private static final Set<Skill> DEMO_SKILLS =
      Stream.of(new Skill("testSkill1"), new Skill("testSkill2"))
          .collect(Collectors.toCollection(() -> new TreeSet<>()));

  /** test recipes. */
  private static final Set<String> DEMO_RECIPES =
      Stream.of("testItem1", "testFireGround1", "testSpell1")
          .collect(Collectors.toCollection(() -> new TreeSet<>()));

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
   * @throws ConfigurationError
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
    characterSheet.setSkills(DEMO_SKILLS);
    characterSheet.setRecipes(DEMO_RECIPES);
    // TODO characterSheet.setAbilityScores(null);
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
    world.setWeightMax(DEMO_WEIGHT_VOLUME);
    world.setVolumeMax(DEMO_WEIGHT_VOLUME);

    // Scabbard
    Scabbard scabbard = new Scabbard();
    scabbard.add(new Sword());

    Scabbard scabbard2 = new Scabbard();
    scabbard2.add(new Sword());

    Bag boh = new BagOfHolding(1);
    boh.add(new Bag());
    boh.add(new Box());
    boh.add(new Candle());
    boh.add(new Cloak());
    boh.add(new Cookie());
    boh.add(new Crossbow());
    boh.add(new CrossbowBolt());
    boh.add(new Ring());
    boh.add(scabbard);
    boh.add(new Torch());

    // a backpack of stuff
    Bag backpack = new Backpack();
    backpack.setWeightMax(DEMO_WEIGHT_VOLUME);
    backpack.setVolumeMax(DEMO_WEIGHT_VOLUME);
    backpack.add(boh);

    Quiver quiver = new Quiver();
    quiver.add(new Arrow());
    quiver.add(new Arrow());
    quiver.add(new Arrow());

    Bag bag2 = new Bag();
    bag2.add(new Cookie());
    bag2.add(new Cookie());
    bag2.add(new Cookie());
    backpack.add(bag2);

    // a human with a bag of cookies
    Human human = new Human();
    human.setWeightMax(DEMO_WEIGHT_VOLUME);
    human.setVolumeMax(DEMO_WEIGHT_VOLUME);

    // Automatic placement
    human.wear(new Shield());
    human.wear(scabbard2);
    human.wear(quiver);
    human.wear(backpack);
    human.wear(new MagicRing());
    world.add(human);

    world.add(new Sword());
    world.add(new Horse());

    Bag bag3 = new Bag();
    bag3.add(new Cookie());
    bag3.add(new Cookie());
    bag3.add(new Cookie());
    world.add(bag3);

    return world;
  }
}
