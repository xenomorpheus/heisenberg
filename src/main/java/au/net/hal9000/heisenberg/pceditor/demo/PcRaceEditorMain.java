package au.net.hal9000.heisenberg.pceditor.demo;

import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.pceditor.PcRaceEditor;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PcRaceEditorMain { // NO_UCD (unused code)

  /**
   * Launch the application.
   *
   * @param args line args.
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(
        new Runnable() {
          public void run() {
            try {
              PcRace pc = DemoEnvironment.getPcRace();
              PcRaceEditor editor = new PcRaceEditor();
              editor.setPcRace(pc);
              JFrame window = new JFrame();
              window.add(editor);
              window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              window.pack();
              window.setLocationRelativeTo(null); // Centre
              window.setVisible(true);
            } catch (ConfigurationError e) {
              e.printStackTrace();
            }
          }
        });
  }
}
