package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** */
public class SkillDetailTest {

  /** Method testSkillStringString. */
  @Test
  public void testSkillStringString() {
    SkillDetail skillDetail = new SkillDetail("theId", "the description");
    assertEquals("id", "theId", skillDetail.getId());
    assertEquals("description", "the description", skillDetail.getDescription());
  }
}
