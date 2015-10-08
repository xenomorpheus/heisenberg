package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 */
public class LocationTest {

    /**
     * Method testSetVolumeMax.
     */
    @Test
    public void testSetVolumeMax() {
        float volumeMax = 20F;
        Location location = new Location("World");
        location.setVolumeMax(volumeMax);
        float v = location.getVolumeMax();
        assertEquals("location.getVolumeMax=", volumeMax, v, 0.0001F);
    }

    /**
     * Method testAdd.
 
     * @throws InvalidTypeException 
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void testAdd() throws InvalidTypeException,  TooHeavyException, TooLargeException {
        float volumeMax = 10F;
        float weightMax = 20F;
        // Location
        Location location = new Location("World");
        location.setWeightMax(weightMax);
        location.setVolumeMax(volumeMax);
        // Item
        Cookie i = new Cookie();
        // This should just fit
        i.setVolumeBase(volumeMax);
        i.setWeightBase(weightMax);
        location.add(i);
    }

    /**
     * Method testAddMulti.
     * 
     * @throws InvalidTypeException

     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void testAddMulti() throws InvalidTypeException,  TooHeavyException, TooLargeException {
        Location location = new Location("World");
        Location newLocation = new Location("New Location");
        Cookie c1 = new Cookie();
        Cookie c2 = new Cookie();
        Cookie c3 = new Cookie();
        List<Item> items = new ArrayList<Item>();
        items.add(c1);
        items.add(c2);
        items.add(c3);
        location.addItems(items);
        assertEquals("add multi size", 3, location.getContentsCount());
        location.empty(newLocation);
        assertEquals("location size after empty", 0,
                location.getContentsCount());
        assertEquals("newLocation size", 3, newLocation.getContentsCount());
    }

    /**
     * Method testBeNot.
 
     * @throws InvalidTypeException 
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void testBeNot() throws InvalidTypeException,  TooHeavyException, TooLargeException {
        Location location = new Location("World");
        Cookie c1 = new Cookie();
        Cookie c2 = new Cookie();
        Cookie c3 = new Cookie();
        location.add(c1);
        location.add(c2);
        location.add(c3);
        assertEquals("add multi size", 3, location.getContentsCount());
        location.beNot();
        assertEquals("empty size", 0, location.getContentsCount());
    }

}
