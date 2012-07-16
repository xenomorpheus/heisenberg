package au.net.hal9000.player.item;

import au.net.hal9000.player.item.exception.ExceptionInvalidType;
import au.net.hal9000.player.item.exception.ExceptionTooBig;
import au.net.hal9000.player.item.exception.ExceptionTooHeavy;

public class Quiver extends ItemContainer {
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Quiver(){
	  super("Quiver");
  }
  public Quiver(String pName){
	  super(pName);
  }

  public void add (ItemImpl pItem) throws ExceptionInvalidType, ExceptionTooHeavy, ExceptionTooBig{
	  if (! (pItem instanceof Arrow)){
		  throw new ExceptionInvalidType(pItem.getName()+" is not a Arrow");
	  }
	  super.add(pItem);
  }
  
}
