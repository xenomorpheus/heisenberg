package au.net.hal9000.heisenberg.jbox2d.demo;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;

public class HunterPreyModelState {

    private Entity hunter;
    private Item prey;

    HunterPreyModelState(Entity hunter, Item prey) {
        super();
        this.hunter = hunter;
        this.prey = prey;
    }

    public Entity getHunter() {
        return hunter;
    }

    public Item getPrey() {
        return prey;
    }

}
