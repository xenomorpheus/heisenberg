package au.net.hal9000.player.units;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class RecipeTest {

	@Test
	public void test() {
		ArrayList<Recipe> recipes = null;
		try {
			File file = new File("config/example/Recipe.xml");
			recipes = Recipe.FileReader(file);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertTrue("recipes !=null", recipes != null);
		for (Recipe recipe : recipes ){
			// recipe.show();
		}
	}

}
