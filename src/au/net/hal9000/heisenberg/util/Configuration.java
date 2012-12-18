package au.net.hal9000.heisenberg.util;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Set;
import java.util.TreeSet;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import au.net.hal9000.heisenberg.crafting.Requirement;
import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.units.PowerWord;
import au.net.hal9000.heisenberg.units.PowerWordDetail;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.units.SkillDetail;

public class Configuration {

    private static Configuration lastConfig = null; // Singleton
    private Vector<String> itemClasses;
    private TreeMap<String, SkillDetail> skillDetails;
    private TreeMap<String, PowerWordDetail> powerWordDetails;
    private TreeMap<String, Recipe> recipes;
    private TreeMap<String, PcClass> pcClasses;
    // TODO private TreeMap<String,PcClass> npcClasses;
    private Vector<String> races;
    private Vector<String> genders;

    public Configuration(String filename) throws ValidityException,
            IOException, Exception {
        super();
        this.init(filename);
        lastConfig = this;
    }
    
    public static Configuration lastConfig(){
        if (lastConfig == null){
            throw new RuntimeException("Please fetch config before using Singleton");
        }
        return lastConfig;
    }

    // Getters and Setters
    
    
    /**
     * @return the itemClasses
     */
    public final Vector<String> getItemClasses() {
        return itemClasses;
    }

    public final TreeMap<String, SkillDetail> getSkillDetails() {
        return skillDetails;
    }

    public TreeMap<String, PowerWordDetail> getPowerWordDetails() {
        return powerWordDetails;
    }

    public final TreeMap<String, Recipe> getRecipes() {
        return recipes;
    }

