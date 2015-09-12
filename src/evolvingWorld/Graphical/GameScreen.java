package EvolvingWorld.Graphical;

import java.awt.HeadlessException;
import javax.swing.JFrame;
/**
 * <p>
 * The JFrame to display the games Graphics on.</p>
 *
 * @author Dynisious 12/09/2015
 * @versions 0.0.1
 */
public class GameScreen extends JFrame {

    public GameScreen() throws HeadlessException {
        setResizable(false);
        setSize(700, 700);
        setVisible(true);
    }

}
