package au.net.hal9000.heisenberg.util;

import java.io.File;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Set;
import java.util.TreeSet;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import au.net.hal9000.heisenberg.crafting.ProductEntityProperty;
import au.net.hal9000.heisenberg.crafting.Requirement;
import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.crafting.Product;
import au.net.hal9000.heisenberg.crafting.ProductItem;
import au.net.hal9000.heisenberg.crafting.Recipe;
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
 */

public class Configuration {

    /** Singleton. Holder of the config */
    private static Configuration lastConfig = null;
    // Object
    private Vector<String> genders;
    private Vector<ItemClassConfiguration> itemClasses;
    // TODO private TreeMap<String,PcClass> npcClasses;
    private TreeMap<String, PcClass> pcClasses;
    private Vector<String> races;
    private TreeMap<String, Recipe> recipes;
    private TreeMap<String, SkillDetail> skillDetails;
    private Vector<String> sizes;
    private TreeMap<String, SpriteSheetConfiguration> spriteSheets;

    // Constructor
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

    public static Configuration lastConfig() {
        if (lastConfig == null) {
            throw new RuntimeException(
                    "Please fetch config before using Singleton");
        }
        return lastConfig;
    }

    // Getters and Setters
    /**
     * @return the itemClasses
     */
    public final Vector<ItemClassConfiguration> getItemClasses() {
        return itemClasses;
    }

    /**
     * get skill details.
     * @return skill details.
     */
    public final TreeMap<String, SkillDetail> getSkillDetails() {
        return skillDetails;
    }

    public final TreeMap<String, Recipe> getRecipes() {
        return recipes;
    }

    // TODO remove/refactor so caller can't modify pcClasses
    public TreeMap<String, PcClass> getPcClasses() {
        return pcClasses;
    }

    // TODO remove/refactor so caller can't modify races
    public Vector<String> getRaces() {
        return races;
    }

    // TODO remove/refactor so caller can't modify sizes
    public Vector<String> getSizes() {
        return sizes;
    }

    // TODO remove/refactor so caller can't modify genders
    public Vector<String> getGenders() {
        return genders;
    }

    /**
     * Return the sprite sheet details.
     * 
     * @return the sprite sheet details
     */
    public TreeMap<String, SpriteSheetConfiguration> getSpriteSheets() {
        return spriteSheets;
    }

