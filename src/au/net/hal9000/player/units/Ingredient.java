package au.net.hal9000.player.units;

import java.io.*;
import nu.xom.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Ingredients are the alphabet of a {@link Recipe}.
 * 
 * <P>
 * Typically the more powerful ingredients are more complex.
 * </P>
 * <P>
 * The more powerful ingredients are rarer than others.
 * </P>
 * 
 * <P>
 * The description of a ingredients may never be revealed to players. This is
 * part of game play, working out what it does.
 * </P>
 * 
 * <P>
 * As part of the fun of game play, player may try to:
 * <ul>
 * <li>Quest/discover hidden Ingredients.</li>
 * <li>Guess the hidden description of a Ingredient.</li>
 * <li>Quest/Experiment with the permutations to discover Recipes.</li>
 * <li>Work out how to protect themselves from the recipe they are casting.</li>
 * </ul>
 * </P>
 * 
 * <P>
 * Ideas for Ingredients
 * </P>
 * <ul>
 * <li>Some for each Fire, Ice, Electricity, etc</li>
 * <li>Multiple levels of each word, eg small fire, bigger fire.</li>
 * <li>Some for healing</li>
 * <li>Some for targeting e.g. Magic Missile style</li>
 * <li>Some for defence from other recipes</li>
 * <li>Some for recipe invocation shielding, otherwise recipe causes damage to the
 * caster. Part of the fun of trying to develop your own recipes is to learn to
 * be careful.</li>
 * <li>Some for multiple e.g. two magic missiles at once</li>
 * <li>Some for repeat e.g. next round another the second missle fires</li>
 * <li>Some for duration</li>
 * 
 * 
 * <P>
 * Developer Notes:Lets try making Ingredients mutable and see how it goes
 * </P>
 * 
 * @author bruins
 */
public class Ingredient implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The word that is uttered as part of a recipe.
	 */
	private String name;
	/**
	 * The game developer's hidden description of the word.
	 */
	private String description;

	Ingredient(String word, String description) {
		super();
		this.name = word;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	/** Read the Ingredient set from XML */
	public static ArrayList<Ingredient> FileReader(File file) throws ParsingException,
			IOException {
		Builder builder = new Builder();
		Document doc = builder.build(file);
		// get doc root element, <properties>
		Element root = doc.getRootElement();
		// <ingredient name="ASDFASDFA1"
		// description="description 1"></ingredient>
		Elements entries = root.getChildElements("ingredient");
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (int current = 0; current < entries.size(); current++) {
			// get current ingredient
			Element entry = entries.get(current);
			Ingredient pw = new Ingredient(entry.getAttributeValue("name"),
					entry.getAttributeValue("description"));
			ingredients.add(pw);
		}
		return ingredients;
	}

	public void show() {
		System.out.println("\nIngredient:");
		System.out.println("Name: " + this.getName());
		System.out.println("Description: " + this.getDescription());
	}


}
