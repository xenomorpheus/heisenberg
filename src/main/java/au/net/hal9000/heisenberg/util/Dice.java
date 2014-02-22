package au.net.hal9000.heisenberg.util;

/**
 * Simulate dice rolls.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class Dice {

    
    /** 6 sided die. */
    static final int D6 = 6;
    /** 20 sided die. */
    static final int D20 = 20;
    
    /** constructor. */
    public Dice() {
        super();
    }

    /**
     * defaults to 1d6.
     * 
    
     * @return the value rolled on the dice. */
    public int roll() {
        return roll(1, D6);
    }

    /**
     * default to one roll of the dice with the number of sides specified.
     * 
     * @param sides
     *            how many sides on the dice.
    
     * @return the value rolled on the dice. */
    public int roll(int sides) {
        return roll(1, sides);
    }

    /**
     * specify the number of rolls and of a dice with number of sides specified<br>
     * e.g. 3d6 is roll(3,6)
     * 
     * @param rolls
     *            how many rolls of the dice.
     * @param sides
     *            how many sides on the dice.
    
     * @return the total dice value of all rolls. */
    public int roll(int rolls, int sides) {
        int total = 0;
        if (rolls < 1) {
            throw new IllegalArgumentException("Invalid number of rolls");
        }
        if (sides < 1) {
            throw new IllegalArgumentException("Invalid number of sides");
        }
        for (int roll = 0; roll < rolls; roll++) {
            total += Math.random() * sides + 1;
        }
        return total;
    }

}
