package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;

public class ItemThawFromFileTest {

    @Test
    public void cookieFilePersistenceTest() {
        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "cookie_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Cookie old = new Cookie();
        // Store the object
        try {
            old.freezeToFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        }
        // Get the object back
        Cookie newObj = null;
        try {
            newObj = ItemThawFromFile.cookieThawFromFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        } catch (ClassNotFoundException ex) {
            fail(ex.toString());
        }
        assertTrue("newObj not null", newObj != null);
        assertTrue("deserialized Cookie equals old cookie", old.equals(newObj));

    }

    @Test
    public void persistTwoCookies() {
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
        try {
            old.freezeToFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        }
        // Get the object back
        Location newObj = null;
        try {
            newObj = ItemThawFromFile.locationThawFromFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        } catch (ClassNotFoundException ex) {
            fail(ex.toString());
        }
        assertTrue("newObj not null", newObj != null);
        assertTrue("deserialized Location equals old cookie",
                old.equals(newObj));
        // TODO - check for the cookies in the newObj
    }

    @Test
    public void locationPersistenceShallowTest() {

        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "empty_location_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Location old = new Location("World");
        // Store the object
        try {
            old.freezeToFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        }
        // Get the object back
        Location newObj = null;
        try {
            newObj = ItemThawFromFile.locationThawFromFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        } catch (ClassNotFoundException ex) {
            fail(ex.toString());
        }
        assertTrue("newObj not null", newObj != null);
        assertTrue("deserialized Location equals old cookie",
                old.equals(newObj));

    }

    @Test
    public void locationPersistenceDeepTest() {
        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "location_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Location old = new Location("World");
        old.add(new Cookie());
        // Store the object
        try {
            old.freezeToFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        }
        // Get the object back
        Location newObj = null;
        try {
            newObj = ItemThawFromFile.locationThawFromFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        } catch (ClassNotFoundException ex) {
            fail(ex.toString());
        }
        assertTrue("newObj not null", newObj != null);
        assertTrue("deserialized Location equals old cookie",
                old.equals(newObj));

    }

    @Test
    public void cookiePersistenceShallowTest() {

        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "empty_bag_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Bag old = new Bag();
        // Store the object
        try {
            old.freezeToFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        }
        // Get the object back
        Bag newObj = null;
        try {
            newObj = ItemThawFromFile.bagThawFromFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        } catch (ClassNotFoundException ex) {
            fail(ex.toString());
        }
        assertTrue("newObj not null", newObj != null);
        assertTrue("deserialized Bag equals old cookie", old.equals(newObj));

    }

    @Test
    public void cookiePersistenceDeepTest() {
        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "bag_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Bag old = new Bag("World");
        old.add(new Cookie());
        // Store the object
        try {
            old.freezeToFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        }
        // Get the object back
        Bag newObj = null;
        try {
            newObj = ItemThawFromFile.bagThawFromFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        } catch (ClassNotFoundException ex) {
            fail(ex.toString());
        }
        assertTrue("newObj not null", newObj != null);
        assertTrue("deserialized equals old", old.equals(newObj));

    }

}
