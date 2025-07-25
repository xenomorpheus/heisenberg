package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.crafting.Product;
import au.net.hal9000.heisenberg.crafting.ProductEntityProperty;
import au.net.hal9000.heisenberg.crafting.ProductItem;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.crafting.Requirement;
import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.units.SkillDetail;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 * Configuration read the XML config file and return in an object to easily access the properties.
 *
 * <p>Singleton Design Pattern.
 *
 * <p>Please consider the values read only.
 */
public class Configuration {

  /** Singleton. Holder of the config */
  private static Configuration lastConfig = null;

  /** A list of genders. */
  private List<String> genders;

  /** A map of ItemClassConfiguration objects. */
  private Map<String, ItemClassConfiguration> itemClasses;

  // TODO private TreeMap<String,PcClass> npcClasses;
  /** A map of possible pcClass details. */
  private Map<String, PcClass> pcClasses;

  /** list of valid species. */
  private List<String> species;

  /** A map of Recipe objects. */
  private Map<String, Recipe> recipes;

  /** A map of SkillDetail objects. */
  private Map<Skill, SkillDetail> skillDetails;

  /** A list of sizes. */
  private List<String> sizes;

  /** Images to show in UI. */
  private Map<String, SpriteSheetConfiguration> spriteSheets;

  /**
   * Constructor.
   *
   * @param filename config file to read.
   * @throws ConfigurationError the configuration error.
   */
  public Configuration(String filename) throws ConfigurationError {
    super();
    init(filename);
    setLastConfig(this);
  }

  // Static - Getters and Setters
  /**
   * Change a static variable. Required by findbugs.
   *
   * @param config Configuration.
   */
  private static void setLastConfig(Configuration config) {
    Configuration.lastConfig = config;
  }

  /**
   * Get the last configuration.
   *
   * @return Return the last config that was read.
   */
  public static Configuration lastConfig() {
    if (null == lastConfig) {
      throw new RuntimeException("Please fetch config before using Singleton");
    }
    return lastConfig;
  }

  // Getters and Setters
  /**
   * Get the item classes.
   *
   * @return the itemClasses
   */
  public final Map<String, ItemClassConfiguration> getItemClasses() {
    return itemClasses;
  }

  /**
   * get skill details.
   *
   * @return skill details.
   */
  public final Map<Skill, SkillDetail> getSkillDetails() {
    return skillDetails;
  }

  /**
   * Get the recipes.
   *
   * @return Map of String and Recipe
   */
  public final Map<String, Recipe> getRecipes() {
    return recipes;
  }

  // TODO remove/refactor so caller can't modify pcClasses
  /**
   * Method getPcClasses.
   *
   * @return Map of String and PcClass.
   */
  public Map<String, PcClass> getPcClasses() {
    return pcClasses;
  }

  // TODO remove/refactor so caller can't modify species
  /**
   * Method getSpecies.
   *
   * @return List of strings.
   */
  public List<String> getSpecies() {
    return species;
  }

  // TODO remove/refactor so caller can't modify sizes
  /**
   * Get the sizes.
   *
   * @return List of Strings
   */
  public List<String> getSizes() {
    return sizes;
  }

  // TODO remove/refactor so caller can't modify genders
  /**
   * Get the genders.
   *
   * @return List of Strings.
   */
  public List<String> getGenders() {
    return genders;
  }

  /**
   * Get the sprite sheet details.
   *
   * @return the sprite sheet details
   */
  public Map<String, SpriteSheetConfiguration> getSpriteSheets() {
    return spriteSheets;
  }

