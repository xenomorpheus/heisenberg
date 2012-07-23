package au.net.hal9000.player.util;

public class Dice {

	/** constructor */
	Dice() {
		super();
	}

	/** defaults to 1d6 */
	public int roll() {
		return roll(1, 6);
	}

	/** default to one roll of the dice with the number of sides specified */
	public int roll(int sides) {
		return roll(1, sides);
	}

	/** specify the number of rolls and of a dice with number of sides specified */
	public int roll(int rolls, int sides) {
		int total = 0;
		if (rolls < 1)
			throw new IllegalArgumentException("Invalid nuber of rolls");
		if (sides < 1)
			throw new IllegalArgumentException("Invalid number of sides");
		for (int roll = 0; roll < rolls; roll++) {
			total += Math.random() * sides + 1;
		}
		return total;
	}

}