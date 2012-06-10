package au.net.hal9000.dnd.item;

public class Damage {
	private float impact = 0;
	private float fire = 0;
	private float ice = 0;
	private float electrical = 0;
	private float sonic = 0;

	Damage() {
		super();
	}

	public float getImpact() {
		return impact;
	}

	public void setImpact(float impact) {
		this.impact = impact;
	}

	public void setFire(float fire) {
		this.fire = fire;
	}

	public void setIce(float ice) {
		this.ice = ice;
	}

	public void setElectrical(float electrical) {
		this.electrical = electrical;
	}

	public void setSonic(float sonic) {
		this.sonic = sonic;
	}

	public float getFire() {
		return fire;
	}

	public float getIce() {
		return ice;
	}

	public float getElectrical() {
		return electrical;
	}

	public float getSonic() {
		return sonic;
	}

}
