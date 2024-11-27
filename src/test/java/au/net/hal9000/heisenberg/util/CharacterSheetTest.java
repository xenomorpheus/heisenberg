package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

/** */
public class CharacterSheetTest {

  /** Field config. */
  private Configuration config = null;

  @Before
  public void initialize() {
    DemoEnvironment.setup();
    config = Configuration.lastConfig();
  }

  // Constructor
  /** Method testPlayer. */
  @Test
  public void testCharacterSheet() {
    CharacterSheet characterSheet = new CharacterSheet();
    assertNotNull(characterSheet);
  }

  // Getters and Setters in field order.

  /** Test name. */
  @Test
  public void name() {
    final String expectedValue = "Fake Name";
    CharacterSheet characterSheet = new CharacterSheet();
    characterSheet.setName(expectedValue);
    assertEquals(expectedValue, characterSheet.getName());
  }

  /** Test description. */
  @Test
  public void description() {
    final String expectedValue = "Fake Description";
    CharacterSheet characterSheet = new CharacterSheet();
    characterSheet.setDescription(expectedValue);
    assertEquals(expectedValue, characterSheet.getDescription());
  }

  /** Test Level. */
  @Test
  public void testLevel() {
    final int expectedValue = 18;
    CharacterSheet characterSheet = new CharacterSheet();
    assertEquals(0, characterSheet.getLevel());
    characterSheet.setLevel(expectedValue);
    assertEquals(expectedValue, characterSheet.getLevel());
  }

  /** Test pcClass. */
  @Test
  public void testPcClass() {
    final var expectedValue = new PcClass();
    CharacterSheet characterSheet = new CharacterSheet();
    characterSheet.setPcClass(expectedValue);
    assertEquals(expectedValue, characterSheet.getPcClass());
  }

  /** Test species. */
  @Test
  public void testSpecies() {
    final var expectedValue = config.getSpecies().get(0);
    CharacterSheet characterSheet = new CharacterSheet();
    characterSheet.setSpecies(expectedValue);
    assertEquals(expectedValue, characterSheet.getSpecies());
  }

  /** Test gender. */
  @Test
  public void testGender() {
    final String expectedValue = config.getGenders().get(0);
    CharacterSheet characterSheet = new CharacterSheet();
    characterSheet.setGender(expectedValue);
    assertEquals(expectedValue, characterSheet.getGender());
  }

  /** Test size. */
  @Test
  public void testSize() {
    final var expectedValue = config.getSizes().get(0);
    CharacterSheet characterSheet = new CharacterSheet();
    characterSheet.setSize(expectedValue);
    assertEquals(expectedValue, characterSheet.getSize());
  }

  /** Test skills. */
  @Test
  public void testSkills() {
    final Set<Skill> expectedValue =
        Stream.of(new Skill("testSkill1"), new Skill("testSkill2"))
            .collect(Collectors.toCollection(() -> new TreeSet<>()));
    CharacterSheet characterSheet = new CharacterSheet();
    assertEquals(0, characterSheet.getSkills().size());
    characterSheet.setSkills(expectedValue);
    assertEquals(expectedValue, characterSheet.getSkills());
  }

  /** Test recipes. */
  @Test
  public void testRecipes() {
    final var expectedValue =
        Stream.of("testItem1", "testFireGround1", "testSpell1")
            .collect(Collectors.toCollection(() -> new TreeSet<>(String.CASE_INSENSITIVE_ORDER)));
    CharacterSheet characterSheet = new CharacterSheet();
    assertEquals(0, characterSheet.getRecipes().size());
    characterSheet.setRecipes(expectedValue);
    assertEquals(expectedValue, characterSheet.getRecipes());
  }

  /** Test abilityScores. */
  @Test
  public void testAbilityScores() {
    final Map<String, AbilityScore> expectedValue = new TreeMap<>();
    // TODO set a meaningful value for expectedValue
    CharacterSheet characterSheet = new CharacterSheet();
    assertEquals(0, characterSheet.getAbilityScores().size());
    characterSheet.setAbilityScores(expectedValue);
    assertEquals(expectedValue, characterSheet.getAbilityScores());
  }

  /** Test serialisation. */
  @Test
  public void serDe() {

    CharacterSheet originalObject = new CharacterSheet();
    final String nameExpected = "Rumple";
    originalObject.setName(nameExpected);

    try {
      // Step 1: Serialise the object into a byte array
      ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
      try (ObjectOutputStream oos = new ObjectOutputStream(byteArrayOut)) {
        oos.writeObject(originalObject);
        System.out.println("Object serialised to byte array: " + originalObject);
      }

      // Step 2: Deserialize the object from the byte array
      ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(byteArrayOut.toByteArray());
      try (ObjectInputStream ois = new ObjectInputStream(byteArrayIn)) {
        CharacterSheet deserialisedObject = (CharacterSheet) ois.readObject();
        System.out.println("Object deserialised from byte array: " + deserialisedObject);
        assertEquals("name", nameExpected, deserialisedObject.getName());
      }

    } catch (IOException | ClassNotFoundException e) {
      fail(e.toString());
    }
  }
}
