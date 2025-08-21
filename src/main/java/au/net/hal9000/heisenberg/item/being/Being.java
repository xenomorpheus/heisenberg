package au.net.hal9000.heisenberg.item.being;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.ItemImpl;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.mixin.AnimalConsumeSustenance;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PlayableState;
import jakarta.persistence.MappedSuperclass;

/**
 * Being is the basis of conscious entities. <br>
 * May living or undead. May be PC, NPC, animal, etc.
 */
@MappedSuperclass
public abstract class Being extends ItemImpl {

  private CharacterSheet characterSheet = null;

  // TODO probably mark as not storable in database
  private PlayableState playableState = null;

  // Constructor
  protected Being() {
    super();
    // By default PCs are living, but this may be changed at any time.
    ItemProperty.setLiving(this);
    ItemProperty.setAeration(this, ItemProperty.HEALTH_METRIC_IDEAL);
    ItemProperty.setEntertainment(this, ItemProperty.HEALTH_METRIC_IDEAL);
    ItemProperty.setHydration(this, ItemProperty.HEALTH_METRIC_IDEAL);
    ItemProperty.setNourishment(this, ItemProperty.HEALTH_METRIC_IDEAL);
    ItemProperty.setRest(this, ItemProperty.HEALTH_METRIC_IDEAL);
  }

  // Getters and Setters

  /**
   * Gets the character sheet for this entity. If the character sheet is null, a new one is created.
   *
   * @return the character sheet of this entity
   */
  public CharacterSheet getCharacterSheet() {
    if (null == characterSheet) {
      characterSheet = new CharacterSheet();
    }
    return characterSheet;
  }

  public void setCharacterSheet(CharacterSheet characterSheet) {
    this.characterSheet = characterSheet;
  }

  /**
   * Gets the playable state for this entity. If the playable state is null, a new one is created.
   *
   * @return the playable state of this entity
   */
  public PlayableState getPlayableState() {
    if (null == playableState) {
      playableState = new PlayableState();
    }
    return playableState;
  }

  public void setPlayableState(PlayableState playableState) {
    this.playableState = playableState;
  }

  // Getters and Setters redirected to CharacterSheet

  @Override
  public String getName() {
    return getCharacterSheet().getName();
  }

  @Override
  public void setName(final String name) {
    getCharacterSheet().setName(name);
  }

  @Override
  public String getDescription() {
    return getCharacterSheet().getDescription();
  }

  @Override
  public void setDescription(final String description) {
    getCharacterSheet().setDescription(description);
  }

  /**
   * Creates a new Cooker instance for the given recipe ID.
   *
   * @param recipeId the ID of the recipe to create a Cooker for
   * @return a new Cooker instance
   * @throws RuntimeException if the recipe with the given ID is not found
   */
  public Cooker getCooker(String recipeId) {
    Recipe recipe = Configuration.lastConfig().getRecipe(recipeId);
    if (recipe == null) {
      throw new RuntimeException("Failed to find recipe=" + recipeId);
    }
    return recipe.getNewCooker(this);
  }

  /**
   * Consumes the given item as sustenance.
   *
   * @param consumable the item to be consumed
   */
  public void consume(Item consumable) {
    AnimalConsumeSustenance.eat((Animal) this, consumable); // TODO fix cast to Animal
  }
}
