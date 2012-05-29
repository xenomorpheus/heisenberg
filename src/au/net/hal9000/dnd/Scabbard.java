package au.net.hal9000.dnd;

public class Scabbard extends Item implements SwordSheath {
	Sword sword = null;
	
	public Scabbard(){
		super("Scabbard");
	}

	Scabbard(String pString){
		super(pString);
	}

	@Override
	public void add(Sword pSword) throws ExceptionCantWear {
		if (sword != null){
			throw new ExceptionCantWear("scabbard full");
		}
        sword = pSword;
        pSword.setLocation(this);
	}

	@Override
	public Sword remove(Location l) throws ExceptionCantRemove {
		if (sword == null){
			throw new ExceptionCantRemove("scabbard empty");
		}
		Sword swordReturn = sword;
        sword.setLocation(location);
        sword = null;
        return swordReturn;
	}

	@Override
	public float getWeight() {
		float total = weightBase;
        if (sword != null){		
			total += sword.getWeight();
		}
		return total;
	}
}
