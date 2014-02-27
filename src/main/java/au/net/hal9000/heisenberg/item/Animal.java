package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

public abstract class Animal extends Entity {

    // Constructor
    /**
     * Constructor for Animal.
     * @param name String
     * @param description String
     */
    protected Animal(String name, String description) {
        super(name, description);
    }

    /**
     * Constructor for Animal.
     * @param name String
     */
    protected Animal(String name) {
        this(name, "");
    }
    
    
    /**
     * serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Try to drink some water.
     * 
     * @param water
     *            Water (or sub-class)
     * 
     * @return Any error message, or null on success.
     * @throws InvalidTypeException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    public String drink(Water water) throws InvalidTypeException,  TooHeavyException, TooLargeException {
        Cooker cooker = getCooker("drinkWater");
        String error = cooker.setItemsAvailable(0, water);
        if (null != error) {
            return error;
        }
        return cooker.cook();
    }
    
    
}
