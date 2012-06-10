package au.net.hal9000.dnd.item;

/**
 * @author bruins
 * 
 */
// abstract decorator class - note that it implements Item
abstract class ItemDecorator implements Item {
	protected Item decoratedItem; // the Item being decorated

	public ItemDecorator(Item decoratedItem) {
		this.decoratedItem = decoratedItem;
	}

	// Feature
	public boolean isMagical() {
		return decoratedItem.isMagical();
	}

	public boolean isExtraDimensional() {
		return decoratedItem.isExtraDimensional();
	}

	public boolean isHumanoidFood() {
		return decoratedItem.isHumanoidFood();
	}

	public boolean isHumanoidMount() {
		return decoratedItem.isHumanoidMount();
	}

	public boolean isRingWearer() {
		return decoratedItem.isRingWearer();
	}

	public boolean isLiving() {
		return decoratedItem.isLiving();
	}

	public boolean isArmour() {
		return decoratedItem.isArmour();
	}

	public boolean isClothing() {
		return decoratedItem.isClothing();
	}

	public boolean isSharp() {
		return decoratedItem.isSharp();
	}

	public String getName() {
		return decoratedItem.getName();
	}

	public void setName(String pName) {
		decoratedItem.setName(pName);
	}

	public String getDescription() {
		return decoratedItem.getDescription();
	}

	public void setDescription(String pDescription) {
		decoratedItem.setDescription(pDescription);
	}

	public float getWeightBase() {
		return decoratedItem.getWeightBase();
	}

	public void setWeightBase(float baseWeight) {
		decoratedItem.setWeightBase(baseWeight);
	};

	public float getHitPoints() {
		return decoratedItem.getHitPoints();
	}

	// For simple items the weight is the weightBase.
	// will be overridden by collections
	public float getVolume() {
		return decoratedItem.getVolume();
	}

	public float getVolumeBase() {
		return decoratedItem.getVolumeBase();
	}

	public void setVolumeBase(float volumeWeight) {
		decoratedItem.setVolumeBase(volumeWeight);
	}

	public Currency getValueBase() {
		return decoratedItem.getValueBase();
	}

	// For simple items the value is the valueBase.
	// will be overridden by collections
	public Currency getValue() {
		return decoratedItem.getValue();
	}

	public void setValueBase(Currency pValueBase) {
		decoratedItem.setValueBase(pValueBase);
	}

	public float getDamageModifierImpact() {
		return decoratedItem.getDamageModifierImpact();
	}

	public void setDamageModifierImpact(float damageModifierImpact) {
		decoratedItem.setDamageModifierImpact(damageModifierImpact);
	}

	public float getDamageModifierFire() {
		return decoratedItem.getDamageModifierFire();
	}

	public void setDamageModifierFire(float damageModifierFire) {
		decoratedItem.setDamageModifierFire(damageModifierFire);
	}

	public float getDamageModifierIce() {
		return decoratedItem.getDamageModifierIce();
	}

	public void setDamageModifierIce(float damageModifierIce) {
		decoratedItem.setDamageModifierIce(damageModifierIce);
	}

	public float getDamageModifierElectrical() {
		return decoratedItem.getDamageModifierElectrical();
	}

	public void setDamageModifierElectrical(float damageModifierElectrical) {
		decoratedItem.setDamageModifierElectrical(damageModifierElectrical);
	}

	public float getDamageModifierSonic() {
		return decoratedItem.getDamageModifierSonic();
	}

	public void setDamageModifierSonic(float damageModifierSonic) {
		decoratedItem.setDamageModifierSonic(damageModifierSonic);
	}

	public void setHitPoints(float hitPoints) {
		decoratedItem.setHitPoints(hitPoints);
	}

	public Item getLocation() {
		return decoratedItem.getLocation();
	}

	public void setLocation(Item location) {
		decoratedItem.setLocation(location);
	}

	// misc methods

	// For simple items the weight is the weightBase.
	// will be overridden by collections
	public float getWeight() {
		return decoratedItem.getWeight();
	}

	public boolean equals(Item other) {
		return decoratedItem.equals(other);
	}

	// Attempt to unlink the item from everything so that
	// it can be garbage collected.
	// Won't work if anything is referencing this item.
	public void beNot() {
		decoratedItem.beNot();
		// this.beNot();
	}

	public String toString() {
		return decoratedItem.toString();
	}

	public String detailedDescription() {
		return decoratedItem.detailedDescription();
	}

	// Find items that match the criteria
	public void accept(ItemVisitor visitor) {
		decoratedItem.accept(visitor);
	}

	// Receive damage
	public void accept(Damage damage) {
		decoratedItem.accept(damage);
	}

}
