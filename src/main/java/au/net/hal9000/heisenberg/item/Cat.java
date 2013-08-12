package au.net.hal9000.heisenberg.item;

public class Cat extends Entity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Cat() {
        super("Cat");
    }

    public Cat(String name) {
        this();
        setName(name);
    }
}