  // Misc
  /**
   * Method init.
   *
   * @param filename String
   * @throws ConfigurationError exception
   */
  private void init(String filename) throws ConfigurationError {
    File file = new File(filename);

    Builder builder = new Builder();
    Document doc;
    try {
      doc = builder.build(file);
    } catch (ParsingException e) {
      throw new ConfigurationError(e);
    } catch (IOException e) {
      // TODO retry
      throw new ConfigurationError(e);
    }
    Element root = doc.getRootElement();

    // skill entries
    skillDetails = xmlToSkillDetails(root.getFirstChildElement("skills"));

    // Item Classes
    itemClasses = xmlToItemClasses(root.getFirstChildElement("items"));

    // recipes
    recipes = xmlToRecipes(root.getFirstChildElement("recipes"));

    // character
    Element characterElement = root.getFirstChildElement("character");
    // character - classes - pcClass
    Elements pcClassesElements = characterElement.getChildElements("pcClass");
    pcClasses = xmlToPcClasses(pcClassesElements);
    // character - classes - npcClass
    // Elements npcClassesElements = characterElement
    // .getChildElements("npcClass");
    // TODO npcClasses = xmlToNpcClasses(npcClassesElements);
    // character - traits
    // TODO Element traitElements =
    // characterElement.getFirstChildElement("traits");
    // TODO traits = xmlToTraits(traitElements);

    // character - sizes
    Element sizeElement = characterElement.getFirstChildElement("sizes");
    sizes = xmlToIdList(sizeElement.getChildElements());

    // character - genders
    Element genderElement = characterElement.getFirstChildElement("genders");
    genders = xmlToIdList(genderElement.getChildElements());

    // species list
    Element speciesElement = characterElement.getFirstChildElement("species_list");
    species = xmlToIdList(speciesElement.getChildElements());

    // sprite sheet details
    spriteSheets = xmlToSpriteSheets(root.getFirstChildElement("spriteSheets"));
  }

  /**
   * Load details about each Item class.
   *
   * @param itemClassesElement xml element.
   * @return a collection of information about each Item type.
   */
  private Map<String, ItemClassConfiguration> xmlToItemClasses(Element itemClassesElement) {
    Map<String, ItemClassConfiguration> myItemClasses = new TreeMap<>();
    for (var element : itemClassesElement.getChildElements("item")) {
      ItemClassConfiguration itemClassConfiguration = new ItemClassConfiguration();
      String id = element.getAttributeValue("id");
      itemClassConfiguration.setId(id);
      // iconClosedId
      String iconClosedId = element.getAttributeValue("iconClosedId");
      if (null != iconClosedId) {
        itemClassConfiguration.setIconClosedId(Integer.parseInt(iconClosedId));
      }
      // iconLeafId
      String iconLeafId = element.getAttributeValue("iconLeafId");
      if (null != iconLeafId) {
        itemClassConfiguration.setIconLeafId(Integer.parseInt(iconLeafId));
      }
      // iconOpenId
      String iconOpenId = element.getAttributeValue("iconOpenId");
      if (null != iconOpenId) {
        itemClassConfiguration.setIconOpenId(Integer.parseInt(iconOpenId));
      }
      // javaClass
      String javaClass = element.getAttributeValue("javaClass");
      if (null != javaClass) {
        itemClassConfiguration.setJavaClass(javaClass);
      }
      // Add item class to list
      myItemClasses.put(id, itemClassConfiguration);
    }
    return myItemClasses;
  }

  /**
   * A generic helper that converts a list of IDs to a string vector.
   *
   * @param elements Parsed XML structure.
   * @return a List strings.
   */
  private List<String> xmlToIdList(Elements elements) {
    List<String> list = new ArrayList<>();
    for (var element : elements) {
      list.add(element.getAttributeValue("id"));
    }
    return list;
  }

  /**
   * Method xmlToPcClasses.
   *
   * @param pcClassesElements Elements
   * @return Map of String and PcClass
   */
  private Map<String, PcClass> xmlToPcClasses(Elements pcClassesElements) {
    Map<String, PcClass> classes = new TreeMap<>();
    for (var element : pcClassesElements) {
      PcClass pcClass = xmlToPcClass(element);
      classes.put(pcClass.getId(), pcClass);
    }
    return classes;
  }

