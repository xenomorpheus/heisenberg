package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.util.PcClass;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public abstract class Humanoid extends PcRace {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;
    /**
     * Field head.
     */
    private HumanoidHead head = new HumanoidHead();
    /**
     * Field leftHand.
     */
    private Hand leftHand = new Hand("Left Hand");
    /**
     * Field rightHand.
     */
    private Hand rightHand = new Hand("Right Hand");

    // Constructors
    /**
     * Constructor for Humanoid.
     * @param name String
     */
    protected Humanoid(String name) {
        super(name);
    }

    /**
     * Constructor for Humanoid.
     * @param string String
     * @param description String
     */
    protected Humanoid(String string, String description) {
        super(string, description);
    }

    /**
     * Constructor for Humanoid.
     * @param name String
     * @param description String
     * @param pcClass PcClass
     */
    protected Humanoid(String name, String description, PcClass pcClass) {
        super(name, description, pcClass);
    }

    /**
     * Constructor for Humanoid.
     * @param name String
     * @param pcClass PcClass
     */
    protected Humanoid(String name, PcClass pcClass) {
        super(name, pcClass);
    }

    // Getters and Setters
    // Head
    /**
     * Method getHead.
     * @return HumanoidHead
     */
    public HumanoidHead getHead() {
        return head;
    }

    /**
     * Method setHead.
     * @param head HumanoidHead
     */
    public void setHead(HumanoidHead head) {
        this.head = head;
    }

    // left Hand
    /**
     * Method getLeftHand.
     * @return Hand
     */
    public Hand getLeftHand() {
        return leftHand;
    }

    /**
     * Method setLeftHand.
     * @param hand Hand
     */
    public void setLeftHand(Hand hand) {
        leftHand = hand;
    }

    // right Hand
    /**
     * Method getRightHand.
     * @return Hand
     */
    public Hand getRightHand() {
        return rightHand;
    }

    /**
     * Method setRightHand.
     * @param hand Hand
     */
    public void setRightHand(Hand hand) {
        rightHand = hand;
    }

    // Misc
    /**
     * Eat an item.
     * 
     * @param pFood food Item to eat.
    
     * @throws InvalidTypeException */
    public void eat(Item pFood) throws InvalidTypeException {
        if (!ItemProperty.isHumanoidFood(pFood)) {
            throw new InvalidTypeException(this.getName() + " can't eat "
                    + pFood.getName());
        }
        pFood.beNot();
    }

    /**
     * add - Wear an item.
     * 
     * @param item
     *            clothing Item to wear.
     * 
     * @throws InvalidTypeException
     * @throws CantWearException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    public void add(Item item) throws InvalidTypeException, CantWearException, TooHeavyException, TooLargeException {
        if (!ItemProperty.isClothing(item)) {
            throw new InvalidTypeException(this.getName() + " can't wear "
                    + item.getName());
        }
        super.add(item);
    }

    /** {@inheritDoc} * @return float
     */
    @Override
    public float getWeight() {
        float total = super.getWeight();
        total += head.getWeight();
        if (null != leftHand) {
            total += leftHand.getWeight();
        }
        if (null != rightHand) {
            total += rightHand.getWeight();
        }
        return total;
    }

    /** {@inheritDoc} * @return float
     */
    @Override
    public float getVolume() {
        float total = super.getVolume();
        total += head.getVolume();
        if (null != leftHand) {
            total += leftHand.getVolume();
        }
        if (null != rightHand) {
            total += rightHand.getVolume();
        }
        return total;
    }

    /** {@inheritDoc} */
    @Override
    public void beNot() {
        // Call beNot on the Items directly declared in this class.
        head.beNot();
        if (null != leftHand) {
            leftHand.beNot();
        }
        if (null != rightHand) {
            rightHand.beNot();
        }
        // Get super to do the rest.
        super.beNot();
    }

    /** {@inheritDoc} * @return int
     */
    @Override
    public int getChildCount() {
        int count = 1; // head
        if (null != leftHand) {
            count++;
        }
        if (null != rightHand) {
            count++;
        }
        count += super.getChildCount();
        return count;
    }

    /** {@inheritDoc} * @param index int
     * @return Item
     */
    @Override
    public Item getChildAt(int index) {
        // index is zero offset.
        // head
        if (0 == index) {
            return head;
        }
        index--;

        // leftHand
        if (null != leftHand) {
            if (0 == index) {
                return leftHand;
            }
            index--;
        }

        // rightHand
        if (null != rightHand) {
            if (0 == index) {
                return rightHand;
            }
            index--;
        }

        // Child is on super.
        return super.getChildAt(index);
    }

    /** {@inheritDoc} * @param child Item
     * @return int
     */
    @Override
    public int getIndexOfChild(Item child) {
        // index is zero offset.
        int index = 0;
        // head
        if (head.equals(child)) {
            return index;
        }

        // leftHand
        if (null != leftHand) {
            index++;
            if (leftHand.equals(child)) {
                return index;
            }
        }

        // rightHand
        if (null != rightHand) {
            index++;
            if (rightHand.equals(child)) {
                return index;
            }
        }

        // Child is on super.
        int superIndexOfChild = super.getIndexOfChild(child);
        if (superIndexOfChild >= 0) {
            return index + 1 + superIndexOfChild;
        }
        // not found
        return -1;
    }

    // Visitor Design Pattern. Find items that match the criteria
    /** {@inheritDoc} * @param visitor ItemVisitor
     */
    @Override
    public void accept(ItemVisitor visitor) {
        // TODO visit head, optional hands then super
        visitor.visit(this);
    }

    /** {@inheritDoc} * @return String
     */
    @Override
    public String getRace() {
        return this.getClass().getSimpleName();
    }
}
