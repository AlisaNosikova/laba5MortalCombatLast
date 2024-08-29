package mortalkombatversion.ui.screens;
import mortalkombatversion.NumberKeyAdapter;
import mortalkombatversion.ui.OnClickListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LocationChooserScreen extends Screen {

    private JTextField countTextField;
    private JButton cancelButton, submitButton;

    @Override
    protected void onCreate() {
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.add(initTopSection(), BorderLayout.NORTH);
        contentPane.add(initCenterSection(), BorderLayout.CENTER);
        contentPane.add(initBottomSection(), BorderLayout.SOUTH);
    }

    private JComponent initTopSection() {
        JLabel titleLabel = new JLabel("ENTER NUMBER OF LOCATIONS", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        return titleLabel;
    }

    private JComponent initCenterSection() {
        countTextField = new JTextField();
        countTextField.setPreferredSize(new Dimension(600, 32));
        countTextField.addKeyListener(new NumberKeyAdapter());
        return countTextField;
    }

    private JComponent initBottomSection() {
        cancelButton = new JButton("Cancel");
        submitButton = new JButton("Submit");
        Box box = new Box(BoxLayout.X_AXIS);
        box.setBorder(new EmptyBorder(20, 0, 0, 0));
        box.add(cancelButton);
        box.add(Box.createHorizontalGlue());
        box.add(submitButton);
        return box;
    }

    public void addOnCancelClickListener(OnClickListener<JButton> listener) {
        cancelButton.addActionListener(actionEvent -> listener.click(cancelButton));
    }

    public void addOnSubmitClickListener(OnClickListener<Integer> listener) {
        submitButton.addActionListener(actionEvent -> {
            String text = countTextField.getText();
            if (!text.isEmpty()) {
                listener.click(Integer.parseInt(text));
            }
        });
    }
}
