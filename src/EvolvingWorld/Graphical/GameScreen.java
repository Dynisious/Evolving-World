package EvolvingWorld.Graphical;

import EvolvingWorld.AppUtils.Events.GlobalEventListener;
import EvolvingWorld.AppUtils.Logger;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.ImageCapabilities;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
/**
 * <p>
 * The JFrame to display the games Graphics on.</p>
 *
 * @author Dynisious 12/09/2015
 * @versions 0.0.1
 */
public class GameScreen extends JFrame implements GlobalEventListener {

    /**
     * <p>
     * Creates, displays and returns a new GameScreen object.</p>
     *
     * @param backBuffers The number of BackBuffers to be created for drawing
     *                    graphics.
     *
     * @throws AWTException Thrown if there is an issue creating the double
     *                      buffer strategy.
     */
    public GameScreen(final int backBuffers) throws AWTException {
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        adjustSize();
        getContentPane().setBackground(Color.BLACK);
        this.createBufferStrategy(backBuffers, new BufferCapabilities(
                new ImageCapabilities(true), new ImageCapabilities(true),
                BufferCapabilities.FlipContents.BACKGROUND));
        addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setState(NORMAL);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setState(ICONIFIED);
            }

        });
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
        getBufferStrategy().dispose();
        dispose();
    }

    @Override
    public void dispose() {
        Logger.instance().write("Game Screen has been closed successfully.",
                3, false);
        super.dispose();
    }

}
