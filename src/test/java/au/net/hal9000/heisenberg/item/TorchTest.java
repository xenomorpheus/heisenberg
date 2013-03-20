package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class TorchTest {

    @Test
    public void testName() {
        final String expected = "my torch";
        Torch torch = new Torch(expected);
        assertEquals(expected, torch.getName());
    }

    @Test
    public void testLit() {
        Torch torch = new Torch();
        assertFalse(torch.isLit());
        assertFalse(torch.getLit());
        torch.setLit(true);
        assertTrue(torch.isLit());
        assertTrue(torch.getLit());
        torch.extinghish();
        assertFalse(torch.isLit());
        assertFalse(torch.getLit());
        torch.light();
        assertTrue(torch.isLit());
        assertTrue(torch.getLit());
    }

    @Test
    public void testGetDetailedDescription() {
        // Name and description
        Torch torch = new Torch("My Name", "My Description");
        assertEquals("My Description. Not lit", torch.getDetaildDescription());
        torch.light();
        assertEquals("My Description. Is lit", torch.getDetaildDescription());
        // Name only
        torch.setDescription("");
        torch.extinghish();
        assertEquals("My Name. Not lit", torch.getDetaildDescription());
        torch.light();
        assertEquals("My Name. Is lit", torch.getDetaildDescription());
        // Neither name nor description
        torch.setName("");
        torch.extinghish();
        assertEquals("Torch. Not lit", torch.getDetaildDescription());
        torch.light();
        assertEquals("Torch. Is lit", torch.getDetaildDescription());
    }
}
