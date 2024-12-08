package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import org.apache.log4j.Logger;

/**
 * JTree nodes may be listened to for changes. This class provides support for adding and removing
 * listeners, also for sending events to those listeners.
 */
public class TreeModelSupport {

  /** class logger. */
  private static final Logger LOGGER = Logger.getLogger(TreeModelSupport.class.getName());

  /**
   * Those that listen for changes to the model.<br>
   * TODO: Using weak references for listener set. It's very easy to forget removing listeners when
   * the actual instance isn't in use any more and thats a source of memory leak.
   */
  private List<TreeModelListener> listeners;

  /**
   * synchronisation lock. http://javarevisited.blogspot.com/2011/04/synchronization
   * -in-java-synchronized.html#ixzz2wy76gzSj
   */
  private final Object objLock = new Object();

  /** Constructor. */
  public TreeModelSupport() {
    listeners = new ArrayList<TreeModelListener>();
  }

  /**
   * Add TreeModelListener.
   *
   * @param listener TreeModelListener
   * @see javax.swing.tree.TreeModel#addTreeModelListener(TreeModelListener)
   */
  public void addTreeModelListener(TreeModelListener listener) {
    LOGGER.debug("listener: " + listener);
    synchronized (objLock) {
      if (listener != null && !listeners.contains(listener)) {
        listeners.add(listener);
      }
    }
  }

  /**
   * Remove TreeModelListener.
   *
   * @param listener TreeModelListener
   * @see javax.swing.tree.TreeModel#removeTreeModelListener(TreeModelListener)
   */
  public void removeTreeModelListener(TreeModelListener listener) {
    LOGGER.debug("listener: " + listener);
    synchronized (objLock) {
      if (listener != null) {
        listeners.remove(listener);
      }
    }
  }

  // http://docs.oracle.com/javase/8/docs/api/javax/swing/event/TreeModelListener.html#treeNodesRemoved-javax.swing.event.TreeModelEvent-
  /**
   * Notify listeners that node(s) have changed.
   *
   * @param e event
   */
  public void fireTreeNodesChanged(TreeModelEvent e) {
    LOGGER.debug("TreeModelEvent=" + e);

    TreeModelListener[] tmpListeners = null;
    // Don't leak the lock.
    synchronized (objLock) {
      tmpListeners = listeners.toArray(new TreeModelListener[listeners.size()]);
    }
    for (TreeModelListener listener : tmpListeners) {
      listener.treeNodesChanged(e);
    }
  }

  /**
   * Notify listeners that node(s) have been inserted.
   *
   * @param e event
   */
  public void fireTreeNodesInserted(TreeModelEvent e) {
    LOGGER.debug("TreeModelEvent=" + e);

    TreeModelListener[] tmpListeners = null;
    // Don't leak the lock.
    synchronized (objLock) {
      tmpListeners = listeners.toArray(new TreeModelListener[listeners.size()]);
    }
    for (TreeModelListener listener : tmpListeners) {
      listener.treeNodesInserted(e);
    }
  }

  /**
   * Notify listeners that node(s) have been removed.
   *
   * @param e event
   */
  public void fireTreeNodesRemoved(TreeModelEvent e) {
    LOGGER.debug("TreeModelEvent=" + e);

    TreeModelListener[] tmpListeners = null;
    // Don't leak the lock.
    synchronized (objLock) {
      tmpListeners = listeners.toArray(new TreeModelListener[listeners.size()]);
    }
    for (TreeModelListener listener : tmpListeners) {
      listener.treeNodesRemoved(e);
    }
  }

  /**
   * Notify listeners that node(s) have changed structure.
   *
   * @param e event
   */
  public void fireTreeStructureChanged(TreeModelEvent e) {
    LOGGER.debug("TreeModelEvent=" + e);

    TreeModelListener[] tmpListeners = null;
    // Don't leak the lock.
    synchronized (objLock) {
      tmpListeners = listeners.toArray(new TreeModelListener[listeners.size()]);
    }
    for (TreeModelListener listener : tmpListeners) {
      listener.treeStructureChanged(e);
    }
  }
}
