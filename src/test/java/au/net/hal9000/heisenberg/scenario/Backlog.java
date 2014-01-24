package au.net.hal9000.heisenberg.scenario;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cloak;
import au.net.hal9000.heisenberg.item.Human;

public class Backlog {

    private void println(String string) {
        // System.out.println(string);
    }

    // Scenario 01
    @Test
    public void characterMountsHorse() {
        println("\n** A character mounts a horse.");
        // Halfling hobbit = new Halfling();
        // Horse horse = new Horse();
        // TODO hobbit.setMount(horse);
    }

    // Scenario 01
    @Test
    public void characterPutsOnCloak() {
        println("\n** A character puts on a cloak.");
        Human human = new Human();
        println("Human created with name: " + human);
        Cloak cloak = new Cloak();
        println("Cloak created with name: " + cloak);
        // TODO human.wear(cloak);
        println("Cloak location: " + cloak.getContainer());
    }

}
