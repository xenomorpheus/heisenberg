package au.net.hal9000.heisenberg.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

// XML Parser
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import au.net.hal9000.heisenberg.crafting.Product;
import au.net.hal9000.heisenberg.crafting.ProductEntityProperty;
import au.net.hal9000.heisenberg.crafting.ProductItem;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.crafting.Requirement;
import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.units.SkillDetail;

/**
 * Configuration read the XML config file and return in an object to easily
 * access the properties.
 * 
 * Singleton Design Pattern.
 * 
 * Please consider the values read only.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */

public class Configuration {

    /** Singleton. Holder of the config */
    private static Configuration lastConfig = null;
    /** A list of genders. */
    private List<String> genders;
    /** A map of ItemClassConfiguration objects. */
    private Map<String,ItemClassConfiguration> itemClasses;
    // TODO private TreeMap<String,PcClass> npcClasses;
    /** A map of possible pcClass details. */
    private Map<String, PcClass> pcClasses;
    /** list of valid races. */
    private List<String> races;
    /** A map of Recipe objects. */
    private Map<String, Recipe> recipes;
    /** A map of SkillDetail objects. */
    private Map<String, SkillDetail> skillDetails;
    /** A list of sizes. */
    private List<String> sizes;
    /** Images to show in UI. */
    private Map<String, SpriteSheetConfiguration> spriteSheets;

    /**
     * Constructor.
     * 
     * @param filename
     *            config file to read.
     * 
     * @throws ConfigurationError
     */
    public Configuration(String filename) throws ConfigurationError {
        super();
        this.init(filename);
        setLastConfig(this);
    }

    // Static - Getters and Setters
    /**
     * Change a static variable. Required by findbugs.
     * 
     * @param config
     */
    private static void setLastConfig(Configuration config) {
        Configuration.lastConfig = config;
    }

    /**
     * 
     * @return Return the last config that was read.
     */
    public static Configuration lastConfig() {
        if (null == lastConfig) {
            throw new RuntimeException(
                    "Please fetch config before using Singleton");
        }
        return lastConfig;
    }

    // Getters and Setters
    /**
     * 
     * @return the itemClasses
     */
    public final Map<String,ItemClassConfiguration> getItemClasses() {
        return itemClasses;
    }

    /**
     * get skill details.
     * 
     * 
     * @return skill details.
     */
    public final Map<String, SkillDetail> getSkillDetails() {
        return skillDetails;
    }

    /**
     * Method getRecipes.
     * 
     * @return TreeMap<String,Recipe>
     */
    public final Map<String, Recipe> getRecipes() {
        return recipes;
    }

    // TODO remove/refactor so caller can't modify pcClasses
    /**
     * Method getPcClasses.
     * 
     * @return TreeMap<String,PcClass>
     */
    public Map<String, PcClass> getPcClasses() {
        return pcClasses;
    }

    // TODO remove/refactor so caller can't modify races
    /**
     * Method getRaces.
     * 
     * @return List<String>
     */
    public List<String> getRaces() {
        return races;
    }

    // TODO remove/refactor so caller can't modify sizes
    /**
     * Method getSizes.
     * 
     * @return List<String>
     */
    public List<String> getSizes() {
        return sizes;
    }

    // TODO remove/refactor so caller can't modify genders
    /**
     * Method getGenders.
     * 
     * @return List<String>
     */
    public List<String> getGenders() {
        return genders;
    }

    /**
     * Return the sprite sheet details.
     * 
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
     * @param filename
     *            String
     * @throws ConfigurationError
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
        Elements pcClassesElements = characterElement
                .getChildElements("pcClass");
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
        Element genderElement = characterElement
                .getFirstChildElement("genders");
        genders = xmlToIdList(genderElement.getChildElements());

        // races
        Element raceElement = characterElement.getFirstChildElement("races");
        races = xmlToIdList(raceElement.getChildElements());

        // sprite sheet details
        spriteSheets = xmlToSpriteSheets(root
                .getFirstChildElement("spriteSheets"));

    }

    /**
     * Load details about each Item class.
     * 
     * @param itemClassesElement
     * 
     * @return a collection of information about each Item type.
     */
    private Map<String,ItemClassConfiguration> xmlToItemClasses(
            Element itemClassesElement) {
        Elements itemClassElements = itemClassesElement
                .getChildElements("item");
        Map<String,ItemClassConfiguration> myItemClasses = new TreeMap<>();
        for (int i = 0; i < itemClassElements.size(); i++) {
            Element element = itemClassElements.get(i);
            ItemClassConfiguration itemClassConfiguration = new ItemClassConfiguration();
            String id = element.getAttributeValue("id");
            itemClassConfiguration.setId(id);
            // iconClosedId
            String iconClosedId = element.getAttributeValue("iconClosedId");
            if (null != iconClosedId) {
                itemClassConfiguration.setIconOpenId(Integer
                        .parseInt(iconClosedId));
            }
            // iconLeafId
            String iconLeafId = element.getAttributeValue("iconLeafId");
            if (null != iconLeafId) {
                itemClassConfiguration.setIconOpenId(Integer
                        .parseInt(iconLeafId));
            }
            // iconOpenId
            String iconOpenId = element.getAttributeValue("iconOpenId");
            if (null != iconOpenId) {
                itemClassConfiguration.setIconOpenId(Integer
                        .parseInt(iconOpenId));
            }
            // javaClass
            String javaClass = element.getAttributeValue("javaClass");
            if (null != javaClass) {
                itemClassConfiguration.setJavaClass(javaClass);
            }
            // Add item class to list
            myItemClasses.put(id,itemClassConfiguration);
        }
        return myItemClasses;
    }

