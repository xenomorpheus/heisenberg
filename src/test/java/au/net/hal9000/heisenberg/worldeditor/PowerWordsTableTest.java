package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import javax.swing.JTable;
import org.junit.Before;
import org.junit.Test;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.util.Configuration;

public class PowerWordsTableTest {

    Configuration config = null;

    @Before
    public void setUp() {
        try {
            config = new Configuration("src/test/resources/config.xml");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void testAbilityScoresTable() {
        Human human = new Human();
        JTable basicPanel = new AbilityScoresTable(human, config);
        assertNotNull("BasicPanel not null", basicPanel);
    }

}
