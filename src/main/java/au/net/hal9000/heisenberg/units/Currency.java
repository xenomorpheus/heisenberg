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
 * @version $Revision: 1.0 $
 */
public class Currency implements Serializable, Cloneable {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /** standard coin conversion ratio. */
    static final int COIN_CONVERSION = 10;

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
    /**
     * Method getGpEquiv.
     * @return Float
     */
    public Float getGpEquiv() {
        return (pp * PP_AS_GP) + gp + (sp * SP_AS_GP) + (cp * CP_AS_GP);
    }

    /**
     * Get the number of platinum pieces.
     * @return int
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
     * @return int
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
     * @return int
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
     * @return int
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
     * @param otherCollection Currency
     */
    public void add(final Currency otherCollection) {
        // add the value to the current Currency.
        pp += otherCollection.getPp();
        gp += otherCollection.getGp();
        sp += otherCollection.getSp();
        cp += otherCollection.getCp();
    }

    /**
     * transfer all the value of the passed currency object.
     * 
     * @param fromCollection
     *            losing collection.
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

    /**
     * Method hashCode.
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cp;
        result = prime * result + gp;
        result = prime * result + pp;
        result = prime * result + sp;
        return result;
    }

    /**
     * Method equals.
     * @param obj Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Currency other = (Currency) obj;
        if (cp != other.cp) {
            return false;
        }
        if (gp != other.gp) {
            return false;
        }
        if (pp != other.pp) {
            return false;
        }
        if (sp != other.sp) {
            return false;
        }
        return true;
    }

    /**
     * Method toString.
     * @return String
     */
    @Override
    public String toString() {
        String str = "0 GP";
        if (0 != pp || 0 != gp || 0 != sp || 0 != cp) {
            StringBuilder sb = new StringBuilder(13);
            String joiner = "";
            if (0 != pp) {
                sb.append(joiner).append(pp).append("PP");
                joiner = ", ";
            }
            if (0 != gp) {
                sb.append(joiner).append(gp).append("GP");
                joiner = ", ";
            }
            if (0 != sp) {
                sb.append(joiner).append(sp).append("SP");
                joiner = ", ";
            }
            if (0 != cp) {
                sb.append(joiner).append(cp).append("CP");
                joiner = ", ";
            }
            str = sb.toString();
        }
        return str;
    }

    /**
     * Method writeObject.
     * @param out ObjectOutputStream
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    /**
     * Method readObject.
     * @param in ObjectInputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        // our "pseudo-constructor"
        in.defaultReadObject();
        // now we are a "live" object again, so let's run rebuild and start
    }

    /**
     * Method clone.
     * @return Currency
     * @throws CloneNotSupportedException 
     */
    @Override
    public Currency clone() throws CloneNotSupportedException  {
        Currency clone = (Currency) super.clone();

        // Make sure the cloning is deep, not shallow.
        // e.g. set the non-mutable, non-primitives
        clone.pp = pp;
        clone.gp = gp;
        clone.sp = sp;
        clone.cp = cp;
        return clone;
    }
}