  /**
   * Read in an XML list of Skill IDs and produce an ingredient object.<br>
   * <&lt;> skill id="fire lighting" /<&gt;>
   *
   * @param entries XML list of Skill IDs
   * @return Set of Skill Objects.
   */
  private static Set<Skill> xmlToSkills(Elements entries) {
    Set<Skill> skills = new TreeSet<>();
    for (var entry : entries) {
      skills.add(new Skill(entry.getAttributeValue("id")));
    }
    return skills;
  }

  /**
   * Read in an XML list of Items details and produce ingredient objects.<br>
   * <&lt;>item id="wood" consumed="yes" weightMin="3" /<&gt;>
   *
   * @param entries XML list of Item details.
   * @return A map of RequirementItem objects, keyed by ID.
   */
  private static Map<String, Requirement> xmlToRecipeRequirementItems(Elements entries) {
    Map<String, Requirement> ingredients = new TreeMap<>();
    for (var entry : entries) {
      String itemId = entry.getAttributeValue("id");
      // type
      String itemType = entry.getAttributeValue("type");
      if (null == itemType) {
        itemType = itemId;
      }
      // consumed
      String consumedString = entry.getAttributeValue("consumed");
      boolean consumed = true;
      if (null != consumedString) {
        consumed = Boolean.parseBoolean(consumedString);
      }
      // weightMin
      float weightMin = 0;
      String weightMinString = entry.getAttributeValue("weightMin");
      if (null != weightMinString) {
        weightMin = Float.parseFloat(weightMinString);
      }

      RequirementItem requirement = new RequirementItem(itemId, itemType, consumed, weightMin);
      ingredients.put(itemId, requirement);
    }
    return ingredients;
  }

  /**
   * Read in an XML list of Recipe Product Items.<br>
   *
   * @param entries XML list of Item details.
   * @return A list of Recipe Product objects.
   */
  private static List<Product> xmlToRecipeProductItems(Elements entries) {
    List<Product> products = new ArrayList<>();
    for (var entry : entries) {
      String id = entry.getAttributeValue("id");
      String type = entry.getAttributeValue("itemType");
      if (null == type) {
        type = id;
      }
      String weightBaseString = entry.getAttributeValue("weightBase");
      float weightBase = 0;
      if (null != weightBaseString) {
        weightBase = Float.parseFloat(weightBaseString);
      }
      ProductItem product = new ProductItem(id, type, weightBase);
      products.add(product);
    }
    return products;
  }

  /**
   * Read in an XML list of Recipe Product Entity Properties.<br>
   *
   * @param entries XML list of Property details.
   * @return A list of Recipe Product objects.
   */
  private static List<Product> xmlToRecipeProductEntityProperties(Elements entries) {
    List<Product> products = new ArrayList<>();
    for (var entry : entries) {
      String id = entry.getAttributeValue("id");
      String propertyName = entry.getAttributeValue("propertyName");
      if (null == propertyName) {
        propertyName = id;
      }
      String propertyDeltaString = entry.getAttributeValue("propertyDelta");
      float propertyDelta = 0;
      if (null != propertyDeltaString) {
        propertyDelta = Float.parseFloat(propertyDeltaString);
      }
      ProductEntityProperty product = new ProductEntityProperty(id, propertyName, propertyDelta);
      products.add(product);
    }
    return products;
  }

  /**
   * Read in XML Recipe Product details and produce Recipe Product object(s).<br>
   *
   * @param entry an XML Recipe Product element.
   * @return List of Product
   */
  private static List<Product> xmlToRecipeProducts(Element entry) {

    List<Product> products = new ArrayList<Product>();
    if (null != entry) {
      Elements items = entry.getChildElements("item");
      if (null != items) {
        products.addAll(xmlToRecipeProductItems(items));
      }

      Elements properties = entry.getChildElements("property");
      if (null != properties) {
        products.addAll(xmlToRecipeProductEntityProperties(properties));
      }
    }
    return products;
  }

  /**
   * Read in XML Requirement details and produce Requirement object(s).<br>
   *
   * @param entry an XML Requirement element.
   * @return List of Requirement
   */
  private static Map<String, Requirement> xmlToRecipeRequirements(Element entry) {
    Elements items = entry.getChildElements("item");
    // TODO Config Recipe locations e.g. <location id="ground" />
    // Elements locations = entry.getChildElements("location");

    return xmlToRecipeRequirementItems(items);
  }

