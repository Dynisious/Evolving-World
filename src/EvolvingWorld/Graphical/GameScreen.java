package EvolvingWorld.Graphical;

import EvolvingWorld.AppUtils.Events.GlobalEventListener;
import EvolvingWorld.AppUtils.Logger;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import javax.swing.JFrame;
/**
 * <p>
 * The JFrame to display the games Graphics on.</p>
 *
 * @author Dynisious 12/09/2015
 * @versions 0.0.1
 */
public class GameScreen extends JFrame
        implements GlobalEventListener, GraphicsListener {

    public GameScreen() {
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        adjustSize();
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
    }

    /**
     * <p>
     * Resize the JFrame to fille the screen.</p>
     */
    private void adjustSize() {
        final DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDisplayMode();
        setSize(dm.getWidth(), dm.getHeight());
        setLocationRelativeTo(null);
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

    private long lastDrawn = -1; //The ID of the last frame that was drawn.

    @Override
    public synchronized void frameDrawn(final Image frame, final long frameId) {
        if (frameId > lastDrawn && isDisplayable()) { //This is a more recent frame than the last
            //frame that was drawn.
            Graphics2D g = (Graphics2D) getGraphics();
            paintAll(g);
            g.drawImage(frame, 0, 0, this);
            lastDrawn = frameId;
        }
    }

    @Override
    public synchronized void paintAll(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }

}
