package au.net.hal9000.heisenberg.crafting;


/**
 * 
 * This is an abstract class.<br>
 * Requirement objects hold details about a requirement for a Recipe objects.<br>
 * A Recipe may have any number of Requirement objects.
 * 
 * e.g.
 * <ul>
 * <li>Item - Wood with a minimum of 3 weight units.
 * <li>Item - FlintAndTinder, not consumed.
 * <ul>
 * 
 * @author bruins
 * 
 */
public abstract class Product {

    String id;
    
    public Product(String id){
        super();
        this.id = id;
    }
    
    /**
     * @return Return a description of the object.
     */
    abstract String getDescription();

    public String getId() {
        return id;
    }

}