    // init
    private void init(String filename) throws ValidityException, Exception,
            IOException {
        File file = new File(filename);

        Builder builder = new Builder();
        Document doc = builder.build(file);
        Element root = doc.getRootElement();

        // powerWord entries
        Element powerWordsElement = root.getFirstChildElement("powerWords");
        powerWordDetails = xmlToPowerWordDetails(powerWordsElement);

        // skill entries
        Element skillsElement = root.getFirstChildElement("skills");
        skillDetails = xmlToSkillDetails(skillsElement);

        // Item Classes
        Element itemClassesElement = root.getFirstChildElement("items");
        itemClasses = xmlToIdList(itemClassesElement.getChildElements());

        // recipes
        Element recipesElement = root.getFirstChildElement("recipes");
        recipes = xmlToRecipes(recipesElement);

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
        // character - genders
        Element genderElement = characterElement
                .getFirstChildElement("genders");
        genders = xmlToIdList(genderElement.getChildElements());
        Element raceElement = characterElement.getFirstChildElement("races");
        races = xmlToIdList(raceElement.getChildElements());

    }

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
     * Read in an XML list of PowerWord IDs and produce an ingredient object.<br>
     * <&lt;> powerWord id="fire lighting" /<&gt;>
     * 
     * @param entries
     *            XML list of PowerWord IDs
     * @return Set of PowerWord Objects.
     */
    public static Set<PowerWord> xmlToPowerWords(Elements entries) {
        Set<PowerWord> powerWords = new TreeSet<PowerWord>();

        for (int current = 0; current < entries.size(); current++) {
            powerWords.add(new PowerWord(entries.get(current)
                    .getAttributeValue("id")));
        }
        return powerWords;
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
    public static Vector<RequirementItem> xmlToIngredientItem(Elements entries) {
        Vector<RequirementItem> ingredients = new Vector<RequirementItem>();
        for (int current = 0; current < entries.size(); current++) {
            // get current ingredient
            Item item = Factory.createItem(entries.get(current)
                    .getAttributeValue("id").toString());
            RequirementItem iItem = new RequirementItem(item);
            ingredients.add(iItem);
        }
        return ingredients;
    }

    /**
     * Read in an XML list of Recipe Products<br>
     * 
     * @param entries
     *            XML list of Item details.
     * @return A list of Recipe Product objects.
     */
    // TODO Recipe Products are Strings for now, but should have more
    // attributes.
    public static Vector<String> xmlToRecipeProduct(Elements entries) {
        Vector<String> ingredients = new Vector<String>();
        for (int current = 0; current < entries.size(); current++) {
            // get current ingredient
            ingredients.add(entries.get(current).getAttributeValue("id")
                    .toString());
        }
        return ingredients;
    }

    /**
     * Read in XML Recipe Product details and produce Recipe Product object(s).<br>
     * 
     * @param entry
     *            an XML Recipe Product element.
     */
    public static Vector<String> xmlToRecipeProducts(Element entry) {
        Vector<String> products = new Vector<String>();

        Elements items = entry.getChildElements("item");
        products.addAll(xmlToRecipeProduct(items));
        return products;
    }

    /**
     * Read in XML Requirement details and produce Requirement object(s).<br>
     * 
     * @param entry
     *            an XML Requirement element.
     */
    public static Vector<Requirement> xmlToIngredients(Element entry) {
        Vector<Requirement> requirements = new Vector<Requirement>();

        Elements items = entry.getChildElements("item");
        requirements.addAll(xmlToIngredientItem(items));

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
        Attribute processAttribute = recipeElement
                .getAttribute("process");
        if (processAttribute != null) {
            process = processAttribute.getValue();
        }
        
        // PowerWords
        Set<PowerWord> powerWords = new TreeSet<PowerWord>();
        Elements powerWordElements = recipeElement
                .getChildElements("powerWord");
        powerWords.addAll(xmlToPowerWords(powerWordElements));

        // Skills
        Set<Skill> skills = new TreeSet<Skill>();
        Elements skillElements = recipeElement.getChildElements("skill");
        skills.addAll(xmlToSkills(skillElements));

        // Ingredients
        Vector<Requirement> requirements = new Vector<Requirement>();
        Elements ingredientElements = recipeElement
                .getChildElements("ingredients");
        for (int current = 0; current < ingredientElements.size(); current++) {
            requirements
                    .addAll(xmlToIngredients(ingredientElements.get(current)));
        }

        // Add Product items.
        Vector<String> products = new Vector<String>();
        Elements productElements = recipeElement.getChildElements("products");
        for (int current = 0; current < productElements.size(); current++) {
            products.addAll(xmlToRecipeProducts(productElements.get(current)));
        }

        // Return the completed recipe
        return new Recipe(id, description, process, mana, actionPoints,
                requirements, powerWords, skills, products);

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
    public static TreeMap<String, Recipe> xmlToRecipes(Element element)
            throws ParsingException, IOException {

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
     * Read multiple PowerWordDetail objects from an XML element.
     * 
     * @param element
     * @return TreeMap of PowerWordDetail objects.
     */
    private static TreeMap<String, PowerWordDetail> xmlToPowerWordDetails(
            Element element) {
        Elements entries = element.getChildElements("powerWord");
        TreeMap<String, PowerWordDetail> skillDetails = new TreeMap<String, PowerWordDetail>();
        for (int current = 0; current < entries.size(); current++) {
            // get current PowerWord
            Element entry = entries.get(current);
            PowerWordDetail pw = new PowerWordDetail(
                    entry.getAttributeValue("id"),
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
     * @param id
     *            the class name e.g. Soldier
     * @return the PC Character Class.
     */
    public PcClass getPcClass(String id) {
        return pcClasses.get(id);
    }

    // TODO remove/refactor so caller can't modify pcClasses
    public TreeMap<String, PcClass> getPcClasses() {
        return pcClasses;
    }

    // TODO remove/refactor so caller can't modify races
    public Vector<String> getRaces() {
        return races;
    }

    // TODO remove/refactor so caller can't modify genders
    public Vector<String> getGenders() {
        return genders;
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

    public String[] getItems() {
        // TODO Auto-generated method stub
        return null;
    }

}
