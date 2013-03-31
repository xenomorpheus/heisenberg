package au.net.hal9000.heisenberg.worldeditor;

//Use the GridBagConstraints to determine how the component
import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import nu.xom.ValidityException;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;

public class BasicFrame {
    JFrame guiFrame;

    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                Configuration config = null;
                try {
                    config = new Configuration("test/config/config.xml");
                } catch (ValidityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                PcRace human = new Human();
                human.setLevel(0);
                new BasicFrame(human, config);
            }
        });

    }

    public BasicFrame(final PcRace pcClass, final Configuration config) {

        guiFrame = new JFrame();

        // make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("GridBagLayout Example");
        guiFrame.setSize(800, 300);

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        JPanel basicPanel = new BasicPanel(pcClass, config);

        // add to JFrame
        guiFrame.add(basicPanel);
        guiFrame.setVisible(true);

    }

}