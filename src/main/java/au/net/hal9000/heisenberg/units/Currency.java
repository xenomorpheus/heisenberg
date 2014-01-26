package au.net.hal9000.heisenberg.units;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// This is NOT an Item
/**
 * A representation of currency, value, wealth.
 * 
 * Currency contains a whole number of pieces (coins) of each of the following:
 * copper, silver, gold and platinum. These are abbreviated as CP, SP, GP and PP
 * respectively.
 * 
 * Objects are mutable.
 * 
 * @author bruins
 * 
 */
public class Currency implements Serializable, Cloneable {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /** standard coin conversion ratio. */
    private static final int COIN_CONVERSION = 10;

    /**
     * Coin conversion: 1PP = 10 GP.
     */
    public static final Float PP_AS_GP = 10F;

    /**
     * Coin conversion: 1GP = 10 SP.
     */
    public static final Float SP_AS_GP = 0.1F;

    /**
     * Coin conversion: 1 SP = 10 GP.
     */
    public static final Float CP_AS_GP = 0.01F;

    // coin count in this collection
    /** platinum pieces. */
    private int pp = 0;
    /** gold pieces. */
    private int gp = 0;
    /** silver pieces. */
    private int sp = 0;
    /** coper pieces. */
    private int cp = 0;

    // Constructors
    /**
     * Creates a zero value Currency object that can be modified later.
     * 
     */
    public Currency() {
        super();
    }

    /**
     * Creates a Currency object that can be modified later.
     * 
     * @param pp
     *            Number of Platinum Pieces in this object.
     * @param gp
     *            Number of Gold Pieces in this object.
     * @param sp
     *            Number of Silver Pieces in this object.
     * @param cp
     *            Number of Copper Pieces in this object.
     */
    public Currency(int pp, int gp, int sp, int cp) {
        super();
        setPp(pp);
        setGp(gp);
        setSp(sp);
        setCp(cp);
    }

    /**
     * Clones an exiting Currency object. Each can be independently modified
     * later.
     * 
     * @param otherCollection
     *            The Currency object being cloned.
     */
    public Currency(final Currency otherCollection) {
        this(otherCollection.pp, otherCollection.gp, otherCollection.sp,
                otherCollection.cp);
    }

    // instance methods
    public Float getGpEquiv() {
        return (pp * PP_AS_GP) + gp + (sp * SP_AS_GP) + (cp * CP_AS_GP);
    }

    /**
     * Get the number of platinum pieces.
     */
    public int getPp() {
        return pp;
    }

    /**
     * Set the number of platinum pieces.
     * 
     * @param pp
     *            The number of platinum pieces.
     */
    public void setPp(final int pp) {
        if (pp < 0) {
            throw new RuntimeException("Invalid amount");
        }
        this.pp = pp;
    }

    /**
     * Get the number of gold pieces.
     */
    public int getGp() {
        return gp;
    }

    /**
     * Set the number of gold pieces.
     * 
     * @param gp
     *            The number of gold pieces.
     */
    public void setGp(final int gp) {
        if (gp < 0) {
            throw new RuntimeException("Invalid amount");
        }
        this.gp = gp;
    }

    /**
     * Get the number of silver pieces.
     */
    public int getSp() {
        return sp;
    }

    /**
     * Set the number of silver pieces.
     * 
     * @param sp
     *            The number of silver pieces.
     */
    public void setSp(final int sp) {
        if (sp < 0) {
            throw new RuntimeException("Invalid amount");
        }
        this.sp = sp;
    }

    /**
     * Get the number of copper pieces.
     */
    public int getCp() {
        return cp;
    }

    /**
     * Set the number of coper pieces.
     * 
     * @param cp
     *            The number of coper pieces.
     */
    public void setCp(final int cp) {
        if (cp < 0) {
            throw new RuntimeException("Invalid amount");
        }
        this.cp = cp;
    }

    /**
     * increment the current object by the argument currency. note that the
     * currency arg is not changed.
     */
    public void add(final Currency otherCollection) {
        // add the value to the current Currency.
        this.pp += otherCollection.getPp();
        this.gp += otherCollection.getGp();
        this.sp += otherCollection.getSp();
        this.cp += otherCollection.getCp();
    }

    /** transfer all the value of the passed currency object. 
     * 
     * @param fromCollection losing collection.
     */
    public void transfer(Currency fromCollection) {
        this.add(fromCollection);
        // remove the value from the original otherCollection.
        fromCollection.pp = 0;
        fromCollection.gp = 0;
        fromCollection.sp = 0;
        fromCollection.cp = 0;
    }

    /**
     * convert into smallest number of coins. The number of coins will change in
     * a purse. I consider this a feature not a bug.
     **/
    public void normalise() {
        while (cp >= COIN_CONVERSION) {
            sp++;
            cp -= COIN_CONVERSION;
        }
        while (sp >= COIN_CONVERSION) {
            gp++;
            sp -= COIN_CONVERSION;
        }
        while (gp >= COIN_CONVERSION) {
            pp++;
            gp -= COIN_CONVERSION;
        }
    }

    @Override
    public boolean equals(Object object) {
        Boolean isEqual = false;
        if (object instanceof Currency) {
            final Currency otherCurrency = (Currency) object;
            normalise();
            otherCurrency.normalise();
            isEqual = (pp == otherCurrency.getPp())
                    && (gp == otherCurrency.getGp())
                    && (sp == otherCurrency.getSp())
                    && (cp == otherCurrency.getCp());
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        // TODO will this work for a non-nomalised Currency object?
        return ((((pp * COIN_CONVERSION) + gp) * COIN_CONVERSION) + sp) * COIN_CONVERSION + cp;
    }

    @Override
    public String toString() {
        String str = "0 gp";
        if (pp != 0 || gp != 0 || sp != 0 || cp != 0) {
            StringBuilder sb = new StringBuilder();
            String joiner = "";
            if (pp != 0) {
                sb.append(this.pp + "pp" + joiner);
                joiner = ",";
            }
            if (gp != 0) {
                sb.append(this.gp + "gp" + joiner);
                joiner = ",";
            }
            if (sp != 0) {
                sb.append(this.sp + "sp" + joiner);
                joiner = ",";
            }
            if (cp != 0) {
                sb.append(this.cp + "cp" + joiner);
                joiner = ",";
            }
            str = sb.toString();
        }
        return str;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        // our "pseudo-constructor"
        in.defaultReadObject();
        // now we are a "live" object again, so let's run rebuild and start
    }

    @Override
    public Currency clone() throws CloneNotSupportedException {
        Currency clone = (Currency) super.clone();

        // Make sure the cloning is deep, not shallow.
        // e.g. set the non-mutable, non-primitives

        return clone;
    }
}