    /**
     * A generic helper that converts a list of IDs to a string vector.
     * 
     * @param elements
     *            Parsed XML structure.
     * 
     * @return a List strings.
     */
    private List<String> xmlToIdList(Elements elements) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            list.add(elements.get(i).getAttributeValue("id"));
        }
        return list;
    }

    /**
     * Method xmlToPcClasses.
     * 
     * @param pcClassesElements
     *            Elements
     * @return TreeMap<String,PcClass>
     */
    private Map<String, PcClass> xmlToPcClasses(Elements pcClassesElements) {
        Map<String, PcClass> classes = new TreeMap<String, PcClass>();
        for (int current = 0; current < pcClassesElements.size(); current++) {
            PcClass pcClass = xmlToPcClass(pcClassesElements.get(current));
            classes.put(pcClass.getId(), pcClass);
        }
        return classes;
    }

    /**
     * Read in an XML list of Skill IDs and produce an ingredient object.<br>
     * <&lt;> skill id="fire lighting" /<&gt;>
     * 
     * @param entries
     *            XML list of Skill IDs
     * 
     * @return Set of Skill Objects.
     */
    private static Set<Skill> xmlToSkills(Elements entries) {
        Set<Skill> skills = new TreeSet<Skill>();

        for (int current = 0; current < entries.size(); current++) {
            skills.add(new Skill(entries.get(current).getAttributeValue("id")));
        }
        return skills;
    }

    /**
     * Read in an XML list of Items details and produce ingredient objects.<br>
     * 
     * <&lt;>item id="wood" consumed="yes" weightMin="3" /<&gt;>
     * 
     * @param entries
     *            XML list of Item details.
     * 
     * @return A map of RequirementItem objects, keyed by ID.
     */
    private static Map<String, Requirement> xmlToRecipeRequirementItems(
            Elements entries) {
        Map<String, Requirement> ingredients = new TreeMap<>();
        for (int current = 0; current < entries.size(); current++) {
            Element entry = entries.get(current);
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

            RequirementItem requirement = new RequirementItem(itemId, itemType,
                    consumed, weightMin);
            ingredients.put(itemId, requirement);
        }
        return ingredients;
    }

    /**
     * Read in an XML list of Recipe Product Items.<br>
     * 
     * @param entries
     *            XML list of Item details.
     * 
     * @return A list of Recipe Product objects.
     */
    private static List<Product> xmlToRecipeProductItems(Elements entries) {
        List<Product> products = new ArrayList<Product>();
        for (int current = 0; current < entries.size(); current++) {
            Element entry = entries.get(current);
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
     * @param entries
     *            XML list of Property details.
     * 
     * @return A list of Recipe Product objects.
     */
    private static List<Product> xmlToRecipeProductEntityProperties(
            Elements entries) {
        List<Product> products = new ArrayList<Product>();
        for (int current = 0; current < entries.size(); current++) {
            Element entry = entries.get(current);
            String id = entry.getAttributeValue("id");
            String propertyName = entry.getAttributeValue("propertyName");
            if (null == propertyName) {
                propertyName = id;
            }
            String propertyDeltaString = entry
                    .getAttributeValue("propertyDelta");
            float propertyDelta = 0;
            if (null != propertyDeltaString) {
                propertyDelta = Float.parseFloat(propertyDeltaString);
            }
            ProductEntityProperty product = new ProductEntityProperty(id,
                    propertyName, propertyDelta);
            products.add(product);
        }
        return products;
    }

    /**
     * Read in XML Recipe Product details and produce Recipe Product object(s).<br>
     * 
     * @param entry
     *            an XML Recipe Product element.
     * @return List<Product>
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
     * @param entry
     *            an XML Requirement element.
     * @return List<Requirement>
     */
    private static Map<String,Requirement> xmlToRecipeRequirements(Element entry) {
        Elements items = entry.getChildElements("item");
        // TODO Config Recipe locations e.g. <location id="ground" />
        // Elements locations = entry.getChildElements("location");

        return xmlToRecipeRequirementItems(items);
    }

    /**
     * Convert an XML structure into a Recipe object.
     * 
     * @param recipeElement
     *            XML details of a Recipe.
     * 
     * @return a Recipe object.
     */
    private static Recipe xmlToRecipe(Element recipeElement) {
        String id = recipeElement.getAttributeValue("id");
        String description = recipeElement.getAttributeValue("description");

        // <ingredient mana="2" actionPoints="42">
        // mana
        int mana = 0;
        Attribute manaAttribute = recipeElement.getAttribute("mana");
        if (null != manaAttribute) {
            mana = Integer.parseInt(manaAttribute.getValue());
        }
        // actionPoints
        int actionPoints = 0;
        Attribute actionPointsAttribute = recipeElement
                .getAttribute("actionPoints");
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
        Element requirementsElement = recipeElement
                .getFirstChildElement("requirements");
        Map<String,Requirement> requirements = xmlToRecipeRequirements(requirementsElement);

        // Product objects.
        Element productElement = recipeElement.getFirstChildElement("products");
        List<Product> products = xmlToRecipeProducts(productElement);

        // Return the completed recipe
        return new Recipe(id, description, process, mana, actionPoints,
                requirements, skills, products);
    }

    /**
     * Read multiple recipes from an XML file.
     * 
     * @param element
     *            XML element containing recipes.
     * 
     * 
     * 
     * @return A set of Recipe objects. * @throws ParsingException * @throws
     *         IOException
     */
    private static Map<String, Recipe> xmlToRecipes(Element element) {

        Elements recipeElementSet = element.getChildElements("recipe");
        Map<String, Recipe> recipes = new TreeMap<String, Recipe>();
        for (int recipeCurrent = 0; recipeCurrent < recipeElementSet.size(); recipeCurrent++) {
            // get current Recipe
            Element recipeElement = recipeElementSet.get(recipeCurrent);
            Recipe recipe = xmlToRecipe(recipeElement);
            recipes.put(recipe.getId(), recipe);
        }
        return recipes;
    }

    /**
     * Read multiple SkillDetail objects from an XML element.
     * 
     * @param element
     * 
     * @return TreeMap of SkillDetail objects.
     */
    private static Map<String, SkillDetail> xmlToSkillDetails(
            Element element) {
        Elements entries = element.getChildElements("skill");
        Map<String, SkillDetail> skillDetails = new TreeMap<String, SkillDetail>();
        for (int current = 0; current < entries.size(); current++) {
            // get current Skill
            Element entry = entries.get(current);
            SkillDetail pw = new SkillDetail(entry.getAttributeValue("id"),
                    entry.getAttributeValue("description"));
            skillDetails.put(entry.getAttributeValue("id"), pw);
        }
        return skillDetails;
    }

    /**
     * Read one PcClass object from an XML element.
     * 
     * @param element
     *            XML element.
     * 
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
        Elements abilityScores = element.getChildElements("ability");
        for (int i = 0; i < abilityScores.size(); i++) {
            pcClass.setAbilityScore(xmlToAbilityScore(abilityScores.get(i)));
        }

        return pcClass;
    }

    /**
     * Get an AbilityScore object from an XML element.
     * 
     * @param element
     * 
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
     * 
     * 
     * 
     * @param spriteSheets
     *            Element
     * @return TreeMap<String,SpriteSheetConfiguration>
     */
    private Map<String, SpriteSheetConfiguration> xmlToSpriteSheets(
            Element spriteSheets) {
        Elements entries = spriteSheets.getChildElements();
        Map<String, SpriteSheetConfiguration> mySpriteSheets = new TreeMap<String, SpriteSheetConfiguration>();
        for (int current = 0; current < entries.size(); current++) {
            Element entry = entries.get(current);
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
     * @param id
     *            the class name e.g. Soldier
     * 
     * @return the PC Character Class.
     */
    public PcClass getPcClass(String id) {
        return pcClasses.get(id);
    }

    // misc

    /**
     * Get the Recipe object with this recipeId.
     * 
     * @param recipeId
     *            the id of the Recipe
     * 
     * @return the Recipe object
     */
    public Recipe getRecipe(String recipeId) {
        return recipes.get(recipeId);
    }

    /**
     * Return the sprite sheet details object with the supplied name.
     * 
     * @param name
     *            name of sprite sheet.
     * 
     * @return the sprite sheet details
     */
    SpriteSheetConfiguration getSpriteSheet(String name) {
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
