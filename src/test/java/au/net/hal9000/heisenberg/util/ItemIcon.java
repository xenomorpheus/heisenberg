package au.net.hal9000.heisenberg.util;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ItemIcon {

    private static ClassLoader cl = ItemIcon.class.getClassLoader();
    private static HashMap<String, Icon> iconByName = new HashMap<String, Icon>();

    public static Icon get(String iconPathAndName) {
        if (!iconByName.containsKey(iconPathAndName)) {
            iconByName.put(
                    iconPathAndName,
                    new ImageIcon(new ImageIcon(cl.getResource(iconPathAndName))
                            .getImage().getScaledInstance(16, 16,
                                    Image.SCALE_SMOOTH)));
        }
        return iconByName.get(iconPathAndName);
    }

}
