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
import au.net.hal9000.heisenberg.crafting.Ingredient;
import au.net.hal9000.heisenberg.crafting.IngredientItem;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.units.SkillDetail;

public class Configuration {

	private Vector<SkillDetail> skillDetails;
	private Vector<Recipe> recipes;
	private TreeMap<String, PcRace> pcClasses;
	// TODO private TreeMap<String,PcClass> npcClasses;
	private Vector<String> races;
	private Vector<String> genders;

	public Configuration(String filename) throws ValidityException,
			IOException, Exception {
		super();
		this.init(filename);
	}

	// Getters and Setters
	public final Vector<SkillDetail> getSkillDetails() {
		return skillDetails;
	}

	public final Vector<Recipe> getRecipes() {
		return recipes;
	}

	// init
	private void init(String filename) throws ValidityException, Exception,
			IOException {
		File file = new File(filename);

		Builder builder = new Builder();
		Document doc = builder.build(file);
		Element root = doc.getRootElement();

		// skills
		Element skillsElement = root.getFirstChildElement("skills");
		skillDetails = xmlToSkillDetails(skillsElement);
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
		//		.getChildElements("npcClass");
		// TODO npcClasses = xmlToNpcClasses(npcClassesElements);
		// character - traits
		// TODO Element traitElements = characterElement.getFirstChildElement("traits");
		// TODO traits = xmlToTraits(traitElements);
		// character - genders
		Element genderElement = characterElement
				.getFirstChildElement("genders");
		genders = xmlToIdList(genderElement.getChildElements());
		Element raceElement = characterElement
				.getFirstChildElement("races");
		races = xmlToIdList(raceElement.getChildElements());
	}

	private Vector<String> xmlToIdList(Elements elements) {
		Vector<String> list = new Vector<String>();
		for (int i = 0; i < elements.size() ; i++){
			list.add(elements.get(i).getAttributeValue("id").toString());
		}
		return list;
	}

	private TreeMap<String, PcRace> xmlToPcClasses(Elements pcClassesElements) {
		TreeMap<String, PcRace> classes = new TreeMap<String, PcRace>();
		for (int current = 0; current < pcClassesElements.size(); current++) {
			PcRace pcClass = xmlToPcClass(pcClassesElements.get(current));
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
	 * @return A list of IngredientItem objects.
	 */
	public static Vector<IngredientItem> xmlToIngredientItem(Elements entries) {
		Vector<IngredientItem> ingredients = new Vector<IngredientItem>();
		for (int current = 0; current < entries.size(); current++) {
			// get current ingredient
			Item item = Factory.createItem(entries.get(current)
					.getAttributeValue("id").toString());
			IngredientItem iItem = new IngredientItem(item);
			ingredients.add(iItem);
		}
		return ingredients;
	}

	/**
	 * Read in XML Ingredient details and produce Ingredient object(s).<br>
	 * 
	 * @param entry
	 *            an XML Ingredient element.
	 */
	public static Vector<Ingredient> xmlToIngredients(Element entry) {
		Vector<Ingredient> ingredients = new Vector<Ingredient>();

		Elements items = entry.getChildElements("item");
		ingredients.addAll(xmlToIngredientItem(items));

		// TODO locations
		// TODO <&lt;>location id="ground" /<&gt;>
		// Elements locations = entry.getChildElements("location");

		return ingredients;
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
		// Skills
		Set<Skill> skills = new TreeSet<Skill>();
		Elements skillElements = recipeElement.getChildElements("skill");
		skills.addAll(xmlToSkills(skillElements));

		// Ingredients
		Vector<Ingredient> ingredients = new Vector<Ingredient>();
		Elements ingredientElements = recipeElement
				.getChildElements("ingredients");
		for (int current = 0; current < ingredientElements.size(); current++) {
			ingredients
					.addAll(xmlToIngredients(ingredientElements.get(current)));
		}

		// TODO Add Product items.
		Vector<String> products = null;

		return new Recipe(id, description, mana, actionPoints, ingredients,
				skills, products);

	}

	/**
	 * Read multiple recipes from an XML file.
	 * 
	 * @param file
	 *            XML file containing recipes.
	 * @return a list of recipe objects.
	 * @throws ParsingException
	 * @throws IOException
	 */
	public static Vector<Recipe> xmlToRecipes(Element recipes)
			throws ParsingException, IOException {

		Elements recipeElementSet = recipes.getChildElements("recipe");
		Vector<Recipe> Recipes = new Vector<Recipe>();
		for (int recipeCurrent = 0; recipeCurrent < recipeElementSet.size(); recipeCurrent++) {
			// get current Recipe
			Element recipeElement = recipeElementSet.get(recipeCurrent);
			Recipe recipe = xmlToRecipe(recipeElement);
			Recipes.add(recipe);
		}
		return Recipes;
	}

	private static Vector<SkillDetail> xmlToSkillDetails(Element element) {
		Elements entries = element.getChildElements("skill");
		Vector<SkillDetail> skillDetails = new Vector<SkillDetail>();
		for (int current = 0; current < entries.size(); current++) {
			// get current Skill
			Element entry = entries.get(current);
			SkillDetail pw = new SkillDetail(entry.getAttributeValue("id"),
					entry.getAttributeValue("description"));
			skillDetails.add(pw);
		}
		return skillDetails;
	}

	private static PcRace xmlToPcClass(Element element) {
		PcRace pcClass = new PcRace();

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
	public PcRace getPcClass(String id) {
		return pcClasses.get(id);
	}

	// TODO remove/refactor so caller can't modify pcClasses
	public TreeMap<String, PcRace> getPcClasses() {
		return pcClasses;
	}

	// TODO read from config.
	public Vector<String> getRaces() {
		return races;
	}

	// TODO read from config.
	public Vector<String> getGenders() {
		return genders;
	}

	// misc
	public String description() {
		// skills
		String string = "Skills:\n";
		for (SkillDetail skillDetail : skillDetails) {
			string = string.concat("  " + skillDetail.toString() + "\n");
		}

		// recipes
		string = string.concat("Recipes:\n");
		for (Recipe recipe : recipes) {
			string = string.concat("Recipe:\n" + recipe.toString() + "\n");
		}
		return string;
	}

}
