package au.net.hal9000.dnd.item;

public class Shield extends ItemDecorator {

	float reductionImpact = 0;

	public Shield(Item decoratedItem) {
		super(decoratedItem);
	}

	public Shield(Item decoratedItem, float pReductionImpact) {
		super(decoratedItem);
		this.reductionImpact = pReductionImpact;
	}

	public void setReductionImpact(float pReductionImpact) {
		this.reductionImpact = pReductionImpact;
	}

	// Features
	public boolean isArmour() {
		return true;
	}

	// Receive damage
	public void accept(Damage damage) {
		float impact = damage.getImpact();
		if (impact > 0) {
			impact -= reductionImpact;
			if (impact < 0) {
				impact = 0;
			}
			damage.setImpact(impact);
		}
		decoratedItem.accept(damage);
	}
}
