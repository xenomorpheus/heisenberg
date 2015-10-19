package au.net.hal9000.heisenberg.ai;

import org.junit.Test;

public class SearchAStarTest {

    @Test
    public void testGetFringeExpansionCount() {
        SearchAStar searchAStar = new SearchAStar(null, null, null);
        // not dying is good enough. There is no requirement for the
        // value at this stage.
        searchAStar.getFringeExpansionCount();
    }

    @Test
    public void testGetFringeAdded() {
        SearchAStar searchAStar = new SearchAStar(null, null, null);
        // not dying is good enough. There is no requirement for the
        // value at this stage.
        searchAStar.getFringeAdded();
    }

}
