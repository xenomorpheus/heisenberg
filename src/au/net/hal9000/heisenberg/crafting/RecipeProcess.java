package au.net.hal9000.heisenberg.crafting;

import java.lang.reflect.*;
import java.util.TreeMap;

/**
 * RecipeProcess <br>
 * Store the code that is part of some recipes.
 * 
 * @author bruins
 * 
 */

public class RecipeProcess {

    private static TreeMap<String, Method> methodsByName = new TreeMap<String, Method>();

    public RecipeProcess() {
        Class<? extends RecipeProcess> c = getClass();
        Method m[] = c.getDeclaredMethods();
        for (int i = 0; i < m.length; i++) {
            methodsByName.put(m[i].getName(), m[i]);
            System.out.println(m[i].getName());
        }
    }

    static void testPowerWord1(Cooker cooker) {
        System.out.println("testPowerWord1"+cooker);
    }

}
