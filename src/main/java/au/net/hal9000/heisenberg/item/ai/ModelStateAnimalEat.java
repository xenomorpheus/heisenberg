package au.net.hal9000.heisenberg.item.ai;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;

public interface ModelStateAnimalEat extends ModelState {

    Item getFood();

    Animal getAnimal();

}
