package au.net.hal9000.player.units;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 * Recipes are recipes for magic.
 * 
 * <P>
 * Recipes are comprised of a list of Ingredients ({@link Ingredient}).
 * <P>
 * 
 * <P>
 * In order to perform a Recipe, both the Recipe and all the Ingredients for it
 * must be known.
 * </P>
 * 
 * <P>
 * Typically the more complex the recipe, the more ingredients that are required.
 * </P>
 * 
 * <P>
 * Developer Notes: Lets try making Recipes mutable and see how it goes
 * </P>
 * 
 * <P>
 * 
 * @author bruins
 *         </P>
 */
public class Recipe {
	private String name = new String();
	private String description = new String();
	private ArrayList<String> ingredientNames;

	// Constructors
	Recipe() {
		super();
	}

	Recipe(String name, String description, ArrayList<String> ingredientNames) {
		super();
		this.name = name;
		this.description = description;
		this.ingredientNames = ingredientNames;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<String> getIngredientNames() {
		return ingredientNames;
	}

	/** Read the Recipe set from XML */
	public static ArrayList<Recipe> FileReader(File file)
			throws ParsingException, IOException {
		Builder builder = new Builder();
		Document doc = builder.build(file);
		// get doc root element
		Element root = doc.getRootElement();

		Elements recipeElementSet = root.getChildElements("recipe");
		ArrayList<Recipe> Recipes = new ArrayList<Recipe>();
		for (int recipeCurrent = 0; recipeCurrent < recipeElementSet.size(); recipeCurrent++) {
			// get current Recipe
			Element recipeElement = recipeElementSet.get(recipeCurrent);
			// get Ingredient set
			ArrayList<String> ingredientNames = new ArrayList<String>();
			Elements ingredientSet = recipeElement.getChildElements("ingredientName");
			for (int ingredientCurrent = 0; ingredientCurrent < ingredientSet.size(); ingredientCurrent++){
				String ingredientName = ingredientSet.get(ingredientCurrent).getValue();
                ingredientNames.add(ingredientName);			
			}
			Recipe recipe = new Recipe(
					recipeElement.getAttributeValue("name"),
					recipeElement.getAttributeValue("description"),
					ingredientNames
					);
			Recipes.add(recipe);
		}
		return Recipes;
	}

	public void show() {
		System.out.println("\nRecipe:");
		System.out.println("Name: " + this.getName());
		System.out.println("Description: " + this.getDescription());
		System.out.print("Syllables:");
		for (String name : this.getIngredientNames()){
			System.out.print(" "+name);
		}
		System.out.println();
	}



}
