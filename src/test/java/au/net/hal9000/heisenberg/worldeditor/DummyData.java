package au.net.hal9000.heisenberg.worldeditor;

import java.util.TreeMap;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.PcClass;

public class DummyData {
    
    
    public static PcRace elf() throws ConfigurationError{
        Configuration config = new Configuration(
                "src/test/resources/config.xml");
        TreeMap<String, PcClass> pcClasses = config.getPcClasses();
        PcRace pc = (PcRace) Factory.createItem("Elf");
        pc.setName("Jane");
        pc.setPcClass(pcClasses.get("Paladin"));
        pc.setDescription("The Paladin");
        pc.setGender("Female"); // TODO get from config
        pc.setSize("Small");
        pc.setLevel(3);
        pc.skillsAdd(new String[] { "testSkill1", "testSkill2",
                "testSkill3" });
        pc.recipesAdd(new String[] { "testItem1",
                "testFireGround1", "testSpell1" });
        return pc;
    }

}
