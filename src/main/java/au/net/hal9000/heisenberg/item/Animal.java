package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;

public interface Animal {

    /**
     * eat.
     * 
     * @param sustenance
     *            sustenance to consume.<br>
     */
    public void consume(Item sustenance);

}
