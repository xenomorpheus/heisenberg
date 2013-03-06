package au.net.hal9000.heisenberg.units;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// This is NOT an IItem
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
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Coin conversion: 1PP = 10 GP.
     */
    public static final Float ppAsGp = 10F;

    /**
     * Coin conversion: 1GP = 10 SP.
     */
    public static final Float spAsGp = 0.1F;

    /**
     * Coin conversion: 1 SP = 10 GP.
     */
    public static final Float cpAsGp = 0.01F;

    // coin count in this collection
    private int pp = 0;
    private int gp = 0;
    private int sp = 0;
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
     * @param pPp
     *            Number of Platinum Pieces in this object.
     * @param pGp
     *            Number of Gold Pieces in this object.
     * @param pSp
     *            Number of Silver Pieces in this object.
     * @param pCp
     *            Number of Copper Pieces in this object.
     */
    public Currency(int pPp, int pGp, int pSp, int pCp) {
        super();
        this.pp = pPp;
        this.gp = pGp;
        this.sp = pSp;
        this.cp = pCp;
    }

    /**
     * Clones an exiting Currency object. Each can be independently modified
     * later.
     * 
     * @param otherCollection
     *            The Currency object being cloned.
     */
    public Currency(final Currency otherCollection) {
        super();
        this.pp = otherCollection.pp;
        this.gp = otherCollection.gp;
        this.sp = otherCollection.sp;
        this.cp = otherCollection.cp;
    }

    // instance methods
    public Float getGpEquiv() {
        return (pp * ppAsGp) + gp + (sp * spAsGp) + (cp * cpAsGp);
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
        this.cp = cp;
    }

    // increment the current object by the argument currency.
    // note that the currency arg is not changed.
    public void add(final Currency otherCollection) {
        // add the value to the current Currency.
        this.pp += otherCollection.getPp();
        this.gp += otherCollection.getGp();
        this.sp += otherCollection.getSp();
        this.cp += otherCollection.getCp();
    }

    // transfer all the value of the passed currency object.
    public void transfer(Currency fromCollection) {
        this.add(fromCollection);
        // remove the value from the original otherCollection.
        fromCollection.pp = fromCollection.gp = fromCollection.sp = fromCollection.cp = 0;
    }

    @Override
    public boolean equals(Object object) {
        Boolean isEqual = false;
        if (object instanceof Currency) {
            final Currency otherCurrency = (Currency) object;
            isEqual = (pp == otherCurrency.getPp())
                    && (gp == otherCurrency.getGp())
                    && (sp == otherCurrency.getSp())
                    && (cp == otherCurrency.getCp());
        }
        return isEqual;
    }

    public String toString() {
        return new String(this.pp + "pp, " + this.gp + "gp, " + this.sp
                + "sp, " + this.cp + "cp ");
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
