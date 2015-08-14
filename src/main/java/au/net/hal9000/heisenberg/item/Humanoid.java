package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.util.PcClass;

/**
 * Humanoid is more to do with the shape, so can be undead.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
abstract class Humanoid extends PcRace {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;
    /**
     * head percentage of max weight and volume.
     */
    private static final float HEAD_PERCENTAGE_MAX_WEIGHT_VOLUME = 9;
    /**
     * left hand percentage of max weight and volume.
     */
    private static final float LEFT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME = 9;
    /**
     * right hand percentage of max weight and volume.
     */
    private static final float RIGHT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME = 9;
    /**
     * core percentage of max weight and volume.
     */
    private static final float CORE_PERCENTAGE_MAX_WEIGHT_VOLUME = 36;
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

    /**
     * Field core.
     */
    private ItemContainer core = new ItemContainer("core");

    // Constructors
    /**
     * Constructor for Humanoid.
     * 
     * @param name
     *            String
     */
    protected Humanoid(String name) {
        super(name);
    }

    /**
     * Constructor for Humanoid.
     * 
     * @param string
     *            String
     * @param description
     *            String
     */
    protected Humanoid(String string, String description) {
        super(string, description);
    }

    /**
     * Constructor for Humanoid.
     * 
     * @param name
     *            String
     * @param description
     *            String
     * @param pcClass
     *            PcClass
     */
    protected Humanoid(String name, String description, PcClass pcClass) {
        super(name, description, pcClass);
    }

    /**
     * Constructor for Humanoid.
     * 
     * @param name
     *            String
     * @param pcClass
     *            PcClass
     */
    protected Humanoid(String name, PcClass pcClass) {
        super(name, pcClass);
    }

    // Getters and Setters
    // Head
    /**
     * Method getHead.
     * 
     * @return HumanoidHead
     */
    public HumanoidHead getHead() {
        return head;
    }

    /**
     * Method setHead.
     * 
     * @param head
     *            HumanoidHead
     */
    public void setHead(HumanoidHead head) {
        this.head = head;
    }

    // left Hand
    /**
     * Method getLeftHand.
     * 
     * @return Hand
     */
    public Hand getLeftHand() {
        return leftHand;
    }

    /**
     * Method setLeftHand.
     * 
     * @param hand
     *            Hand
     */
    public void setLeftHand(Hand hand) {
        leftHand = hand;
    }

    // right Hand
    /**
     * Method getRightHand.
     * 
     * @return Hand
     */
    public Hand getRightHand() {
        return rightHand;
    }

    /**
     * Method setRightHand.
     * 
     * @param hand
     *            Hand
     */
    public void setRightHand(Hand hand) {
        rightHand = hand;
    }

    // Weight and capacity is spread across body.
    /**
     * 
     * @return The max weight that can be carried.
     */
    public float getWeightMax() {
        float weightMax = 0;
        return weightMax;
    }

    /**
     * Set the max weight that may be carried.
     * 
     * @param weightMax
     *            The max weight that can be carried.
     */
    public void setWeightMax(float weightMax) {
        // head
        head.setWeightMax(weightMax * HEAD_PERCENTAGE_MAX_WEIGHT_VOLUME);
        // left hand
        leftHand.setWeightMax(weightMax
                * LEFT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
        // right hand
        rightHand.setWeightMax(weightMax
                * RIGHT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
        // core
        core.setWeightMax(weightMax * CORE_PERCENTAGE_MAX_WEIGHT_VOLUME);
    }

    /**
     * get the maximum volume that this item can hold.
     * 
     * 
     * @return the maximum volume that this item can hold.
     */
    public float getVolumeMax() {
        float volumeMax = 0; // TODO
        return volumeMax;
    }

    /**
     * Set the maximum volume that this item can hold.
     * 
     * @param volumeMax
     *            the maximum volume that this item can hold.
     */
    public void setVolumeMax(float volumeMax) {
        final float corePercentageTotal = 100;
        float corePercentage = corePercentageTotal;
        // head
        head.setVolumeMax(volumeMax * HEAD_PERCENTAGE_MAX_WEIGHT_VOLUME);
        corePercentage -= HEAD_PERCENTAGE_MAX_WEIGHT_VOLUME;
        // left hand
        leftHand.setVolumeMax(volumeMax
                * LEFT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
        corePercentage -= LEFT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME;
        // right hand
        rightHand.setVolumeMax(volumeMax
                * RIGHT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
        corePercentage -= RIGHT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME;
        // core
        core.setVolumeMax(volumeMax * corePercentage);
    }

    // Misc

    /**
     * add - Wear an item.
     * 
     * @param item
     *            clothing Item to wear.
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    public void wear(Item item) throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        if (!ItemProperty.isClothing(item)) {
            throw new InvalidTypeException(this.getName() + " can't wear "
                    + item.getName());
        }
        core.add(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getWeight() {
        float total = core.getWeight();
        total += head.getWeight();
        if (null != leftHand) {
            total += leftHand.getWeight();
        }
        if (null != rightHand) {
            total += rightHand.getWeight();
        }
        return total;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getVolume() {
        float total = core.getVolume();
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
        if (null != leftHand) {
            leftHand.beNot();
        }
        if (null != rightHand) {
            rightHand.beNot();
        }
        core.beNot();
        head.beNot();
        // Get super to do the rest.
        super.beNot();
    }

    // Visitor Design Pattern. Find items that match the criteria
    /**
     * {@inheritDoc} * @param visitor ItemVisitor
     */
    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(head);
        if (null != leftHand) {
            visitor.visit(leftHand);
        }
        if (null != rightHand) {
            visitor.visit(rightHand);
        }
        visitor.visit(core);
    }

    /**
     * {@inheritDoc} * @return String
     */
    @Override
    public String getRace() {
        return this.getClass().getSimpleName();
    }
}
