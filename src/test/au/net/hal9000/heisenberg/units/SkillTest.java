package test.au.net.hal9000.heisenberg.units;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.units.Skill;

public class SkillTest {

	@Test
	public void testSkillString() {
		try {
			Skill skill = new Skill("theId");
	        assertEquals("id","theId",skill.getId());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
}
