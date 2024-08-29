package mortalkombatversion.ui.screens;

import mortalkombatversion.ui.OnClickListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EndRoundScreen extends Screen {

    private JLabel winLabel;
    private JButton nextButton;

    @Override
    protected void onCreate() {
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        winLabel = new JLabel("", JLabel.CENTER);
        nextButton = new JButton("Next");

        contentPane.add(winLabel, BorderLayout.NORTH);
        contentPane.add(nextButton, BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(480, 240));
    }

    public void addOnNextClickListener(OnClickListener<JButton> listener) {
        nextButton.addActionListener(event -> listener.click(nextButton));
    }

    public void setWinText(String text) {
        winLabel.setText(text);
    }

    public void setEnabledInput(boolean enabled) {
        nextButton.setEnabled(enabled);
    }
}
