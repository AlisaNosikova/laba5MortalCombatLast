package mortalkombatversion.ui.screens;

import mortalkombatversion.ui.OnClickListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SuccessEndGameScreen extends Screen {

    private JButton endButton;
    private JTextField nameTextField;

    @Override
    protected void onCreate() {
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.add(initTopSection(), BorderLayout.NORTH);
        contentPane.add(initCenterSection(), BorderLayout.CENTER);
        contentPane.add(initBottomSection(), BorderLayout.SOUTH);
    }

    public void addOnEndClickListener(OnClickListener<String> listener) {
        endButton.addActionListener(event -> {
            if (!nameTextField.getText().isBlank()) {
                listener.click(nameTextField.getText());
            }
        });
    }

    private JComponent initTopSection() {
        JLabel titleLabel = new JLabel("You win");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        return titleLabel;
    }

    private JComponent initCenterSection() {
        JLabel nameLabel = new JLabel("Enter your name");
        nameTextField = new JTextField();

        Box box = new Box(BoxLayout.Y_AXIS);
        box.setBorder(new EmptyBorder(20, 20, 20, 20));
        box.add(nameLabel);
        box.add(nameTextField);
        return box;
    }

    private JComponent initBottomSection() {
        return endButton = new JButton("End");
    }
}
