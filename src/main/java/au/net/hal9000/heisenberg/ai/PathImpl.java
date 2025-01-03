package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.Path;
import java.util.ArrayList;

/** AI path of Action objects. <br> */
public class PathImpl extends ArrayList<Action> implements Path {

  /** Version of this class. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
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
