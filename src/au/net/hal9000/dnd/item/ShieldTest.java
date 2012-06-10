package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShieldTest {

	@Test
	public void testShield() {

		// Human1
		Item human1 = new Human("Human1");
		human1.setHitPoints(20);
		System.out.println(human1 + " has " + human1.getHitPoints()
				+ " hitpoints.");
		Damage damage1 = new Damage();
		damage1.setImpact(5);
		System.out.println(human1 + " is dealt " + damage1.getImpact()	+ " damage.");
		human1.accept(damage1);
		System.out.println(human1 + " has " + human1.getHitPoints()
				+ " hitpoints.");
        assertEquals("human1 without sheild", 15.0F, human1.getHitPoints(), 0.0001F);
		
		// Human2
		Item human2 = new Shield(new Human("Human2"));
		human2.setHitPoints(20);
		System.out.println(human2 + " has " + human2.getHitPoints()
				+ " hitpoints.");
		Damage damage2 = new Damage();
		damage2.setImpact(5);
		System.out.println(human2 + " is dealt " + damage2.getImpact()	+ " damage.");
		human2.accept(damage2);
		System.out.println(human2 + " has " + human2.getHitPoints()
				+ " hitpoints.");
        assertEquals("human2 with sheild(0)", 15.0F, human2.getHitPoints(), 0.0001F);

		// Human3
		Item human3 = new Shield(new Human("Human3"), 2.0F);
		human3.setHitPoints(20);
		System.out.println(human3 + " has " + human3.getHitPoints()
				+ " hitpoints.");
		Damage damage3 = new Damage();
		damage3.setImpact(5);
		System.out.println(human3 + " is dealt " + damage3.getImpact()	+ " damage.");
		human3.accept(damage3);
		System.out.println(human3 + " has " + human3.getHitPoints()
				+ " hitpoints.");
        assertEquals("human3 without sheild(2)", 17.0F, human3.getHitPoints(), 0.0001F);
	}

}
