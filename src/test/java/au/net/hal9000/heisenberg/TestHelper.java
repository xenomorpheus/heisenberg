package au.net.hal9000.heisenberg;

import au.net.hal9000.heisenberg.fifthed.characterclass.CombatFeat;
import au.net.hal9000.heisenberg.fifthed.characterclass.Fighter;
import au.net.hal9000.heisenberg.fifthed.characterclass.Magus;
import au.net.hal9000.heisenberg.fifthed.item.BowCrossLight;
import au.net.hal9000.heisenberg.fifthed.item.BowLong;
import au.net.hal9000.heisenberg.fifthed.item.Dagger;
import au.net.hal9000.heisenberg.fifthed.playercharacter.Human;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacterCondition;
import au.net.hal9000.heisenberg.fifthed.spell.BladeLash;
import au.net.hal9000.heisenberg.fifthed.spell.Fireball;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import org.apache.log4j.Logger;

public class TestHelper {

  /** Field LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(TestHelper.class.getName());

  /** file containing test configuration. */
  private static String TEST_CONFIG_FILE = "src/test/resources/config.xml";

  /** Setup demo environment. */
  public static void setup() {
    try {
      new Configuration(TEST_CONFIG_FILE);
    } catch (ConfigurationError e) {
      throw new RuntimeException(e);
    }
    LOGGER.info("Test Env setup");
  }

  public static PlayerCharacter getMagus() {
    PlayerCharacter pc = new Human();
    pc.setName("Mr Magus");

    Magus magus = new Magus();
    magus.setClassLevel(3);

    // Spells
    magus.spellsAdd(new Fireball());
    magus.spellsAdd(new BladeLash());

    // Combat Feats
    magus.combatFeatAdd(CombatFeat.ARTFUL_DODGE);

    pc.classesAdd(magus);

    // Conditions
    pc.conditionAdd(PlayerCharacterCondition.DEAFENED);

    // Equipment
    pc.equippedAdd(new Dagger());
    pc.equippedAdd(new BowCrossLight());

    return pc;
  }

  public static PlayerCharacter getFighter() {
    PlayerCharacter pc = new Human();

    // Classes
    Fighter fighter = new Fighter();
    fighter.setClassLevel(2);

    // Combat Feats
    fighter.combatFeatAdd(CombatFeat.ARTFUL_DODGE);

    pc.classesAdd(fighter);

    // Conditions
    pc.conditionAdd(PlayerCharacterCondition.DEAFENED);

    // Equipment
    pc.equippedAdd(new Dagger());
    pc.equippedAdd(new BowLong());

    return pc;
  }
}
