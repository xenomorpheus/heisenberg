package au.net.hal9000.heisenberg.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import au.net.hal9000.heisenberg.item.Water;
import au.net.hal9000.heisenberg.item.Wood;

public class ItemThawFromFile {

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

}
