package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.util.Configuration;

public class BasicPanelTest {

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
    public void testBasicPanel() {
        Human human = new Human();
        JPanel basicPanel = new BasicPanel(human, config);
        assertNotNull("BasicPanel not null", basicPanel);
    }

}
