package au.net.hal9000.dnd.item;

public class Scabbard extends Item implements SwordSheath {
	private Sword sword = null;
	
	public Scabbard(){
		super("Scabbard");
	}

	public Scabbard(String pString){
		super(pString);
	}

	public void add(Sword pSword) throws ExceptionCantWear {
		if (sword != null){
			throw new ExceptionCantWear("scabbard full");
		}
        sword = pSword;
        pSword.setLocation(this);
	}

	public Sword remove(Location pLocation) throws ExceptionCantRemove {
		if (sword == null){
			throw new ExceptionCantRemove("scabbard empty");
		}
		Sword swordReturn = sword;
        sword.setLocation(pLocation);
        this.sword = null;
        return swordReturn;
	}

	public float getWeight() {
		float total = this.getWeightBase();
        if (sword != null){		
			total += sword.getWeight();
		}
		return total;
	}
}
