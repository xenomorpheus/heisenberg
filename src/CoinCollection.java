
public class CoinCollection {
	public static final Float ppAsGp = 10F;
	public static final Float spAsGp = 0.1F;
	public static final Float cpAsGp = 0.01F;
	int pp = 0;
	int gp = 0;
	int sp = 0;
	int cp = 0;

	CoinCollection(){}

	CoinCollection(int pPp, int pGp, int pSp, int pCp){
		this.pp = pPp;
		this.gp = pGp;
		this.sp = pSp;
		this.cp = pCp;
	}

	Float getGpEquiv(){
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
	
	public void add(CoinCollection cc){
		// add the value to the current CoinCollection.
		this.pp += cc.pp;
		this.gp += cc.gp;
		this.sp += cc.sp;
		this.cp += cc.cp;
		// remove the value from the original cc.
		cc.pp = cc.gp = cc.sp = cc.cp = 0;
	}
}
