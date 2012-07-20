package au.net.hal9000.player.units;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;

public class IngredientTest {

	@Test
	public void test() {
		ArrayList<Ingredient> ingredients = null;
		try {
			File file = new File("config/example/Ingredient.xml");
			ingredients = Ingredient.FileReader(file);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertTrue("ingredients !=null", ingredients != null);
		for (Ingredient pw : ingredients ){
			// pw.show();
		}
	}

}
