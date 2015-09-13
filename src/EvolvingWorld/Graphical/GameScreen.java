package EvolvingWorld.Graphical;

import EvolvingWorld.AppUtils.Events.GlobalEventListener;
import EvolvingWorld.AppUtils.GlobalEvents;
import EvolvingWorld.AppUtils.Logger;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.ImageCapabilities;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e) {
                if (e.getWindow().isDisplayable()) {
                    GlobalEvents.instance().fireApplicationClosingEvent(
                            GlobalEvents.Game_Window_Closed, true);
                }
            }
            @Override
            public void windowClosed(WindowEvent e) {
            }
            @Override
            public void windowIconified(WindowEvent e) {
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
            }
            @Override
            public void windowActivated(WindowEvent e) {
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
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
        super.dispose();
        Logger.instance().write("Game Screen has been closed successfully.",
                3, false);
    }

}
