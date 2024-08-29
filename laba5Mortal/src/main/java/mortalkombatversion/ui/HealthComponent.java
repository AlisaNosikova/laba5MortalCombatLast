package mortalkombatversion.ui;

import javax.swing.*;
import javax.swing.border.*;

public class HealthComponent extends JComponent {

    private final JLabel healthLabel = new JLabel();
    private final JProgressBar healthBar = new JProgressBar();

    public HealthComponent() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        healthBar.setMinimum(0);
        healthBar.setBorder(new EmptyBorder(0, 10, 0, 0));
        add(healthLabel);
        add(healthBar);
    }

    public void setHealthValues(int health, int maxHealth) {
        healthBar.setMaximum(maxHealth);
        healthBar.setValue(health);
        healthLabel.setText(health + "/" + maxHealth);
    }
}
