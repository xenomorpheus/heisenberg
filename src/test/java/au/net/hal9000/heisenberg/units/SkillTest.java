package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 */
public class SkillTest {

    /**
     * Method testSkillString.
     */
    @Test
    public void testSkillString() {
        Skill skill = new Skill("theId");
        assertEquals("id", "theId", skill.getId());
    }
}
