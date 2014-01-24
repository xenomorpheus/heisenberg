package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.exception.ExceptionInvalidType;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.util.PcClass;

public abstract class Humanoid extends PcRace {

    private static final long serialVersionUID = 1L;
    private HumanoidHead head = new HumanoidHead();
    private Hand leftHand = new Hand("Left Hand");
    private Hand rightHand = new Hand("Right Hand");

    // Constructors
    public Humanoid(String name) {
        super(name);
    }

    public Humanoid(String string, String description) {
        super(string, description);
    }

    public Humanoid(String name, String description, PcClass pcClass) {
        super(name, description, pcClass);
    }

    public Humanoid(String name, PcClass pcClass) {
        super(name, pcClass);
    }

    // Getters and Setters
    // Head
    public HumanoidHead getHead() {
        return head;
    }

    public void setHead(HumanoidHead head) {
        this.head = head;
    }

    // left Hand
    public Hand getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Hand hand) {
        this.leftHand = hand;
    }

    // right Hand
    public Hand getRightHand() {
        return rightHand;
    }

    public void setRightHand(Hand hand) {
        this.rightHand = hand;
    }

    // Misc
    /**
     * Eat an item.
     * 
     * @param pFood food Item to eat.
     * @throws ExceptionInvalidType
     */
    public void eat(Item pFood) {
        if (!ItemProperty.isHumanoidFood(pFood)) {
            throw new ExceptionInvalidType(this.getName() + " can't eat "
                    + pFood.getName());
        }
        pFood.beNot();
    }

    /**
     * add - Wear an item.
     * 
     * @param item
     *            clothing Item to wear.
     * @throws ExceptionInvalidType
     */
    public void add(Item item) {
        if (!ItemProperty.isClothing(item)) {
            throw new ExceptionInvalidType(this.getName() + " can't wear "
                    + item.getName());
        }
        super.add(item);
    }

    /** {@inheritDoc} */
    @Override
    public float getWeight() {
        float total = super.getWeight();
        total += head.getWeight();
        if (leftHand != null) {
            total += leftHand.getWeight();
        }
        if (rightHand != null) {
            total += rightHand.getWeight();
        }
        return total;
    }

    /** {@inheritDoc} */
    @Override
    public float getVolume() {
        float total = super.getVolume();
        total += head.getVolume();
        if (leftHand != null) {
            total += leftHand.getVolume();
        }
        if (rightHand != null) {
            total += rightHand.getVolume();
        }
        return total;
    }

    /** {@inheritDoc} */
    @Override
    public void beNot() {
        // Call beNot on the Items directly declared in this class.
        head.beNot();
        if (leftHand != null) {
            leftHand.beNot();
        }
        if (rightHand != null) {
            rightHand.beNot();
        }
        // Get super to do the rest.
        super.beNot();
    }

    // Traverse Tree
    /** {@inheritDoc} */
    @Override
    public boolean isLeaf() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public int getChildCount() {
        int count = 1; // head
        if (leftHand != null) {
            count++;
        }
        if (rightHand != null) {
            count++;
        }
        count += super.getChildCount();
        return count;
    }

    /** {@inheritDoc} */
    @Override
    public Item getChildAt(int index) {
        // index is zero offset.
        // head
        if (index == 0) {
            return head;
        }
        index--;

        // leftHand
        if (leftHand != null) {
            if (index == 0) {
                return leftHand;
            }
            index--;
        }

        // rightHand
        if (rightHand != null) {
            if (index == 0) {
                return rightHand;
            }
            index--;
        }

        // Child is on super.
        return super.getChildAt(index);
    }

    /** {@inheritDoc} */
    @Override
    public int getIndexOfChild(Item child) {
        // index is zero offset.
        int index = 0;
        // head
        if (head.equals(child)) {
            return index;
        }

        // leftHand
        if (leftHand != null) {
            index++;
            if (leftHand.equals(child)) {
                return index;
            }
        }

        // rightHand
        if (rightHand != null) {
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
    /** {@inheritDoc} */
    @Override
    public void accept(ItemVisitor visitor) {
        // TODO visit head, optional hands then super
        visitor.visit(this);
    }

    /** {@inheritDoc} */
    @Override
    public String getRace() {
        return this.getClass().getSimpleName();
    }
}
