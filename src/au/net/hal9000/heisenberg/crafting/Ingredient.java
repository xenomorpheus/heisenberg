package au.net.hal9000.heisenberg.crafting;

/**
 * 
 * An Ingredient object hold details about a requirement for a Recipe objects.
 * A Recipe may have any number of Ingredient objects.
 * 
 * e.g.<ul>
 * <li> Item - Wood with a minimum of 3 weight units.
 * <li> Item - FlintAndTinder, not consumed.
 * <ul>
 * 
 * @author bruins
 *
 */
public abstract class Ingredient {

	/**
	 * @return Return a description of the object.
	 */
	abstract String getDescription();

}
