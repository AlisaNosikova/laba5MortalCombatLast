package mortalkombatversion.ui.screens;

import mortalkombatversion.ui.OnClickListener;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainScreen extends Screen {

    private JButton startButton, resultsButton;

    @Override
    protected void onCreate() {
        contentPane.setBorder(new EmptyBorder(20, 20, 0, 20));
        contentPane.add(initCenterSection(), BorderLayout.CENTER);
        contentPane.add(initBottomSection(), BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JComponent initCenterSection() {
        JLabel titleLabel = new JLabel("Mortal Kombat", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        return titleLabel;
    }

    private JComponent initBottomSection() {
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(128, 64));

        resultsButton = new JButton("Results Table");
        resultsButton.setPreferredSize(new Dimension(128, 0));

        Box box = new Box(BoxLayout.X_AXIS);
        box.setBorder(new EmptyBorder(60, 0, 0, 0));
        box.add(resultsButton);
        box.add(Box.createHorizontalGlue());
        box.add(startButton);
        return box;
    }

    public void addOnStartClickListener(OnClickListener<JButton> listener) {
        startButton.addActionListener(event -> listener.click(startButton));
    }

    public void addOnResultsClickListener(OnClickListener<JButton> listener) {
        resultsButton.addActionListener(event -> listener.click(resultsButton));
    }
}
