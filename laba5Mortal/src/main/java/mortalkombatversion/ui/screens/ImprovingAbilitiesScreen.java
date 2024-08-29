package mortalkombatversion.ui.screens;

import mortalkombatversion.ui.OnClickListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ImprovingAbilitiesScreen extends Screen {

    private JButton healthyButton;
    private JButton damageButton;

    @Override
    protected void onCreate() {
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Выберите, что улучшить", JLabel.CENTER);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        contentPane.add(titleLabel, BorderLayout.NORTH);

        healthyButton = new JButton("Health");
        damageButton = new JButton("Damage");

        Box box = new Box(BoxLayout.X_AXIS);
        contentPane.add(box, BorderLayout.SOUTH);

        box.add(damageButton);
        box.add(Box.createHorizontalGlue());
        box.add(healthyButton);
    }

    public void addOnDamageListener(OnClickListener<JButton> listener) {
        damageButton.addActionListener(event -> listener.click(damageButton));
    }

    public void addOnHealthListener(OnClickListener<JButton> listener) {
        healthyButton.addActionListener(event -> listener.click(healthyButton));
    }
}
