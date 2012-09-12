package au.net.hal9000.heisenberg.item;

import java.util.Vector;
import java.util.Set;
import java.util.TreeSet;

import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.exception.ExceptionInvalidType;
import au.net.hal9000.heisenberg.item.property.Clothing;
import au.net.hal9000.heisenberg.item.property.HumanoidFood;
import au.net.hal9000.heisenberg.item.property.Living;
import au.net.hal9000.heisenberg.units.*;

/**
 * Being is the bases of conscious entities.
 * 
 * @author bruins
 * 
 */

public abstract class Being extends ItemContainer implements Living {
	private static final long serialVersionUID = 1L;

	// Fields
	/**
	 * The {@link Recipe} list that is known by this Being.
	 */
	private Vector<Recipe> recipes = new Vector<Recipe>();
	/**
	 * A measure of the amount of things the entity can do in the current round.
	 * This is the remaining, not the maximum.
	 */
	private int actionPoints = 0;
	/**
	 * The size of the magic user's fuel tank. This is the remaining, not the
	 * maximum.
	 */
	private int mana = 0;
	/**
	 * The {@link Skill} objects required.
	 */
	private Set<Skill> skills = new TreeSet<Skill>();

	// Constructor
	public Being(String pName) {
		super(pName);
	}

	// Methods

	// Getters and Setters

	// recipes
	public Vector<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Vector<Recipe> recipes) {
		this.recipes = recipes;
	}

	// actionPoints
	public int getActionPoints() {
		return actionPoints;
	}

	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}

	public void actionPointsAdjust(int adjust) {
		actionPoints += adjust;
	}

	// mana
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void manaAdjust(int adjust) {
		mana += adjust;

	}

	// skills
	/**
	 * Get the Skill objects.
	 * 
	 * @return a set of Skill objects
	 */
	public final Set<Skill> getSkills() {
		return skills;
	}

	public final void setSkills(final Set<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * Set the PowerWords
	 * 
	 * @param newSkills
	 *            list of powerWord IDs as Strings.
	 */
	public void setSkills(final String[] newSkills) {
		skills.clear();
		skillsAdd(newSkills);
	}

	/**
	 * Add extra Skills to the list of required ingredients.
	 * 
	 * @param skills
	 */
	public final void skillsAdd(final Set<Skill> skills) {
		skills.addAll(skills);
	}

	/**
	 * Add extra PowerWords to the list of required ingredients.
	 * 
	 * @param powerWords
	 *            a Set of PowerWord objects to add.
	 */
	public final void skillsAddAll(final Set<Skill> powerWords) {
		powerWords.addAll(powerWords);
	}

	/**
	 * Add extra Skills to the list of required ingredients.
	 * 
	 * @param newSkills
	 */
	public final void skillsAdd(final String[] newSkills) {
		for (int i = newSkills.length - 1; i >= 0; i--) {
			skills.add(new Skill(newSkills[i]));
		}
	}

	// Misc

	/**
	 * Wear an item
	 * 
	 * @param item
	 * @throws ExceptionInvalidType
	 */
	public void add(IItem item) throws ExceptionInvalidType {
		if (!(item instanceof Clothing)) {
			throw new ExceptionInvalidType(this.getName() + " can't wear "
					+ item.getName());
		}
	}

	/**
	 * Eat an item
	 * 
	 * @param pFood
	 * @throws ExceptionInvalidType
	 */
	public void eat(IItem pFood) throws ExceptionInvalidType {
		if (!(pFood instanceof HumanoidFood)) {
			throw new ExceptionInvalidType(this.getName() + " can't eat "
					+ pFood.getName());
		}
		pFood.beNot();
	}

	// Find items that match the criteria
	/** {@inheritDoc} */
	@Override
	public void accept(ItemVisitor visitor) {
		// TODO visit equipment then super
		visitor.visit(this);
	}

	public Item search(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

}