  /**
   * Convert an XML structure into a Recipe object.
   *
   * @param recipeElement XML details of a Recipe.
   * @return a Recipe object.
   */
  private static Recipe xmlToRecipe(Element recipeElement) {
    // <ingredient mana="2" actionPoints="42">
    // mana
    int mana = 0;
    Attribute manaAttribute = recipeElement.getAttribute("mana");
    if (null != manaAttribute) {
      mana = Integer.parseInt(manaAttribute.getValue());
    }
    // actionPoints
    int actionPoints = 0;
    Attribute actionPointsAttribute = recipeElement.getAttribute("actionPoints");
    if (null != actionPointsAttribute) {
      actionPoints = Integer.parseInt(actionPointsAttribute.getValue());
    }
    // process
    String process = null;
    Attribute processAttribute = recipeElement.getAttribute("process");
    if (null != processAttribute) {
      process = processAttribute.getValue();
    }

    // Skills
    Set<Skill> skills = new TreeSet<Skill>();
    Elements skillElements = recipeElement.getChildElements("skill");
    skills.addAll(xmlToSkills(skillElements));

    // Requirement objects
    Element requirementsElement = recipeElement.getFirstChildElement("requirements");
    Map<String, Requirement> requirements = xmlToRecipeRequirements(requirementsElement);

    // Product objects.
    Element productElement = recipeElement.getFirstChildElement("products");
    List<Product> products = xmlToRecipeProducts(productElement);

    // Return the completed recipe
    String id = recipeElement.getAttributeValue("id");
    String description = recipeElement.getAttributeValue("description");
    return new Recipe(id, description, process, mana, actionPoints, requirements, skills, products);
  }

  /**
   * Read multiple recipes from an XML file.
   *
   * @param element XML element containing recipes.
   * @return A set of Recipe objects. * @throws ParsingException * @throws IOException
   */
  private static Map<String, Recipe> xmlToRecipes(Element element) {
    Map<String, Recipe> recipes = new TreeMap<>();
    for (var recipeElement : element.getChildElements("recipe")) {
      Recipe recipe = xmlToRecipe(recipeElement);
      recipes.put(recipe.getId(), recipe);
    }
    return recipes;
  }

  /**
   * Read multiple SkillDetail objects from an XML element.
   *
   * @param element xml element.
   * @return Map of SkillDetail objects.
   */
  private static Map<Skill, SkillDetail> xmlToSkillDetails(Element element) {
    Map<Skill, SkillDetail> skillDetails = new TreeMap<>();
    for (var entry : element.getChildElements("skill")) {
      SkillDetail pw =
          new SkillDetail(entry.getAttributeValue("id"), entry.getAttributeValue("description"));
      skillDetails.put(new Skill(entry.getAttributeValue("id")), pw);
    }
    return skillDetails;
  }

