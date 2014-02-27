package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Backpack;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Candle;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Crossbow;
import au.net.hal9000.heisenberg.item.CrossbowBolt;
import au.net.hal9000.heisenberg.item.FlintAndTinder;
import au.net.hal9000.heisenberg.item.Halfling;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.Quiver;
import au.net.hal9000.heisenberg.item.Scabbard;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.Torch;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 */
public class ScenarioSet01 {

    /**
     * Method println.
     * 
     * @param string
     *            String
     */
    private void println(String string) {
        // System.out.println(string);
    }

    /**
     * Method swordIntoScabbard.
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void swordIntoScabbard() throws InvalidTypeException, TooHeavyException, TooLargeException {
        println(System.lineSeparator() + "** A sword is placed in a scabbard.");
        Sword sword = new Sword();
        println("Sword created with name: " + sword);
        Scabbard scabbard = new Scabbard();
        println("Scabbard created with name: " + scabbard);
        try {
            scabbard.add(sword);
            println("Sword now in scabbard.");
        } catch (InvalidTypeException e) {
            fail("Could not sheath the sword.");
        }
        println("Sword location: " + sword.getContainer());
    }

    /**
     * Method fullBackpackIntoBox.
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void fullBackpackIntoBox() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        println(System.lineSeparator()
                + "** A full backpack is placed in a box.");
        Backpack backpack = new Backpack();
        println("Backpack created with name: " + backpack);
        Box box = new Box();
        println("Box created with name: " + box);
        // TODO fill it
        box.add(backpack);
        println("Full backpack placed into box.");
        println("Backpack location: " + backpack.getContainer());
    }

    /**
     * Method crossbowIsLoaded.
     */
    @Test
    public void crossbowIsLoaded() {
        println(System.lineSeparator() + "** A crossbow is loaded.");
        Crossbow crossbow = new Crossbow();
        println("Crossbow created with name: " + crossbow);
        CrossbowBolt bolt = new CrossbowBolt();
        println("CrossbowBolt created with name: " + bolt);
        Location ground = new Location("Ground");
        bolt.setContainer(ground);
        crossbow.setLoadedBolt(bolt);
        CrossbowBolt got = crossbow.getLoadedBolt();
        assertEquals("bow's bolt", bolt, got);
        assertEquals("bolt's location", ground, got.getContainer());
        println("Crossbow has been loaded with the bolt");
    }

    /**
     * Method torchIsLit.
     */
    @Test
    public void torchIsLit() {
        println(System.lineSeparator() + "** A torch is lit.");
        Torch torch = new Torch();
        println("Torch description:" + System.lineSeparator() + ""
                + torch.getDescription());
        println("Light the torch.");
        FlintAndTinder flintAndTinder = new FlintAndTinder();
        assertFalse("torch unlit", torch.isLit());
        torch.lightWith(flintAndTinder);
        assertTrue("torch unlit", torch.isLit());
        println("Torch description:" + System.lineSeparator() + ""
                + torch.getDescription());
    }

    /**
     * Method shieldAdd.
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void shieldAdd() throws InvalidTypeException,  TooHeavyException, TooLargeException {
        println(System.lineSeparator() + "** A Shield is equipped.");
        Shield shield = new Shield();
        Human human = new Human();
        println("Equip the shield.");
        human.wear(shield);
        println("Shield location: " + shield.getContainer());
    }

    /**
     * Method quiverIsFilled.
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void quiverIsFilled() throws InvalidTypeException,  TooHeavyException, TooLargeException {
        println(System.lineSeparator() + "** A quiver is filled.");
        Quiver quiver = new Quiver();
        println("Quiver created.");
        println("TODO - set quiver size");
        println("Arrow created.");
        quiver.add(new Arrow());
        println("Arrow created.");
        quiver.add(new Arrow());
        println("Arrow created.");
        quiver.add(new Arrow());
        println("Arrow created.");
        quiver.add(new Arrow());
        println("Arrow created.");
        quiver.add(new Arrow());
        println("Arrow created.");
        quiver.add(new Arrow());
        println("Quiver Description: " + quiver.detailedDescription());
    }

    // TODO candle runs out
    /**
     * Method candleRunsOut.
     */
    @Test
    public void candleRunsOut() {
        println(System.lineSeparator() + "** A candle runs out.");
        Candle candle = new Candle();
        println("Candle description:" + System.lineSeparator() + ""
                + candle.getDescription());
        // TODO candle.run_out();
        println("Torch description:" + System.lineSeparator() + ""
                + candle.getDescription());
    }

    /**
     * Method hobbitEatsACookie.
     * 
     * @throws InvalidTypeException
     */
    @Test
    public void hobbitEatsACookie() throws InvalidTypeException {
        println(System.lineSeparator() + "** A hobbit eats a cookie.");
        Halfling halfling = new Halfling();
        Cookie cookie = new Cookie();
        halfling.eat(cookie);
        println("Halfling ate a cookie");
    }

}
