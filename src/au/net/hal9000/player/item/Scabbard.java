package au.net.hal9000.player.item;

import au.net.hal9000.player.item.exception.ExceptionCantRemove;
import au.net.hal9000.player.item.exception.ExceptionCantWear;
import au.net.hal9000.player.item.property.SwordSheath;
import au.net.hal9000.player.units.Weight;

public class Scabbard extends ItemImpl implements SwordSheath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public Sword remove(Item pLocation) throws ExceptionCantRemove {
		if (sword == null){
			throw new ExceptionCantRemove("scabbard empty");
		}
		Sword swordReturn = sword;
        sword.setLocation(pLocation);
        this.sword = null;
        return swordReturn;
	}

	public Weight getWeight() {
		Weight total = this.getWeightBase();
        if (sword != null){		
			total.add( sword.getWeight());
		}
		return total;
	}

}
