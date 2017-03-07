package au.net.hal9000.heisenberg.ai;

import java.util.ArrayList;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.Path;

/**
 * AI path of Action objects. <br>
 * 
 * @version $Revision: 1.0 $
 * @author bruins
 */
public class PathImpl extends ArrayList<Action> implements Path {

	/**
	 * Version of this class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 */
	public PathImpl() {
		super();
	}

	@Override
	public Path duplicate() {
		Path path = new PathImpl();
		path.addAll(this);
		return path;
	}

}
