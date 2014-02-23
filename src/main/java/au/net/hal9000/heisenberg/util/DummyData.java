package au.net.hal9000.heisenberg.util;

import java.util.TreeMap;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Backpack;
import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Candle;
import au.net.hal9000.heisenberg.item.Cloak;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Crossbow;
import au.net.hal9000.heisenberg.item.CrossbowBolt;
import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Hand;
import au.net.hal9000.heisenberg.item.Horse;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.MagicRing;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.item.Quiver;
import au.net.hal9000.heisenberg.item.Ring;
import au.net.hal9000.heisenberg.item.Scabbard;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.Torch;
import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;

/**
 * Utility class for building test Item objects.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public final class DummyData {
    /** test skills. */
    private static final String[] TEST_SKILLS = new String[] {"testSkill1", "testSkill2", "testSkill3"};
    /** test recipes. */
    private static final String[] TEST_RECIPES = new String[] {"testItem1", "testFireGround1", "testSpell1" };
    /** test level. */
    private static final int TEST_LEVEL = 3;
    /** test weight or volume. */
    private static final int TEST_WEIGHT_VOLUME = 100000;
    /** Constructor. */
    private DummyData() {
        super();
    }

    /** return the config. * @return Configuration
     * @throws ConfigurationError
     */
    public static Configuration config() throws ConfigurationError {
        Configuration config = new Configuration(
                "src/test/resources/config.xml");
        return config;

    }

    /**
     * Method elf.
     * @return PcRace
     * @throws ConfigurationError
     */
    public static PcRace elf() throws ConfigurationError {
        Configuration config = config();
        TreeMap<String, PcClass> pcClasses = config.getPcClasses();
        PcRace pc = (PcRace) Factory.createItem("Elf");
        pc.setName("Jane");
        pc.setPcClass(pcClasses.get("Paladin"));
        pc.setDescription("The Paladin");
        pc.setGender("Female"); // TODO get from config
        pc.setSize("Small");
        pc.setLevel(TEST_LEVEL);
        pc.skillsAddArray(TEST_SKILLS);
        pc.recipesAdd(TEST_RECIPES);
        return pc;
    }

    /**
     * Create a demo world with some Item objects.
     * 
     * 
     * @return a demo world of Item objects.
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    public static Location getDemoWorld() throws InvalidTypeException, CantWearException {
        // Ad-hoc test world
        Location world = new Location("World");
        world.setWeightMax(TEST_WEIGHT_VOLUME);
        world.setVolumeMax(TEST_WEIGHT_VOLUME);

        // Scabbard
        Scabbard scabbard = new Scabbard();
        scabbard.add(new Sword());

        Scabbard scabbard2 = new Scabbard("Scabbard2");
        scabbard2.add(new Sword());

        Bag boh = new BagOfHolding(1);
        boh.add(new Bag("Bag1"));
        boh.add(new Box("Box1"));
        boh.add(new Candle());
        boh.add(new Cloak());
        boh.add(new Cookie("Cookie1"));
        boh.add(new Crossbow());
        boh.add(new CrossbowBolt());
        boh.add(new Ring());
        boh.add(scabbard);
        boh.add(new Torch());

        // a backpack of stuff
        Bag backpack = new Backpack("Backpack1");
        backpack.setWeightMax(TEST_WEIGHT_VOLUME);
        backpack.setVolumeMax(TEST_WEIGHT_VOLUME);
        backpack.add(boh);

        Quiver quiver = new Quiver();
        quiver.add(new Arrow());
        quiver.add(new Arrow());
        quiver.add(new Arrow());

        Bag bag2 = new Bag("Bag2");
        bag2.add(new Cookie("Cookie6"));
        bag2.add(new Cookie("Cookie7"));
        bag2.add(new Cookie("Cookie8"));
        backpack.add(bag2);

        // a human with a bag of cookies
        Human human = new Human("Human1");
        human.setWeightMax(TEST_WEIGHT_VOLUME);
        human.setVolumeMax(TEST_WEIGHT_VOLUME);

        human.add(new Shield());
        human.add(scabbard2);
        human.add(quiver);
        human.add(backpack);
        world.add(human);

        Hand leftHand = human.getLeftHand();
        leftHand.add(new MagicRing());

        world.add(new Sword());
        world.add(new Horse());

        // bag3
        Bag bag3 = new Bag("Bag3");
        bag3.add(new Cookie("Cookie9"));
        bag3.add(new Cookie("Cookie10"));
        bag3.add(new Cookie("Cookie11"));
        world.add(bag3);

        return world;
    }

}
