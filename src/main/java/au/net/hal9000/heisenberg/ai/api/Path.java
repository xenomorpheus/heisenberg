package au.net.hal9000.heisenberg.ai.api;

import java.util.List;

public interface Path extends List<Action> {

	/**
	 * Shallow clone current path.
	 * 
	 * @return new path.
	 */
	Path duplicate();

	/**
	 * @return number of actions in the path.
	 */
	int size();

}
