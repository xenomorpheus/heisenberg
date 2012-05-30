package au.net.hal9000.dnd.item;

public class Quiver extends ItemContainer {
	
  public Quiver(){
	  super("Quiver");
  }
  public Quiver(String pName){
	  super(pName);
  }

  public void add (Item pItem) throws ExceptionInvalidType, ExceptionTooHeavy, ExceptionTooBig{
	  if (! (pItem instanceof Arrow)){
		  throw new ExceptionInvalidType(pItem.getName()+" is not a Arrow");
	  }
	  super.add(pItem);
  }
  
}
