package mortalkombatversion.ui.screens;

import java.awt.*;
import javax.swing.*;

public abstract class Screen {

    protected final JFrame frame = new JFrame();
    protected final JPanel contentPane = new JPanel(new BorderLayout());

    public Screen() {
        onCreate();
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(null);
    }

    protected abstract void onCreate();

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }
}
