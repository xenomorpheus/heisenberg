package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

public class TreeModelSupport {
   private List<TreeModelListener> listeners = new ArrayList<TreeModelListener>();

   public void addTreeModelListener( TreeModelListener listener ) {
      if ( listener != null && !listeners.contains( listener ) ) {
         listeners.add( listener );
      }
   }

   public void removeTreeModelListener( TreeModelListener listener ) {
      if ( listener != null ) {
         listeners.remove( listener );
      }
   }

   public void fireTreeNodesChanged( TreeModelEvent e ) {
      for (TreeModelListener listener: listeners){
          listener.treeNodesChanged( e ); 
      }
   }

   public void fireTreeNodesInserted( TreeModelEvent e ) {
       for (TreeModelListener listener: listeners){
         listener.treeNodesInserted( e );
      }
   }

   public void fireTreeNodesRemoved( TreeModelEvent e ) {
       for (TreeModelListener listener: listeners){
         listener.treeNodesRemoved( e );
      }
   }

   public void fireTreeStructureChanged( TreeModelEvent e ) {
       for (TreeModelListener listener: listeners){
         listener.treeStructureChanged( e );
      }
   }
}
