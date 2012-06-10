package au.net.hal9000.dnd.item;

public interface Item {

	// Feature
	public boolean isMagical();

	public boolean isExtraDimensional();

	public boolean isHumanoidFood();

	public boolean isHumanoidMount();

	public boolean isRingWearer();

	public boolean isLiving();

	public boolean isArmour();

	public boolean isClothing();

	public boolean isSharp();

	public String getName();

	public void setName(String pName);

	public String getDescription();

	public void setDescription(String pDescription);

	public float getWeightBase();

	public void setWeightBase(float baseWeight);

	public float getHitPoints();

	// For simple items the weight is the weightBase.
	// will be overridden by collections
	public float getVolume();

	public float getVolumeBase();

	public void setVolumeBase(float volumeWeight);

	public Currency getValueBase();

	// For simple items the value is the valueBase.
	// will be overridden by collections
	public Currency getValue();

	public void setValueBase(Currency pValueBase);

	public float getDamageModifierImpact();

	public void setDamageModifierImpact(float damageModifierImpact);

	public float getDamageModifierFire();

	public void setDamageModifierFire(float damageModifierFire);

	public float getDamageModifierIce();

	public void setDamageModifierIce(float damageModifierIce);

	public float getDamageModifierElectrical();

	public void setDamageModifierElectrical(float damageModifierElectrical);

	public float getDamageModifierSonic();

	public void setDamageModifierSonic(float damageModifierSonic);

	public void setHitPoints(float hitPoints);

	public Item getLocation();

	public void setLocation(Item location);

	// misc methods

	// For simple items the weight is the weightBase.
	// will be overridden by collections
	public float getWeight();

	public boolean equals(Item other);

	// Attempt to unlink the item from everything so that
	// it can be garbage collected.
	// Won't work if anything is referencing this item.
	public void beNot();

	public String toString();

	public String detailedDescription();

	// Find items that match the criteria
	public void accept(ItemVisitor visitor);

	// Receive damage
	public void accept(Damage damage);

}
