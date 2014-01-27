package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SkillDetailTest {

    @Test
    public void testSkillStringString() {
        try {
            SkillDetail skillDetail = new SkillDetail("theId",
                    "the description");
            assertEquals("id", "theId", skillDetail.getId());
            assertEquals("description", "the description",
                    skillDetail.getDescription());
        } catch (Exception e) {
            fail(e.toString());
        }

    }
}
