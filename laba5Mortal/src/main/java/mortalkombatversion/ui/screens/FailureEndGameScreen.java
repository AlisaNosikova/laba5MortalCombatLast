package mortalkombatversion.ui.screens;

import mortalkombatversion.ui.OnClickListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FailureEndGameScreen extends Screen {
    private JButton endButton;

    @Override
    protected void onCreate() {
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.add(initTopSection(), BorderLayout.NORTH);
        contentPane.add(initBottomSection(), BorderLayout.SOUTH);
    }

    public void addOnEndClickListener(OnClickListener<JComponent> listener) {
        endButton.addActionListener(event -> listener.click(endButton));
    }

    private JComponent initTopSection() {
        JLabel titleLabel = new JLabel("You're not win", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        return titleLabel;
    }

    private JComponent initBottomSection() {
        return endButton = new JButton("End");
    }
}
