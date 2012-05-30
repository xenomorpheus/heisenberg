package au.net.hal9000.dnd.item;

public class Quiver extends ItemContainer {
	
  public Quiver(){
	  super("Quiver");
  }
  public Quiver(String pName){
	  super(pName);
  }

  public void add (Item pItem) throws ExceptionInvalidType{
	  if (! (pItem instanceof Sword)){
		  throw new ExceptionInvalidType(pItem.getName()+" is not a Sword");
	  }
	  this.add(pItem);
  }
  
}
