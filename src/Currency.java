
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
}
