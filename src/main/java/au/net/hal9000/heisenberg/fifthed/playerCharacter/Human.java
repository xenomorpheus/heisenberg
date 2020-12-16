package au.net.hal9000.heisenberg.fifthed.playerCharacter;

public class Human extends Humanoid {

  public Human(String name) {
    super(name);
    this.setCreatureSize(CreatureSize.MEDIUM);
  }

  @Override
  public String getRaceName() {
    return "Human";
  }
}
