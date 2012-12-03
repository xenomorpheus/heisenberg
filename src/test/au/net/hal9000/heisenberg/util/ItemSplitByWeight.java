package test.au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.IItem;

public abstract class ItemSplitByWeight {

    public static IItem splitByWeight(IItem item, float newItemWeight) {
        if (newItemWeight <= 0) {
            throw new IllegalArgumentException(
                    "New weight must be strictly positive");
        }

        // New weight must be strictly less than current weight
        float oldItemWeight = item.getWeightBase();
        if (newItemWeight >= oldItemWeight) {
            throw new IllegalArgumentException(
                    "New weight must be strictly less than current weight");
        }

        // Create new Item
        IItem newItem = Factory.createItem(item.getClass().getSimpleName());
        // Split old item by weight, volume, etc.
        newItem.setWeightBase(newItemWeight);
        float factor = newItemWeight / oldItemWeight;
        float oldItemVolume = item.getVolumeBase();
        newItem.setVolumeBase(oldItemVolume * factor);

        // Update old item weight, volume, etc
        item.setWeightBase(oldItemWeight - newItem.getWeightBase());
        item.setVolumeBase(oldItemVolume - newItem.getVolumeBase());

        return newItem;
    }
}