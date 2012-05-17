
public class Currency {
	public static final Float ppAsGp = 0F;
	public static final Float epAsGp = 0F;
	public static final Float spAsGp = 0F;
	public static final Float cpAsGp = 0F;
	int pp = 0;
	int ep = 0;
	int gp = 0;
	int sp = 0;
	int cp = 0;

	Currency(){
		
	}
	
	Float getGpEquiv(){
		return (pp * ppAsGp) + (ep * epAsGp) + (gp * 1F) + (sp * spAsGp) + (cp * cpAsGp);
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

	public int getEp() {
		return ep;
	}

	public void setEp(int ep) {
		this.ep = ep;
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
}
