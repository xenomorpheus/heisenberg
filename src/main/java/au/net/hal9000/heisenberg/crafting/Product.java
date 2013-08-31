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

    public Product(String id) {
        super();
        this.id = id;
    }

    /**
     * @return Return a description of the object.
     */
    abstract String getDescription();

    /**
     * 
     * @param cooker
     * @return the reason why this cooker can't build this product. Null if the
     *         cooker can build this product.
     */
    abstract String meetsRequirements(Cooker cooker);

    /**
     * Actually create the product.
     * 
     * @param cooker
     * @return the reason why this coker can't crate this product. Null if the
     *         cooker can build this product.
     */
    abstract String createProduct(Cooker cooker);

    public String getId() {
        return id;
    }

}