    // Misc
    private void init(String filename) throws ConfigurationError {
        File file = new File(filename);

        Builder builder = new Builder();
        Document doc;
        try {
            doc = builder.build(file);
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
     * @return a collection of information about each Item type.
     */
    private Vector<ItemClassConfiguration> xmlToItemClasses(
            Element itemClassesElement) {
        Elements itemClassElements = itemClassesElement
                .getChildElements("item");
        Vector<ItemClassConfiguration> myItemClasses = new Vector<ItemClassConfiguration>();
        for (int i = 0; i < itemClassElements.size(); i++) {
            Element element = itemClassElements.get(i);
            ItemClassConfiguration itemClassConfiguration = new ItemClassConfiguration();
            String id = element.getAttributeValue("id");
            itemClassConfiguration.setId(id);
            // iconClosedId
            String iconClosedId = element.getAttributeValue("iconClosedId");
            if (iconClosedId != null) {
                itemClassConfiguration.setIconOpenId(Integer
                        .parseInt(iconClosedId));
            }
            // iconLeafId
            String iconLeafId = element.getAttributeValue("iconLeafId");
            if (iconLeafId != null) {
                itemClassConfiguration.setIconOpenId(Integer
                        .parseInt(iconLeafId));
            }
            // iconOpenId
            String iconOpenId = element.getAttributeValue("iconOpenId");
            if (iconOpenId != null) {
                itemClassConfiguration.setIconOpenId(Integer
                        .parseInt(iconOpenId));
            }
            myItemClasses.add(itemClassConfiguration);
        }
        return myItemClasses;
    }

    /**
     * A generic helper that converts a list of IDs to a string vector.
     * 
     * @param elements
     *            Parsed XML structure.
     * @return a Vector strings.
     */
    private Vector<String> xmlToIdList(Elements elements) {
        Vector<String> list = new Vector<String>();
        for (int i = 0; i < elements.size(); i++) {
            list.add(elements.get(i).getAttributeValue("id").toString());
        }
        return list;
    }

    private TreeMap<String, PcClass> xmlToPcClasses(Elements pcClassesElements) {
        TreeMap<String, PcClass> classes = new TreeMap<String, PcClass>();
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
     * @return Set of Skill Objects.
     */
    public static Set<Skill> xmlToSkills(Elements entries) {
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
     * @return A list of RequirementItem objects.
     */
    public static Vector<RequirementItem> xmlToRecipeRequirementItems(
            Elements entries) {
        Vector<RequirementItem> ingredients = new Vector<RequirementItem>();
        for (int current = 0; current < entries.size(); current++) {
            Element entry = entries.get(current);
            String itemId = entry.getAttributeValue("id");
            // type
            String itemType = entry.getAttributeValue("type");
            if (itemType == null) {
                itemType = itemId;
            }
            // consumed
            String consumedString = entry.getAttributeValue("consumed");
            boolean consumed = true;
            if (consumedString != null) {
                consumed = Boolean.parseBoolean(consumedString);
            }
            // weightMin
            float weightMin = 0;
            String weightMinString = entry.getAttributeValue("weightMin");
            if (weightMinString != null) {
                weightMin = Float.parseFloat(weightMinString);
            }

            RequirementItem requirement = new RequirementItem(itemId, itemType,
                    consumed, weightMin);
            ingredients.add(requirement);
        }
        return ingredients;
    }

    /**
     * Read in an XML list of Recipe Product Items<br>
     * 
     * @param entries
     *            XML list of Item details.
     * @return A list of Recipe Product objects.
     */
    public static Vector<Product> xmlToRecipeProductItems(Elements entries) {
        Vector<Product> products = new Vector<Product>();
        for (int current = 0; current < entries.size(); current++) {
            Element entry = entries.get(current);
            String id = entry.getAttributeValue("id");
            String type = entry.getAttributeValue("itemType");
            if (type == null) {
                type = id;
            }
            String weightBaseString = entry.getAttributeValue("weightBase");
            float weightBase = 0;
            if (weightBaseString != null) {
                weightBase = Float.parseFloat(weightBaseString);
            }
            ProductItem product = new ProductItem(id, type, weightBase);
            products.add(product);
        }
        return products;
    }

    /**
     * Read in an XML list of Recipe Product Entity Properties<br>
     * 
     * @param entries
     *            XML list of Property details.
     * @return A list of Recipe Product objects.
     */
    public static Vector<Product> xmlToRecipeProductEntityProperties(
            Elements entries) {
        Vector<Product> products = new Vector<Product>();
        for (int current = 0; current < entries.size(); current++) {
            Element entry = entries.get(current);
            String id = entry.getAttributeValue("id");
            String propertyName = entry.getAttributeValue("propertyName");
            if (propertyName == null) {
                propertyName = id;
            }
            String propertyDeltaString = entry
                    .getAttributeValue("propertyDelta");
            float propertyDelta = 0;
            if (propertyDeltaString != null) {
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
     */
    public static Vector<Product> xmlToRecipeProducts(Element entry) {

        Vector<Product> products = new Vector<Product>();
        if (entry != null) {
            Elements items = entry.getChildElements("item");
            if (items != null) {
                products.addAll(xmlToRecipeProductItems(items));
            }

            Elements properties = entry.getChildElements("property");
            if (properties != null) {
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
     */
    public static Vector<Requirement> xmlToRecipeRequirements(Element entry) {
        Vector<Requirement> requirements = new Vector<Requirement>();

        Elements items = entry.getChildElements("item");
        requirements.addAll(xmlToRecipeRequirementItems(items));

        // TODO Config Recipe locations e.g. <location id="ground" />
        // Elements locations = entry.getChildElements("location");

        return requirements;
    }

    /**
     * Convert an XML structure into a Recipe object.
     * 
     * @param recipeElement
     *            XML details of a Recipe.
     * @return a Recipe object.
     */
    public static Recipe xmlToRecipe(Element recipeElement) {
        String id = recipeElement.getAttributeValue("id");
        String description = recipeElement.getAttributeValue("description");

        // <ingredient mana="2" actionPoints="42">
        // mana
        int mana = 0;
        Attribute manaAttribute = recipeElement.getAttribute("mana");
        if (manaAttribute != null) {
            mana = Integer.parseInt(manaAttribute.getValue());
        }
        // actionPoints
        int actionPoints = 0;
        Attribute actionPointsAttribute = recipeElement
                .getAttribute("actionPoints");
        if (actionPointsAttribute != null) {
            actionPoints = Integer.parseInt(actionPointsAttribute.getValue());
        }
        // process
        String process = null;
        Attribute processAttribute = recipeElement.getAttribute("process");
        if (processAttribute != null) {
            process = processAttribute.getValue();
        }

        // Skills
        Set<Skill> skills = new TreeSet<Skill>();
        Elements skillElements = recipeElement.getChildElements("skill");
        skills.addAll(xmlToSkills(skillElements));

        // Requirement objects
        Element requirementsElement = recipeElement
                .getFirstChildElement("requirements");
        Vector<Requirement> requirements = xmlToRecipeRequirements(requirementsElement);

        // Product objects.
        Element productElement = recipeElement.getFirstChildElement("products");
        Vector<Product> products = xmlToRecipeProducts(productElement);

        // Return the completed recipe
        return new Recipe(id, description, process, mana, actionPoints,
                requirements, skills, products);
    }

    /**
     * Read multiple recipes from an XML file.
     * 
     * @param element
     *            XML element containing recipes.
     * @return A set of Recipe objects.
     * @throws ParsingException
     * @throws IOException
     */
    public static TreeMap<String, Recipe> xmlToRecipes(Element element) {

        Elements recipeElementSet = element.getChildElements("recipe");
        TreeMap<String, Recipe> Recipes = new TreeMap<String, Recipe>();
        for (int recipeCurrent = 0; recipeCurrent < recipeElementSet.size(); recipeCurrent++) {
            // get current Recipe
            Element recipeElement = recipeElementSet.get(recipeCurrent);
            Recipe recipe = xmlToRecipe(recipeElement);
            Recipes.put(recipe.getId(), recipe);
        }
        return Recipes;
    }

    /**
     * Read multiple SkillDetail objects from an XML element.
     * 
     * @param element
     * @return TreeMap of SkillDetail objects.
     */
    private static TreeMap<String, SkillDetail> xmlToSkillDetails(
            Element element) {
        Elements entries = element.getChildElements("skill");
        TreeMap<String, SkillDetail> skillDetails = new TreeMap<String, SkillDetail>();
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
     * @return a PcClass object.
     */
    private static PcClass xmlToPcClass(Element element) {
        PcClass pcClass = new PcClass();

        // id
        String id = element.getAttributeValue("id");
        if (id == null) {
            throw new IllegalArgumentException("id must be set");
        }
        pcClass.setId(id);

        // combatDice
        String combatDice = element.getAttributeValue("combatDice");
        if (combatDice == null) {
            throw new IllegalArgumentException("combatDice must be set");
        }
        pcClass.setCombatDice(Integer.parseInt(combatDice));

        // magicDice
        String magicDice = element.getAttributeValue("magicDice");
        if (magicDice == null) {
            throw new IllegalArgumentException("magicDice must be set");
        }
        pcClass.setMagicDice(Integer.parseInt(magicDice));
        // stealthDice
        String stealthDice = element.getAttributeValue("stealthDice");
        if (stealthDice == null) {
            throw new IllegalArgumentException("stealthDice must be set");
        }
        pcClass.setStealthDice(Integer.parseInt(stealthDice));
        // generalDice
        String generalDice = element.getAttributeValue("generalDice");
        if (generalDice == null) {
            throw new IllegalArgumentException("generalDice must be set");
        }
        pcClass.setGeneralDice(Integer.parseInt(generalDice));
        // encumbrance
        String encumbrance = element.getAttributeValue("encumbrance");
        if (encumbrance == null) {
            throw new IllegalArgumentException("encumbrance must be set");
        }
        pcClass.setEncumbrance(Integer.parseInt(encumbrance));
        // mana
        String mana = element.getAttributeValue("mana");
        if (mana == null) {
            throw new IllegalArgumentException("mana must be set");
        }
        pcClass.setMana(Integer.parseInt(mana));
        // actionPoint
        String actionPoint = element.getAttributeValue("actionPoint");
        if (actionPoint == null) {
            throw new IllegalArgumentException("actionPoint must be set");
        }
        pcClass.setActionPoints(Integer.parseInt(actionPoint));
        // health
        String health = element.getAttributeValue("health");
        if (health == null) {
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
     * @return the AbilityScore object.
     */
    private static AbilityScore xmlToAbilityScore(Element element) {

        String id = element.getAttributeValue("id");
        if (id == null) {
            throw new IllegalArgumentException("id missing");
        }
        String value = element.getAttributeValue("value");
        if (value == null) {
            throw new IllegalArgumentException("value missing");
        }
        return new AbilityScore(id, value);
    }

    /**
     * 
     * @param childElements
     * @return
     */
    private TreeMap<String, SpriteSheetConfiguration> xmlToSpriteSheets(
            Element spriteSheets) {
        Elements entries = spriteSheets.getChildElements();
        TreeMap<String, SpriteSheetConfiguration> mySpriteSheets = new TreeMap<String, SpriteSheetConfiguration>();
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
     * @return the PC Character Class.
     */
    public PcClass getPcClass(String id) {
        return pcClasses.get(id);
    }

    // misc

    /**
     * Get the Recipe object with this recipeId
     * 
     * @param recipeId
     *            the id of the Recipe
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
     * @return the sprite sheet details
     */
    public SpriteSheetConfiguration getSpriteSheet(String name) {
        return spriteSheets.get(name);
    }

    public Vector<String> getItemClassIds() {
        Vector<String> itemClassIds = new Vector<String>();
        for (ItemClassConfiguration itemClassConfiguration : itemClasses) {
            itemClassIds.add(itemClassConfiguration.getId());
        }
        return itemClassIds;
    }

}
