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
import au.net.hal9000.heisenberg.item.exception.ExceptionCantWear;

public class ScenarioSet01 {

    private void println(String string) {
        // System.out.println(string);
    }

    @Test
    public void swordIntoScabbard() {
        println("\n** A sword is placed in a scabbard.");
        Sword sword = new Sword();
        println("Sword created with name: " + sword);
        Scabbard scabbard = new Scabbard();
        println("Scabbard created with name: " + scabbard);
        try {
            scabbard.add(sword);
            println("Sword now in scabbard.");
        } catch (ExceptionCantWear e) {
            fail("Could not sheath the sword.");
        }
        println("Sword location: " + sword.getContainer());
    }

    @Test
    public void fullBackpackIntoBox() {
        println("\n** A full backpack is placed in a box.");
        Backpack backpack = new Backpack();
        println("Backpack created with name: " + backpack);
        Box box = new Box();
        println("Box created with name: " + box);
        // TODO fill it
        box.add(backpack);
        println("Full backpack placed into box.");
        println("Backpack location: " + backpack.getContainer());
    }

    @Test
    public void crossbowIsLoaded() {
        println("\n** A crossbow is loaded.");
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

    @Test
    public void torchIsLit() {
        println("\n** A torch is lit.");
        Torch torch = new Torch();
        println("Torch description:\n" + torch.getDescription());
        println("Light the torch.");
        FlintAndTinder flintAndTinder = new FlintAndTinder();
        assertFalse("torch unlit", torch.isLit());
        torch.lightWith(flintAndTinder);
        assertTrue("torch unlit", torch.isLit());
        println("Torch description:\n" + torch.getDescription());
    }

    @Test
    public void shieldAdd() {
        println("\n** A Shield is equipped.");
        Shield shield = new Shield();
        Human human = new Human();
        println("Equip the shield.");
        try {
            human.add(shield);
        } catch (Exception e) {
            fail("Humanoid could not wear shield");
        }
        println("Shield location: " + shield.getContainer());
    }

    @Test
    public void quiverIsFilled() {
        println("\n** A quiver is filled.");
        Quiver quiver = new Quiver();
        println("Quiver created.");
        println("TODO - set quiver size");
        try {
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
        } catch (Exception e) {
            fail(e.toString());
        }
        println("Quiver Description: " + quiver.detailedDescription());
    }

    // TODO candle runs out
    @Test
    public void candleRunsOut() {
        println("\n** A candle runs out.");
        Candle candle = new Candle();
        println("Candle description:\n" + candle.getDescription());
        // TODO candle.run_out();
        println("Torch description:\n" + candle.getDescription());
    }

    @Test
    public void hobbitEatsACookie() {
        println("\n** A hobbit eats a cookie.");
        Halfling halfling = new Halfling();
        Cookie cookie = new Cookie();
        try {
            halfling.eat(cookie);
            println("Halfling ate a cookie");
        } catch (Exception e) {
            fail("Failed to eat Cookie because " + e.toString());
        }
    }

}
