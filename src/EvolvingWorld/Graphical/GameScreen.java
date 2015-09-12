package EvolvingWorld.Graphical;

import EvolvingWorld.AppUtils.Events.GlobalEventListener;
import EvolvingWorld.AppUtils.Logger;
import java.awt.Color;
import javax.swing.JFrame;
/**
 * <p>
 * The JFrame to display the games Graphics on.</p>
 *
 * @author Dynisious 12/09/2015
 * @versions 0.0.1
 */
public class GameScreen extends JFrame implements GlobalEventListener {

    public GameScreen() {
        setResizable(false);
        setUndecorated(true);
        setSize(700, 700);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    @Override
    public void applicationClosing(int reason) {
        setVisible(false);
        dispose();
    }

    @Override
    public void dispose() {
        Logger.instance().write("Game Screen has been closed successfully.",
                3, false);
        super.dispose();
    }

}
