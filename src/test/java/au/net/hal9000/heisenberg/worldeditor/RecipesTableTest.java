package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;

public class RecipesTableTest {

    Configuration config = null;

    @Before
    public void setUp() throws ConfigurationError {
        config = new Configuration("src/test/resources/config.xml");

    }

    @Test
    public void testRecipesTable() {
        Human human = new Human();
        RecipesTable basicPanel = new RecipesTable();
        basicPanel.setItem(human);
        assertNotNull("BasicPanel not null", basicPanel);
    }

}
