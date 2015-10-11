package au.net.hal9000.heisenberg.item.api;

/** An ordered list of Item objects. e.g. inside a backpack. For a Humanoid this might be one for each
 * of core, head, arms and legs. */

public interface ItemList {

	/** Number of Item objects e.g. inside a backpack. For a Humanoid this might be one for each
	 * of core, head, arms and legs. */
	public int size();

	public Item get(int index);

	public int indexOf(Item child);

    public void add(int size, Item newNode);

}
