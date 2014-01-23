package au.net.hal9000.heisenberg.item;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.exception.ExceptionCantRemove;
import au.net.hal9000.heisenberg.item.exception.ExceptionInvalidType;
import au.net.hal9000.heisenberg.item.property.ExtraDimensional;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.ItemSearch;
import au.net.hal9000.heisenberg.item.property.ItemSearchExtraDimensional;
import au.net.hal9000.heisenberg.units.Currency;

/*

 http://www.dandwiki.com/wiki/Bag_of_holding

 Bag        | Bag Weight | Contents Weight Limit | Contents Volume Limit | Market Price
 Type I     | 15 lb.     | 250 lb.               | 30 cu. ft.            | 2,500 gp
 Type II    | 25 lb.     | 500 lb.               | 70 cu. ft.            | 5,000 gp
 Type III   | 35 lb.     | 1,000 lb.             | 150 cu. ft.           | 7,400 gp
 Type IV    | 60 lb.     | 1,500 lb.             | 250 cu. ft.           |10,000 gp

 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class BagOfHolding extends Bag implements ExtraDimensional {

    /** bag type. */
    public static final int TYPE_I = 1;

    /** bag type. */
    public static final int TYPE_II = 2;

    /** bag type. */
    public static final int TYPE_III = 3;

    /** bag type. */
    public static final int TYPE_IV = 4;

    /** serial version */
    private static final long serialVersionUID = 1L;

    /** Bag type I-IV */
    private int type;

    /**
     * Constructor.
     * 
     * @param type
     *            type of bag I-IV
     * @param pName
     *            name to use.
     */
    public BagOfHolding(int type, String pName) {
        super(pName);
        this.setType(type);
        ItemProperty.setMagical(this, true);
    }

    public BagOfHolding(int type) {
        this(type, "Bag of Holding");
    }

    public BagOfHolding() {
        this(TYPE_I); // default type
    }

    // Other methods
    private void setType(int pType) {
        if ((pType < TYPE_I) || (pType > TYPE_IV)) {
            throw new IllegalArgumentException("invalid type=" + pType);
        }
        type = pType;
        if (type == TYPE_I) {
            this.setWeightBase(15F);
            this.setWeightMax(250F);
            this.setVolumeMax(30F);
            this.setValueBase(new Currency(0, 2500, 0, 0));
        }
        if (type == TYPE_II) {
            this.setWeightBase(25F);
            this.setWeightMax(500F);
            this.setVolumeMax(70F);
            this.setValueBase(new Currency(0, 5000, 0, 0));
        }
        if (type == TYPE_III) {
            this.setWeightBase(35F);
            this.setWeightMax(1000F);
            this.setVolumeMax(150F);
            this.setValueBase(new Currency(0, 7400, 0, 0));
        }
        if (type == TYPE_IV) {
            this.setWeightBase(60F);
            this.setWeightMax(1500F);
            this.setVolumeMax(150F);
            this.setValueBase(new Currency(0, 10000, 0, 0));
        }
        this.setVolumeBase(2F); // TODO set default volume

    }

    /**
     * take the item out of the bag and place at newLocation.
     * 
     * @param item
     *            the item to relocate
     * @param newLocation
     *            new location
     * @return the relocated item
     */
    public Item getItem(Item item, ItemContainer newLocation) {
        Vector<Item> items = this.getContents();
        if (!items.removeElement(item)) {
            throw new ExceptionCantRemove("remove failed");
        }
        item.setContainer(newLocation);
        return item;
    }

    // Magic
    /** Contents don't add to weight for a BoH. */
    @Override
    public float getWeight() {
        return this.getWeightBase();
    }

    // Magic
    /** Contents don't add to volume for a BoH. */
    @Override
    public float getVolume() {
        return this.getVolumeBase();
    }

    /**
     * BOH Type I,II,III or IV.
     * 
     * @return the type I-IV
     */
    public int getType() {
        return type;
    }

    /** A BOH rupturing is kind of special :-) */
    /** {@inheritDoc} */
    @Override
    public void rupture() {
        this.beNot();
    }

    /**
     * Add item to the bag.
     * 
     * @param item
     *            item to add.
     */
    public void add(Item item) {

        // Recursively check for ExtraDimensional items.
        ItemSearch search = new ItemSearchExtraDimensional();
        item.accept(search);
        if (search.getMatchingItemsCount() > 0) {
            this.rupture();
            throw new ExceptionInvalidType("ExtraDimensional");
        }
        // TODO Do sharp objects cause a ED rupture?

        // Super will check for sharp.
        super.add(item);
    }

}
