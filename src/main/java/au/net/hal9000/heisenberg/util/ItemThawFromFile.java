package au.net.hal9000.heisenberg.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.FlintAndTinder;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.SmallGroundFire;
import au.net.hal9000.heisenberg.item.Water;
import au.net.hal9000.heisenberg.item.Wood;

public class ItemThawFromFile {

    // Store the object in a file
    public static void freezeToFile(final String filename, Item item)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(item);
        out.close();
    }

    // Static
    public static Cookie cookieThawFromFile(String filename)
            throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        Cookie newObj = (Cookie) in.readObject();
        in.close();
        return newObj;
    }

    // Not used (yet)
    public static Wood woodThawFromFile(String filename) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        Wood newObj = (Wood) in.readObject();
        in.close();
        return newObj;
    }

    // Not used (yet)
    public static Water waterThawFromFile(String filename) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        Water newObj = (Water) in.readObject();
        in.close();
        return newObj;
    }

    // Not used (yet)
    public static FlintAndTinder FlintAndTinderThawFromFile(String filename)
            throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        FlintAndTinder newObj = (FlintAndTinder) in.readObject();
        in.close();
        return newObj;
    }

    // Static
    public static SmallGroundFire SmallGroundFileThawFromFile(String filename)
            throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        SmallGroundFire newObj = (SmallGroundFire) in.readObject();
        in.close();
        return newObj;
    }

    // Just experimenting
    // private void writeObject(ObjectOutputStream out) throws IOException {
    // throw new NotSerializableException("Not today!");
    // }

    public static Location locationThawFromFile(final String filename)
            throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        Location newObj = (Location) in.readObject();
        in.close();
        return newObj;
    }

    // Static
    public static Bag bagThawFromFile(final String filename)
            throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        Bag newObj = (Bag) in.readObject();
        in.close();
        return newObj;
    }
}