  /**
   * Read one PcClass object from an XML element.
   *
   * @param element XML element.
   * @return a PcClass object.
   */
  private static PcClass xmlToPcClass(Element element) {
    PcClass pcClass = new PcClass();

    // id
    String id = element.getAttributeValue("id");
    if (null == id) {
      throw new IllegalArgumentException("id must be set");
    }
    pcClass.setId(id);

    // combatDice
    String combatDice = element.getAttributeValue("combatDice");
    if (null == combatDice) {
      throw new IllegalArgumentException("combatDice must be set");
    }
    pcClass.setCombatDice(Integer.parseInt(combatDice));

    // magicDice
    String magicDice = element.getAttributeValue("magicDice");
    if (null == magicDice) {
      throw new IllegalArgumentException("magicDice must be set");
    }
    pcClass.setMagicDice(Integer.parseInt(magicDice));
    // stealthDice
    String stealthDice = element.getAttributeValue("stealthDice");
    if (null == stealthDice) {
      throw new IllegalArgumentException("stealthDice must be set");
    }
    pcClass.setStealthDice(Integer.parseInt(stealthDice));
    // generalDice
    String generalDice = element.getAttributeValue("generalDice");
    if (null == generalDice) {
      throw new IllegalArgumentException("generalDice must be set");
    }
    pcClass.setGeneralDice(Integer.parseInt(generalDice));
    // encumbrance
    String encumbrance = element.getAttributeValue("encumbrance");
    if (null == encumbrance) {
      throw new IllegalArgumentException("encumbrance must be set");
    }
    pcClass.setEncumbrance(Integer.parseInt(encumbrance));
    // mana
    String mana = element.getAttributeValue("mana");
    if (null == mana) {
      throw new IllegalArgumentException("mana must be set");
    }
    pcClass.setMana(Integer.parseInt(mana));
    // actionPoint
    String actionPoint = element.getAttributeValue("actionPoint");
    if (null == actionPoint) {
      throw new IllegalArgumentException("actionPoint must be set");
    }
    pcClass.setActionPoints(Integer.parseInt(actionPoint));
    // health
    String health = element.getAttributeValue("health");
    if (null == health) {
      throw new IllegalArgumentException("health must be set");
    }
    pcClass.setHealth(Integer.parseInt(health));

    // <ability id="dweomerLore" value="0/2" />
    for (var abilityScore : element.getChildElements("ability")) {
      pcClass.setAbilityScore(xmlToAbilityScore(abilityScore));
    }

    return pcClass;
  }

  /**
   * Get an AbilityScore object from an XML element.
   *
   * @param element xml element.
   * @return the AbilityScore object.
   */
  private static AbilityScore xmlToAbilityScore(Element element) {

    String id = element.getAttributeValue("id");
    if (null == id) {
      throw new IllegalArgumentException("id missing");
    }
    String value = element.getAttributeValue("value");
    if (null == value) {
      throw new IllegalArgumentException("value missing");
    }
    return new AbilityScore(id, value);
  }

  /**
   * Get a map of String and SpriteSheetConfiguration.
   *
   * @param spriteSheets Element
   * @return Map of String and SpriteSheetConfiguration.
   */
  private Map<String, SpriteSheetConfiguration> xmlToSpriteSheets(Element spriteSheets) {
    Map<String, SpriteSheetConfiguration> mySpriteSheets = new TreeMap<>();
    for (var entry : spriteSheets.getChildElements()) {
      SpriteSheetConfiguration ssd = new SpriteSheetConfiguration();
      String id = entry.getAttributeValue("id");
      ssd.setId(id);
      ssd.setFilename(entry.getAttributeValue("filename"));
      ssd.setWidth(Integer.parseInt(entry.getAttributeValue("width")));
      ssd.setHeight(Integer.parseInt(entry.getAttributeValue("height")));
      ssd.setRows(Integer.parseInt(entry.getAttributeValue("rows")));
      ssd.setColumns(Integer.parseInt(entry.getAttributeValue("columns")));
      mySpriteSheets.put(id, ssd);
    }
    return mySpriteSheets;
  }

  /**
   * Get PC class.
   *
   * @param id the class name e.g. Soldier
   * @return the PC Character Class.
   */
  public PcClass getPcClass(String id) {
    return pcClasses.get(id);
  }

  // misc

  /**
   * Get the Recipe object with this recipeId.
   *
   * @param recipeId the id of the Recipe
   * @return the Recipe object
   */
  public Recipe getRecipe(String recipeId) {
    return recipes.get(recipeId);
  }

  /**
   * Return the sprite sheet details object with the supplied name.
   *
   * @param name name of sprite sheet.
   * @return the sprite sheet details
   */
  public SpriteSheetConfiguration getSpriteSheet(String name) {
    return spriteSheets.get(name);
  }

  /**
   * Method getItemClassIds.
   *
   * @return Item class names.
   */
  public Set<String> getItemClassIds() {
    return itemClasses.keySet();
  }

  public ItemClassConfiguration getItemClassConfiguration(String type) {
    return itemClasses.get(type);
  }
}
