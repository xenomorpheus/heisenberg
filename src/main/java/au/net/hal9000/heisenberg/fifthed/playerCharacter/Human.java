package au.net.hal9000.heisenberg.fifthed.playercharacter;

public class Human extends Humanoid {

  public Human(String name) {
    super(name);
    this.setCreatureSize(CreatureSize.MEDIUM);
  }

  @Override
  public String getSpeciesName() {
    return "Human";
  }
}
