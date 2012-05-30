package au.net.hal9000.dnd.item;

public class Currency {
	// conversion
	public static final Float ppAsGp = 10F;
	public static final Float spAsGp = 0.1F;
	public static final Float cpAsGp = 0.01F;
	
	// coin count in this collection
	private int pp = 0;
	private int gp = 0;
	private int sp = 0;
	private int cp = 0;

	public Currency() {
	}

	public Currency(int pPp, int pGp, int pSp, int pCp) {
		this.pp = pPp;
		this.gp = pGp;
		this.sp = pSp;
		this.cp = pCp;
	}

	public Currency(Currency cc) {
		this.pp = cc.pp;
		this.gp = cc.gp;
		this.sp = cc.sp;
		this.cp = cc.cp;
	}

	Float getGpEquiv() {
		return (pp * ppAsGp) + gp + (sp * spAsGp) + (cp * cpAsGp);
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

	public int getGp() {
		return gp;
	}

	public void setGp(int gp) {
		this.gp = gp;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	// increment the current object by the argument currency.
	// note that the currency arg is not changed.
	public void add(Currency cc) {
		// add the value to the current Currency.
		this.pp += cc.pp;
		this.gp += cc.gp;
		this.sp += cc.sp;
		this.cp += cc.cp;
	}
	
    // transfer all the value of the passed currency object.
	public void transfer(Currency cc) {
		this.add(cc);
		// remove the value from the original cc.
		cc.pp = cc.gp = cc.sp = cc.cp = 0;
	}

	public boolean equals(Currency cc) {
		return (pp == cc.pp) && (gp == cc.gp) && (sp == cc.sp) && (cp == cc.cp);
	}
}
