package au.net.hal9000.heisenberg.fifthed.item;

import au.net.hal9000.heisenberg.units.Currency;

public abstract class Item {

	String name = null;
	String url = null;
	Currency cost = null;
	float weight = 0f;

	public Item() {
		super();
	}

	// Getters and Setters

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public Item setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public Item setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * @return the cost
	 */
	public Currency getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public Item setCost(Currency cost) {
		this.cost = cost;
		return this;
	}

	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public Item setWeight(float weight) {
		this.weight = weight;
		return this;
	}

	public String getSummary() {
		return getName();
	}

}
