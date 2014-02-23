package au.net.hal9000.heisenberg.item;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.ItemNotPresentException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
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
/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class BagOfHolding extends Bag implements ExtraDimensional {

    /** bag type I. */
    public static final int TYPE_I = 1;
    /** type I weight base. */
    static final float TYPE_I_WEIGHT_BASE = 15F;
    /** type I weight max. */
    static final float TYPE_I_WEIGHT_MAX = 250F;
    /** type I volume max. */
    static final float TYPE_I_VOLUME_MAX = 30F;
    /** type I value. */
    static final int TYPE_I_VALUE_GP = 2500;

    /** bag type II. */
    public static final int TYPE_II = 2;
    /** type I weight base. */
    static final float TYPE_II_WEIGHT_BASE = 25F;
    /** type I weight max. */
    static final float TYPE_II_WEIGHT_MAX = 500F;
    /** type I volume max. */
    static final float TYPE_II_VOLUME_MAX = 70F;
    /** type I value. */
    static final int TYPE_II_VALUE_GP = 5000;

    /** bag type III. */
    public static final int TYPE_III = 3;
    /** type I weight base. */
    static final float TYPE_III_WEIGHT_BASE = 35F;
    /** type I weight max. */
    static final float TYPE_III_WEIGHT_MAX = 1000F;
    /** type I volume max. */
    static final float TYPE_III_VOLUME_MAX = 150F;
    /** type I value. */
    static final int TYPE_III_VALUE_GP = 7400;

    /** bag type IV. */
    public static final int TYPE_IV = 4;
    /** type I weight base. */
    static final float TYPE_IV_WEIGHT_BASE = 60F;
    /** type I weight max. */
    static final float TYPE_IV_WEIGHT_MAX = 1500F;
    /** type I volume max. */
    static final float TYPE_IV_VOLUME_MAX = 250F;
    /** type I value. */
    static final int TYPE_IV_VALUE_GP = 10000;

    /** serial version. */
    private static final long serialVersionUID = 1L;

    /** Bag type I-IV. */
    private int type;

    /**
     * Constructor.
     * 
     * Note: Use only objects as params if you want Factory to work.
     * 
     * @param type
     *            type of bag I-IV
     * @param pName
     *            name to use.
     */
    public BagOfHolding(Integer type, String pName) {
        super(pName);
        this.setType(type);
        ItemProperty.setMagical(this, true);
    }

    /**
     * Constructor.
     * 
     * Note: Use only objects as params if you want Factory to work.
     * 
     * @param type
     *            bag type is I - IV.
     */
    public BagOfHolding(Integer type) {
        this(type, "Bag of Holding");
    }

    /**
     * Constuctor. Defaults to type I.
     */
    public BagOfHolding() {
        this(TYPE_I); // default type
    }

    // Other methods
    /**
     * Set the type of the bag.
     * 
     * @param type
     *            bag type
     */
    private void setType(int type) {
        if ((type < TYPE_I) || (type > TYPE_IV)) {
            throw new IllegalArgumentException("invalid type=" + type);
        }

        this.type = type;
        if (type == TYPE_I) {
            this.setWeightBase(TYPE_I_WEIGHT_BASE);
            this.setWeightMax(TYPE_I_WEIGHT_MAX);
            this.setVolumeMax(TYPE_I_VOLUME_MAX);
            this.setValueBase(new Currency(0, TYPE_I_VALUE_GP, 0, 0));
        }
        if (type == TYPE_II) {
            this.setWeightBase(TYPE_II_WEIGHT_BASE);
            this.setWeightMax(TYPE_II_WEIGHT_MAX);
            this.setVolumeMax(TYPE_II_VOLUME_MAX);
            this.setValueBase(new Currency(0, TYPE_II_VALUE_GP, 0, 0));
        }
        if (type == TYPE_III) {
            this.setWeightBase(TYPE_III_WEIGHT_BASE);
            this.setWeightMax(TYPE_III_WEIGHT_MAX);
            this.setVolumeMax(TYPE_III_VOLUME_MAX);
            this.setValueBase(new Currency(0, TYPE_III_VALUE_GP, 0, 0));
        }
        if (type == TYPE_IV) {
            this.setWeightBase(TYPE_IV_WEIGHT_BASE);
            this.setWeightMax(TYPE_IV_WEIGHT_MAX);
            this.setVolumeMax(TYPE_IV_VOLUME_MAX);
            this.setValueBase(new Currency(0, TYPE_IV_VALUE_GP, 0, 0));
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
     * 
     * 
     * 
     * @return the relocated item * @throws CantRemoveException
     */
    public Item getItem(Item item, ItemContainer newLocation)
            throws ItemNotPresentException {
        Vector<Item> items = this.getContents();
        if (!items.removeElement(item)) {
            throw new ItemNotPresentException("remove failed");
        }
        item.setContainer(newLocation);
        return item;
    }

    /**
     * Contents don't add to weight for a BoH. Magic.
     * 
     * 
     * 
     * @return the total weight.
     */
    @Override
    public float getWeight() {
        return this.getWeightBase();
    }

    /**
     * Contents don't add to volume for a BoH. Magic.
     * 
     * 
     * 
     * @return the total volume.
     */
    @Override
    public float getVolume() {
        return this.getVolumeBase();
    }

    /**
     * BOH Type I,II,III or IV.
     * 
     * 
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
     * 
     * 
     * @throws InvalidTypeException
     * @throws CantWearException 
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    public void add(Item item) throws InvalidTypeException, TooHeavyException, TooLargeException, CantWearException {

        // Recursively check for ExtraDimensional items.
        ItemSearch search = new ItemSearchExtraDimensional();
        item.accept(search);
        if (search.getMatchingItemsCount() > 0) {
            this.rupture();
            throw new InvalidTypeException("ExtraDimensional");
        }
        // TODO Do sharp objects cause a ED rupture?

        // Super will check for sharp.
        super.add(item);
    }

}
