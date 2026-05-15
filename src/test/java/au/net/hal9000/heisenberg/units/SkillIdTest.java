package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** */
public class SkillIdTest {

  /** Method testSkillString. */
  @Test
  public void testSkillString() {
    SkillId skill = new SkillId("theId");
    assertEquals("id", "theId", skill.getId());
  }
}
