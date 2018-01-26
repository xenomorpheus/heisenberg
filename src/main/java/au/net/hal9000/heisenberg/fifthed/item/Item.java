package au.net.hal9000.heisenberg.fifthed.item;

import au.net.hal9000.heisenberg.units.Currency;

public abstract class Item {

	String name = null;
	String url = null;
	Currency cost = null;

	public Item() {
		// TODO Auto-generated constructor stub
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
	public void setName(String name) {
		this.name = name;
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
	public void setUrl(String url) {
		this.url = url;
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
	public void setCost(Currency cost) {
		this.cost = cost;
	}


}
