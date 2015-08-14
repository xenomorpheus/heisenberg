package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.crafting.Cooker;

public abstract class Animal extends Entity {

    // Constructor
    /**
     * Constructor for Animal.
     * 
     * @param name
     *            String
     * @param description
     *            String
     */
    protected Animal(String name, String description) {
        super(name, description);
    }

    /**
     * Constructor for Animal.
     * 
     * @param name
     *            String
     */
    protected Animal(String name) {
        this(name, "");
    }

    /**
     * serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Drink.
     * 
     * @param water
     *            water to drink.<br>
     *            Throw RuntimeException on error.
     */
    public void drink(Item water) {
        Cooker cooker = getCooker("animalDrinkWater");
        cooker.setItemsAvailable("Water", water);
        cooker.cook();
    }

}
