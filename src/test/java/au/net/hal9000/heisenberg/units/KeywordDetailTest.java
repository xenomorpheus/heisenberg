package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.*;

import org.junit.Test;

public class KeywordDetailTest {
    private static String SOME_ID = "Some Id";
    private static String SOME_DESC = "Some desc";

    @Test
    public void testKeywordDetail() {
        KeywordDetail kd = new KeywordDetail(SOME_ID, SOME_DESC);
        assertNotNull(kd);
    }

    @Test
    public void testGetId() {
        KeywordDetail kd = new KeywordDetail(SOME_ID, SOME_DESC);
        assertTrue(SOME_ID.equals(kd.getId()));
    }

    @Test
    public void testGetDescription() {
        KeywordDetail kd = new KeywordDetail(SOME_ID, SOME_DESC);
        assertTrue(SOME_DESC.equals(kd.getDescription()));
    }

    @Test
    public void testCompareTo() {
        KeywordDetail kd = new KeywordDetail(SOME_ID, SOME_DESC);
        KeywordDetail kd2 = new KeywordDetail(SOME_ID, "some other");
        assertEquals(0, kd.compareTo(kd2));
    }

    @Test
    public void testToString() {
        KeywordDetail kd = new KeywordDetail(SOME_ID, SOME_DESC);
        assertEquals("Id: "+SOME_ID+": "+SOME_DESC,kd.toString());
    }

}
