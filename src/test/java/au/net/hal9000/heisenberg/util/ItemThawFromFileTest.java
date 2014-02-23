package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;

/**
 * Testing Persistence of Item objects.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class ItemThawFromFileTest {

    /**
     * Testing freezing and thawing a simple Item to a file.
     * 
    
    
     * @throws IOException * @throws ClassNotFoundException */
    @Test
    public void cookieFilePersistenceTest() throws IOException,
            ClassNotFoundException {
        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "cookie_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Cookie old = new Cookie();
        // Store the object
        old.freezeToFile(filename);

        // Get the object back
        Cookie newObj = ItemThawFromFile.cookieThawFromFile(filename);

        assertNotNull("newObj not null", newObj);
        assertTrue("deserialized Cookie equals old cookie", old.equals(newObj));
    }

    /**
     * Persist two Item objects to disk.
     * 
     * 
     * 
     * @throws IOException
     *             * @throws ClassNotFoundException
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void persistTwoCookies() throws IOException, ClassNotFoundException, InvalidTypeException, CantWearException {
        // System.out.println("\n** persist two cookies.");
        Location old = new Location("world");
        Cookie cookie = new Cookie();
        Cookie cookie2 = new Cookie();
        old.add(cookie);
        old.add(cookie2);

        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "location_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        // Store the object
        old.freezeToFile(filename);
        // Get the object back
        Location newObj = ItemThawFromFile.locationThawFromFile(filename);

        assertTrue("newObj not null", null != newObj);
        assertTrue("deserialized Location equals old cookie",
                old.equals(newObj));
        // TODO - check for the cookies in the newObj
    }

    /**
     * Freeze and thaw a Container Item to file.
    
    
     * @throws IOException * @throws ClassNotFoundException */
    @Test
    public void locationPersistenceShallowTest() throws IOException,
            ClassNotFoundException {

        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "empty_location_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Location old = new Location("World");
        // Store the object
        old.freezeToFile(filename);

        // Get the object back
        Location newObj = ItemThawFromFile.locationThawFromFile(filename);

        assertTrue("newObj not null", null != newObj);
        assertTrue("deserialized Location equals old cookie",
                old.equals(newObj));

    }

    /**
     * Method locationPersistenceDeepTest.
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void locationPersistenceDeepTest() throws IOException,
            ClassNotFoundException, InvalidTypeException, CantWearException {
        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "location_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Location old = new Location("World");
        old.add(new Cookie());
        // Store the object
        old.freezeToFile(filename);

        // Get the object back
        Location newObj = ItemThawFromFile.locationThawFromFile(filename);

        assertNotNull("newObj not null", newObj);
        assertTrue("deserialized Location equals old cookie",
                old.equals(newObj));

    }

    /**
     * Method cookiePersistenceShallowTest.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void cookiePersistenceShallowTest() throws IOException,
            ClassNotFoundException {

        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "empty_bag_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Bag old = new Bag();
        // Store the object
        old.freezeToFile(filename);
        // Get the object back
        Bag newObj = ItemThawFromFile.bagThawFromFile(filename);
        assertNotNull("newObj not null", newObj);
        assertTrue("deserialized Bag equals old cookie", old.equals(newObj));

    }

    /**
     * Method cookiePersistenceDeepTest.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws CantWearException 
     * @throws InvalidTypeException 
     */
    @Test
    public void cookiePersistenceDeepTest() throws IOException,
            ClassNotFoundException, InvalidTypeException, CantWearException {
        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "bag_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Bag old = new Bag("World");
        old.add(new Cookie());
        // Store the object
        old.freezeToFile(filename);
        // Get the object back
        Bag newObj = ItemThawFromFile.bagThawFromFile(filename);
        assertNotNull("newObj not null", newObj);
        assertTrue("deserialized equals old", old.equals(newObj));

    }

}
